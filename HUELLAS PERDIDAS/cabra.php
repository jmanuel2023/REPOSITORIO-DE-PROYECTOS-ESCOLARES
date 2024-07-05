<style>
table {  font: 14px Arial;
border: 1px solid #CCCCCC;
margin-bottom:10px; }
td { width:100px; }
a.boton {
  font: bold 14px Arial;
  text-decoration: none;
  background-color: #EEEEEE;
  color: #333333;
  padding: 2px 6px 2px 6px;
  border: 1px solid #CCCCCC;
}
</style>
<?php 
// conectar al servidor mysql
$link = mysqli_connect("localhost", "root", "1234","animales");
if (!$link) {
    die('Error de conexion a mysql : ' . mysql_error());
}
echo "<table border=1 cellspacing=0 >";
echo "<tr>";
echo "<td><b>Nombre</b></td>";
echo "<td><b>Continente</b></td>";
echo "<td><b>Dieta</b></td>";
echo "<td><b>Año</b></td>";
echo "<td><b>Imagen</b></td>";
echo "<td><b>Sonido</b></td>";
echo "</tr>";
$resultado = mysqli_query($link,"SELECT * FROM `extintos` WHERE Código='EUCAMO'") or trigger_error(mysql_error());
while($filas = mysqli_fetch_array($resultado,MYSQLI_ASSOC)){


echo "<tr>"; 
echo "<td valign='top'>" . $filas['Nombre'] . "</td>";
echo "<td valign='top'>" . $filas['Continente'] . "</td>";
echo "<td valign='top'>" . $filas['Dieta'] . "</td>"; 
echo "<td valign='top'>" .  $filas['Año'] . "</td>"; 
echo "<td valign='top'><a href='verfoto.php?Codigo=" . $filas['Código'] . "'> Ver Foto</a></td>";
if($filas['Sonido']==NULL){
  echo "<td valign='top'>No hay sonido registrado</td>";
} 
else
echo "<td valign='top'>" . $filas['Sonido'] . "</td>";
echo "</tr>";
}
echo "</table>";
?>