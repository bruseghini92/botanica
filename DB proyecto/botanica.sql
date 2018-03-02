-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 06-10-2017 a las 04:34:27
-- Versión del servidor: 5.6.17
-- Versión de PHP: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `botanica`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `climas`
--

CREATE TABLE IF NOT EXISTS `climas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `climas`
--

INSERT INTO `climas` (`id`, `nombre`) VALUES
(1, 'Calido'),
(2, 'Templado'),
(3, 'Arido'),
(4, 'Frio'),
(5, 'nanan');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados`
--

CREATE TABLE IF NOT EXISTS `estados` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `estados`
--

INSERT INTO `estados` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Saludable', 'Planta en buen estado general con posibilidad de continuar su ciclo. Optimas condiciones'),
(2, 'Enferma', 'La planta se encuentra en riesgos, requiere atencion sanitaria. '),
(3, 'Muerta', 'La planta ha llegado a un punto irreversible. Es necesario discontinuar su cuidado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `etapas`
--

CREATE TABLE IF NOT EXISTS `etapas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `etapas`
--

INSERT INTO `etapas` (`id`, `nombre`, `descripcion`) VALUES
(1, 'Germinacion', 'Esta etapa comprende entierro de la semilla, la cual absorbe agua y se hincha. Luego se abre y sale una pequeña raiz. Por ultimo el tallo sale hacia arriba y aparecen las primeras hojas.'),
(2, 'Crecimiento', 'La raiz y el tallo crece, salen nuevas hojas y/o ramas.'),
(3, 'Floracion', 'La planta ya ha alcanzado la madurez en su ciclo vital. Aparecen las flores donde se encuentran los órganos masculinos y/o femeninos de la planta y donde se lleva a cabo la polinización y/o formación de semillas y frutos.'),
(4, 'Polinizacion', '                        Proceso de transporte del polen que se encuentra en el interior de los estambres y se tiene que unir al óvulo que se encuentra en el pistilo.'),
(5, 'Formacion del fruto', 'Después de ser polinizada, la flor se transforma en fruto. Y dentro del fruto se encuentran las semillas para dar lugar a una nueva planta.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE IF NOT EXISTS `personas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `apellido` varchar(50) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `edad` int(11) DEFAULT NULL,
  `usuarios_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `usuario_fk_idx` (`usuarios_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id`, `apellido`, `nombre`, `edad`, `usuarios_id`) VALUES
(1, 'padilla', 'perla', 22, 6),
(2, 'ascurra', 'daiana', 27, 7),
(3, 'rojo', 'martin', 21, 8),
(4, 'sabio', 'leandro', 23, 9),
(5, 'martinez', 'brenda', 24, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plantas`
--

CREATE TABLE IF NOT EXISTS `plantas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `mes_inicio` date DEFAULT NULL,
  `mes_fin` date DEFAULT NULL,
  `suelo_id` int(11) DEFAULT NULL,
  `clima_id` int(11) DEFAULT NULL,
  `descripcion` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_clima_idx` (`clima_id`),
  KEY `id_suelo_idx` (`suelo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

CREATE TABLE IF NOT EXISTS `preguntas` (
  `id` int(11) NOT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `usuarios_id` int(11) DEFAULT NULL,
  `cerrado` tinyint(1) DEFAULT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `temas_id` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `temas_id_UNIQUE` (`temas_id`),
  UNIQUE KEY `usuarios_id_UNIQUE` (`usuarios_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuestas`
--

CREATE TABLE IF NOT EXISTS `respuestas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `preguntas_id` int(11) DEFAULT NULL,
  `texto` varchar(255) DEFAULT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `respuestascol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `preguntas_id_UNIQUE` (`preguntas_id`),
  UNIQUE KEY `usuario_id_UNIQUE` (`usuario_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rol` varchar(50) DEFAULT NULL,
  `descripcion` varchar(520) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `rol`, `descripcion`) VALUES
(1, 'ROLE_ADMIN', 'Administrador'),
(2, 'ROLE_USER', 'Usuario Comun'),
(3, 'ROLE_VENDEDOR', 'Vendedor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suelos`
--

CREATE TABLE IF NOT EXISTS `suelos` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `suelos`
--

INSERT INTO `suelos` (`id`, `nombre`) VALUES
(1, 'Secos y arenosos'),
(2, 'Salitrosos'),
(3, 'Arcillosos'),
(4, 'Alcalinos'),
(5, 'Humiferos');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temas`
--

CREATE TABLE IF NOT EXISTS `temas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(120) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos`
--

CREATE TABLE IF NOT EXISTS `tipos` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='			';

--
-- Volcado de datos para la tabla `tipos`
--

INSERT INTO `tipos` (`id`, `nombre`, `descripcion`) VALUES
(0, 'Arboreas', 'Se reconocen por que tienen un solo tronco principal del cual salen las ramas, luego las hojas y las flores. Su tronco es leñoso y usualmente tiene una composición clásica; raíces debajo de la tierra y una copa de hojas fronda. Son muy duraderos.'),
(1, 'Arbustivas', '	No suelen tener un tronco principal, sino varios que salen desde la base de la planta. Las ramas y troncos también suelen ser leñosos. Usualmente tienen un crecimiento de formas variadas, pero son ideales para podar y darle forma a gusto'),
(2, 'Herbaceas', 'Suelen ser pequeñas.  Sus ramas y troncos no son leñosos sino más blandos.'),
(3, 'Tuberosas', 'se desarrollan a través de un tubérculo. Esta es una parte de la planta que se ha modificado, ya sea la raíz o el tallo, para almacenar alimentos en las épocas donde escasean el agua o los nutrientes.'),
(4, 'Bulbosas', 'Los bulbos se desarrollan debajo de la tierra y sirven para almacenar alimentos, sobre todo durante el invierno. Los bulbos tienden a tener formas más redondeadas'),
(5, 'Aereas', '	Se les llama así debido a que sus raíces no viven dentro de la tierra. Estas se anclan en las copas de los árboles y absorben el alimento y el agua que corre sobre la corteza de los mismos'),
(6, 'Suculentas', ' han adaptado algunas de sus partes, ya sea el tallo o las hojas, para almacenar agua y alimentos en épocas de escasez.'),
(7, 'Acuaticas', 'viven sobre el agua o en estanques donde el sustrato siempre está inundado. Estas se han adaptado a estos altos niveles de humedad lo mismo en cuerpos de agua salinos como de agua dulce');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_plantas`
--

CREATE TABLE IF NOT EXISTS `tipos_plantas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipos_id` int(11) DEFAULT NULL,
  `plantas_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `id_tipos_idx` (`tipos_id`),
  KEY `id_plantas_idx` (`plantas_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) COLLATE utf8_spanish_ci NOT NULL,
  `user` varchar(16) COLLATE utf8_spanish_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_spanish_ci DEFAULT NULL,
  `roles_id` int(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_UNIQUE` (`user`),
  KEY `roles_fk` (`roles_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci AUTO_INCREMENT=12 ;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `password`, `user`, `email`, `roles_id`) VALUES
(6, '1234', 'perla_padilla', 'perlapadilla@hotmail.com', 3),
(7, '4321', 'daiana_ascurra', 'daianaascurra@hotmail.com', 2),
(8, 'abc123', 'martin_rojo', 'martinrojo@hotmail.com', 1),
(9, '123abc', 'leandro_sabio', 'leandrosabio@hotmail.com', 1),
(10, 'gallega', 'brenda_martinez', 'bredamartinez@hotmail.com', 3),
(11, '1234', 'ramiro', 'ramirog@hotmail.com', 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario_plantas`
--

CREATE TABLE IF NOT EXISTS `usuario_plantas` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `planta_id` int(11) NOT NULL,
  `etapa_id` int(11) DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL,
  `remedio` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_usuario_UNIQUE` (`usuario_id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `id_planta_UNIQUE` (`planta_id`),
  KEY `id_etapas_idx` (`etapa_id`),
  KEY `id_estados_idx` (`estado_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `personas`
--
ALTER TABLE `personas`
  ADD CONSTRAINT `usuario_fk` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `plantas`
--
ALTER TABLE `plantas`
  ADD CONSTRAINT `id_clima` FOREIGN KEY (`clima_id`) REFERENCES `climas` (`id`),
  ADD CONSTRAINT `id_suelo` FOREIGN KEY (`suelo_id`) REFERENCES `suelos` (`id`);

--
-- Filtros para la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD CONSTRAINT `temas_id` FOREIGN KEY (`temas_id`) REFERENCES `temas` (`id`),
  ADD CONSTRAINT `usuarios_id` FOREIGN KEY (`usuarios_id`) REFERENCES `usuarios` (`id`);

--
-- Filtros para la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD CONSTRAINT `preguntas_id` FOREIGN KEY (`preguntas_id`) REFERENCES `preguntas` (`id`);

--
-- Filtros para la tabla `tipos_plantas`
--
ALTER TABLE `tipos_plantas`
  ADD CONSTRAINT `id_plantas` FOREIGN KEY (`plantas_id`) REFERENCES `plantas` (`id`),
  ADD CONSTRAINT `id_tipos` FOREIGN KEY (`tipos_id`) REFERENCES `tipos` (`id`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `roles_fk` FOREIGN KEY (`roles_id`) REFERENCES `roles` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `usuario_plantas`
--
ALTER TABLE `usuario_plantas`
  ADD CONSTRAINT `fk_estados` FOREIGN KEY (`estado_id`) REFERENCES `estados` (`id`),
  ADD CONSTRAINT `fk_etapas` FOREIGN KEY (`etapa_id`) REFERENCES `etapas` (`id`),
  ADD CONSTRAINT `fk_plantas` FOREIGN KEY (`planta_id`) REFERENCES `plantas` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_usuarios` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
