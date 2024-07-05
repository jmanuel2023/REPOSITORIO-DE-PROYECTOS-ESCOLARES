<?php
include ("../Conexion.php");
$link=Conexion();
session_start();
//$resultado=mysqli_query($conexion,$sql);
$result= mysqli_query($link, "select * from profesor");
while($filas = mysqli_fetch_array($result,MYSQLI_ASSOC)){

echo "<head><title>PROFESORES INSCRITOS</title></head>";
echo "<p>";
echo "<center>";
echo "<table border=10 cellpadding='5px' cellspacing='5px'>";
echo "<TR>";
echo "<TH width=40>"."Matricula";
echo "<TH width=40>"."Nombre";
echo "<TH width=40>"."Apellidos";
echo "<TH width=40>"."Fecha de Nacimiento";
echo "<TH width=40>"."Genero";
echo "<TH width=40>"."correo";
echo "<TH width=40>BORRAR";
echo "<TH width=40>EDITAR";
echo "</TR>";
echo "</center>";
echo "<tr>";
echo "<td valign='top'>" . $filas['matricula'] . "</td>"; 
echo "<td valign='top'>" . $filas['nombreProf'] . "</td>";
echo "<td valign='top'>" . $filas['primerApeP'] . "\t".$filas   ['segundoApeP']."</td>";
echo "<td valign='top'>" . $filas['fechaN'] . "</td>";
echo "<td valign='top'>" . $filas['genero'] . "</td>";
echo "<td valign='top'>" . $filas['correo'] . "</td>";
echo "<td valign='top'><a href='borrarProf.php?m=".$filas['matricula']."'>Borrar</a></td>";
echo "<td valign='top'><a href='formProfAdmin.php?m=".$filas['matricula']."'>Editar</a></td>";
echo "</tr>";
};


mysqli_free_result($result); 
mysqli_close($link);
?>
</TABLE>
<H3>&nbsp;</H3>
<H3>&nbsp;</H3>
<a href="../admin.html"><font color="red">REGRESA A LA PAGINA PRINCIPAL</font></a>