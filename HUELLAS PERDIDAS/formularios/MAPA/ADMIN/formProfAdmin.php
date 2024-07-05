<!DOCTYPE html>
<head>
   <meta charset="UTF-8">
   <title>Huellas perdidas</title>
   <link rel="stylesheet" href="../../estilos_PROFES.css">
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
$matricula=$_REQUEST['m'];
include ("../Conexion.php");
$link=Conexion();
//$resultado=mysqli_query($conexion,$sql);
$resultado = mysqli_query($link,"SELECT * FROM `profesor` WHERE `matricula`='$matricula'");
$filas = mysqli_fetch_array($resultado,MYSQLI_ASSOC);

?>
         
<form name="Registro_P" method="post" id="formulario" action="editarProf.php">
   <fieldset id="menu_profes">
       <legend><strong>Registro de profesor</strong></legend><br>
       <div id="grupo__matricula">
         <label for="matricula">Matricula del docente:</label>
          <input type="text" name="matricula" id="matricula" value="<?php echo $filas['matricula']?>" minlength="0"  maxlength="10"  required="">
          <p class="formulario__input-error">La matricula es de 10 digitos..</p>
          </div>
       <div id="grupo__nombrep">
      <label for="nombrep">Nombre del docente:</label>
       <input type="text" name="nombrep" id="nombrep" value="<?php echo $filas['nombreProf']?>" minlength="0"  maxlength="10"  required="">
       <p class="formulario__input-error">Este campo solo admite letras mayusculas y minusculas.</p>
       </div>
       <div id="grupo__primerp">
         <label for="primerp">Primer Apellido: </label>
       <input type="text" name="primerp" id="primerp" value="<?php echo $filas['primerApeP']?>" required="">
       <p class="formulario__input-error">Este campo solo admite letras mayusculas y minusculas.</p>
       </div>
       <div id="grupo__segundop">
         <label for="segundop">Apellido Materno: </label>
       <input type="text" name="segundop" value="<?php echo $filas['segundoApeP']?>" id="segundop">
       <p class="formulario__input-error">Este campo solo admite letras mayusculas y minusculas.</p>
       </div>
       <div id="grupo__fechaN">
         <label for="Fecha_N">Fecha de nacimiento: </label>
       <input type="date" name="fechaN" id="fechaN" value="<?php echo $filas['fechaN']?>"  required="">
       </div>
       <div id="grupo__genero">
         <label for="Genero">Genero:</label>
       <input type="radio" name="genero" value="M">Mujer
       <input type="radio" name="genero" value="H">Hombre
       </div>
       <div id="grupo__correo">
         <label for="correo">Correo Electrónico:</label>
       <input type="email" name="correo" id="correo" value="<?php echo $filas['correo']?>" placeholder="@example.com">
       <p class="formulario__input-error">Correo electronico invalido.</p>
       </div>
       <div id="grupo__pass">
         <label for="pass">Ingrese una contraseña:</label>
       <input type="text" name="pass" id="pass" value="<?php echo $filas['password']?>" placeholder="contraseña" minlength="0"  maxlength="10"  required="">
       <p class="formulario__input-error">La contraseña debe de ser de minimo 5 caracteres. Maximo de 12 caracteres.</p>
       </div>
       <div id="grupo__pass2">
         <label for="pass2">Confirme su contraseña:</label>
       <input type="password" name="pass2" id="pass2" placeholder="contraseña" minlength="0"  maxlength="10" required="">
       <p class="formulario__input-error">La contraseña no es la misma que puso anteiormente</p>
       </div>
       
      <input type="reset" value="RESTABLECER DATOS" class="btn btn-1">
            <br><br><!--botones-->
       <input type="submit" value="EDITAR DATOS" class="btn btn-2">
       <br>
   </fieldset>

   <script src="../../validacionprofesor.js"></script>
</body>
</html>