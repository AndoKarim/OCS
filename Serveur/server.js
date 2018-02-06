//La variable express nous permettra d'utiliser les fonctionnalités du module Express.  
var http = require('http');
//Config FCM
var FCM = require('fcm-node');
var serverKey = 'AAAAhxp-kiY:APA91bE9pjcDyMo25LfjXxzF8sd--mKFZ7iQO1OqcDHlfRA45j1y57JJ595NUxB6gYrDoJBkcaoL_L_qbGxois1DGpYyba0fV0RnMxR9tZON5XQsz5gPRhvsZcqsgEYKOYUUrFa7rl4P'; //put your server key here
var fcm = new FCM(serverKey);

function askRasp(param, callback){
 // var url = "192.168.1.155"
 console.log(param);

  var url = "192.168.43.225"
  var data = JSON.stringify({"data":"data"});
  var options = {
    host: url,
    port: 3000,
   // port: 80,
    path: '/api/?'+param,
    method: 'GET'
  };

  http.request(options, function(res) {
    res.setEncoding('utf8');
    res.on('data', function (chunk) {
      console.log('BODY: ' + chunk);
      callback(chunk);
     // console.log("BKVKBKB",res);
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





//Serveur

//L'application requiert l'utilisation du module Express.
//La variable express nous permettra d'utiliser les fonctionnalités du module Express.  
var express = require('express'); 
// Nous définissons ici les paramètres du serveur.
var hostname = '192.168.43.231'; 
var port = 3000; 

// Nous créons un objet de type Express. 
var app = express(); 
//Afin de faciliter le routage (les URL que nous souhaitons prendre en charge dans notre API), nous créons un objet Router.
//C'est à partir de cet objet myRouter, que nous allons implémenter les méthodes. 
var myRouter = express.Router(); 


// Je vous rappelle notre route (/piscines).  
myRouter.route('/')
// J'implémente les méthodes GET, PUT, UPDATE et DELETE
// GET
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
      //var respoJSON = JSON.parse(resp);
      //res.json({message : respoJSON.message);
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

/*
myRouter.route('/addDevice')

.get(function(req,res){


}


);*/

// Nous demandons à l'application d'utiliser notre routeur
app.use(myRouter);  

// Démarrer le serveur 
app.listen(port, hostname, function(){
  console.log("Mon serveur fonctionne sur http://"+ hostname +":"+port); 
});
