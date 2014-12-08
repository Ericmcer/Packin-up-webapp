<%-- 
    Document   : BackpackApp
    Created on : Nov 3, 2014, 3:26:57 PM
    Author     : eric
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Backpack App</title>
        <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="Backpack-App_styles.css">
    </head>
    <body>
        
       <div class="container-fluid" id="background"> 
        <h1 id="page_title">Days Until Trip: ${groupInfo.tripDays}<br></h1>
        
        <button type="button" class="btn btn-success" id="saveButton" onclick="saveGroup(event)">Save Group Information</button>
       
        <c:forEach var="backpacker" items="${groupInfo.group}" varStatus="loop"> 
            <c:if test="${loop.index % 3 == 0}"> 
                <div class="row">
            </c:if>
            <div class="col-md-4">
            <div id="BackpackContents${loop.index}" class="contents" hidden="true">
            <table id="contentsTable${loop.index}" class="table table-bordered">
            <c:forEach var="item" items="${backpacker.items}" varStatus="loop2">       
                <c:if test="${loop2.index % 3 == 0}">
                    <tr>
                </c:if>      
                    <td>${item}</td>
                <c:if test="${loop2.index % 3 == 3}">
                    </tr>
                </c:if>
            </c:forEach>
            </table>
         </div>
            <div id ="Backpack${loop.index}" class="Backpack">${backpacker.name}</div>
           <div class ="btn-group btn-group-sm" role="group"> 
            <button type="button" id="AddItemButton${loop.index}" class="btn btn-default" onclick="addItem(event)">Add Item</button>
            <button type="button" id="showContentButton${loop.index}" class="btn btn-default" onclick="showContents(event)">Show Contents</button>
            <button type="button" id="changeNameButton${loop.index}" class="btn btn-default" onclick="changeName(event)">Change Name</button>
            <button type="button" id="RemoveItemButton${loop.index}" class ="btn btn-default" onclick="removeItem(event)">Remove Item</button>
           </div>
        </div>
         <c:if test="${loop.index % 3 == 2}"> 
            </div>
        </c:if>    
        </c:forEach>
        </div>
        
            
            <div id="nameDialog" title="Change Name"></div>
        
            <form action="SaveBackpackPage" method="POST">
                <input type="number" name="groupID" value="${groupID}">
                <input type="number" name="groupSize" value="${groupInfo.groupSize}">
                <input type ="text" name="names" id="secretHiddenFormNames">
                <input type="text" name="packContents" id="secretHiddenFromContents">
            </form>
        
       <!-- acquire groupSize from request object for JS functions later -->
       <script> var gSize = '${groupInfo.groupSize}';</script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
        <script src="jqueryui.js" type="text/javascript"></script>
        <script src="Backpack-App.js" type="text/javascript"></script>    
    </body>
</html>
