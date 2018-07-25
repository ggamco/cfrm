//Modificado 15-09-16 || 2

// REPETIR LA ANIMACION BOUNCE DE LA FLECHA ====================================

$(document).ready(function () {
    animateItems();
    animateFire();
});

var animationRef;

function animateItems()
{
    $('.icon-arrow-down2').removeClass("bounce");

    window.setTimeout(function () {
        $('.icon-arrow-down2').addClass("bounce");
    }, 1000);

    animationRef = window.setTimeout(animateItems, 7000);
}
;

var animationFire;

function animateFire()
{
    $('.is').removeClass("shake");

    window.setTimeout(function () {
        $('.is').addClass("shake");
    }, 1000);

    animationFire = window.setTimeout(animateFire, 10000);
}
;

//JS CONTROL MENU ==============================================================

$(document).ready(main);

var contador = 1;
var rotar = 0;

function main() {
    $('#loading').hide();

    $('.menu_bar').click(function (e) {
        e.preventDefault();
        if (contador == 1) {
            $('.header-nav-cont').animate({
                left: '0'
            });
            $('.tablet-menu').animate({
                left: '279px'
            });
            $('.escudo').animate({
                left: '0'
            });
            $('.main-cont').css('position', 'fixed');
            contador = 0;
        } else {
            contador = 1;
            $('.header-nav-cont').animate({
                left: '-279px'
            });
            $('.tablet-menu').animate({
                left: '0'
            });
            $('.escudo').animate({
                left: '-279px'
            });
            $('.main-cont').css('position', 'initial');
        }
    });
//
//	// Mostramos y ocultamos submenus
//	$('.submenu').click(function(e){
//                e.preventDefault();
//		$(this).children('.children').slideToggle();
//                $(this).find('.icon-circle-down').toggleClass("rotate");
//	});
//        
}

function cerrarMenu() {
    if (contador === 0) {
        contador = 1;
        $('.main-cont').css('position', 'initial');
        $('.header-nav-cont').animate({
            left: '-279px'
        });
        $('.tablet-menu').animate({
            left: '0'
        });

    }
}

//JS BACK TO TOP ===============================================================

$(document).ready(function () {

    //Check to see if the window is top if not then display button
    $(window).scroll(function () {
        if ($(this).scrollTop() > 100) {
            $('.scrollToTop').fadeIn();
        } else {
            $('.scrollToTop').fadeOut();
        }
    });

    //Click event to scroll to top
    $('.scrollToTop').click(function () {
        $('html, body').animate({scrollTop: 0}, 800);
        return false;
    });

});

//JS FADE-IN EFFECTS ===========================================================

$(document).ready(function () {

    /* Every time the window is scrolled ... */
    $(window).scroll(function () {

        /* Check the location of each desired element */
        $('.fadeIn').each(function (i) {

            var bottom_of_object = $(this).offset().top + $(this).outerHeight();
            var bottom_of_window = $(window).scrollTop() + $(window).height();

            /* If the object is completely visible in the window, fade it it */
            if (bottom_of_window > bottom_of_object) {

                $(this).animate({'opacity': '1'}, 500);

            }

        });

    });

});

//JS LOCALSCROLL ===============================================================

$(document).ready(function () {

    $('.goTo').localScroll({
        target: 'body'
    });

});

//=============================
//           PRUEBAS
//=============================

var base = document.URL;
var project;

function extractDomain(url) {
    var domain;
    //find & remove protocol (http, ftp, etc.) and get domain
    if (url.indexOf("://") > -1) {
        domain = url.split('/')[2];
    } else {
        domain = url.split('/')[0];
    }

    //find & remove port number
    //domain = domain.split(':')[0];

    if (domain === "f7majadahonda.es") {
        project = "/es/";
    } else {
        project = "/";
    }

    return domain;
}

base = "http://" + extractDomain(base) + project;

function buscarJornada() {
    var URL_buscar = base + "BuscarJornada";
//    alert(URL_buscar);
    var torneoVar = $("#torneo-cal").val();
    var yearVar = $('#year-cal').val();
    var jornadaVar = $("#selJor").val();

    for (i = 1; i < 7; i++) {
        if ($("#b" + i).attr("aria-expanded") === "true") {
            var press = i;
        }
        ;

    }

    $('#tab_cal0').addClass("active");

    // Si en vez de por post lo queremos hacer por get, cambiamos el $.post por $.get
    $.post(URL_buscar, {
        torneo: torneoVar,
        year: yearVar,
        jornada: jornadaVar,
        press: press
    }, pintarCal);

}

