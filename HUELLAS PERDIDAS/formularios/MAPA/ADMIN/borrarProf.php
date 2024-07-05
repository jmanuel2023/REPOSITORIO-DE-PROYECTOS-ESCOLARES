<html>
<style type="text/css">
</style>
<?php
include("../Conexion.php");
$link=Conexion();
session_start();
$matricula=$_GET['m'];  
$borra="delete from profesor where matricula='$matricula'";
if($consulta = mysqli_query($link,$borra))
{
echo "<center><p><h1>:::Se ha eliminado el registro satisfactoriamente:::</p>";
echo "<a href='verProfes.php'>Regresar</a>";
}
else
{
echo "<p>:::Se ha producido un error:::</p>";
}
?>