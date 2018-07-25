-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 10, 2017 at 12:01 PM
-- Server version: 5.6.34-log
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cfrm`
--
CREATE DATABASE IF NOT EXISTS `cfrm` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `cfrm`;

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `crearSocio`$$
CREATE PROCEDURE `crearSocio`(IN `p_num` VARCHAR(11), IN `p_codigo` VARCHAR(26), IN `p_credencial` VARCHAR(4), IN `p_nombre` VARCHAR(100), IN `p_apellido1` VARCHAR(100), IN `p_apellido2` VARCHAR(100), IN `p_dni` VARCHAR(10), IN `p_nacimiento` VARCHAR(10), IN `p_alta` VARCHAR(10), IN `p_correo` VARCHAR(100), IN `p_telefono` VARCHAR(15), IN `p_direccion` VARCHAR(150), IN `p_postal` VARCHAR(5), IN `p_ciudad` VARCHAR(100), IN `p_ccc` VARCHAR(29))
BEGIN
 
    INSERT INTO socio (numero, codigoSistema, credencial) VALUES (p_num, p_codigo, p_credencial);
    
    INSERT INTO socio_data (nombre, apellido1, apellido2, dni, nacimiento, alta, correo, telefono, direccion, codigoPostal, ciudad, ccc, codigoSistema) VALUES (p_nombre, p_apellido1, p_apellido2, p_dni, p_nacimiento, p_alta, p_correo, p_telefono, p_direccion, p_postal, p_ciudad, p_ccc, p_codigo);
END$$

DROP PROCEDURE IF EXISTS `crearUsuario`$$
CREATE PROCEDURE `crearUsuario`(IN `p_user` VARCHAR(32), IN `p_pass` VARCHAR(64), IN `p_role` VARCHAR(10), IN `p_nombre` VARCHAR(45), IN `p_apellido1` VARCHAR(45), IN `p_apellido2` VARCHAR(45), IN `p_dni` VARCHAR(32), IN `p_nacimiento` VARCHAR(10), IN `p_email` VARCHAR(100))
BEGIN
    DECLARE _id_usuario INT DEFAULT 0;
 
    INSERT INTO usuario (user, pass, role) VALUES (p_user, p_pass ,p_role);
    
    SET _id_usuario = LAST_INSERT_ID();
    
    INSERT INTO usuario_data (nombre, apellido1, apellido2, dni, nacimiento, email, id_usuario) VALUES (p_nombre, p_apellido1, p_apellido2, p_dni, p_nacimiento, p_email, _id_usuario);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `credencial`
--

DROP TABLE IF EXISTS `credencial`;
CREATE TABLE IF NOT EXISTS `credencial` (
  `idCredencial` int(11) NOT NULL,
  `credencial` varchar(100) COLLATE utf8_bin NOT NULL,
  `codigo` varchar(4) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Truncate table before insert `credencial`
--

TRUNCATE TABLE `credencial`;
--
-- Dumping data for table `credencial`
--

INSERT INTO `credencial` (`idCredencial`, `credencial`, `codigo`) VALUES
(1, 'SOCIO', 'RMSC');

-- --------------------------------------------------------

--
-- Table structure for table `socio`
--

DROP TABLE IF EXISTS `socio`;
CREATE TABLE IF NOT EXISTS `socio` (
  `idAbonado` int(11) NOT NULL,
  `numero` varchar(11) COLLATE utf8_bin NOT NULL,
  `codigoSistema` varchar(26) COLLATE utf8_bin NOT NULL,
  `credencial` varchar(4) COLLATE utf8_bin NOT NULL,
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active` tinyint(4) NOT NULL DEFAULT '1',
  `printed` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Truncate table before insert `socio`
--

TRUNCATE TABLE `socio`;
--
-- Dumping data for table `socio`
--