function pintarCal(responseText) {
    $('#cal-wrap').html(responseText);




}

$(document).ready(prueba);

var url = base + "/CargarIndex";
var url2 = base + "/CargarCopa";
var url3 = base + "/CargarCompeticion";
var url4 = base + "/CargarContacto";
var url5 = base + "/CargarInscripcion";
//var url3 = "http://localhost:8080/F7Majadahonda/CargarCompeticion";
//var url4 = "http://localhost:8080/F7Majadahonda/CargarContacto";

var isContactoPushed = false;

function prueba() {

    var TORNEO = $('#activo').val();

    $('.btf1').click(function (e) {
        e.preventDefault();
        var click = $('#apertura').attr('id');

        if (TORNEO !== click) {
            TORNEO = click;
            $('#loading').show();
            $.post(url, {torn: TORNEO}, ok);
        }
    });

    $('.btf2').click(function (e) {
        e.preventDefault();
        var click = $('#clausura').attr('id');

        if (TORNEO !== click) {
            TORNEO = click;
            $('#loading').show();
            $.post(url, {torn: TORNEO}, ok);
        }
    });

    $('.btf3').click(function (e) {
        e.preventDefault();
        var click = $('#copa').attr('id');

        if (TORNEO !== click) {
            TORNEO = click;
            $('#loading').show();
            $.post(url2, {torn: TORNEO}, ok);
        }
    });

    $('.btf4').click(function (e) {
        e.preventDefault();
        var click = $('#competicion').attr('id');

        if (TORNEO !== click) {
            TORNEO = click;
            $('#loading').show();
            $.post(url3, {torn: TORNEO}, ok);
        }
    });

    $('.btf5').click(function (e) {
        e.preventDefault();
        var click = $('#contacto').attr('id');

        if (TORNEO !== click) {
            TORNEO = click;
            $('#loading').show();
            $.post(url4, {torn: TORNEO}, ok);
        }
        isContactoPushed = true;
    });

    $('.btf6').click(function (e) {
        e.preventDefault();

        $('#loading').show();
        $.post(url5, {torn: TORNEO}, ok);
        isContactoPushed = true;
    });

}

function ok(responseText) {
    $('#principal').html(responseText);
    $('#loading').hide();

    if (isContactoPushed) {
        $('html, body').animate({
            scrollTop: $('#calendario').offset().top
        }, 800);
        return false;
    }

    isContactoPushed = false;
}

var idActivo = 0;

function selectPills(id) {

    if (idActivo === 0) {
        for (var a = 1; a <= 6; a++) {
            $('#lib' + a).removeClass('active');
            $('#tab_com' + a).removeClass('active');
            $('#b' + a).removeAttr("aria-expanded");
        }
    } else {
        $('#lib' + idActivo).removeClass('active');
        $('#tab_com' + idActivo).removeClass('active');
        $('#b' + idActivo).removeAttr("aria-expanded");
    }

    $('#lib' + id).addClass('active');
    $('#tab_com' + id).addClass('active');
    $('#b' + id).attr("aria-expanded", "true");

    idActivo = id;

}

function validaTeam() {
    var URI = "http://" + extractDomain(base) + project + "CargarInscripcion";

    var team = $('#team').val().trim().toUpperCase();
    var color = $('#color').val().trim().toUpperCase();

    var control = true;

    if (team.length === 0) {
        $("#team").css("border-color", "#E6344A");
        control = false;
    } else {
        $("#team").css("border-color", "#0055a0");
    }

    if (color.length === 0) {
        $("#color").css("border-color", "#E6344A");
        control = false;
    } else {
        $("#color").css("border-color", "#0055a0");
    }

    if (control) {
        $.post(URI, {
            team: team,
            color: color,
            step: 1
        }, next);
    } else {
        alert("Por favor, rellene los campos marcados como obligatorios");
    }

}

