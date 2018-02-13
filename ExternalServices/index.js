var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});
/* Get Electricity Consumption per hour*/
router.get('/api/consommation', function(req, res, next){
	var	heure = req.param('heure');
	var responseObject = [ 

        { "heure" :"08",
          "prix" : "0.1593" },
        { "heure" :"09",
          "prix" : "0.1593" },
        { "heure" :"10",
          "prix" : "0.1593" },
        { "heure" :"11",
          "prix" : "0.1593" },
        { "heure" :"12",
          "prix" : "0.1593" },
        { "heure" :"13",
          "prix" : "0.1593" },
        { "heure" :"14",
          "prix" : "0.1593" },        
        { "heure" :"15",
          "prix" : "0.1593" },  
        { "heure" :"16",
          "prix" : "0.1593" },
        { "heure" :"17",
          "prix" : "0.1593" },
        { "heure" :"18",
          "prix" : "0.1593"},
        { "heure" :"19",
          "prix" : "0.1593" },
        { "heure" :"20",
          "prix" : "0.1593" },
        { "heure" :"21",
          "prix" : "0.1593" },
        { "heure" :"22",
          "prix" : "0.1593" },
        { "heure" :"23",
          "prix" : "0.1593" },
        { "heure" :"00",
          "prix" : "0.1252" },
        { "heure" :"01",
          "prix" : "0.1252"},
        { "heure" :"02",
          "prix" : "0.1252" },
        { "heure" :"03",
          "prix" : "0.1252" }, 
       { "heure" :"04",
          "prix" : "0.1252" }, 
       { "heure" :"05",
          "prix" : "0.1252" },
       { "heure" :"06",
          "prix" : "0.1252" },
       { "heure" :"07",
          "prix" : "0.1252" },
   
 ];
  responseObject.forEach(function (obj) {
        if (obj['heure'] == heure)
            res.json(obj);
        else
          return next;

  });

});
/* Get Available Machines*/
app.get('/api/DispoMachines', function(req, res, next) {
	var responseString= [ 
        { "MachineId" :"01",
          "Staut" : "occupee",
          "MachineImage" : "http://helmisahli.000webhostapp.com/rouge.png",
          "TempsResteEnMinutes" : "30" },
        { "MachineId" :"02",
          "Staut" : "libre",
          "MachineImage" : "http://helmisahli.000webhostapp.com/vert.png",          
          "TempsResteEnMinutes" : "00" }, 
        { "MachineId" :"03",
          "Staut" : "libre",
          "MachineImage" : "http://helmisahli.000webhostapp.com/vert.png",          
          "TempsResteEnMinutes" : "00" },
        { "MachineId" :"04",
          "Staut" : "libre",
          "MachineImage" : "http://helmisahli.000webhostapp.com/vert.png",          
          "TempsResteEnMinutes" : "00" }, 
        { "MachineId" :"05",
          "Staut" : "occupee",
          "MachineImage" : "http://helmisahli.000webhostapp.com/rouge.png",          
          "TempsResteEnMinutes" : "20" },
        { "MachineId" :"06",
          "Staut" : "occupee",
          "MachineImage" : "http://helmisahli.000webhostapp.com/rouge.png",          
          "TempsResteEnMinutes" : "50" },
        { "MachineId" :"07",
          "Staut" : "occupee",
          "MachineImage" : "http://helmisahli.000webhostapp.com/rouge.png",          
          "TempsResteEnMinutes" : "40" },
        { "MachineId" :"08",
          "Staut" : "libre",
          "MachineImage" : "http://helmisahli.000webhostapp.com/vert.png",          
          "TempsResteEnMinutes" : "00" }          
       ];;
	res.send(responseString);
});
app.listen(port);