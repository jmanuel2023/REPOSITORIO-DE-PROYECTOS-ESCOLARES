<?php
echo "<link rel='stylesheet' href='../estilos_ALMUNO.css'>";
include ("Conexion.php");
$link=Conexion();
//$resultado=mysqli_query($conexion,$sql);
$result= mysqli_query($link, "select * from alumno");
while($filas = mysqli_fetch_array($result,MYSQLI_ASSOC)){

echo "<head><title>ALUMNOS INSCRITOS</title></head>";
echo "<p>";
echo "<center>";
echo "<table border=10 cellpadding='5px' cellspacing='5px'>";
echo "<TR>";
echo "<TH width=40>"."Nombre";
echo "<TH width=40>"."Apellidos";
echo "<TH width=40>"."correo";
echo "</TR>";
echo "</center>";
echo "<tr>"; 
echo "<td valign='top'>" . $filas['nombreAlum'] . "</td>";
echo "<td valign='top'>" . $filas['primerApeA'] . "\t".$filas['segundoApeA']."</td>";
echo "<td valign='top'>" . $filas['correo'] . "</td>";
echo "</tr>";
};

mysqli_free_result($result); 
mysqli_close($link);
?>
</TABLE>
<H3>&nbsp;</H3>
<H3>&nbsp;</H3>
<a href="MAPAP.php"><font color="red">REGRESA A LA PAGINA PRINCIPAL</font></a>