<div class="alert-container"></div>
<div class="panel panel-default">
    <div class="panel-body">
        <form class="form-horizontal" id="socio">
            <div class="form-group container-fluid" id="form-user">
                <label for="socio" class="col-sm-2 col-md-2 col-lg-2 control-label">N�mero de Socio:</label>
                <div class="col-sm-2 col-md-2 col-lg-">
                    <input type="number" name="numeroSocio" value="<%=numero%>" class="form-control" id="socio" aria-describedby="inputSuccess2Status">
                </div>
                <div class="col-sm-2 col-md-2 col-lg-">
                    <input type="hidden" name="credencial" class="form-control" id="credencialCode" style="text-transform:uppercase">
                    <button onclick="openCredential()" type="button" class="btn btn-primary btn-block" id="form-sub" >Credencial</button>
                </div>
                <label class="col-sm-2 col-md-2 col-lg-2 control-label">C�digo sistema:</label>
                <div class="col-sm-4 col-md-4 col-lg-4">
                    <input type="text" disabled class="form-control" value="<%=authCode%>" aria-describedby="inputSuccess2Status" style="text-transform:uppercase">
                    <input type="hidden" name="codigoSistema" class="form-control" value="<%=authCode%>" aria-describedby="inputSuccess2Status" style="text-transform:uppercase">
                </div>
            </div>
            <div class="form-group container-fluid">
                <label for="direccion" class="col-sm-12 col-md-12 col-lg-12 control-label"></label>
            </div>
            <div class="form-group container-fluid" id="form-name">
                <label for="nombre" class="col-sm-2 col-md-2 col-lg-2 control-label">Nombre:</label>
                <div class="col-sm-4 col-md-4 col-lg-4">
                    <input type="text" name="datos[nombre]" class="form-control" id="nombre" aria-describedby="inputSuccess2Status" placeholder="">
                </div>
                <label for="primerApellido" class="col-sm-2 col-md-2 col-lg-2 control-label">Primer apellido:</label>
                <div class="col-sm-4 col-md-4 col-lg-4">
                    <input type="text" name="datos[primerApellido]" class="form-control" id="primerApellido" aria-describedby="inputSuccess2Status" placeholder="">
                </div>
            </div>
            <div class="form-group container-fluid">
                <label for="segundoApellido" class="col-sm-2 col-md-2 col-lg-2 control-label">Segundo apellido:</label>
                <div class="col-sm-4 col-md-4 col-lg-4">
                    <input type="text" name="datos[segundoApellido]" class="form-control" id="segundoApellido" aria-describedby="inputSuccess2Status" placeholder="">
                </div>
                <label for="dni" class="col-sm-2 col-md-2 col-lg-2 control-label">DNI:</label>
                <div class="col-sm-3 col-md-3 col-lg-3">
                    <input maxlength="8" type="text" name="datos[dni][]" class="form-control" id="dni">
                </div>
                <div class="col-sm-1 col-md-1 col-lg-1">
                    <input maxlength="1" type="text" name="datos[dni][]" class="form-control" id="letra" style="text-transform:uppercase">
                </div>
            </div>
            <div class="form-group container-fluid">
                <label for="nacimiento" class="col-sm-2 col-md-2 col-lg-2 control-label">Fecha Nacimiento:</label>
                <div class="col-sm-4 col-md-4 col-lg-4">
                    <input type="date" name="datos[nacimiento]" class="form-control" id="nacimiento">
                </div>
                <label for="alta" class="col-sm-2 col-md-2 col-lg-2 control-label">Fecha Alta:</label>
                <div class="col-sm-4 col-md-4 col-lg-4">
                    <input type="date" name="datos[alta]" class="form-control" id="alta">
                </div>
            </div>

            <div class="form-group container-fluid">
                <label for="direccion" class="col-sm-12 col-md-12 col-lg-12 control-label"></label>
            </div>

            <div class="form-group container-fluid">
                <label for="email" class="col-sm-2 col-md-2 col-lg-2 control-label">Correo electr�nico:</label>
                <div class="col-sm-4 col-md-4 col-lg-4">
                    <input type="email" name="datos[correo]" class="form-control" id="email" aria-describedby="inputSuccess2Status">
                </div>
                <label for="phone" class="col-sm-2 col-md-2 col-lg-2 control-label">Tel�fono:</label>
                <div class="col-sm-4 col-md-4 col-lg-4">
                    <input type="tel" name="datos[telefono]" class="form-control" id="phone" aria-describedby="inputSuccess2Status">
                </div>
            </div>

            <div class="form-group container-fluid">
                <label for="direccion" class="col-sm-12 col-md-12 col-lg-12 control-label"></label>
            </div>

            <div class="form-group container-fluid">
                <label for="direccion" class="col-sm-2 col-md-2 col-lg-2 control-label">Direcci�n:</label>
                <div class="col-sm-6 col-md-6 col-lg-6">
                    <input type="text" name="datos[direccion][]" class="form-control" id="direccion" aria-describedby="inputSuccess2Status">
                </div>
                <div class="col-sm-2 col-md-2 col-lg-2">
                    <input type="text" name="datos[direccion][]" placeholder="N�" class="form-control" id="numero" aria-describedby="inputSuccess2Status">
                </div>
                <div class="col-sm-2 col-md-2 col-lg-2">
                    <input type="text" name="datos[direccion][]" placeholder="Piso" class="form-control" id="piso" aria-describedby="inputSuccess2Status">
                </div>
            </div>

            <div class="form-group container-fluid">
                <label for="postal" class="col-sm-2 col-md-2 col-lg-2 control-label"></label>
                <div class="col-sm-2 col-md-2 col-lg-2">
                    <input type="number" name="datos[codigoPostal]" placeholder="C. Postal" class="form-control" id="postal" aria-describedby="inputSuccess2Status">
                </div>
                <div class="col-sm-4 col-md-4 col-lg-4">
                    <input type="text" name="datos[ciudad]" placeholder="Ciudad" class="form-control" id="ciudad" aria-describedby="inputSuccess2Status">
                </div>
            </div>

            <div class="form-group container-fluid">
                <label class="col-sm-2 col-md-2 col-lg-2 control-label"></label>
            </div>

            <div class="form-group container-fluid">
                <div class="col-sm-2 col-md-2 col-lg-2">
                    <input type="text" maxlength="4" name="datos[iban][]" placeholder="ESXX" class="input_center form-control" id="direccion" aria-describedby="inputSuccess2Status" style="text-transform:uppercase">
                </div>
                <div class="col-sm-2 col-md-2 col-lg-2">
                    <input type="text" maxlength="4" name="datos[iban][]" class="input_center form-control" aria-describedby="inputSuccess2Status">
                </div>
                <div class="col-sm-2 col-md-2 col-lg-2">
                    <input type="text" maxlength="4" name="datos[iban][]" class="input_center form-control" aria-describedby="inputSuccess2Status">
                </div>
                <div class="col-sm-2 col-md-2 col-lg-2">
                    <input type="text" maxlength="4" name="datos[iban][]" class="input_center form-control" aria-describedby="inputSuccess2Status">
                </div>
                <div class="col-sm-2 col-md-2 col-lg-2">
                    <input type="text" maxlength="4" name="datos[iban][]" class="input_center form-control" aria-describedby="inputSuccess2Status">
                </div>
                <div class="col-sm-2 col-md-2 col-lg-2">
                    <input type="text" maxlength="4" name="datos[iban][]" class="input_center form-control" aria-describedby="inputSuccess2Status">
                </div>
            </div>
            <div class="form-group container-fluid">
                <label class="col-sm-8 col-md-8 col-lg-8 control-label"></label>
                <div class="col-sm-4 col-md-4 col-lg-4">
                    <button onclick="callServerWithValidation('RegistroSocios', 'socio')" type="button" class="btn btn-primary btn-lg btn-block" id="form-sub" >Registrar Socio en el sistema</button>
                </div>
            </div>

        </form>
    </div>
</div>