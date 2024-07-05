<?php
$conexion=mysqli_connect("localhost","root","1234","registros");


$email= $_REQUEST['correo'];
$pass= $_REQUEST['pass'];
    $sql = "SELECT * FROM `profesor` WHERE `correo`='$email'";
    $consulta = mysqli_query($conexion,$sql);
    $resultado = mysqli_fetch_array($consulta);

    $nombre=$resultado[1];
    $email=$resultado[6];
    session_start();
    $_SESSION['nombreP']=$nombre;
    $_SESSION['correo']=$email;

if($email==$resultado[6] && $pass==$resultado[7])
header("Location:formularios/MAPA/MAPAP.php");
else
{
echo "<center><p><h2>Verifique su usuario y/o password</center></p></h2>";
echo "<a href='LOGIN_p.php'><font color='red'>REGRESAR</font></a>";
}

?>