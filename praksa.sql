-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 09, 2014 at 12:53 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `praksa`
--

-- --------------------------------------------------------

--
-- Table structure for table `commision`
--

CREATE TABLE IF NOT EXISTS `commision` (
  `commisionID` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`commisionID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `commision`
--

INSERT INTO `commision` (`commisionID`) VALUES
(3),
(4),
(5),
(6);

-- --------------------------------------------------------

--
-- Table structure for table `commision_member`
--

CREATE TABLE IF NOT EXISTS `commision_member` (
  `commision_memberID` bigint(20) NOT NULL AUTO_INCREMENT,
  `commisionID` bigint(20) NOT NULL,
  `professor` bigint(20) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`commision_memberID`),
  KEY `commisionID` (`commisionID`),
  KEY `professor` (`professor`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `communication`
--

CREATE TABLE IF NOT EXISTS `communication` (
  `communicationID` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee` bigint(20) NOT NULL,
  `student` bigint(20) NOT NULL,
  `locked` tinyint(1) NOT NULL,
  PRIMARY KEY (`communicationID`),
  KEY `employee` (`employee`),
  KEY `student` (`student`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `communication`
--

INSERT INTO `communication` (`communicationID`, `employee`, `student`, `locked`) VALUES
(1, 1, 14, 1),
(2, 1, 17, 0),
(3, 21, 17, 0),
(4, 22, 23, 1),
(5, 21, 24, 0);

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE IF NOT EXISTS `course` (
  `courseID` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`courseID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `course`
--

INSERT INTO `course` (`courseID`, `name`) VALUES
(1, 'ISiT'),
(2, 'OM'),
(3, 'ME');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `departmentID` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `chief` bigint(20) DEFAULT NULL,
  `faculty` bigint(20) NOT NULL,
  PRIMARY KEY (`departmentID`),
  KEY `chief` (`chief`),
  KEY `faculty` (`faculty`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`departmentID`, `name`, `chief`, `faculty`) VALUES
(1, 'Strukture podataka i algoritmi', NULL, 1),
(2, 'Katedra za Menadzment i organizaciju', NULL, 1),
(3, 'Katedra za multimedijalne komunikacije', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `employeeID` bigint(20) NOT NULL,
  `department` bigint(20) NOT NULL,
  `title` bigint(20) NOT NULL,
  PRIMARY KEY (`employeeID`),
  KEY `title` (`title`),
  KEY `department` (`department`),
  KEY `department_2` (`department`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`employeeID`, `department`, `title`) VALUES
(1, 1, 1),
(19, 2, 1),
(20, 2, 1),
(21, 3, 1),
(22, 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `employee_subject`
--

CREATE TABLE IF NOT EXISTS `employee_subject` (
  `employe_subjectID` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee` bigint(20) NOT NULL,
  `subject` bigint(20) NOT NULL,
  PRIMARY KEY (`employe_subjectID`),
  KEY `employee` (`employee`,`subject`),
  KEY `employee_2` (`employee`),
  KEY `subject` (`subject`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `employee_subject`
--

INSERT INTO `employee_subject` (`employe_subjectID`, `employee`, `subject`) VALUES
(1, 1, 1),
(2, 19, 2),
(3, 20, 3),
(4, 21, 5),
(5, 21, 6),
(6, 22, 3);

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE IF NOT EXISTS `faculty` (
  `facultyID` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`facultyID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`facultyID`, `name`) VALUES
(1, 'Fakultet organizacionih nauka');

-- --------------------------------------------------------

--
-- Table structure for table `keywords`
--

CREATE TABLE IF NOT EXISTS `keywords` (
  `keywordsID` bigint(20) NOT NULL AUTO_INCREMENT,
  `work` bigint(20) NOT NULL,
  `keyword` varchar(255) NOT NULL,
  PRIMARY KEY (`keywordsID`),
  KEY `work` (`work`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `keywords`
--

INSERT INTO `keywords` (`keywordsID`, `work`, `keyword`) VALUES
(1, 1, 'goran'),
(2, 1, 'maturski');

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE IF NOT EXISTS `message` (
  `messageID` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` text NOT NULL,
  `fileURI` varchar(255) DEFAULT NULL,
  `isRead` tinyint(1) NOT NULL,
  `sender` bigint(20) NOT NULL,
  `reciever` bigint(20) NOT NULL,
  `communication` bigint(20) NOT NULL,
  PRIMARY KEY (`messageID`),
  KEY `sender` (`sender`),
  KEY `reciever` (`reciever`),
  KEY `communication` (`communication`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`messageID`, `text`, `fileURI`, `isRead`, `sender`, `reciever`, `communication`) VALUES
(1, 'Pozdrav!', NULL, 0, 1, 14, 1),
(2, 'Pozdrav i vama!', NULL, 0, 14, 1, 1),
(3, 'asd', NULL, 0, 1, 14, 1),
(4, 'asd', NULL, 0, 1, 14, 1),
(5, 'asd', NULL, 0, 1, 14, 1),
(6, 'asdfasdfasdf', NULL, 0, 15, 1, 2),
(7, 'Poruka 2\r\n', NULL, 0, 1, 14, 1),
(8, 'asdfadf', NULL, 0, 1, 14, 1),
(9, 'asdfasdf', NULL, 0, 1, 14, 1),
(10, 'asdfasdfasdfasdfasdf asd fasdf', NULL, 0, 1, 14, 1),
(11, 'asdfasdfasdfasdfasdf', NULL, 0, 1, 14, 1),
(12, 'Pozdrava alo bre', NULL, 0, 1, 14, 1),
(13, 'Alo bre\r\n', NULL, 0, 14, 1, 1),
(14, 'asdf', NULL, 0, 14, 1, 1),
(15, 'dsfasdf', NULL, 0, 14, 1, 1),
(16, 'hello', NULL, 0, 1, 14, 1),
(17, 'Opaaaa', 'milanj\\milanj1412347642182.txt', 0, 17, 1, 2),
(18, 'Tema je odbijena.', NULL, 0, 1, 14, 1),
(19, 'Tema je odobrena.', NULL, 0, 1, 14, 1),
(20, 'Tema je odbijena.', NULL, 0, 1, 14, 1),
(21, 'Tema je odobrena.', NULL, 0, 1, 14, 1),
(22, 'Tema je odobrena.', NULL, 0, 1, 17, 2),
(23, 'Poruka 2', 'milanj\\milanj1412349987956.docx', 0, 17, 1, 2),
(24, 'Tema je odobrena.', NULL, 0, 22, 23, 4),
(25, 'Hvala sto ste mi odobrili temu.\r\nU prilogu je probna verzija rada', 'dragan\\dragan1412359255942.docx', 0, 23, 22, 4),
(26, 'Dobar rad, Dragane. Svaka cast. \r\nMozete ga okaciti kao finalnu verziju.', NULL, 0, 22, 23, 4),
(27, 'asdfkljas;ldf', NULL, 0, 22, 23, 4),
(28, ';aklsjd;fklajsdfasdf', NULL, 0, 23, 22, 4),
(29, 'Tema je odbijena.', NULL, 0, 22, 23, 4),
(30, 'Tema je odobrena.', NULL, 0, 22, 23, 4),
(31, 'Tema je odobrena.', NULL, 0, 21, 24, 5);

-- --------------------------------------------------------

--
-- Table structure for table `person`
--

CREATE TABLE IF NOT EXISTS `person` (
  `personID` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `pictureURI` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`personID`),
  UNIQUE KEY `email` (`email`,`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=25 ;

--
-- Dumping data for table `person`
--

INSERT INTO `person` (`personID`, `name`, `surname`, `email`, `username`, `password`, `pictureURI`) VALUES
(1, 'Djordje', 'Pantelic', 'djpantelicdj@gmail.com', 'probros', '123', 'probros.jpg'),
(14, 'Goran', 'Maksimovic', 'visionary1', 'Rango1', '123', 'milosjLav.png'),
(15, 'Sinisa', 'Neskovic', 'sine@gmail.com', 'sine', '123', 'asdasdasd'),
(16, 'Milan', 'Mirkovic', 'milan@gmail.com', 'superadmin', '123', NULL),
(17, 'Milan', 'Jovanovic', 'milance@gmail.com', 'milanj', '123', 'milanjRango1.jpeg'),
(18, 'Stefan', 'Jovanovic', 'stefanj@gmail.com', 'stefanj', '123', 'stefanjsteffeynman.jpg'),
(19, 'Ondrej', 'Jasko', 'jasko@gmail.com', 'ondrejj', '123', ''),
(20, 'Dejan', 'Petrovic', 'dejanp@gmail.com', 'dejanp', '123', ''),
(21, 'Dusan', 'Starcevic', 'starcevic@gmail.com', 'dusans', '123', ''),
(22, 'Nevena', 'Zarkovic', 'nena@gmail.com', 'nena', '123', 'nenaTulips.jpg'),
(23, 'Dragan', 'Markovic', 'dragan@gmail.com', 'dragan', '123', ''),
(24, 'ivan', 'ivan', 'ivan@ivan.ivan', 'ivan', '123', '');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `studentID` bigint(20) NOT NULL,
  `indexNo` varchar(255) NOT NULL,
  `course` bigint(20) NOT NULL,
  `jmbg` varchar(255) NOT NULL,
  PRIMARY KEY (`studentID`),
  UNIQUE KEY `indexNo` (`indexNo`),
  UNIQUE KEY `jmbg` (`jmbg`),
  KEY `studentID` (`studentID`,`course`),
  KEY `course` (`course`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentID`, `indexNo`, `course`, `jmbg`) VALUES
(14, '12321', 1, '2605991772061'),
(17, '122/09', 1, '1234567891023'),
(18, '55/08', 1, '1234567891027'),
(23, '25/05/I', 1, '4589685741256'),
(24, '458', 1, '5896574125896');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

CREATE TABLE IF NOT EXISTS `subject` (
  `subjectID` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `department` bigint(20) NOT NULL,
  `course` bigint(20) NOT NULL,
  PRIMARY KEY (`subjectID`),
  KEY `department` (`department`),
  KEY `course` (`course`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`subjectID`, `name`, `department`, `course`) VALUES
(1, 'Strukture podataka', 1, 1),
(2, 'Osnove organizacije', 2, 3),
(3, 'Menadzment', 2, 3),
(4, 'Zastita racunarskih sistema', 3, 1),
(5, 'Multimedijalne komunikacije', 3, 1),
(6, 'Racunarske mreze', 3, 1);

-- --------------------------------------------------------

--
-- Table structure for table `super_admin`
--

CREATE TABLE IF NOT EXISTS `super_admin` (
  `super_adminID` bigint(20) NOT NULL,
  PRIMARY KEY (`super_adminID`),
  KEY `person` (`super_adminID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `super_admin`
--

INSERT INTO `super_admin` (`super_adminID`) VALUES
(16);

-- --------------------------------------------------------

--
-- Table structure for table `title`
--

CREATE TABLE IF NOT EXISTS `title` (
  `titleID` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`titleID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `title`
--

INSERT INTO `title` (`titleID`, `name`) VALUES
(1, 'Profesor'),
(2, 'Asistent');

-- --------------------------------------------------------

--
-- Table structure for table `work`
--

CREATE TABLE IF NOT EXISTS `work` (
  `workID` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `finalFileURI` varchar(255) DEFAULT NULL,
  `acceptanceDate` datetime DEFAULT NULL,
  `examDate` datetime DEFAULT NULL,
  `student` bigint(20) NOT NULL,
  `mentor` bigint(20) NOT NULL,
  `commision` bigint(20) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  `description` text NOT NULL,
  `subject` bigint(20) NOT NULL,
  PRIMARY KEY (`workID`),
  KEY `commision` (`commision`),
  KEY `mentor` (`mentor`),
  KEY `student` (`student`),
  KEY `subject` (`subject`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `work`
--

INSERT INTO `work` (`workID`, `title`, `finalFileURI`, `acceptanceDate`, `examDate`, `student`, `mentor`, `commision`, `grade`, `status`, `description`, `subject`) VALUES
(1, 'goranov maturski', 'Rango1\\Rango1.txt', '2014-10-03 17:25:14', NULL, 14, 1, NULL, NULL, 1, 'asdasdasdasd', 1),
(2, 'Naslov teme', 'milanj\\milanj.docx', '2014-10-03 17:25:25', NULL, 17, 1, NULL, 7, 1, 'Opis teme', 1),
(5, 'mreze tema 1', NULL, '2014-09-29 17:56:07', NULL, 17, 21, NULL, NULL, -1, 'Opis teme za mreze', 6),
(6, 'Naslov men', NULL, NULL, NULL, 17, 20, NULL, NULL, 0, 'Opis teme', 3),
(7, 'Tema iz menadzmenta', 'dragan\\dragan.docx', '2014-10-04 17:46:10', NULL, 23, 22, 6, NULL, 1, 'Ovo je opis teme iz menadzmenta', 3),
(8, 'O jes', 'ivan\\ivan.docx', '2014-10-04 17:57:55', NULL, 24, 21, NULL, NULL, 1, 'sssss', 5);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `commision_member`
--
ALTER TABLE `commision_member`
  ADD CONSTRAINT `commision_member_ibfk_3` FOREIGN KEY (`commisionID`) REFERENCES `commision` (`commisionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commision_member_ibfk_4` FOREIGN KEY (`professor`) REFERENCES `employee` (`employeeID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `communication`
--
ALTER TABLE `communication`
  ADD CONSTRAINT `communication_ibfk_1` FOREIGN KEY (`employee`) REFERENCES `employee` (`employeeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `communication_ibfk_2` FOREIGN KEY (`student`) REFERENCES `student` (`studentID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `department`
--
ALTER TABLE `department`
  ADD CONSTRAINT `department_ibfk_3` FOREIGN KEY (`chief`) REFERENCES `employee` (`employeeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `department_ibfk_4` FOREIGN KEY (`faculty`) REFERENCES `faculty` (`facultyID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_4` FOREIGN KEY (`employeeID`) REFERENCES `person` (`personID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `employee_ibfk_5` FOREIGN KEY (`department`) REFERENCES `department` (`departmentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `employee_ibfk_7` FOREIGN KEY (`title`) REFERENCES `title` (`titleID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `employee_subject`
--
ALTER TABLE `employee_subject`
  ADD CONSTRAINT `employee_subject_ibfk_1` FOREIGN KEY (`employee`) REFERENCES `employee` (`employeeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `employee_subject_ibfk_2` FOREIGN KEY (`subject`) REFERENCES `subject` (`subjectID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `keywords`
--
ALTER TABLE `keywords`
  ADD CONSTRAINT `keywords_ibfk_1` FOREIGN KEY (`work`) REFERENCES `work` (`workID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`sender`) REFERENCES `person` (`personID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `message_ibfk_2` FOREIGN KEY (`reciever`) REFERENCES `person` (`personID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `message_ibfk_3` FOREIGN KEY (`communication`) REFERENCES `communication` (`communicationID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_5` FOREIGN KEY (`studentID`) REFERENCES `person` (`personID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ibfk_6` FOREIGN KEY (`course`) REFERENCES `course` (`courseID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`department`) REFERENCES `department` (`departmentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `subject_ibfk_2` FOREIGN KEY (`course`) REFERENCES `course` (`courseID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `super_admin`
--
ALTER TABLE `super_admin`
  ADD CONSTRAINT `super_admin_ibfk_1` FOREIGN KEY (`super_adminID`) REFERENCES `person` (`personID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `work`
--
ALTER TABLE `work`
  ADD CONSTRAINT `work_ibfk_4` FOREIGN KEY (`student`) REFERENCES `student` (`studentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `work_ibfk_5` FOREIGN KEY (`mentor`) REFERENCES `employee` (`employeeID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `work_ibfk_6` FOREIGN KEY (`commision`) REFERENCES `commision` (`commisionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `work_ibfk_7` FOREIGN KEY (`subject`) REFERENCES `subject` (`subjectID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
