<div class="header-nav-cont">
    <header>
        <div class="escudo">
            <img src="../img/EscudoNew.png" alt="Rayo Majadahonda"/>
            <h1>GESTION</h1> 
            <h2>CF Rayo Majadahonda</h2>
        </div>   
    </header>
    
    <nav class="sidemenu">
        <ul class="accordion" id="accordion">
            <%if(role.equals("sudo")){%>
            <li class="goTo up"><a href="?view=usuarios">Control de usuarios</a></li>
            <%}%>
            <li class="goTo up"><a href="?view=socio">Gestion Socios</a></li>
            <li class="goTo up"><a href="?view=abonado">Gestion Abonados</a></li>
            <li class="goTo up"><a href="?view=escuela">Alumnos Escuela</a></li>
            <li class="goTo up"><a href="?view=evento">Gestión Eventos</a></li>

            <li class="linked">
                <i class="hide">icon-2</i>
                <label style="cursor: pointer;" id="clausura" class="link btf2">Eventos<span id="temp"></span><span class="icon-circle-down" id="icon-2"></span></label>
                <ul class="children">
                    <li class="goTo"><a href="" onclick="cerrarMenu()">Nuevo Evento</a></li>
                    <li class="goTo"><a href="" onclick="cerrarMenu()">Listar Eventos</a></li>
                </ul>
            </li>
            
            <li class="linked">
                <i class="hide">icon-2</i>
                <label style="cursor: pointer;" id="clausura" class="link btf2">Gestion Entradas<span id="temp"></span><span class="icon-circle-down" id="icon-2"></span></label>
                <ul class="children">
                    <li class="goTo"><a href="" onclick="cerrarMenu()">Categor�as</a></li>
                    <li class="goTo"><a href="" onclick="cerrarMenu()">Tarifas</a></li>
                    <li class="goTo"><a href="" onclick="cerrarMenu()">Gestor de impresi�n</a></li>
                </ul>
            </li>
            
            <li class="goTo up"><a href="../LogOut">Cerrar Sesion</a></li>
        </ul>

    </nav>
</div>
<script>
    
    // SCRIPT QUE REALIZA LA FUNCION DE DESPLIEGUE Y RECOGIDA DE LOS SUBMENU

    $(document).ready(function () {
        
        $('.up').click(function(){
            $('li > ul').slideUp('400', function(){
                $('#icon-1').removeClass("rotate");
                $('#icon-2').removeClass("rotate");
                $('#icon-3').removeClass("rotate");
                $('#icon-4').removeClass("rotate");
            });
        });
        
        $('.linked').click(function () {
            
            var id = $(this).children('i').text();
            
            $('li > ul').not($(this).children("ul").slideDown('400', function(){
                $('#'+id).addClass("rotate");
            })).slideUp('400', function(){
                switch(id){
                    case 'icon-1':
                        $('#icon-2').removeClass("rotate");
                        $('#icon-3').removeClass("rotate");
                        $('#icon-4').removeClass("rotate");
                        break;
                    case 'icon-2':
                        $('#icon-1').removeClass("rotate");
                        $('#icon-3').removeClass("rotate");
                        $('#icon-4').removeClass("rotate");
                        break;
                    case 'icon-3':
                        $('#icon-2').removeClass("rotate");
                        $('#icon-1').removeClass("rotate");
                        $('#icon-4').removeClass("rotate");
                        break;
                    case 'icon-4':
                        $('#icon-2').removeClass("rotate");
                        $('#icon-3').removeClass("rotate");
                        $('#icon-1').removeClass("rotate");
                        break;    
                }
            });
//            $(this).find('.icon-circle-down').toggleClass("rotate");
            //$('span').not($(this).find('.icon-circle-down').toggleClass("rotate")).removeClass("rotate");

        });
    });


</script>
