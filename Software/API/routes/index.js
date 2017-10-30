var express = require('express');
var router = express.Router();

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.get('/test', function(req, res){
	var	responseObject = {messages : 'OK'};
	res.send(responseObject);
})

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

module.exports = router;