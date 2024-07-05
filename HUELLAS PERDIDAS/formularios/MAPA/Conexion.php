
<?php
function Conexion()
{    $host="localhost";
     $usuario="root";
     $clave="1234";
     $bd="registros";
     $idCone=mysqli_connect($host,$usuario,$clave,$bd)
     or die ("Error tratando de conectar al servidor $host con el nombre de usuario $usuario");
     mysqli_select_db($idCone, $bd) or die ("Error seleccionando la base de datos");
     return $idCone;
}
?>