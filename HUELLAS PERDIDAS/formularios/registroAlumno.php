<?php
$conexion=mysqli_connect("localhost","root","","registros"); 

$curp= $_REQUEST['curp'];
$nombreA=$_REQUEST['nombre'];
$primerApe= $_REQUEST['primerp'];
$segundoApe= $_REQUEST['segundop'];
$date= $_REQUEST['fechaN'];
$gender= $_REQUEST['genero'];
$email= $_REQUEST['correo'];
$password= $_REQUEST['pass'];
	if (strlen($curp)   && strlen($nombreA) >= 1 && strlen($primerApe) >= 1 && strlen($segundoApe) >= 1 && strlen($date) >= 1 && strlen($gender) >= 1 && strlen($email) >= 1 && strlen($password) >= 1){
		$SQL="INSERT INTO `alumno`(`curp`, `nombreAlum`, `primerApeA`, `segundoApeA`, `fechaN`, `genero`, `correo`, `password`) VALUES ('$curp','$nombreA','$primerApe','$segundoApe','$date','$gender','$email','$password')";
		
		if (mysqli_query($conexion, $SQL))
		{
			session_start();
			$_SESSION['nombre']=$nombreA;
			
            echo "<center><p><h1>:::Su usuario se ha dado de alta correctamente. Por favor regrese para iniciar sesion:::</center></p></h1>";
            header("Location: ../LOGIN_a.php");
			
			//echo "<a href='principal.html'>Regresar</a>";
			echo "\n";
		}
		else
		{
			echo "<p> Se ha producido un error</p>";
			header("Location: formulario_Almunoi.html");
		}
		
		
		}
		else{
			echo "<h3>RELLENA BIEN TODOS LOS DATOS</h3>";
            header("Location: formulario_Almunoi.html");
		} 

?>