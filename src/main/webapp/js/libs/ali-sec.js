var p_user = false;
var p_mail = false;
var p_pass = false;

$("#login").submit(function () {

    var cryptoPass = $.md5($("#password").val().trim());

    $("#cryptoPass").val(cryptoPass);
    $("#password").val('');
});

function validateForm(id) {

    if ($('#' + id + ' input').val().trim().length > 0) {
        $('#' + id).removeClass('has-error');
        $('#' + id).addClass('has-success');
        p_user = true;
    } else {
        $('#' + id).removeClass('has-error');
        $('#' + id).addClass('has-error');
        p_user = false;
    }
    enableSubmit();
}

function validateMail(sEmail) {

    var filter = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;

    if (filter.test(sEmail)) {
        $('#form-mail').removeClass('has-error');
        $('#form-mail').addClass('has-success');
        p_mail = true;
    } else {
        $('#form-mail').removeClass('has-success');
        $('#form-mail').addClass('has-error');
        p_mail = false;
    }
    enableSubmit();
}

function validatePass() {
    var pass = $('#password').val().trim();
    var repa = $('#repassword').val().trim();

    if (pass === repa && pass.length > 0) {
        $('#form-pass').removeClass('has-error');
        $('#form-pass').addClass('has-success');
        p_pass = true;
        
        encryptPass(pass);
    } else {
        $('#form-pass').removeClass('has-success');
        $('#form-pass').addClass('has-error');
        $('#password').val('');
        $('#repassword').val('');
        
        $("input[name=cryptoPass]").val('');
        p_pass = false;
    }
    enableSubmit();
}

function askAgain(){
    if($('#repassword').val().trim().length > 0){
        $('#form-pass').removeClass('has-success');
        $('#repassword').val('');
    }
}

function enableSubmit() {
    if (p_user & p_mail & p_pass) {
        $('#form-sub').removeAttr("disabled");
    } else {
        $('#form-sub').attr("disabled", "true");
    }
}

function encryptPass(pass) {
    var cryptoPass = $.md5(pass);
        
    $("#cryptoPass").val(cryptoPass);
}