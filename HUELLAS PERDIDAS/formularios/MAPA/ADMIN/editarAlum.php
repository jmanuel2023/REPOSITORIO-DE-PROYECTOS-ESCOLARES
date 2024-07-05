<html>
<style type="text/css">
</style>
<?php
include("../Conexion.php");
$link=Conexion();
$curp=$_REQUEST['curp'];
$nombreA=$_REQUEST['nombre'];
$primerApe=$_REQUEST['primerp'];
$segundoApe=$_REQUEST['segundop'];  
$fechaN=$_REQUEST['fechaN'];
$genero=$_REQUEST['genero'];
$correo=$_REQUEST['correo'];
$password=$_REQUEST['pass'];
$edita="UPDATE `alumno` SET `curp`='$curp',`nombreAlum`='$nombreA',`primerApeA`='$primerApe',`segundoApeA`='$segundoApe',`fechaN`='$fechaN',`genero`='$genero',`correo`='$correo',`password`='$password' WHERE `curp`='$curp'";
if($consulta = mysqli_query($link,$edita))
{
echo "<center><p><h1>:::Se ha editado el registro satisfactoriamente:::</p>";
echo "<a href='verAlumnos.php'>Regresar</a>";
}
else
{
echo "<p>:::Se ha producido un error:::</p>";
}

?>