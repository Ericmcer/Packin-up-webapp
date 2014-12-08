<%-- 
    Document   : mainPage
    Created on : Nov 3, 2014, 2:50:44 PM
    Author     : eric
--%>

<!DOCTYPE html>
<html>
<head>
	<title>Backpack Planner</title>
	<meta charset='UTF-8'>
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="mainPage_Backpack_styles.css">
</head>
<body>
     <video autoplay loop poster="main-page-background.jpg" id="bgvid">

        <source src="forestLoop.webm" type="video/webm">


    </video>
	<div class="container-fluid">
            <div class="row">
                <div class="col-md-12" style="background:rgba(50,50,50,0.8);padding-bottom:5px;padding-top:5px;">
                    <form action="BpackPage" method="POST" id="loginForm"><span id="top-submit-group" style="color:white">Group<input name="Group-Name" id="group" style="color:black" type="text"/>
                            Password<input name="Group-Password" id="password" style="color:black" type="password"/></span></form>
                </div>
                <button id="login-button" class="btn btn-default btn-md" style="left:2em;background:rgba(50,40,40,0.7);color:white;margin-left:5px;">Login</button>
                                        
                <p style="color:red" id="error-message" style="background:rgba(230,230,230,.6);">Name and password empty.</p>
                
                <% if(request.getAttribute("error") == "WrongName"){ %>
                <p style="color:red;margin-left:10px">name and password not found</p>
                <% } %>
                
            </div>
            <div class="row" style="margin-top:120px">
                <div class="col-md-6 col-md-offset-3">
                        <div id="title-background">
                            <p id="title">Packing Up</p>
                        </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-8 col-md-offset-2">
                    <div id="site-description-background">
                        <p id="site-description-text">A web application to help coordinate backpacking trips between multiple people.</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-12">
                    <form action="BpackPage" method="GET">
                        <button type="submit" id="start-button" >Get Started!</button>
                    </form>
                </div>
            </div>
	</div>
    
    
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script src="main-page-javascript.js" type="text/javascript"></script>
</body>
</html>
