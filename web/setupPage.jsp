<%-- 
    Document   : setupPage.jsp
    Created on : Nov 25, 2014, 4:47:20 PM
    Author     : eric
--%>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset='utf-8'>
        <title>Setup</title>
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="mainPage_Backpack_styles.css">
        <link rel="stylesheet" href="setupPage_styles.css">
    </head>
    
    <body>
        
        <video autoplay loop poster="main-page-background.jpg" id="bgvid">
            
        <source src="forestLoop.webm" type="video/webm">

        </video>

        <div class="container-fluid">
            <div class="row" style="margin-top:25px;">
                <div class="col-md-12">
                    <h1 style="color:white;background-color:rgba(50,50,50,.6);">New Group setup</h1>
                </div>
            </div>
            <div class="row" style="margin-top:75px;padding:1px;">
                <div class="col-md-7">
                    <div id="setup-description-box">
                        <p id="page-description">
                            Share with other members of the group things you are bringing on a Backpacking trip.<br>
                            For a casual trip this could be used to share which luxury and fun items you will bring.<br>
                            For a more hardcore trip this could be used to manage food and shelter.<br><br>
                            &#x1F43A Create a new group name and group password. Share this with other members of the group.
                            <br><br>&#x1F43A Add what you want to your backpack, see what others have in theirs.
                            <br><br>&#x1F43A Post stuff you would like to have in a request area.
                            <br><br>&#x1F43A coordinate to see who is bringing what, and what is wanted!
                        </p>
                    </div>
                </div>
                <div class="col-md-5" style="margin-top:40px;">
                    <div id="inputBackground">
                   <form action="NewBpackPage" method="POST">
                        <span class="setupText"><label>Group Name:</label></span> <input type="text" class="newgroup" name="Group-Name"><br>
                        <span class="setupText"><label>Group Password:</label></span> <input type="password" class="newgroup" id="pw1" name="Group-Password"><br>
                        <span class="setupText"><label>Password (Verify):</label></span> <input type="password" class="newgroup" id="pw2" name="Group-Password"><br>
                        <span class="setupText"><label>Group Size:</label></span> <input type="number" class="newgroup" name="Group-Size" id="groupSize"><br>
                        <span class="setupText"><label>Days until trip:</label></span> <input type="number" class="newgroup" name="Countdown" id="days">
                    </form>
                    </div>
                    
                        <button class="btn btn-default btn-lg" id="new-group-submission" style="float:right;margin-top:10px">Submit</button>   
                  
                    <p id="error_message">Fill in all the fields!</p>
                    <p id="error_message2">Passwords don't match</p>
                    <p id="error_message3">Group Size limited to 6 maximum and 1 minimum.</p>
                    <p id="error_message4">Days must be greater than 0 and less than 30.</p>
                    <% if(request.getAttribute("error") == "DuplicateName"){ %>
                        <p id="error_message5">Duplicate group name found, try another.</p>
                    <%}%>
                    <p id="error_message5">
                </div>
            </div>
         
        </div>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script src="setup-page-javascript.js" type="text/javascript"></script>
    </body>
</html>
