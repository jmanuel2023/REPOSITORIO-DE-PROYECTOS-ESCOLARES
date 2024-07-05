<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>MAPA MUNDIAL</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/navbars/">

    

    

<link href="../../boostrape/assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }
      #fondo{
        background-color: #00CCFF;
      }
    </style>

    
    <!-- Custom styles for this template -->
    <link href="../../boostrape/navbars/navbar.css" rel="stylesheet">
    
</head>
<body id="fondo">
  <main>
  <?php
  session_start();
  $nombre=$_SESSION['nombreP'];
  $correo=$_SESSION['correo'];
  ?>
    <nav class="navbar navbar-dark bg-black" aria-label="First navbar example">
      <div class="container-fluid">
        <a class="navbar-brand" href="../../MENU_PRINCIAL.html"><img src="Imagenes/logoHP.png" width="50" height="50"></a>
        <h1 style="color:blanchedalmond;font-size:20px; text-align:center;"><p>Hola Profesor@:<?php echo $nombre?></p></h1>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample01" aria-controls="navbarsExample01" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
  
        <div class="collapse navbar-collapse" id="navbarsExample01">
          <ul class="navbar-nav me-auto mb-2">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-expanded="false">ACCESOS</a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="agregarAnimal.php" target="_blank">PROPUESTA DE ANIMAL: ENVIO DE CORREO</a></li>
                <li><a class="dropdown-item" href="verAlumnos.php" target="_blank">VER ALUMNOS INSCRITOS</a></li>
                
              </ul>
              <a class="nav-link dropdown-toggle" href="#" data-bs-toggle="dropdown" aria-expanded="false">PERFIL</a>
              <ul class="dropdown-menu">
                <li><a class="dropdown-item" href="../../MENU_PRINCIAL.html">CERRAR SESION</a></li>
              </ul>
            </li>
          </ul>
        </div>
      </div>
      <script src="mapamundial2.js"></script>
<script src="continentmap.js"></script>
    </nav>
<div>
    
    <div id="map">
        <p style="font-size:xx-large; color: brown;">Mapa Mundial De Huellas Perdidas</p>
    </div>
</div>
</main>
<script src="../../boostrape/assets/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>