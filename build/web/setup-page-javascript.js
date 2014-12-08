/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    $('#error_message').hide();
    $('#error_message2').hide();
    $('#error_message3').hide();
    $('#error_message4').hide();
    $('#new-group-submission').on('click', create_new_group);
    $('.newgroup').on('click', hide_error)
    
});

//check all fields for some input, if all are filled in submit form
//response will confirm group submitted or some kind of error
function create_new_group(){
    var emptyFieldsError = false;
    var passwordMatchingError = false;
    var groupTooLargeError = false;
    var daysTilTripError = false;
    var count = 0;
    var testPasswords;
    $('.newgroup').each(function (){
           if($(this).val() === ''){
              emptyFieldsError = true; 
           }
    });
    
    if($('#pw1').val() !== $('#pw2').val()){
       passwordMatchingError = true;
    }
    
    if($('#groupSize').val() > 9 || $('#groupSize').val() < 1){
        groupTooLargeError = true;
    }
    if($('#days').val() > 30 || $('#days').val() < 1){
        daysTilTripError = true;
    }
    
    if(emptyFieldsError){
        $('#error_message').show();
        return;
    }
    if(passwordMatchingError && !emptyFieldsError){
        $('#error_message2').show();
        return;
    }
    if(groupTooLargeError){
        $('#error_message3').show();
        return;
    }
    if(daysTilTripError){
        $('#error_message4').show();
        return;
    }
    $("form").submit();
};

function hide_error(){
    $('#error_message').hide();
    $('#error_message2').hide();
    $('#error_message3').hide();
    $('#error_message4').hide();
    $('#error_message5').hide();

};
