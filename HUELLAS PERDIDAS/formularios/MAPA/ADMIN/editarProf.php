<html>
<style type="text/css">
</style>
<?php
include("../Conexion.php");
$link=Conexion();
$matricula=$_REQUEST['matricula'];
$nombreA=$_REQUEST['nombrep'];
$primerApe=$_REQUEST['primerp'];
$segundoApe=$_REQUEST['segundop'];  
$fechaN=$_REQUEST['fechaN'];
$genero=$_REQUEST['genero'];
$correo=$_REQUEST['correo'];
$password=$_REQUEST['pass'];
$edita="UPDATE `profesor` SET `matricula`='$matricula',`nombreProf`='$nombreA',`primerApeP`='$primerApe',`segundoApeP`='$segundoApe',`fechaN`='$fechaN',`genero`='$genero',`correo`='$correo',`password`='$password' WHERE `matricula`='$matricula'";
if($consulta = mysqli_query($link,$edita))
{
echo "<center><p><h1>:::Se ha editado el registro satisfactoriamente:::</p>";
echo "<a href='verProfes.php'>Regresar</a>";
}
else
{
echo "<p>:::Se ha producido un error:::</p>";
}

?>