<?php
$conexion=mysqli_connect("localhost","root","","registros"); 
$matricula= $_REQUEST['matricula'];
$nombreP=$_REQUEST['nombrep'];
$primerApe= $_REQUEST['primerp'];
$segundoApe= $_REQUEST['segundop'];
$date= $_REQUEST['fechaN'];
$gender= $_REQUEST['genero'];
$email= $_REQUEST['correo'];
$password= $_REQUEST['pass'];
	if (strlen($matricula) >= 1 && strlen($nombreP) >= 1 && strlen($primerApe) >= 1 && strlen($segundoApe) >= 1 && strlen($date) >= 1 && strlen($gender) >= 1 && strlen($email) >= 1 && strlen($password) >= 1){
		$SQL="INSERT INTO `profesor`(`matricula`, `nombreProf`, `primerApeP`, `segundoApeP`, `fechaN`, `genero`, `correo`, `password`) VALUES ('$matricula','$nombreP','$primerApe','$segundoApe','$date','$gender','$email','$password')";
		if (mysqli_query($conexion, $SQL))
		{
			session_start();
		$_SESSION['nombreP']=$nombreP;
		$_SESSION['correo']=$email;
            echo "<center><p><h1>:::Su usuario se ha dado de alta correctamente. Por favor regrese para iniciar sesion:::</center></p></h1>";
            header("Location: ../LOGIN_p.php");
			
			//echo "<a href='principal.html'>Regresar</a>";
			echo "\n";
		}
		else
		{
			echo "<p> Se ha producido un error</p>";
		}
		
		
		}
		else{
			echo "<h3>RELLENA BIEN TODOS LOS DATOS</h3>";
            header("Location: formulario_PROFES.html");
		} 

?>