//La variable express nous permettra d'utiliser les fonctionnalités du module Express.  
var http = require('http');

//Config FCM
var FCM = require('fcm-node');
var serverKey = 'AAAAhxp-kiY:APA91bE9pjcDyMo25LfjXxzF8sd--mKFZ7iQO1OqcDHlfRA45j1y57JJ595NUxB6gYrDoJBkcaoL_L_qbGxois1DGpYyba0fV0RnMxR9tZON5XQsz5gPRhvsZcqsgEYKOYUUrFa7rl4P'; //put your server key here
var fcm = new FCM(serverKey);

var express = require('express'); 
var bodyParser = require('body-parser');
var hostname = 'localhost'; 
var port = 3000; 
var app = express(); 

//body parser for post request
app.use(bodyParser.json()); // support json encoded bodies
app.use(bodyParser.urlencoded({ extended: true }));

var myRouter = express.Router();

var flatfile = require('flat-file-db');
var db = flatfile.sync('./tmp/my.db');

var ssdp = require('node-ssdp').Client
    , clientUpnp = new ssdp()



function askRasp(param, callback){
 console.log(param);

  var url = "192.168.1.225"
  var data = JSON.stringify({"data":"data"});
  var options = {
    host: url,
    port: 3000,
    path: '/api/?'+param,
    method: 'GET'
  };

  http.request(options, function(res) {
    res.setEncoding('utf8');
    res.on('data', function (chunk) {
      console.log('BODY: ' + chunk);
      callback(chunk);
    });
  }).write(data);
};



function askExternal(param,callback){
  var url = "anasghira.com"
  var data = JSON.stringify({"data":"data"});
  var options = {
    host: url,
    path: '/OCS/api.php?'+param,
    method: 'GET'
  };

  http.request(options, function(res) {
    res.setEncoding('utf8');
    res.on('data', function (chunk) {
      console.log('BODY: ' + chunk);
      var chunkJSON = JSON.parse(chunk);
      callback(chunkJSON.prix);
    });
  }).write(data);
};

 


myRouter.route('/')
.get(function(req,res){ 
    var param = req.query;
    var paramstr="";
    for(var i in param){
      paramstr+="&"+i
      if(param[i]!="")
        paramstr+="="+param[i];
    }
    paramstr = paramstr.substring(1);
    console.log("aaa",paramstr);
    askRasp(paramstr,function(resp){  
      res.json({message : resp});
    });
  });

//ROUTE NOTIFY QUAND LA RASP NOUS ANNONCE PAR EXEMPLE QUE LE PANIER EST FULL
myRouter.route('/notify')
.get(function(req,res){ 
    var param = req.query;
    var poidsPanier;
    var date = new Date();
    var hour =""+date.getHours();
    hour=(hour.length===1?"0":'')+hour;
    for(var i in param){
      if(i=="poids")
        poidsPanier = param[i];
    }
    poidsPanier = parseInt(poidsPanier);
    poidsPanier = poidsPanier>1000 ? (poidsPanier*0.001).toFixed(1) +"kg" : poidsPanier + "g";

    //Si le panier n'est pas tout à fait rempli mais peut être vaut la peine d'être lavé.
    if(poidsPanier<8000){
      askExternal("heure="+hour,function(price){
        console.log("Prix de l'éléctricité à cette heure : ",price);
      })
    }

    var message = { //this may vary according to the message type (single recipient, multicast, topic, et cetera)
        to: 'fuHiC4mq1Yg:APA91bE9xjF_FQVHiOFBEb191m2EiqQKD5qe2QzeIr43yL2CdETPQaInN5eegct8X2UuSohPfNow2UFdS95xRfH9J9WOfKB6hXTUJf05O48HrKDsX6O6Gwdlv8YZBnKobpHO-jVmHmEh',
        notification: {  //you can send only notification or only data(or include both)
            title: 'Vide ta machine',
            body: 'Ton panier pèse ' + poidsPanier
        }
    };

    fcm.send(message, function(err, response){
        if (err) {
            console.log(err);
            console.log("Something has gone wrong!");
        } else {
            console.log("Successfully sent with response: ", response);
        }
    });
    console.log("POIDS DU PANIER: ",poidsPanier);
});

/**
 * @api {post} /addDevice Add new balance to the box
 * @apiName AddDevice
 * @apiGroup Device
 *
 * @apiParam {String} name Name for the balance.
 *
 * @apiSuccess {String} status Status.
 * @apiSuccess {String} ip Address Ip of the balance.
 *
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "status": "Ok",
 *       "ip": "192.168.1.1"
 *     }
 *
 * @apiError NoDevice No device found.
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 Not Found
 *     {
 *       "error": "NoDevice"
 *     }
 */
myRouter.route('/addDevice')
.post(function(req,res){
	name=req.body.name;
	console.log("We are trying to add a new balance with the name "+name);
	
	//TODO faire la decouverte UPNP et ajouter la box
	

	var prom1 = new Promise(function( resolve ){
					console.log('test');
					clientUpnp.on('response', function inResponse(headers, code, rinfo) {
						console.log('Got a response to an m-search:\n%d\n%s\n%s', code, JSON.stringify(headers, null, '  '), rinfo.address)
						clientUpnp.stop()
						//Check si le nom n'existe pas deja ...
						db.put(name, {ip:rinfo.address}); 
						resolve(rinfo.address);
					})
				});

	clientUpnp.search('urn:schemas-upnp-org:device:BoxBalance:1')

	prom1.then(function(val) {
		res.json({status : "Ok", ip: val});
    });

// Or maybe if you want to scour for everything after 5 seconds
/*setTimeout(function() {
  client.search('ssdp:all')
}, 5000)*/
	//res.json({status : "Ok", ip: "192.168.2.1"});

});

/**
 * @api {post} /removeDevice Remove a balance from the box
 * @apiName RemoveDevice
 * @apiGroup Device
 *
 * @apiParam {String} name Name of the balance.
 *
 * @apiSuccess {String} status Status.
 *
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "status": "Balance removed with success",
 *     }
 *
 * @apiError NoDevice No device found.
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 Not Found
 *     {
 *       "error": "NoDeviceFound"
 *     }
 */
myRouter.route('/removeDevice')
.post(function(req,res){
	name=req.body.name;
	db.del(name);
	res.json({status : "Ok"});
});




/**
 * @api {get} /infoDevice Get informations about a balance
 * @apiName InfoDevice
 * @apiGroup Device
 *
 * @apiParam {String} name Name of the balance.
 *
 * @apiSuccess {String} status Status.
 *
 * @apiSuccessExample Success-Response:
 *     HTTP/1.1 200 OK
 *     {
 *       "status": "Ok",
		 "ip": "192.168.1.101",
		 "weight": "10" 
 *     }
 *
 * @apiError NoDevice No device found.
 *
 * @apiErrorExample Error-Response:
 *     HTTP/1.1 404 Not Found
 *     {
 *       "error": "NoDeviceFound"
 *     }
 */
myRouter.route('/infoDevice')
.get(function(req,res){
	name = req.params.name
	console.log(db.has(name));
	//realiser la requete vers la rasp
	res.json({status : "Ok"});
});
app.use(myRouter);  

app.listen(port, hostname, function(){
  console.log("Mon serveur fonctionne sur http://"+ hostname +":"+port); 
});
