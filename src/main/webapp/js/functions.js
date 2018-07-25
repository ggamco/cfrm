var BASE = document.URL;
var project = "/cfrm/";
var ssl = "http://";

function extractDomain(url) {
    console.log(url);
    var domain;
    //find & remove protocol (http, ftp, etc.) and get domain
    if (url.indexOf("://") > -1) {
        domain = url.split('/')[2];
    } else {
        domain = url.split('/')[0];
    }

    if (domain === 'gmbdesign.es') {
        ssl = "https://";
    }
    return domain;
}

function getService(service) {
    var domain = extractDomain(BASE);
    return ssl + domain + project + service;
}

function callServerWithValidation(service, form) {
    if ($('#credencialCode').val().length === 0) {
        createAlert('ko', 'No has seleccionado una credencial. Haz click y selecciona una credencial.', new CustomError());
    } else {
        callServer(service, form);
    }
}

function callServer(service, form) {

    var data = $('#' + form).serializeJSON();

    $.ajax({
        type: 'POST',
        url: getService(service),
        data: {json: btoa(unescape(encodeURIComponent(data)))},
        dataType: 'text',
        success: function (text) {
            if (text.length !== 0) {
                $('.listaSocios').html(text);
            } else {
                createAlert('ok', 'Todo ha ido bien y la solicitud se ha realizado correctamente.', null);
            }
        },
        error: function (jqXHR)
        {
            createAlert('ko', 'Se ha producido un error al procesar la petición.', jqXHR);
        }
    });

    $('form').each(function () {
        //this.reset();
        $('.form-group').removeClass('has-success');
    });
}

function createAlert(type, msg, error) {
    var container = $('.alert-container');
    var clase;
    var mensaje;

    switch (type) {
        case 'ok':
            clase = 'alert-success';
            break;
        case 'ko':
            clase = 'alert-warning';
            break;
    }

    if (error !== null) {
        mensaje = '<strong>ERROR ' + error.status + '.</strong> ' + msg;
    } else {
        mensaje = '<strong>¡PERFECTO!</strong> ' + msg;
    }

    var alertHTML = "<div class='alert fade in out " + clase + " alert-dismissible' role='alert'><button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button>" + mensaje + "</div>";

    container.html(alertHTML);
}

var control = true;

function cargarModal(code, service) {
    if (control) {
        var code = code;
        var url = getService(service);

        if (service === 'CargarInfoSocio') {
            $.post(url, {
                codigoSistema: code
            }, loadInfo);
        } else {
            $.post(url, {
                codigoSistema: code
            }, loadCard);
        }
    } else {
        if (service === 'CargarInfoSocio') {
            $('#myModal').modal('show');
        } else {
            $('#myModal').modal('show');
        }
    }

    if (code === 0) {
        control = false;
    }

}

function loadInfo(responseText) {
    $('#modal-info').html(responseText);
    $('#myModal').modal('show');
}

function loadCard(responseText) {
    $('#modal-card').html(responseText);
    $('#myModal').modal('show');
}

function print() {
    var form = document.getElementById("toPrint");
    form.submit();
}

function openCredential() {
    loadCredential();
}

function loadCredential() {
    $.ajax({
        type: 'GET',
        url: getService('CargarCredenciales'),
        success: function (data) {
            $('#modal-card').html(data);
            $('#credentials').modal('show');
        },
        error: function (jqXHR)
        {
            createAlert('ko', 'Se ha producido un error al procesar la petición.', jqXHR);
        }
    });

}

function insertUpdateCredential() {
    let id = $('#idCredencial').val();
    let credential = $('#credencial').val();
    let code = $('#codigo').val();
    $.ajax({
        type: 'POST',
        data: {idCredencial: id, credencial: credential, codigo: code},
        url: getService('InsertUpdateCredenciales'),
        success: function (data) {
            $('#data').html(data);
        },
        error: function (jqXHR)
        {
            createAlert('ko', 'Se ha producido un error al procesar la petición.', jqXHR);
        }
    });
}

function deleteCredential(id) {
    let idCredential = id;
    let title = 'Necesitamos su confirmación...';
    let msg = '¿Estás seguro que quieres eliminar esta credencial?';
    showDialog(title, msg, idCredential);
}

function showDialog(title, msg, id) {
    BootstrapDialog.show({
        title: title,
        message: msg,
        buttons: [{
                label: 'Si',
                action: function (dialog) {
                    callDeleteCredential(id);
                    dialog.close();
                }
            }, {
                label: 'No',
                action: function (dialog) {
                    dialog.close();
                }
            }]
    });
}

function callDeleteCredential(id) {
    $.ajax({
        type: 'GET',
        data: {idCredencial: id},
        url: getService('EliminarCredenciales'),
        success: function (data) {
            $('#data').html(data);
        },
        error: function (jqXHR)
        {
            createAlert('ko', 'Se ha producido un error al procesar la petición.', jqXHR);
        }
    });
}

function selectCredential(id) {
    let code = $('#cd_' + id).val();
    if (code != null) {
        $('#credencialCode').val(code);
        $('#credentials').modal('hide');
    }

}

function CustomError(message) {
    this.name = "NotImplementedError";
    this.message = (message || "");
    this.status = '2000';
}
CustomError.prototype = Error.prototype;

function activaForm() {
    let selection = $('#selector-busqueda').val();
    switch (selection) {
        case 'nombre':
            $('#param1').prop('disabled', false);
            $('#param2').prop('disabled', true);
            $('#param3').prop('disabled', true);
            break;
        case 'numero':
            $('#param1').prop('disabled', true);
            $('#param2').prop('disabled', false);
            $('#param3').prop('disabled', true);
            break;
        case 'rango':
            $('#param1').prop('disabled', true);
            $('#param2').prop('disabled', false);
            $('#param3').prop('disabled', false);
            break;
        case 'notPrinted':
            $('#param1').prop('disabled', true);
            $('#param2').prop('disabled', true);
            $('#param3').prop('disabled', true);
            break;    
    }
}