function validaDelegado() {
    var URI = "http://" + extractDomain(base) + project + "CargarInscripcion";

    var nombre = $('#nombre').val().trim().toUpperCase();
    var apellido1 = $('#apellido1').val().trim().toUpperCase();
    var apellido2 = $('#apellido2').val().trim().toUpperCase();
    var dni = $('#dni').val().trim().toUpperCase();
    var telefono = $('#telefono').val().trim().toUpperCase();
    var mail = $('#mail').val().trim();
    var idTeam = $('#idTeam').val();

    var control = true;
    var control2 = false;

    if (nombre.length === 0) {
        $("#nombre").css("border-color", "#E6344A");
        control = false;
    } else {
        $("#nombre").css("border-color", "#0055a0");
    }

    if (apellido1.length === 0) {
        $("#apellido1").css("border-color", "#E6344A");
        control = false;
    } else {
        $("#apellido1").css("border-color", "#0055a0");
    }

    if (apellido2.length === 0) {
        $("#apellido2").css("border-color", "#E6344A");
        control = false;
    } else {
        $("#apellido2").css("border-color", "#0055a0");
    }

    if (dni.length === 0) {
        $("#dni").css("border-color", "#E6344A");
        control = false;
    } else if (dni.length > 9) {
        control = false;
        control2 = true;
        $("#dni").css("border-color", "#E6344A");

    } else {
        $("#dni").css("border-color", "#0055a0");
    }

    if (telefono.length === 0) {
        $("#telefono").css("border-color", "#E6344A");
        control = false;
    } else {
        $("#telefono").css("border-color", "#0055a0");

        var numberArray = telefono.split(" ");
        telefono = "";

        for (var i = 0; i < numberArray.length; i++) {
            telefono += numberArray[i];
        }

    }

    if (mail.length === 0 | !isEmail(mail)) {
        $("#mail").css("border-color", "#E6344A");
        control = false;
    } else {
        $("#mail").css("border-color", "#0055a0");
    }

    if (control) {
        $.post(URI, {
            nombre: nombre,
            apellido1: apellido1,
            apellido2: apellido2,
            dni: dni,
            telefono: telefono,
            mail: mail,
            idTeam: idTeam,
            step: 2
        }, printPlayers);
    } else {
        alert("Por favor, rellene los campos marcados como obligatorios correctamente.");
        if (control2) {
            alert("El DNI o NIE introducido no tiene el formato correcto.");
        }
    }

}

function printPlayers(responseText) {
    $('#model-eq').html(responseText);
    $('#myModal').modal('show');
}

function next(responseText) {
    $('.data').html(responseText);
}

function sendForm() {
    var URI = base + "/EnviarFormulario";

    var nombre = $('#nombre').val().trim();
    var email = $('#email').val().trim();
    var telefono = $('#telefono').val().trim();
    var asunto = $('#asunto').val();
    var mensaje = $('#mensaje').val().trim();

    var control = true;

    if (nombre.length === 0) {
        $("#nombre").css("border-color", "#E6344A");
        control = false;
    } else {
        $("#nombre").css("border-color", "#0055a0");
    }

    if (email.length === 0 | !isEmail(email)) {
        $("#email").css("border-color", "#E6344A");
        control = false;
    } else {
        $("#email").css("border-color", "#0055a0");
    }

    if (asunto === null) {
        $("#asunto").css("border-color", "#E6344A");
        control = false;
    } else {
        $("#asunto").css("border-color", "#0055a0");
    }

    if (mensaje.length === 0) {
        $("#mensaje").css("border-color", "#E6344A");
        control = false;
    } else {
        $("#mensaje").css("border-color", "#0055a0");
    }

    if (control) {
        $.post(URI, {
            nombre: nombre,
            email: email,
            telefono: telefono,
            asunto: asunto,
            mensaje: mensaje
        }, response);
    } else {
        alert("Por favor, rellene los campos marcados como obligatorios");
    }
}

function response(responseText) {
    $('#nombre').val('');
    $('#email').val('');
    $('#telefono').val('');
    $('#asunto').val('');
    $('#mensaje').val('');

    $("#nombre").css("border-color", "#0055a0");
    $("#email").css("border-color", "#0055a0");
    $("#asunto").css("border-color", "#0055a0");
    $("#mensaje").css("border-color", "#0055a0");

    alert("Tu mensaje ha sido enviado correctamente. En breve recibiras confirmación en el email facilitado. Gracias por contactar!");
}

function isEmail(email) {
    var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
    return regex.test(email);
}