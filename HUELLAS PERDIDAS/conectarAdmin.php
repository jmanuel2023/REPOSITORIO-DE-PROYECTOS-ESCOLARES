<?php
$conexion=mysqli_connect("localhost","root","1234","registros");


$user= $_REQUEST['usuario'];
$pass= $_REQUEST['pass'];
    
$sql = "SELECT * FROM `admin` WHERE `usuario`='$user' AND`password`='$pass' ";
    $consulta = mysqli_query($conexion,$sql);
    $resultado = mysqli_fetch_array($consulta);

    $nombre=$resultado[1];
    session_start();
    $_SESSION['usuario']=$user;

if($user=$resultado[0] && $pass=$resultado[1])
header("Location:formularios/MAPA/admin.html");
else
{
echo "<center><p><h2>Verifique su usuario y/o password</center></p></h2>";
echo "<a href='login_ad.html'><font color='red'>REGRESAR</font></a>";
}

?>