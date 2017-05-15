<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html>
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
<div class="container" style="padding-top: 50px">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <p class="bg-primary text-center" style="font-size:30px; background-color: #3B5998"><b>Facebook Fun App</b></p>
        </div>
        <div class="col-md-10 col-md-offset-1" style="padding-top: 20px;background: url('images/banner.jpg');background-size: 100% 100% ;height: 400px"></div>
        <div class="col-md-4 col-md-offset-4" style="padding-top: 10px">
            <a class="btn btn-block btn-social btn-lg btn-facebook" onclick="makeRequest()">
                <span class="fa fa-facebook"></span> Continue with Facebook
            </a>
        </div>
    </div>
</div>

</body>
</html>
