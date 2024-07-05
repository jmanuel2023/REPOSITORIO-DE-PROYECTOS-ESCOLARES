<?php
$nombre=$_REQUEST['nombre'];
$link = mysqli_connect("localhost", "root", "","registros");
if (!$link) {
    die('Error de conexion a mysql : ' . mysql_error());
}
echo "<link rel='icon' href='Imagenes/logoHP.png' type='image/png'>";
$resultado = mysqli_query($link,"SELECT * FROM `animales` WHERE `nombre` LIKE '$nombre%'");
while($filas = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){

echo "<head><title>ANIMALES</title></head>";
echo "<p>";
echo "<center>";
echo "<table border=10 cellpadding='5px' cellspacing='5px'>";
echo "<TR>";
echo "<TH width=40>"."Nombre";
echo "<TH width=40>"."Continente";
echo "<TH width=40>"."Dieta";
echo "<TH width=40>"."Estado actual";
if($filas['estado actual']=="En peligro de extincion")
echo "<td><b>Aproximados vivos</b></td>";
else
if($filas['estado actual']=="Endemico")
echo "<td><b>Esperanza de vida</b></td>";
else
echo "<td><b>Año de extincion</b></td>";
echo "<TH width=200>"."Imagen";
echo "<TH width=200>"."Sonido";
echo "</TR>";
echo "</center>";
echo "<tr>"; 
echo "<td valign='top'>" . $filas['nombre'] . "</td>";
echo "<td valign='top'>" . $filas['continente'] . "</td>";
echo "<td valign='top'>" . $filas['estado actual'] . "</td>";
echo "<td valign='top'>" . $filas['dieta'] . "</td>"; 
echo "<td valign='top'>" .  $filas['datonum'] . "</td>";
echo "<td valign='top'><img src='" .  $filas['imagen'] . "' width=100 height=100></td>";

if($filas['sonido']==NULL){
  echo "<td valign='top'>No hay sonido registrado</td>";
} 
else
echo "<td valign='top'><a href='" . $filas['sonido'] . "'>Oir Sonido</a></td>";
echo "</tr>";
};

mysqli_free_result($resultado); 
mysqli_close($link);
?>
</TABLE>