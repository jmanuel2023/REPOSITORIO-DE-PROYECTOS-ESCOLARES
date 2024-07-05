<?php
include ("Conexion.php");
$link=Conexion();
//$resultado=mysqli_query($conexion,$sql);
$result= mysqli_query($link, "select * from endemicos");
echo "<head><title>ANIMALES ENDEMICOS</title></head>";
echo "<p>";
echo "<center>";
echo "<table border=10 cellpadding='5px' cellspacing='5px'>";
echo "<TR>";
echo "<TH width=40>"."Código";
echo "<TH width=40>"."Nombre";
echo "<TH width=40>"."Continente";
echo "<TH width=40>"."Dieta";
echo "<TH width=40>"."Esperanza de vida";
echo "<TH width=40>"."Zona";
echo "<TH width=200>"."Imagen";
echo "<TH width=200>"."Sonido";
echo "</TR>";
echo "</center>";
while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC)){

printf("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%d</td><td>%s</td><td><img src=%s width=200px></td><td>%s</td></tr>\n",$row["Código"],$row["Nombre"],$row["Continente"],$row["Dieta"],$row["EsperanzaV"],$row["Zona"],$row["Imagen"],$row["Sonido"]);
//printf("<tr><td><a href=Comprar.php?cod=%s&mar=%s&pre=%d&mod=%s&yea=%d><font color='#FFFFFF'>Comprar</font></a></td></tr>\n",$row['codigo'],$row['marca'],$row['precio'],$row['modelo'],$row['years']);
} 
mysqli_free_result($result); 
mysqli_close($link);
?>
</TABLE>
<H3>&nbsp;</H3>
<H3>&nbsp;</H3>
<a href="Mapa.html"><font color="red">REGRESA A LA PAGINA PRINCIPAL</font></a>