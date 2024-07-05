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
<?php
$Codigo=$_REQUEST["Codigo"];
$tipo=$_REQUEST["tipo"];
$nombre=$_REQUEST['nombre'];    
// conectar al servidor mysql
$link = mysqli_connect("localhost", "root", "","registros");
if (!$link) {
    die('Error de conexion a mysql : ' . mysql_error());
}
echo "<link rel='icon' href='Imagenes/logoHP.png' type='image/png'>";
echo "<link href='estilosp.css' rel='stylesheet' type='text/css'>";
$resultado = mysqli_query($link,"SELECT * FROM `animales` WHERE `codigo`='$Codigo' AND `estado actual`='$tipo'");
$filas = mysqli_fetch_array($resultado,MYSQLI_ASSOC);
    echo "<head><title>".$filas['nombre']."</title></head>";
    echo "<body class='fondo'>";
    echo "<div class='title'>";
    echo "<img src='Imagenes/logoHP.png' width='100' height='100' usemap='#Map' border='0' align='left'>";
    echo "<img src='".$filas['imagen']. "' width='200' height='100' usemap='#Map' border='0' align='right'>";
    echo "".$filas['nombre']."";
    echo "<br><br><br>";
    echo "Categoria:\t\t\t".$filas['estado actual']."";
    echo "</div>";
    echo "<div>";
  if($tipo=="En peligro de extincion")  
    echo "<table class='center tabla' border=10 cellspacing=0 bgcolor='red' width=50 height=50>";
    else if($tipo=="Endemico")
    echo "<table class='center tabla' border=10 cellspacing=0 bgcolor='green' width=50 height=50>";
    else
    echo "<table class='center tabla' border=10 cellspacing=0 bgcolor='black' width=100 height=100 >";
echo "<tr>";
echo "<td><b>Nombre</b></td>";
echo "<td><b>Continente</b></td>";
echo "<td><b>Dieta</b></td>";
if($tipo=="En peligro de extincion")
echo "<td><b>Aproximados vivos</b></td>";
else
if($tipo=="Endemico")
echo "<td><b>Esperanza de vida</b></td>";
else
echo "<td><b>Año de extincion</b></td>";
echo "<td><b>Sonido</b></td>";
echo "</tr>";
echo "<tr>"; 
echo "<td valign='top'>" . $filas['nombre'] . "</td>";
echo "<td valign='top'>" . $filas['continente'] . "</td>";
echo "<td valign='top'>" . $filas['dieta'] . "</td>"; 
echo "<td valign='top'>" .  $filas['datonum'] . "</td>"; 
if($filas['sonido']==NULL){
  echo "<td valign='top'>No hay sonido registrado</td>";
} 
else
echo "<td valign='top'><a href='" . $filas['sonido'] . "'>Oir Sonido</a></td>";
echo "</tr>";
echo "</table>";
echo "<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>";
echo "<a href='MAPA.PHP'><input type='submit' value='Regresar' class='boton title'></a>";
echo "</div>";
echo "</body>";




?>
