/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function(){

    $('form').css("display", "none");
});

function showContents(event){
    var curId = event.target.id;
   
    var lenShort = curId.length - 1;
    var lenLong = curId.length;
    var newId = curId.toString().slice(lenShort,lenLong);

    var tag = "#BackpackContents" + newId;
    $(tag).show();
    var tag2 = "#showContentButton" + newId;
    $(tag2).bind('mouseleave', function(){
        setTimeout(function(){
            $(tag).hide();
        }, 1000);
    })
}

function changeName(event){
     var curId = event.target.id;
   
    var lenShort = curId.length - 1;
    var lenLong = curId.length;
    var newId = "#Backpack" + curId.toString().slice(lenShort,lenLong);
    var newName = prompt("Enter new name:");
    $(newId).text(newName);
}

function addItem(event){
     var curId = event.target.id;
   
    var lenShort = curId.length - 1;
    var lenLong = curId.length;
    var appendInt = curId.toString().slice(lenShort,lenLong);
    var newId = "contentsTable" + appendInt;
    var newItem = prompt("Enter new Item");
    if(newItem === ""){
        return;
    }
    var tableId = document.getElementById(newId);
    addToTable(tableId, newItem);
    
}

function addToTable(tableId, newItem){
   
    var newCell;
    var cellCount;
    var row;
    
    //special condition for first item inserted into table
    if(tableId.rows.length === 0){
        
       row = tableId.insertRow(tableId.rows.length);
       newCell = row.insertCell(0);
       newCell.innerHTML = newItem;
       return;
    
    }else{

        row = tableId.rows[tableId.rows.length - 1];
    }
    
    
    //subsequent run will increase row if 3 cells or add cell if < 3 cells
    if(row.cells.length === 3){
    
        row = tableId.insertRow(tableId.rows.length);
        newCell = row.insertCell(0);
        newCell.innerHTML = newItem;
        
    
    }else if (row.cells.length === 1){
        
        newCell = row.insertCell(1);
        newCell.innerHTML = newItem;
        
    }else if (row.cells.length === 2){
        
        newCell = row.insertCell(2);
        newCell.innerHTML = newItem;
        
    }else{
        alert("what dafaff");
    }
}

    
    //get name of item to remove, then scan table to find and remove it
    //research better way to do this
function removeItem(event){
        
    var curId = event.target.id;
   
    var lenShort = curId.length - 1;
    var lenLong = curId.length;
    var appendInt = curId.toString().slice(lenShort,lenLong);
    var newId = "contentsTable" + appendInt;
    var itemToRemove = prompt("type name of item to remove");
    if(itemToRemove === ""){
        return;
    }
    var table = document.getElementById(newId);
    removeCell(table, itemToRemove);
}
    

function removeCell(table, itemToRemove){
   
    var rows = table.rows.length;
    var columns;
    var currentRow;
    var currentCell;
    var itemFound = false;
    for(var i = 0; i < rows; i++){
        if(itemFound){
            break;
        }
        currentRow = table.rows[i];
        columns = currentRow.cells.length;
        for(var k = 0; k < columns; k++){
            currentCell = currentRow.cells[k];
            if(currentCell.innerHTML === itemToRemove){
                currentRow.deleteCell(k);
                itemFound = true;
                break;
            }
            
            
        }
    }
    if(!itemFound){
        alert("Item not located");
    }
    
}

    //gather all the user data and pass it off to SaveBackpackPage for writing to DB
function saveGroup(event){
   
   var names = populateNameField();
   $('#secretHiddenFormNames').val(names);
   var contents = populateContentsField();
   $('#secretHiddenFromContents').val(contents);
   $('form').submit();
    
    
}

function populateNameField(){
   
    
    var values = "w";
    var currentID;
    for(var i = 0; i < gSize;i++){
        currentID = document.getElementById("Backpack" + i);
        values += currentID.innerHTML + ",";
    }

    values += ";";
    return values;
}

function populateContentsField(){
    //bunch of variables to make 3 for loops for iterating through all the tables
    //"less" confusing
    var values = "";
    var currentID;
    var currentTable;
    var currentID;
    var currentRow;
    var currentCell;
    var numRows;
    var numCells;
    
    for(var backpackerNumber = 0; backpackerNumber < gSize; backpackerNumber++){
        
        currentID = "contentsTable" + backpackerNumber;
        currentTable = document.getElementById(currentID);
        numRows = currentTable.rows.length;
        
        for(var i = 0; i < numRows;i++){
            currentRow = currentTable.rows[i];
            numCells = currentRow.cells.length;
            
            for(var k = 0; k < numCells; k++){
                
                currentCell = currentRow.cells[k];
                values += currentCell.innerHTML;
                values += ",";
                
            }
        }
        values += "; ";     
    }
    
    return values;
    
}




