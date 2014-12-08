/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//The javascript for this page is only used for getting login/password,
// making sure fields arent empty and displaying error messages
$(document).ready(function(){
  
    //hide submit field for aesthetic reasons 
   $('#top-submit-group').hide();
   $('#error-message').hide();
   
   //attach function to buttons and input fields
   $('#group').on('click', hideError);
   $('#password').on('click', hideError);
   $('#login-button').click(login_firstClick);
});

//first click will show fields and change buttons 
function login_firstClick(){
    $('#top-submit-group').show();
    $('#top-submit-password').show();
    
    $(this).unbind('click');
    $(this).on('click', login_click);
}

//if login fields empty will show error message, if full will submit them to server
function login_click(){
    var error = false;
    //check error
    if($('#group').val() === ''){
       error = true;
    }
    if($('#password').val() === ''){
        error = true;
    }
    
    
    if(error){
        $('#error-message').show();
        
    } 
    else {
       //code for http post here:
       //if response successful will send html page created for group
       //if response failure will produce another error message

       $('#loginForm').submit();
    }
    
}

function hideError(){
    $('#error-message').hide();
    
}