const express = require("express");
const bodyparser = require("body-parser");
const app=express();
app.use(bodyparser.urlencoded({extends:true}));
app.get("/",function(req, res){
  res.sendFile(__dirname + "/index.html");
  });
app.post("/",function(req, res){
  var num1=Number(req.body.num1);
  var num2=Number(req.body.num2);
  console.log(num1 + num2);
  var result = num1+num2;
  res.send(result + " :result");
});
app.listen('3000',function(){console.log("server started on port 3000");});
