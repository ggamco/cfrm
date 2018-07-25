<jsp:include page="/AuthService" />

<%String publicKey = (String) session.getAttribute("publicKey");%>
<div class="container-header">
    <div class="container container-title">
        <div class="tittle-sect-cont">
            <div class="big-text">
                <h2>Control de Usuarios</h2>
            </div>

        </div>

        <div class="tab-nav">
            <ul class="nav nav-tabs nav-justified" role="tablist">
                <li role="presentation" class="active">
                    <a href="#registro" aria-controls="registro" role="tab" data-toggle="tab" aria-expanded="true">Agregar Usuario</a>
                </li>
                <li role="presentation">
                    <a href="#listado" aria-controls="listado" role="tab" data-toggle="tab" aria-expanded='false'>Usuarios Registrados</a>
                </li>
                <li role="presentation">
                    <a href="#listado" aria-controls="listado" role="tab" data-toggle="tab" aria-expanded='false'>Datos estadísticos</a>
                </li>
            </ul>
        </div>
    </div>
</div><!--CABECERA CON NAVEGADOR TABS-->
<div class="container">

    <div class="tab-content">

        <div role="tabpanel" class="tab-pane active" id="registro"> 
            <div class="alert-container"></div>
            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-horizontal" id="usuario">
                        <div class="form-group container-fluid" id="form-user">
                            <label for="usuario" class="col-sm-2 col-md-2 col-lg-2 control-label">Nombre de usuario:</label>
                            <div class="col-sm-10 col-md-10 col-lg-10">
                                <input type="text" name="user" class="form-control" id="usuario" aria-describedby="inputSuccess2Status" onblur="validateForm('form-user')" required>

                            </div>
                        </div>
                        <div class="form-group container-fluid" id="form-mail">
                            <label for="email" class="col-sm-2 col-md-2 col-lg-2 control-label">Correo electrónico:</label>
                            <div class="col-sm-10 col-md-10 col-lg-10">
                                <input type="email" name="datos[email]" class="form-control" id="email" aria-describedby="inputSuccess2Status" onblur="validateMail(this.value)" required>
                            </div>
                        </div>
                        <div class="form-group container-fluid" id="form-pass">
                            <label for="password" class="col-sm-2 col-md-2 col-lg-2 control-label">Contraseña:</label>
                            <div class="col-sm-4 col-md-4 col-lg-4">
                                <input type="password" class="form-control" id="password" aria-describedby="inputSuccess2Status" onchange="askAgain()" required>
                                <input type="hidden" name="pass" id="cryptoPass">
                                <input type="hidden" id="publicKey" value="<%=publicKey%>"/>
                            </div>
                            <label for="repassword" class="col-sm-2 col-md-2 col-lg-2 control-label">Repite contraseña:</label>
                            <div class="col-sm-4 col-md-4 col-lg-4">
                                <input type="password" class="form-control" id="repassword" aria-describedby="inputSuccess2Status" onblur="validatePass()" required>
                            </div>
                        </div>
                        <div class="form-group container-fluid" id="form-name">
                            <label for="nombre" class="col-sm-2 col-md-2 col-lg-2 control-label">Nombre:</label>
                            <div class="col-sm-10 col-md-10 col-lg-10">
                                <input type="text" name="datos[nombre]" class="form-control" id="nombre" aria-describedby="inputSuccess2Status" placeholder="">
                            </div>
                        </div>
                        <div class="form-group container-fluid">
                            <label for="apellido1" class="col-sm-2 col-md-2 col-lg-2 control-label">Primer apellido:</label>
                            <div class="col-sm-4 col-md-4 col-lg-4">
                                <input type="text" name="datos[primerApellido]" class="form-control" id="apellido1" aria-describedby="inputSuccess2Status" placeholder="">
                            </div>
                            <label for="apellido2" class="col-sm-2 col-md-2 col-lg-2 control-label">Segundo apellido:</label>
                            <div class="col-sm-4 col-md-4 col-lg-4">
                                <input type="text" name="datos[segundoApellido]" class="form-control" id="apellido2" aria-describedby="inputSuccess2Status" placeholder="">
                            </div>
                        </div>
                        <div class="form-group container-fluid">
                            <label for="dni" class="col-sm-2 col-md-2 col-lg-2 control-label">DNI:</label>
                            <div class="col-sm-4 col-md-4 col-lg-4">
                                <input type="number" name="datos[dni]" class="form-control" id="dni">
                            </div>
                            <label for="nacimiento" class="col-sm-2 col-md-2 col-lg-2 control-label">Fecha Nacimiento:</label>
                            <div class="col-sm-4 col-md-4 col-lg-4">
                                <input type="date" name="datos[fechaNacimiento]" class="form-control" id="nacimiento">
                            </div>
                        </div>
                        <div class="form-group container-fluid">
                            <label for="role" class="col-sm-2 col-md-2 col-lg-2 control-label">Nivel de acceso:</label>
                            <div class="col-sm-4 col-md-4 col-lg-4">
                                <select name="role" class="form-control">
                                    <option value="admin">Administrador</option>
                                    <option value="sudo">Super Administrador</option>
                                    <option value="test">Usuario de prueba</option>
                                </select>
                            </div>
                            <label for="activo" class="col-sm-2 col-md-2 col-lg-2 control-label"></label>
                            <div class="col-sm-4 col-md-4 col-lg-4">
                                <button onclick="callServer('RegistroUsuarios', 'usuario')" type="button" class="btn btn-primary btn-lg btn-block" id="form-sub" disabled="disabled">Registrar usuario en el sistema</button>
                            </div>
                        </div>    

                    </form>
                </div>
            </div>

        </div>
        <div role="tabpanel" class="tab-pane" id="listado">
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>#</th>
                        <th>Usuario</th>
                        <th>Email</th>
                        <th>Role</th>
                        <th>Fecha Alta</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody class="listaSocios">
                    <%for (int i = 1; i <= 10; i++) {%>
                    <tr>
                        <td><%=i%></td>
                        <td>ggamboa</td>
                        <td>ggamco@gmail.com</td>
                        <td>
                            <select class="form-control">
                                <option>administrador</option>
                                <option>usuario</option>
                                <option>invitado</option>
                            </select>
                        </td>
                        <td>04/05/1983</td>
                        <td>
                            <select class="form-control">
                                <option>activo</option>
                                <option>desactivado</option>
                            </select>
                        </td>
                        <td class="acciones">
                            <span class="icon-pencil2"></span>
                            <span class="icon-bin"></span>
                            <span class="icon-telegram"></span>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </div>
    </div>
</div>
