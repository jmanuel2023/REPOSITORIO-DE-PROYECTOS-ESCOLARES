<html>
<style type="text/css">
</style>
<?php
include("../Conexion.php");
$link=Conexion();
session_start();
$curp=$_GET['c'];  
$borra="delete from alumno where curp='$curp'";
if($consulta = mysqli_query($link,$borra))
{
echo "<center><p><h1>:::Se ha eliminado el registro satisfactoriamente:::</p>";
echo "<a href='verAlumnos.php'>Regresar</a>";
echo "<p><a href='Borrar.html'><font color='#FFFFFF'>Verificar anulacion de su compra </font></a></p>";
}
else
{
echo "<p>:::Se ha producido un error:::</p>";
}
?>