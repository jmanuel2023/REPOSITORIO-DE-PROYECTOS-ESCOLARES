
<html>
<head>
   <meta charset="UTF-8">
   <title>Huellas perdidas</title>
   <link rel="stylesheet" href="../../estilos_ALMUNO.css">
  <link rel="stylesheet" href="../../estiloformu.css">


   <style>
        .bd-placeholder-img {
          font-size: 1.125rem;
          text-anchor: middle;
          -webkit-user-select: none;
          -moz-user-select: none;
          user-select: none;
        }
  
        @media (min-width: 768px) {
          .bd-placeholder-img-lg {
            font-size: 3.5rem;
          }
        }
  
        .b-example-divider {
          height: 3rem;
          background-color: rgba(0, 0, 0, .1);
          border: solid rgba(0, 0, 0, .15);
          border-width: 1px 0;
          box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
        }
  
        .b-example-vr {
          flex-shrink: 0;
          width: 1.5rem;
          height: 100vh;
        }
  
        .bi {
          vertical-align: -.125em;
          fill: currentColor;
        }
  
        .nav-scroller {
          position: relative;
          z-index: 2;
          height: 2.75rem;
          overflow-y: hidden;
        }
  
        .nav-scroller .nav {
          display: flex;
          flex-wrap: nowrap;
          padding-bottom: 1rem;
          margin-top: -1px;
          overflow-x: auto;
          text-align: center;
          white-space: nowrap;
          -webkit-overflow-scrolling: touch;
        }
      </style>

</head>
   
<body>
<table align="center">
   <tr><!--columnas-->
   <td><!--celdas-->
      <h1> Huellas perdidas</h1>
   </td>
      </tr>
</table>
<?php
session_start();
$curp=$_REQUEST['c'];
include ("../Conexion.php");
$link=Conexion();
//$resultado=mysqli_query($conexion,$sql);
$resultado = mysqli_query($link,"SELECT * FROM `alumno` WHERE `curp`='$curp'");
$filas = mysqli_fetch_array($resultado,MYSQLI_ASSOC);

?>
<form name="Registro_P" id="formulario" method="post" action="editarAlum.php">
   <fieldset id="menu_alumno">
       <legend><strong>Registro del Alumno </strong></legend><br>
       <div id="grupo__curp">
         <label for="curp">Ingresa tu CURP:</label>
         <input type="text" name="curp" id="curp"  value="<?php echo $filas['curp']?>">
       <p class="formulario__input-error">TU CURP ES INVALIDO.</p>
      </div>
      <div  id="grupo__nombre">
         <label for="nombre">Nombre del Alumno:</label>
       <input type="text" name="nombre" id="nombre" value="<?= $filas['nombreAlum']?>">
       <i class="formulario__validacion-estado fas fa-times-circle"></i>
       <p class="formulario__input-error">Este campo solo admite letras mayusculas y minusculas.</p>
      </div>
       <div id="grupo__primerp">
       <label for="primerp">Primer Apellido: </label>
       <input type="text" name="primerp" id="primerp" value="<?=$filas['primerApeA']?>">
       <i class="formulario__validacion-estado fas fa-times-circle"></i>
      <p class="formulario__input-error">Este campo solo admite letras mayusculas y minusculas</p>
      </div>
      <div id="grupo__segundop">
       <label for="segundop">Segundo Apellido: </label>
       <input type="text" name="segundop" id="segundop" value="<?=$filas['segundoApeA']?>">
       <i class="formulario__validacion-estado fas fa-times-circle"></i>
      <p class="formulario__input-error">Este campo solo admite letras mayusculas y minusculas</p>
   </div>
      <div id="grupo__fechaN">
       <label for="fechaN">Fecha de nacimiento: </label>
       <input type="date" name="fechaN" id="fechaN" value="<?=$filas['fechaN']?>">
      </div>
      <div id="grupo__genero">
       <label for="Genero">Genero:</label>
       <input type="radio" name="genero" value="M">Mujer
       <input type="radio" name="genero" value="H">Hombre
      </div>
         <div id="grupo__correo">
       <label for="correo">Correo Electrónico:</label>
       <input type="email" name="correo" id="correo" value="<?=$filas['correo']?>">
       <i class="formulario__validacion-estado fas fa-times-circle"></i>
      <p class="formulario__input-error">Correo electronico invalido.</p>
      </div>
      <div id="grupo__pass">
       <label for="pass">Ingrese una contraseña:</label>
       <input type="text" name="pass" id="pass" value="<?= $filas['password']?>">
       <i class="formulario__validacion-estado fas fa-times-circle"></i>
      <p class="formulario__input-error">La contraseña debe de ser de minimo 5 caracteres. Maximo de 12 caracteres.</p>
      </div>
      <div id="grupo__passconf">
       <label for="passconf">Confirme su contraseña:</label>
       <input type="password" name="passconf" id="passconf" placeholder="contraseña">
       <i class="formulario__validacion-estado fas fa-times-circle"></i>
      <p class="formulario__input-error">La contraseña no es la misma que puso anteiormente</p>
      </div>
      <br><br>
      <input type="reset" value="RESTABLECER DATOS" class="btn btn-1"> 
       <input type="submit" value="EDITAR DATOS" class="btn btn-2">
            
       <br>
   </fieldset>

   <script src="../../validacionalumno.js"></script>

</body>
</html>