INSERT INTO `socio` (`idAbonado`, `numero`, `codigoSistema`, `credencial`, `createDate`, `active`, `printed`) VALUES
(1, '1', '3h28iu8c93p07ab3agsjq3lcsu', 'RMSC', '2017-07-23 23:05:57', 1, 0),
(3, '2', 'eol2eu4e0onnm3k2mbgre1vteo', 'RMSC', '2017-08-10 11:54:35', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `socio_data`
--

DROP TABLE IF EXISTS `socio_data`;
CREATE TABLE IF NOT EXISTS `socio_data` (
  `codigoSistema` varchar(26) COLLATE utf8_bin NOT NULL DEFAULT '',
  `nombre` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `apellido1` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `apellido2` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `dni` varchar(10) COLLATE utf8_bin NOT NULL,
  `nacimiento` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `alta` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `correo` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `telefono` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `direccion` varchar(150) COLLATE utf8_bin DEFAULT NULL,
  `codigoPostal` varchar(5) COLLATE utf8_bin DEFAULT NULL,
  `ciudad` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ccc` varchar(29) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Truncate table before insert `socio_data`
--

TRUNCATE TABLE `socio_data`;
--
-- Dumping data for table `socio_data`
--

INSERT INTO `socio_data` (`codigoSistema`, `nombre`, `apellido1`, `apellido2`, `dni`, `nacimiento`, `alta`, `correo`, `telefono`, `direccion`, `codigoPostal`, `ciudad`, `ccc`) VALUES
('3h28iu8c93p07ab3agsjq3lcsu', '', '', '', '/', '', '', '', '', '//', '', '', '/////');

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL,
  `user` varchar(32) COLLATE utf8_bin NOT NULL,
  `pass` varchar(64) COLLATE utf8_bin NOT NULL,
  `role` varchar(45) COLLATE utf8_bin NOT NULL,
  `activo` varchar(45) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `createDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Truncate table before insert `usuario`
--

TRUNCATE TABLE `usuario`;
--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `user`, `pass`, `role`, `activo`, `createDate`) VALUES
(13, 'ggamboa', '914420a9b210195dea7e8a1fdc5234fb1f413c04dba3b5eaabed9df6adb47f51', 'admin', '1', '2017-05-15 12:13:10'),
(14, 'test', 'cd611a5e9326e4642b01d0077cbd32c9f34c4aa6fbb6c48ca14dbe2d0bd2591e', 'admin', '0', '2017-05-17 22:42:41');

-- --------------------------------------------------------

--
-- Table structure for table `usuario_data`
--

DROP TABLE IF EXISTS `usuario_data`;
CREATE TABLE IF NOT EXISTS `usuario_data` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(45) COLLATE utf8_bin NOT NULL,
  `apellido1` varchar(45) COLLATE utf8_bin NOT NULL,
  `apellido2` varchar(45) COLLATE utf8_bin NOT NULL,
  `dni` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `nacimiento` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(100) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Truncate table before insert `usuario_data`
--

TRUNCATE TABLE `usuario_data`;
--
-- Dumping data for table `usuario_data`
--

INSERT INTO `usuario_data` (`id_usuario`, `nombre`, `apellido1`, `apellido2`, `dni`, `nacimiento`, `email`) VALUES
(13, 'gustavo', 'gamboa', 'cordero', '75891423', '04/05/1983', 'gustavo@gmbdesign.es'),
(14, 'test', 'test', 'test', '12345678', '2017-05-18', 'test@test.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `credencial`
--
ALTER TABLE `credencial`
  ADD PRIMARY KEY (`idCredencial`);

--
-- Indexes for table `socio`
--
ALTER TABLE `socio`
  ADD PRIMARY KEY (`idAbonado`),
  ADD UNIQUE KEY `codigoSistema_Unico` (`codigoSistema`) USING BTREE,
  ADD UNIQUE KEY `numeroSocio_Unico` (`numero`);

--
-- Indexes for table `socio_data`
--
ALTER TABLE `socio_data`
  ADD PRIMARY KEY (`codigoSistema`);

--
-- Indexes for table `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `user_UNIQUE` (`user`);

--
-- Indexes for table `usuario_data`
--
ALTER TABLE `usuario_data`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `dni_UNIQUE` (`dni`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `credencial`
--
ALTER TABLE `credencial`
  MODIFY `idCredencial` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `socio`
--
ALTER TABLE `socio`
  MODIFY `idAbonado` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `usuario_data`
--
ALTER TABLE `usuario_data`
  ADD CONSTRAINT `fk_usuarios_data_usuarios` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
