-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-01-2023 a las 03:36:33
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

DROP DATABASE if exists registros;
create database registros;
use registros;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin`
--

CREATE TABLE `admin` (
  `usuario` char(30) NOT NULL,
  `password` char(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `admin`
--

INSERT INTO `admin` (`usuario`, `password`) VALUES
('admin', 'Huellas1010');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `curp` char(18) NOT NULL,
  `nombreAlum` char(20) NOT NULL,
  `primerApeA` char(15) NOT NULL,
  `segundoApeA` char(15) NOT NULL,
  `fechaN` date NOT NULL,
  `genero` char(1) NOT NULL,
  `correo` char(50) NOT NULL,
  `password` char(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`curp`, `nombreAlum`, `primerApeA`, `segundoApeA`, `fechaN`, `genero`, `correo`, `password`) VALUES
('LEPE050413HMCNND01', 'dwfeg', 'Perez', 'egefge', '1999-08-16', 'H', 'hola1@prueba.com', 'Hola1'),
('MAMJ020826HMCNRNA1', 'Joan', 'Manuel', 'Morales', '2002-08-26', 'H', 'joanhanzka2002@gmail.com', 'Joan123'),
('ROJI000114HDFDRSA9', 'Israel', 'Rodriguez', 'Juarez', '2000-01-14', 'H', 'israel@gmail.com', 'Israel123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `animales`
--

CREATE TABLE `animales` (
  `codigo` char(20) NOT NULL,
  `nombre` char(30) NOT NULL,
  `continente` char(10) NOT NULL,
  `estado actual` char(23) NOT NULL,
  `dieta` char(100) NOT NULL,
  `datonum` int(5) NOT NULL,
  `imagen` char(50) NOT NULL,
  `sonido` char(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `animales`
--

INSERT INTO `animales` (`codigo`, `nombre`, `continente`, `estado actual`, `dieta`, `datonum`, `imagen`, `sonido`) VALUES
('AFBUAF01', 'Búfalo africano', 'África', 'Endemico', 'pastos, que complementa con hojas de árboles.', 26, 'Imagenes/Endemicos/bufalo.jpeg', 'https://www.youtube.com/watch?v=LCjuXjHZdEg'),
('AFCASE01', 'Caracol de las seychelles', 'África', 'Extinto', 'Herbívoro', 2000, 'Imagenes/Extintos/caracol.jpg', NULL),
('AFCORI01', 'Conejo ribereño', 'África', 'En peligro de extincion', 'Herbívoro', 3, 'Imagenes/Peligro/conejo.jpeg', 'https://www.youtube.com/watch?v=0NeuNze48UA'),
('AFLECA01', 'León del cabo', 'África', 'Extinto', 'Carnívoro', 1865, 'Imagenes/Extintos/leon.jpeg', 'https://www.youtube.com/watch?v=_837PUc5bjA'),
('AFPAAZ01', 'Pájaro azúcar del cabo', 'África', 'Endemico', 'Néctar y frutas', 10, 'Imagenes/Endemicos/pajaro.jpg', ''),
('AFQU01', 'Quagga', 'África', 'Extinto', 'Herbívoro', 1880, 'Imagenes/Extintos/quagga.jpg', NULL),
('AFRINO01', 'Rinoceronte negro', 'África', 'En peligro de extincion', 'Árboles y arbustos', 5000, 'Imagenes/Peligro/rinoceronte.jpeg', 'https://www.youtube.com/watch?v=X2pzx_srSfs\r\n\r\n'),
('AFROPE01', 'Rodillo de pecho lila', 'África', 'Endemico', 'insectos, lagartos, escorpiones, caracoles', 14, 'Imagenes/Endemicos/rodillo.jpg', ''),
('AMAGCA01', 'Águila calva', 'América', 'Endemico', 'peces, incluido el arenque, el salmón, carpas', 30, 'Imagenes/Endemicos/aguila.jpg', 'https://www.youtube.com/watch?v=-TGYhY3Mtjw'),
('AMAJ01', 'Ajolote', 'América', 'Endemico', 'pequeños crustáceos, larvas de insectos, gusanos,', 15, 'Imagenes/Endemicos/ajolote.jpg', 'https://www.youtube.com/watch?v=JVE8mAeZdz8'),
('AMCA01', 'Caribú', 'América', 'En peligro de extincion', 'Herbívoro', 4000000, 'Imagenes/Peligro/caribu.jpg', 'https://www.youtube.com/watch?v=wQh-HWLIwCw'),
('AMFOMO02', 'Foca monje del caribe', 'America', 'Extinto', 'peces, cefalópodos y crustáceos', 2008, 'Imagenes/Extintos/foca.jpeg', 'https://www.youtube.com/watch?v=iBuS81bO3GQ'),
('AMMONO01', 'Mono nocturno andino', 'América', 'En peligro de extincion', 'frugívoro, hojas brotes e insectos', 15, 'Imagenes/Peligro/mono.jpg', 'https://www.youtube.com/watch?v=z0-HSHVs8UU'),
('AMNUGI01', 'Nutria gigante', 'América', 'Endemico', 'peces y crustáceos, pero a veces captura pequeños', 15, 'Imagenes/Endemicos/Nutria.jpg', 'https://www.youtube.com/watch?v=pTLklcuT7ck'),
('AMSADO01', 'Sapo dorado', 'América', 'Extinto', 'Insectos', 2008, 'Imagenes/Extintos/sapo.jpeg', NULL),
('AMTOGI01', 'Tortuga gigante de pinta', 'América', 'Extinto', 'Herbívoro', 2012, 'Imagenes/Extintos/tortuga.jpeg', NULL),
('AMVAMA01', 'Vaquita Marina', 'América', 'En peligro de extincion', 'Peces, calamares, plancton', 15, 'Imagenes/Peligro/vaquita.jpeg', 'https://www.youtube.com/watch?v=x8QbM7KmSSc'),
('ASDEBA01', 'Delfín baiji', 'Asia', 'Extinto', 'Peces', 2008, 'Imagenes/Extintos/delfin.png', 'https://www.youtube.com/watch?v=Wwk5ywNSsdI&t=10s'),
('ASELAS01', 'Elefante asiático', 'Asia', 'En peligro de extincion', 'Plantas y frutos', 400000, 'Imagenes/Peligro/elefante.jpg', 'https://www.youtube.com/watch?v=hCJxZ5Z9_cs'),
('ASMOHO01', 'Mono de la hoja de nilgiri', 'Asia', 'Endemico', 'frutas, brotes y hojas', 29, 'Imagenes/Endemicos/mono.jpg', 'https://www.youtube.com/watch?v=yKG1NAdKwUA'),
('ASPAGI01', 'Panda gigante', 'Asia', 'Endemico', 'Bambú', 20, 'Imagenes/Endemicos/panda.jpg', 'https://www.youtube.com/shorts/5f0lprE1YB0'),
('ASPAMA01', 'Pato de las marianas', 'Asia', 'Extinto', 'invertebrados acuáticos, pequeños vertebrados y pl', 1981, 'Imagenes/Extintos/pato.jpg', NULL),
('ASPARO01', 'Panda rojo', 'Asia', 'En peligro de extincion', 'Bambú, bayas, frutos y hongos', 10000, 'Imagenes/Peligro/panda.jpeg', 'https://www.youtube.com/watch?v=Eg0y1X-U0Mw'),
('ASTIBE01', 'Tigre de bengala', 'Asia', 'En peligro de extincion', 'Carnívoro', 4000, 'Imagenes/Peligro/tigre.jpg', 'https://www.youtube.com/watch?v=HQijBrgdjp4'),
('ASTIJA01', 'Tigre de java', 'Asia', 'Extinto', 'Carnívoro', 1972, 'Imagenes/Extintos/tigre.jpeg', 'https://www.youtube.com/watch?v=HQijBrgdjp4'),
('EUBIEU01', 'Bisonte europeo', 'Europa', 'Endemico', 'Hierbas, brotes y hojas', 25, 'Imagenes/Endemicos/bisonte.jpg', 'https://www.youtube.com/watch?v=NKdh3E1bePA'),
('EUCAMO', 'Cabra montés de los pirineos', 'Europa', 'Extinto', 'Hojas y arbustos', 2000, 'Imagenes/Extintos/Cabra.jpeg', NULL),
('EUDUMA01', 'Ducula de las marquesas', 'Europa', 'Endemico', 'Frutos', 17, 'Imagenes/Endemicos/ducula.jpg', ''),
('EUFOMO01', 'Foca monje del mediterráneo', 'Europa', 'En peligro de extincion', 'Peces y moluscos', 500, 'Imagenes/Peligro/foca.jpeg', 'https://www.youtube.com/watch?v=iBuS81bO3GQ'),
('EUGRMA01', 'Gran mariposa blanca de madeir', 'Europa', 'Extinto', 'Néctar', 1970, 'Imagenes/Extintos/mariposa.jpg', NULL),
('EULIIB01', 'Lince ibérico', 'Europa', 'Endemico', 'Carnívoro', 15, 'Imagenes/Endemicos/lince.jpeg', 'https://www.youtube.com/watch?v=i6EeIZbcjLg'),
('EULOIB01', 'Lobo ibérico', 'Europa', 'En peligro de extincion', 'Carnívoro', 3000, 'Imagenes/Peligro/lobo.jpeg', 'https://www.youtube.com/watch?v=kABTjUYZNkA'),
('EUOSUN01', 'Ostrero unicolor canario', 'Europa', 'Extinto', 'Moluscos y crustáceos, especialmente lapas', 1940, 'Imagenes/Extintos/ostrero.jpg', NULL),
('EUZOAR01', 'Zorro ártico', 'Europa', 'En peligro de extincion', 'Pequeños mamíferos y aves', 1000000000, 'Imagenes/Peligro/zorro.jpg', 'https://www.youtube.com/watch?v=Xq52cnklTfk'),
('OCALAB01', 'Alcatraz de Abbott', 'Oceanía', 'En peligro de extincion', 'Peces y calamares', 3000, 'Imagenes/Peligro/alcatraz.jpg', 'https://www.youtube.com/watch?v=2r4-yLBmiHw'),
('OCDEHE01', 'Delfín de Héctor', 'Oceanía', 'En peligro de extincion', 'cardúmenes costeros, como bacalao rojo, salmonete', 9000, 'Imagenes/Peligro/delfin.jpeg', 'https://www.youtube.com/watch?v=Wwk5ywNSsdI'),
('OCDEJO01', 'Delfín jorobado de Australia', 'Oceanía', 'Endemico', 'Peces', 10, 'Imagenes/Endemicos/delfin.jpeg', 'https://www.youtube.com/watch?v=Wwk5ywNSsdI&t=10s\r'),
('OCDETA01', 'Demonio de Tasmania', 'Oceanía', 'En peligro de extincion', 'Aves', 25000, 'Imagenes/Peligro/demonio.jpg', 'https://www.youtube.com/watch?v=4Jkv2iD-r1s'),
('OCKA01', 'Kamao', 'Oceanía', 'Extinto', 'frugívora, insectos y otros invertebrados', 2004, 'Imagenes/Extintos/kamao.jpg', NULL),
('OCLECA01', 'Lechuzón cariblanco', 'Oceanía', 'Extinto', 'aves, geckos, murciélagos y roedores', 1914, 'Imagenes/Extintos/lechuzon.jpg', 'https://www.youtube.com/watch?v=uczMK_se6Fc'),
('OCOR01', 'Ornitorrinco', 'Oceanía', 'Endemico', 'plantas marinas, lombrices, renacuajos, crustáceos', 17, 'Imagenes/Endemicos/ornitorrinco.jpeg', 'https://www.youtube.com/watch?v=IZZ0SKOCJ0Q'),
('OCPUAN01', 'Pulpo de anillos azules', 'Oceanía', 'Endemico', ' pequeños cangrejos y camarones', 4, 'Imagenes/Endemicos/pulpo.jpg', ''),
('OCTI01', 'Tilacino', 'Oceanía', 'Extinto', 'Carnívoro', 1986, 'Imagenes/Extintos/tilacino.jpg', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `profesor`
--

CREATE TABLE `profesor` (
  `matricula` char(10) NOT NULL,
  `nombreProf` char(20) NOT NULL,
  `primerApeP` char(15) NOT NULL,
  `segundoApeP` char(15) NOT NULL,
  `fechaN` date NOT NULL,
  `genero` char(1) NOT NULL,
  `correo` char(50) NOT NULL,
  `password` char(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `profesor`
--

INSERT INTO `profesor` (`matricula`, `nombreProf`, `primerApeP`, `segundoApeP`, `fechaN`, `genero`, `correo`, `password`) VALUES
('0000000011', 'Marta Rosa', 'Cordero', 'Lopez', '1960-01-01', 'M', 'mcorderol@ipn.mx', 'Marta123'),
('1234323421', 'Carlos', 'Vence', 'Martinez', '1987-07-14', 'H', 'vence@prueba.com', 'Vence1234'),
('1313939323', 'Sergio', 'Melchor', 'Ocampo', '2005-01-13', 'H', 'sergio@hotmail.com', 'Sergio123'),
('4419181716', 'Gerardo', 'Hernandez', 'Gonzalez', '1979-09-15', 'H', 'gerardo123@gmail.com', 'Hola1234');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`usuario`);

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`curp`);

--
-- Indices de la tabla `animales`
--
ALTER TABLE `animales`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `profesor`
--
ALTER TABLE `profesor`
  ADD PRIMARY KEY (`matricula`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
