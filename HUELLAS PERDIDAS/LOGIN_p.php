<!DOCTYPE html>
<head>
 <meta charset ="UTF-8">
 <title>.:Inicio de sesion:.</title>
 <link rel="stylesheet" href="estilos_menuHP.css">
 <link rel="stylesheet" href="formularios/estiloformu.css">
</head>

<body>
    <div id="LOGIN"> 
        <h1 style="color:aliceblue">HUELLAS PERDIDAS</h1>
        <h2 style="color:rgb(148, 24, 24)">PROFESOR</h2>
          <br>
          <?php
session_start();
?>
          <form action="entrarProfesorMapa.php" method="post" id="formulario">
            <div id="grupo__correo">
              <label for="correo">Correo Electrónico:</label>
              <input type="email" name="correo" id="correo" placeholder="@Alumno.com">
              <i class="formulario__validacion-estado fas fa-times-circle"></i>
             <p class="formulario__input-error">Correo electronico invalido.</p>
             </div>
             <div id="grupo__pass">
              <label for="pass">Ingrese una contraseña:</label>
              <input type="password" name="pass" id="pass" placeholder="contraseña">
              <i class="formulario__validacion-estado fas fa-times-circle"></i>
             <p class="formulario__input-error">La contraseña debe de ser de minimo 5 caracteres. Maximo de 12 caracteres.</p>
             </div>
             <br>
       <input type="submit" value="Enviar" class="bton btn-2">
          </form>
          
          
          <footer>
            <p>Si no tienes una cuenta crea una <a href="formularios/formulario_PROFES.html">aqui</a></p>
          </footer>    
    
    </div> 
<script src="validacionlogin.js"></script>    
</body>
</htmml>
