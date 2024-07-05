<?php
$conexion=mysqli_connect("localhost","root","1234","registros");


$email= $_REQUEST['correo'];
$pass= $_REQUEST['pass'];
    $sql = "SELECT * FROM `alumno` WHERE `correo`='$email'";
    $consulta = mysqli_query($conexion,$sql);
    $resultado = mysqli_fetch_array($consulta);

    $nombre=$resultado[1];
    session_start();
    $_SESSION['nombreA']=$nombre;

if($email==$resultado[6] && $pass==$resultado[7])
header("Location:formularios/MAPA/MAPA.php");
else
{
echo "<center><p><h2>Verifique su usuario y/o password</center></p></h2>";
echo "<a href='LOGIN_a.php'><font color='red'>REGRESAR</font></a>";
}

?>