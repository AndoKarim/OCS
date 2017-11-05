//L'application requiert l'utilisation du module Express.
//La variable express nous permettra d'utiliser les fonctionnalités du module Express.  
var http = require('http'); 

function askRasp(param, callback){
 // var url = "192.168.1.155"

  var url = "192.168.1.155"
  var data = JSON.stringify({"data":"data"});
  var options = {
    host: url,
    port: 3000,
    path: '/api?'+param,
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
var hostname = '192.168.1.114'; 
//var hostname = '172.19.250.230'; 

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
      var respoJSON = JSON.parse(resp);
      res.json({message : "Réponse de la RASP = "+respoJSON.message});
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

    //Si le panier n'est pas tout à fait rempli mais peut être vaut la peine d'être lavé.
    if(poidsPanier<80){
      askExternal("heure="+hour,function(price){
        console.log("Prix de l'éléctricité à cette heure : ",price);
      })
    }
    console.log("POIDS DU PANIER: ",poidsPanier);
});

// Nous demandons à l'application d'utiliser notre routeur
app.use(myRouter);  

// Démarrer le serveur 
app.listen(port, hostname, function(){
  console.log("Mon serveur fonctionne sur http://"+ hostname +":"+port); 
});
