<?php
include("phpMailer/class.phpmailer.php");
include("phpMailer/class.smtp.php");

$nombreAni=$_REQUEST['nombreA'];
$continente=$_REQUEST['continente'];
$categoria=$_REQUEST['categoria'];
$dietaA=$_REQUEST['dieta'];
$dato=$_REQUEST['dato'];

session_start();
$nombre=$_SESSION['nombreP'];
$correo=$_SESSION['correo'];

$email_user = "huellasperdidas1010@gmail.com"; //Debes actualizar esta línea con tu información
$email_password = "hmubtqqanwhsfqfo"; //Debes actualizar esta línea con tu información
$the_subject = "PROPUESTA DE AGREGACION DE ANIMAL";
$address_to = $correo; //Debes actualizar esta línea con tu información
$from_name = "HUELLAS PERDIDAS";
$phpmailer = new PHPMailer();

// ---------- datos de la cuenta de Gmail -------------------------------
$phpmailer->Username = $email_user;
$phpmailer->Password = $email_password; 
//-----------------------------------------------------------------------
// $phpmailer->SMTPDebug = 1;
$phpmailer->SMTPSecure = 'ssl';
$phpmailer->Host = "smtp.gmail.com"; // GMail
$phpmailer->Port = 465;
$phpmailer->IsSMTP(); // use SMTP
$phpmailer->SMTPAuth = true;

$phpmailer->setFrom($phpmailer->Username,$from_name);
$phpmailer->AddAddress($address_to); // recipients email

date_default_timezone_set('UTC'); //Universal Time Coordinated
date_default_timezone_set("America/Mexico_City");

$phpmailer->Subject = $the_subject;	
$phpmailer->Body .="<h1 style='color:#3498db;'>Hola profesor:\t".$nombre."</h1>";
$phpmailer->Body .= "<p>Usted ha solicitado que se haga la alta del siguiente animal.</p>";
$phpmailer->Body .= "<p>Nombre del Animal:".$nombreAni."</p>";
$phpmailer->Body .= "<p>Continente donde habita:".$continente."</p>";
$phpmailer->Body .= "<p>Estado actual del Animal:".$categoria."</p>";
$phpmailer->Body .= "<p>Dieta del Animal:".$dietaA."</p>";
if($categoria=="Endemicos"){
    $phpmailer->Body .= "<p>Esperanza de vida:".$dato."</p>";
}

else if($categoria=="En peligro de extincion"){
    $phpmailer->Body .= "<p>Aproximados vivos:".$dato."</p>";
}

else if($categoria=="Extinto"){
    $phpmailer->Body .= "<p>Año de extinción:".$dato."</p>";
}

$phpmailer->Body .= "<p>Aviso!!!!! Este correo solo es informativo. NO SE DEBE DE RESPONDER.</p>";
$phpmailer->Body .= "<p><em>Fecha: ".date("d-m-Y H:i:s")."</em></p>";

$phpmailer->IsHTML(true);

if(!$phpmailer->Send()){
    echo "El correo no se pudo enviar\n";
}
else
echo "Se envio el correctamente el correo\n";
?>