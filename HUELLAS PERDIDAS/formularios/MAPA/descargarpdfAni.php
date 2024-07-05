<?php
//En este archivo nos conectamos a una base de datos para extraer información de un usuario
    include("fpdf185/fpdf.php");
    //session_start();
   //$boleta1=$_SESSION["boleta"];
   
     
    class PDF extends FPDF
    {
        // Cabecera de página

        function Header()
        {
            global $title;
            // Logo
            $this->Image('Imagenes/logoHP.png',10,8,20);
            $this->Ln(4);
            $this->SetFont('Arial','B',15);
            // Movernos a la derecha
            $this->Cell(80);
            // Título
            $this->Cell(30,10,'REPORTE DE LOS ANIMALES REGISTRADOS',0,0,'C');
            // Salto de línea
            $this->Ln(20);
        }

        function Footer(){
            // Posición: a 1,5 cm del final
            $this->SetY(-15);
            // Arial italic 8
            $this->SetFont('Arial','',5);
            // Número de página
            $this->Cell(0, 20,'Page '.$this->PageNo(),0,0,'R');
        }
}
    //Conexión a la BD y extracción de la información de nuestro interes
    // Creación del objeto de la clase heredada

    
    $pdf = new PDF();
    $pdf->AliasNbPages();
    $pdf->AddPage();
    $pdf->SetFont('courier','',12);
    $pdf->Ln(10);
    $pdf->SetY(30);
    $conexion = mysqli_connect("localhost","root","","registros");
    $sql = "SELECT * FROM animales";
    $res = mysqli_query($conexion, $sql);
    $num = mysqli_num_rows($res);
    $i=46;
    $j=0;
    while ($animal = mysqli_fetch_array($res, MYSQLI_ASSOC)){

            if($j<4){
                $pdf->Image($animal['imagen'],150,$i,40,30);
                $j=$j+1;
            }
            else{
                $i=46;  
                $pdf->AddPage();
                $pdf->Image($animal['imagen'],150,$i,40,30);
                $j=1;

            }
            $pdf->Cell(15,30,utf8_decode("Nombre del animal:".$animal['nombre'].""));
            $pdf->Ln(4);
            $pdf->Cell(15,34,utf8_decode("Continente:".$animal['continente'].""));
            $pdf->Ln(4);
            $pdf->Cell(15,38,utf8_decode("Estado actual:".$animal['estado actual'].""));
            $pdf->Ln(4);
            $pdf->Cell(15,42,utf8_decode("Dieta:".$animal['dieta'].""));
            $pdf->Ln(4);
            if($animal['estado actual']=="En peligro de extinción"){
            $pdf->Cell(15,46,utf8_decode("Aproximados vivos:".$animal['datonum'].""));
            $pdf->Ln(4);
            }
            else if($animal['estado actual']=="Extinto"){
                $pdf->Cell(15,46,utf8_decode("Año de extinción:".$animal['datonum'].""));
                $pdf->Ln(4);
            }
            
            else{
                $pdf->Cell(15,46,utf8_decode("Aproximados vivos:".$animal['datonum'].""));
            $pdf->Ln(4);

            }
            if($animal['sonido']==NULL)
            $pdf->Cell(15,50,utf8_decode("No hay sonido registrado"));
            else
            $pdf->Cell(15,50,utf8_decode("Sonido:".$animal['sonido'].""));
            $pdf->Ln(35);
            $i=$i+55;

    }

    

    $pdf->Output();
?>