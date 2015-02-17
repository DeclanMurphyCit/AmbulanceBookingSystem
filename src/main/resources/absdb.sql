-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Feb 17, 2015 at 08:13 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `absdb`
--
CREATE DATABASE IF NOT EXISTS `absdb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `absdb`;

-- --------------------------------------------------------

--
-- Table structure for table `ambulancebooking`
--

CREATE TABLE IF NOT EXISTS `ambulancebooking` (
`id` int(11) NOT NULL,
  `patientId` int(11) DEFAULT NULL,
  `ambCompanyId` int(11) DEFAULT NULL,
  `amcCrewId` int(11) DEFAULT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `destination` int(11) DEFAULT NULL,
  `origin` int(11) DEFAULT NULL,
  `cardiac` tinyint(1) DEFAULT NULL,
  `urgent` tinyint(1) DEFAULT NULL,
  `approved` tinyint(1) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `creationDateTime` varchar(30) DEFAULT NULL,
  `transferDateTime` varchar(30) DEFAULT NULL,
  `archived` varchar(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=46 ;

--
-- Dumping data for table `ambulancebooking`
--

INSERT INTO `ambulancebooking` (`id`, `patientId`, `ambCompanyId`, `amcCrewId`, `createdBy`, `destination`, `origin`, `cardiac`, `urgent`, `approved`, `cost`, `creationDateTime`, `transferDateTime`, `archived`) VALUES
(41, 1, NULL, NULL, NULL, 3, 1, 1, 1, NULL, NULL, '2015-02-13 15:25:52', '09/05/1992', 'n'),
(42, 2, NULL, NULL, NULL, 2, 1, 1, 0, NULL, NULL, '2015-02-13 15:30:14', '09/05/1992', 'n'),
(43, 3, NULL, NULL, NULL, 4, 1, 0, 1, NULL, NULL, '2015-02-13 15:34:05', '09/05/1992', 'n'),
(44, 4, NULL, NULL, NULL, 3, 4, 0, 0, NULL, NULL, '2015-02-13 17:06:47', '09/05/1992', 'n'),
(45, 1, NULL, NULL, NULL, 1, 3, 0, 1, NULL, NULL, '2015-02-16 17:36:28', '09/05/1992', 'n');

-- --------------------------------------------------------

--
-- Table structure for table `ambulancecompany`
--

CREATE TABLE IF NOT EXISTS `ambulancecompany` (
`id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `cardiac` tinyint(1) DEFAULT NULL,
  `timeStart` varchar(4) DEFAULT NULL,
  `timeEnd` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `ambulancecrew`
--

CREATE TABLE IF NOT EXISTS `ambulancecrew` (
`id` int(11) NOT NULL,
  `ambulanceCompanyId` int(11) DEFAULT NULL,
  `active` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE IF NOT EXISTS `location` (
`id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`id`, `name`) VALUES
(1, 'Bon Secours Cork'),
(2, 'Cork University Hospital'),
(3, 'Mercy Cork'),
(4, 'South Infirmary');

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE IF NOT EXISTS `patient` (
`id` int(11) NOT NULL,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `wardId` int(11) DEFAULT NULL,
  `archived` varchar(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `firstName`, `lastName`, `wardId`, `archived`) VALUES
(1, 'Declan', 'Murphy', 2, 'n'),
(2, 'Mick', 'Power', 2, 'n'),
(3, 'Martin', 'Murtagh', 2, 'n'),
(4, 'John', 'Smyth', 2, 'n');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `firstname`, `lastname`) VALUES
(1, 'admin', 'admin', 1, 'admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `userrole`
--

CREATE TABLE IF NOT EXISTS `userrole` (
`id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `authority` varchar(45) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `userrole`
--

INSERT INTO `userrole` (`id`, `userId`, `authority`) VALUES
(1, 1, 'ROLE_ADMIN'),
(2, 1, 'ROLE_NURSE');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ambulancebooking`
--
ALTER TABLE `ambulancebooking`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ambulancecompany`
--
ALTER TABLE `ambulancecompany`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ambulancecrew`
--
ALTER TABLE `ambulancecrew`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `location`
--
ALTER TABLE `location`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `userrole`
--
ALTER TABLE `userrole`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ambulancebooking`
--
ALTER TABLE `ambulancebooking`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=46;
--
-- AUTO_INCREMENT for table `ambulancecompany`
--
ALTER TABLE `ambulancecompany`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `ambulancecrew`
--
ALTER TABLE `ambulancecrew`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `userrole`
--
ALTER TABLE `userrole`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
