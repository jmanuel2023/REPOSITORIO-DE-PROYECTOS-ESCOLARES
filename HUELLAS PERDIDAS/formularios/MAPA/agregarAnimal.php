<!DOCTYPE html>
<head>
   <meta charset="UTF-8">
   <title>Huellas perdidas:Agregar Animal</title>
   <link rel="stylesheet" href="../estilos_ALMUNO.css">
  <link rel="stylesheet" href="../estiloformu.css">


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
     <script src="jquery-3.6.3.min.js"></script> 

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
 $nombre=$_SESSION['nombreP'];
 $correo=$_SESSION['correo'];
?>
<form name="agregar_Animales" id="formulario" method="post" action="enviocorreo.php">
   <fieldset id="menu_alumno">
       <legend><strong>Registro del Animal </strong></legend><br>
       <div id="grupo__nombreA">
         <label for="nombreA">Ingresa el nombre del Animal:</label>
         <input type="text" name="nombreA" id="nombreA"  placeholder="Nombre">
       <p class="formulario__input-error">El nombre solo debe de contener letras mayusculas y minusculas</p>
      </div>
         <label for="continente">Continente donde habita el Animal:</label>
    <div id="grupo__continente"> 
        <div>
            <select name="continente" id="continente">
                <option value="América">América</option>
                <option value="Europa">Europa</option>
                <option value="África">África</option>
                <option value="Asia">Asia</option>
                <option value="Oceanía">Oceanía</option>
            </select>
    </div>
    </div>
    <label for="categoria">Estado actual del animal:</label>
    <div id="grupo__categoria"> 
      <div>
          <select name="categoria" id="categoria">
           <option value="En peligro de extincion">Es animal en Peligro de extinción</option>
              <option value="Endemico">Es animal Endemico</option>
              
              <option value="Extinto">Es animal extinto</option>
          </select>
  </div>
  </div>
      <div id="grupo__dieta">
       <label for="dieta">Ingrese cual es la dieta del animales:</label>
       <input type="text" name="dieta" id="dieta">
      <p class="formulario__input-error">Este campo solo admite letras mayusculas y minusculas y tiene un limite de 100 caracteres.</p>
   </div>
      <div id="grupo__espV">
       <label id="espV" for="espV">Ingrese su esperanza de vida</label>
       <p class="formulario__input-error">Este campo solo acepta numeros</p>
      </div>
      <div id="grupo__ext">
        <label id="ext" for="ext">Ingrese su año de extinción</label>
        <p class="formulario__input-error">Este campo solo acepta numeros y tiene que ser año valido.</p>
      </div>
      <div id="grupo_vivos">
        <label id="vivos" for="vivos">Ingrese cuantos ejemplares hay aproximadamente</label>
        <p class="formulario__input-error">Este campo solo acepta numeros</p>
      </div>

      <input type="number" name="dato" id="dato">

      <script>
        $(function (){
          var extinto=$('#ext');
          var endemico=$('#espV');
          var peligro =$('#vivos');
          var dato=$('#dato');
          endemico.hide();
          extinto.hide();
          peligro.hide();
          dato.hide();
          var primero = document.getElementById("categoria");
          primero.addEventListener("change", function() {
          endemico.hide();
          extinto.hide();
          peligro.hide();
          dato.hide();

          if(primero.value =="Endemico"){
          endemico.show();
          extinto.hide();
          peligro.hide();
          dato.show();
          }
          else if(primero.value =="En peligro de extincion"){
          endemico.hide();
          extinto.hide();
          peligro.show();
          dato.show();
          }
          else if(primero.value =="Extinto"){
          endemico.hide();
          extinto.show();
          peligro.hide();
          dato.show();
          }
});


          
          }); 
      </script>
      <br><br>
      <input type="reset" value="RESTABLECER DATOS" class="btn btn-1"> 
       <input type="submit" value="ENVIAR CORRE0" class="btn btn-2">
            
       <br>
   </fieldset>

   <script src="validacionAnimal.js"></script>
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>


</body>
</html>
