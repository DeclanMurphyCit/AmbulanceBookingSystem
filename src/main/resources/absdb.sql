-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 15, 2015 at 10:08 PM
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
  `ambCrewId` int(11) DEFAULT NULL,
  `createdBy` int(11) DEFAULT NULL,
  `destination` int(11) DEFAULT NULL,
  `origin` int(11) DEFAULT NULL,
  `cardiac` tinyint(1) DEFAULT NULL,
  `urgent` tinyint(1) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `approved` tinyint(1) DEFAULT NULL,
  `approvedBy` int(11) NOT NULL,
  `cost` double DEFAULT NULL,
  `creationDateTime` varchar(30) DEFAULT NULL,
  `transferDateTime` varchar(30) DEFAULT NULL,
  `archived` varchar(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=227 ;

--
-- Dumping data for table `ambulancebooking`
--

INSERT INTO `ambulancebooking` (`id`, `patientId`, `ambCompanyId`, `ambCrewId`, `createdBy`, `destination`, `origin`, `cardiac`, `urgent`, `status`, `approved`, `approvedBy`, `cost`, `creationDateTime`, `transferDateTime`, `archived`) VALUES
(226, 5, 1, 2, 1, 2, 1, 0, 0, 2, 1, 1, NULL, '10-04-2015 18:35', '10-04-2015 18:35', 'n');

-- --------------------------------------------------------

--
-- Table structure for table `ambulancecompany`
--

CREATE TABLE IF NOT EXISTS `ambulancecompany` (
`id` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `cost` double DEFAULT NULL,
  `cardiac` tinyint(1) DEFAULT NULL,
  `timeActive` varchar(4) DEFAULT NULL,
  `timeInactive` varchar(4) DEFAULT NULL,
  `archived` varchar(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `ambulancecompany`
--

INSERT INTO `ambulancecompany` (`id`, `userId`, `name`, `cost`, `cardiac`, `timeActive`, `timeInactive`, `archived`) VALUES
(1, 3, 'Cork City Ambulances', 1600, 1, '0800', '2200', 'n'),
(2, 4, 'St. John Ambulance Cork', 1800, 0, '0000', '0000', 'n');

-- --------------------------------------------------------

--
-- Table structure for table `ambulancecrew`
--

CREATE TABLE IF NOT EXISTS `ambulancecrew` (
`id` int(11) NOT NULL,
  `ambulanceCompanyId` int(11) DEFAULT NULL,
  `userId` int(11) NOT NULL,
  `crewIdentifier` varchar(20) NOT NULL,
  `active` tinyint(1) DEFAULT NULL,
  `archived` varchar(1) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `ambulancecrew`
--

INSERT INTO `ambulancecrew` (`id`, `ambulanceCompanyId`, `userId`, `crewIdentifier`, `active`, `archived`) VALUES
(1, 1, 6, '05-C-15123', 1, 'n'),
(2, 1, 7, '98-C-312', 1, 'n'),
(3, 2, 8, '14-C-4253', 1, 'n'),
(4, 2, 9, '151-C-642', 1, 'n');

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
(2, 'CUH'),
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`id`, `firstName`, `lastName`, `wardId`, `archived`) VALUES
(1, 'Declan', 'Murphy', 2, 'n'),
(2, 'Mick', 'Power', 2, 'n'),
(3, 'Martin', 'Murtagh', 2, 'n'),
(4, 'John', 'Smyth', 2, 'n'),
(5, 'Molly', 'Marner', 2, 'n'),
(6, 'Jeff', 'McLoughlin', 2, 'n'),
(7, 'Paddy', 'O'' Brien', 1, 'n'),
(8, 'Peter', 'Halligan', 1, 'n'),
(9, 'Claire', 'Kelleher', 1, 'n'),
(10, 'Cormac', 'Molloy', 3, 'n'),
(11, 'Dan', 'Reddie', 3, 'n'),
(12, 'Cliff', 'Farrell', 3, 'n'),
(13, 'Jeff', 'Mills', 4, 'n'),
(14, 'Robert', 'Hood', 4, 'n'),
(15, 'Ben', 'Klock', 4, 'n'),
(16, 'Karl', '''O Connor', 4, 'n'),
(17, 'James', 'Ruskin', 4, 'n'),
(18, 'Anthony', 'Child', 4, 'n');

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `enabled`, `firstname`, `lastname`) VALUES
(1, 'admin', 'admin', 1, 'Admin', 'Tester'),
(2, 'nurse', 'nurse', 1, 'Nurse', 'Tester'),
(3, 'cca', 'cca', 1, 'Cork City', 'Ambulances'),
(4, 'stjohn', 'stjohn', 1, 'St. John', 'Ambulances Cork'),
(5, 'adon', 'adon', 1, 'Adon', 'Tester'),
(6, 'ccacrew1', 'ccacrew1', 1, 'ccacrew1', 'ccacrew1'),
(7, 'ccacrew2', 'ccacrew2', 1, 'ccacrew2', 'ccacrew2'),
(8, 'sjcrew1', 'sjcrew1', 1, 'sjcrew1', 'sjcrew1'),
(9, 'sjcrew2', 'sjcrew2', 1, 'sjcrew2', 'sjcrew2');

-- --------------------------------------------------------

--
-- Table structure for table `userrole`
--

CREATE TABLE IF NOT EXISTS `userrole` (
`id` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `authority` varchar(45) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `userrole`
--

INSERT INTO `userrole` (`id`, `userId`, `authority`) VALUES
(1, 1, 'ROLE_ADMIN'),
(2, 2, 'ROLE_NURSE'),
(3, 3, 'ROLE_AMB_COMP'),
(4, 4, 'ROLE_AMB_COMP'),
(5, 5, 'ROLE_ADON'),
(6, 6, 'ROLE_AMB_CREW'),
(7, 7, 'ROLE_AMB_CREW'),
(8, 8, 'ROLE_AMB_CREW'),
(9, 9, 'ROLE_AMB_CREW');

-- --------------------------------------------------------

--
-- Table structure for table `ward`
--

CREATE TABLE IF NOT EXISTS `ward` (
`id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `ward`
--

INSERT INTO `ward` (`id`, `name`) VALUES
(1, 'St Mary''s'),
(2, 'St Joseph''s'),
(3, 'St Declan''s'),
(4, 'St Kevin''s');

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
-- Indexes for table `ward`
--
ALTER TABLE `ward`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ambulancebooking`
--
ALTER TABLE `ambulancebooking`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=227;
--
-- AUTO_INCREMENT for table `ambulancecompany`
--
ALTER TABLE `ambulancecompany`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `ambulancecrew`
--
ALTER TABLE `ambulancecrew`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `location`
--
ALTER TABLE `location`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `userrole`
--
ALTER TABLE `userrole`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `ward`
--
ALTER TABLE `ward`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
