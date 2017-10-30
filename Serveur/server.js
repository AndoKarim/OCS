//L'application requiert l'utilisation du module Express.
//La variable express nous permettra d'utiliser les fonctionnalités du module Express.  
var http = require('http'); 

function askRasp(param, callback){
 // var url = "192.168.1.155"

  var url = "192.168.0.33"
  var data = JSON.stringify({"data":"data"});
  var options = {
    host: url,
    port: 3000,
    path: '/api?'+param,
    method: 'GET'
  };

  http.request(options, function(res) {
    console.log('STATUS: ' + res.statusCode);
    console.log('HEADERS: ' + JSON.stringify(res.headers));
    res.setEncoding('utf8');
    res.on('data', function (chunk) {
      console.log('BODY: ' + chunk);
      callback(chunk);
     // console.log("BKVKBKB",res);
    });
  }).write(data);
}

//Serveur

//L'application requiert l'utilisation du module Express.
//La variable express nous permettra d'utiliser les fonctionnalités du module Express.  
var express = require('express'); 
// Nous définissons ici les paramètres du serveur.
//var hostname = '192.168.1.114'; 
var hostname = '192.168.0.22'; 

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
    
    res.json({message : "Réponse de la RASP = "+respoJSON.message, methode : req.method, parametre : param});
});
})



//POST
.post(function(req,res){

      res.json({message : "Ajoute une nouvelle piscine à la liste", methode : req.method});
})
//PUT
.put(function(req,res){ 
      res.json({message : "Mise à jour des informations d'une piscine dans la liste", methode : req.method});
})
//DELETE
.delete(function(req,res){ 
res.json({message : "Suppression d'une piscine dans la liste", methode : req.method});  
}); 


//ROUTE NOTIFY QUAND LA RASP NOUS ANNONCE PAR EXEMPLE QUE LE PANIER EST FULL
myRouter.route('/notify')

.get(function(req,res){ 
    var param = req.query;
    var poidsPanier;
    for(var i in param){
      if(i=="poids")
        poidsPanier = param[i];
    }
    console.log("POIDS DU PANIER: ",poidsPanier);
})


//POST
.post(function(req,res){

      res.json({message : "Ajoute une nouvelle piscine à la liste", methode : req.method});
})
//PUT
.put(function(req,res){ 
      res.json({message : "Mise à jour des informations d'une piscine dans la liste", methode : req.method});
})
//DELETE
.delete(function(req,res){ 
res.json({message : "Suppression d'une piscine dans la liste", methode : req.method});  
}); 
// Nous demandons à l'application d'utiliser notre routeur
app.use(myRouter);  

// Démarrer le serveur 
app.listen(port, hostname, function(){
  console.log("Mon serveur fonctionne sur http://"+ hostname +":"+port); 
});

