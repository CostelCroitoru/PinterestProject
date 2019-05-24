$(document).ready(function(){

    $('.login-info-box').fadeOut();
    $('.login-show').addClass('show-log-panel');
});

function showLoginForm(){
    $('.register-info-box').fadeIn();
    $('.login-info-box').fadeOut();

    $('.white-panel').removeClass('right-log');

    $('.login-show').addClass('show-log-panel');
    $('.register-show').removeClass('show-log-panel');

    $('.register-show input[type=password]').val("");
}

function showRegisterForm(){
    $('.register-info-box').fadeOut();
    $('.login-info-box').fadeIn();

    $('.white-panel').addClass('right-log');
    $('.register-show').addClass('show-log-panel');
    $('.login-show').removeClass('show-log-panel');

    $('.login-show input[type=password]').val("");
}


$(".login-reg-panel #label-login").click(function(){
    showRegisterForm();
});

$(".login-reg-panel #label-register").click(function(){
    showLoginForm();
});


/*  Check if password matches */
$("form.register-show input[type=password]").keyup(function(){
    var password = $(this).parent().find("input[type=password][name=password]").val();
    var cpassword = $(this).parent().find("input[type=password][name=cpassword]").val();

    if(password != cpassword){
        $(this).parent().find("label[name=errorMessage]").css("visibility", "visible");
        $(this).parent().find("div button[type=submit]").prop("disabled", true);
    }
    else{
        $(this).parent().find("label[name=errorMessage]").css("visibility", "hidden");
        $(this).parent().find("div button[type=submit]").prop("disabled", false);
    }
});



/*  Submit register */
$("form.register-show").submit(function(event){
    event.preventDefault();

    var userInfo = {
        "firstname" : $(this).find("input[type=text][name=firstname]").val(),
        "lastname"  : $(this).find("input[type=text][name=lastname]").val(),
        "username"  : $(this).find("input[type=text][name=username]").val(),
        "password"  : $(this).find("input[type=password][name=password]").val()
    }

    var cpassword = $(this).find("input[type=password][name=cpassword]").val();

    if(userInfo.password != cpassword){

        $(this).find("label[name=errorMessage]").css("visibility", "visible");
        $(this).parent().find("div button[type=submit]").prop("disabled", true);
    }
    else{

        $.ajax({
            type : "POST",
            contentType : "application/json",
            url : "/perform_register",
            data : JSON.stringify(userInfo),
            success : function(response){

                if(response != null){
                    if(response.hasError == false){
                        alert("Account was created successfully!");
                        showLoginForm();

                    }else if(response.errorType == "DUPLICATE_KEY_EXCEPTION"){
                        alert("This username is already used. Please try new username for your account!");
                    }else{
                        alert("Some error while creating your account! Please try again!");
                    }
                }else{
                    alert("Error! Please try register again!");
                }

            },
            error : function(response){
                alert("Server error: " + JSON.stringify(response));
            }
        });
    }

});

