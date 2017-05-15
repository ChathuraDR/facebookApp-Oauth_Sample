<%@ page import="org.json.simple.parser.JSONParser" %>
<%@ page import="org.json.simple.JSONObject" %>
<%@ page import="org.json.simple.parser.ParseException" %>
<%@ page import="com.chathura.oauth.data.storeData" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.util.concurrent.ThreadLocalRandom" %>


<%--
  Created by IntelliJ IDEA.
  User: ChathuraDR
  Date: 5/13/2017
  Time: 3:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Facebook Fun App</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-social.css">
    <link rel="stylesheet" href="css/font-awesome.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <script src="js/bootstrap.min.js"></script>
    <script src="js/npm.js"></script>
    <script src="js/jquery-3.2.0.min.js"></script>
    <script src="js/myFunction.js"></script>
</head>
<body>

<%
    String key = request.getParameter("key");
    String userData = storeData.getInstance().getResource(key);
    JSONParser parser = new JSONParser();
    Object obj = null;
    String[] maleImg = {"images/joe.jpg","images/chan.jpg","images/ros.jpg"};
    String[] femaleImg = {"images/mon.jpg","images/pheb.jpg","images/rach.jpg"};
    try {
        obj = parser.parse(userData);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    JSONObject jsonObj = (JSONObject) obj;

    // Accessing profile attributes from the json object
    String clientName = jsonObj.get("name").toString();
    String ProfileUrl = jsonObj.get("location").toString();
    String gender = jsonObj.get("gender").toString();
    String imgUrl;
    Random rnd = new Random();
    if(gender.equals("male")){
        int rndm = rnd.nextInt(3);
        imgUrl = maleImg[rndm];
    }else{
        int rndm = rnd.nextInt(3);
        imgUrl = femaleImg[rndm];;
    }


%>

<div class="container" style="padding-top: 50px">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <p class="bg-primary text-center" style="font-size:30px; background-color: #3B5998"><b>Facebook Fun App</b></p>
        </div>
        <div class="col-md-10 col-md-offset-1" style="background: url('<%=imgUrl%>');background-size: 100% 100% ;height: 400px">

            <img style="padding-left: 20px;padding-top: 20px;padding-right:20px;width:200px;height: 200px; float: left;" src="<%=ProfileUrl%>"/>
            <h2 style="padding-top: 70px; font-family: Comic Sans MS, cursive, sans-serif;"><b><%=clientName%></b></h2>
        </div>
        </div>
        <%--<div class="col-md-4 col-md-offset-4" style="padding-top: 10px">
            <a class="btn btn-block btn-social btn-lg btn-facebook" onclick="makeRequest()">
                <span class="fa fa-facebook"></span> Share on Facebook
            </a>
        </div>--%>
    </div>
</div>
</body>
</html>
