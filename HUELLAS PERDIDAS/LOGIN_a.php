
<!DOCTYPE html>
<head>
 <meta charset ="utf-8">
 <title>.:Inicio de sesion:.</title>
 <link rel="stylesheet" href="estilos_menuHP.css">
 <link rel="stylesheet" href="formularios/estiloformu.css">
</head>

<body>
    <div id="LOGIN"> 
        <h1 style="color:aliceblue">HUELLAS PERDIDAS</h1>
        <h2 style="color:rgb(148, 24, 24)">ALUMNO</h2>
          <br>
          <?php
session_start();
?>
          <form action="entrarAlumnoMapa.php" id="formulario" method="post">
            <div id="grupo__correo">
              <label for="correo">Correo Electrónico:</label>
              <input type="email" name="correo" id="correo" placeholder="@example.com">
              <i class="formulario__validacion-estado fas fa-times-circle"></i>
             <p class="formulario__input-error">Correo electronico invalido.</p>
             </div>
             <div id="grupo__pass">
              <label for="pass">Ingrese su contraseña:</label>
              <input type="password" name="pass" id="pass" placeholder="contraseña">
              <i class="formulario__validacion-estado fas fa-times-circle"></i>
             <p class="formulario__input-error">La contraseña debe de ser de minimo 5 caracteres. Maximo de 12 caracteres.</p>
             </div>
       <br>
       <input type="submit" value="Enviar" class="bton btn-2">
          </form>
          <footer>
            <p>Si no tienes una cuenta crea una <a href="formularios/formulario_Almunoi.html">aqui</a></p>
          </footer>    
    
    </div>  
  <script src="validacionlogin.js"></script>    
</body>
</htmml>
