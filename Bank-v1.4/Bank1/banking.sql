-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 03, 2019 at 02:42 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.3.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banking`
--

-- --------------------------------------------------------

--
-- Table structure for table `accountib`
--

CREATE TABLE `accountib` (
  `ID` bigint(20) NOT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `accountib`
--

INSERT INTO `accountib` (`ID`, `Username`, `Password`, `Status`) VALUES
(1, '12525325201', 'a', 1),
(2, '23525230252', 'a1231313123ad', 1);

-- --------------------------------------------------------

--
-- Table structure for table `banknumber`
--

CREATE TABLE `banknumber` (
  `Id` bigint(20) NOT NULL,
  `CustomerId` bigint(20) NOT NULL,
  `NameBN` varchar(45) DEFAULT NULL,
  `CurrentBalance` double NOT NULL,
  `AvailableBalance` double NOT NULL,
  `OpenDateAccount` varchar(255) DEFAULT NULL,
  `LastDealDate` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `banknumber`
--

INSERT INTO `banknumber` (`Id`, `CustomerId`, `NameBN`, `CurrentBalance`, `AvailableBalance`, `OpenDateAccount`, `LastDealDate`) VALUES
(1, 1, '12525325201', 10025393510.406282, 10025393510.406282, '2018-01-01 07:44:47', NULL),
(2, 1, '12525325202', 64453297.77775, 64453297.77775, '2018-03-01 07:44:47', NULL),
(3, 2, '23525230252', 1474245.5199999996, 1474245.5199999996, '2018-03-01 07:44:47', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `MemberId` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`MemberId`) VALUES
(1),
(2);

-- --------------------------------------------------------

--
-- Table structure for table `deal`
--

CREATE TABLE `deal` (
  `ID` bigint(20) NOT NULL,
  `EmployeeId` bigint(20) NOT NULL,
  `PassbookId` bigint(20) NOT NULL,
  `TypePassbookId` bigint(20) DEFAULT NULL,
  `InterestRateId` bigint(20) DEFAULT NULL,
  `BankNumberId` bigint(20) NOT NULL,
  `InterestPaymentId` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `deal`
--

INSERT INTO `deal` (`ID`, `EmployeeId`, `PassbookId`, `TypePassbookId`, `InterestRateId`, `BankNumberId`, `InterestPaymentId`) VALUES
(1, 1, 1, 1, 3, 2, 1),
(2, 1, 2, 1, 9, 2, 1),
(3, 1, 3, 1, 3, 2, 1),
(4, 1, 4, 1, 6, 2, 1),
(5, 1, 5, 1, 9, 1, 1),
(6, 1, 6, 1, 3, 3, 1),
(7, 1, 7, 1, 6, 2, 1),
(8, 1, 8, 3, 28, 2, 1),
(9, 1, 9, 1, 3, 3, 1),
(10, 1, 5, 1, 9, 1, 2),
(11, 1, 8, 1, 28, 2, 2),
(12, 1, 7, 1, 6, 2, 2),
(13, 1, 7, 1, 6, 2, 2),
(14, 1, 4, 1, 6, 2, 2),
(15, 1, 3, 1, 3, 2, 2),
(16, 1, 10, 1, 3, 1, 1),
(17, 1, 11, 1, 12, 1, 1),
(18, 1, 12, 1, 3, 1, 1),
(19, 1, 13, 1, 3, 1, 1),
(20, 1, 11, 1, 12, 1, 2),
(21, 1, 12, 1, 3, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `MemberId` bigint(20) NOT NULL,
  `Salary` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `interestpayment`
--

CREATE TABLE `interestpayment` (
  `ID` bigint(20) NOT NULL,
  `TypeIP` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `interestpayment`
--

INSERT INTO `interestpayment` (`ID`, `TypeIP`) VALUES
(1, 'Nhap goc'),
(2, 'Chuyen khoan');

-- --------------------------------------------------------

--
-- Table structure for table `interestrate`
--

CREATE TABLE `interestrate` (
  `Id` bigint(20) NOT NULL,
  `TypePassbookId` bigint(20) NOT NULL,
  `Period` int(11) DEFAULT NULL,
  `money` varchar(245) DEFAULT NULL,
  `interestrate` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `interestrate`
--

INSERT INTO `interestrate` (`Id`, `TypePassbookId`, `Period`, `money`, `interestrate`) VALUES
(1, 1, 1, '< 1 ty', 5),
(2, 1, 1, '1 ty - 3 ty', 5.1),
(3, 1, 1, '> 3 ty', 5.2),
(4, 1, 3, '< 1 ty', 5.1),
(5, 1, 3, '1 ty - 3 ty', 5.2),
(6, 1, 3, '> 3 ty', 5.3),
(7, 1, 6, '< 1 ty', 6),
(8, 1, 6, '1 ty - 3 ty', 6.1),
(9, 1, 6, '> 3 ty', 6.2),
(10, 1, 9, '< 1 ty', 6.1),
(11, 1, 9, '1 ty - 3 ty', 6.2),
(12, 1, 9, '> 3 ty', 6.3),
(13, 1, 12, '< 1 ty', 6.7),
(14, 1, 12, '1 ty - 3 ty', 6.8),
(15, 1, 12, '> 3 ty', 6.9),
(16, 1, 24, '< 1 ty', 6.7),
(17, 1, 24, '1 ty - 3 ty', 6.8),
(18, 1, 24, '> 3 ty', 6.9),
(19, 1, 36, '< 1 ty', 6.7),
(20, 1, 36, '1 ty - 3 ty', 6.8),
(21, 1, 36, '> 3 ty', 6.9),
(22, 2, 0, '> 3 trieu', 0.3),
(23, 3, 12, '< 100 trieu', 5.8),
(24, 3, 12, '100-200trieu', 5.9),
(25, 3, 12, '200-400tr', 6),
(26, 3, 12, '400-600tr', 6.2),
(27, 3, 12, '600-800tr', 6.4),
(28, 3, 12, '> 800', 6.6),
(29, 4, 1, '> 3 trieu', 5.4),
(30, 4, 3, '> 3 trieu', 5.5),
(31, 4, 6, '> 3 trieu', 6.5),
(32, 4, 9, '> 3 trieu', 6.5),
(33, 4, 12, '> 3 trieu', 7.1),
(34, 4, 24, '> 3 trieu', 7.1),
(35, 4, 36, '> 3 trieu', 7.1),
(36, 5, 1, '> 3 trieu', 4.5),
(37, 5, 3, '> 3 trieu', 4.5),
(38, 5, 6, '> 3 trieu', 5.3),
(39, 5, 9, '> 3 trieu', 5.3),
(40, 5, 12, '> 3 trieu', 5.3),
(41, 5, 24, '> 3 trieu', 5.3),
(42, 5, 36, '> 3 trieu', 5.3),
(43, 6, 1, '> 3 trieu', 4.7),
(44, 6, 3, '> 3 trieu', 4.7),
(45, 6, 6, '> 3 trieu', 5.5),
(46, 6, 9, '> 3 trieu', 5.5),
(47, 6, 12, '> 3 trieu', 5.5),
(48, 6, 24, '> 3 trieu', 5.5),
(49, 6, 36, '> 3 trieu', 5.5),
(50, 7, 12, '25-50tr', 2.23),
(51, 7, 12, '50-200tr', 4.46),
(52, 7, 6, '200-300tr', 5.08),
(53, 7, 9, '200-300tr', 5.35),
(54, 7, 12, '200-300tr', 6.14),
(55, 7, 3, '> 300tr', 3.61),
(56, 7, 6, '> 300tr', 5.45),
(57, 7, 9, '> 300tr', 5.6),
(58, 7, 12, '> 300tr', 6.33);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `Id` bigint(20) NOT NULL,
  `AccountIBID` bigint(20) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Birthday` varchar(255) DEFAULT NULL,
  `CMT` varchar(45) DEFAULT NULL,
  `Address` varchar(45) DEFAULT NULL,
  `Phone` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`Id`, `AccountIBID`, `Name`, `Email`, `Birthday`, `CMT`, `Address`, `Phone`) VALUES
(1, 1, 'Bui Trung Kien', 'kientrungbui1210@gmail.com', '1997-01-01', '163332523', 'Ha Noi', '0981532251'),
(2, 2, 'NHL', 'kun9xx9@gmail.com', '1997-01-01', '252526652', 'Ha Noi', '555222255');

-- --------------------------------------------------------

--
-- Table structure for table `passbook`
--

CREATE TABLE `passbook` (
  `Id` bigint(20) NOT NULL,
  `TypePassbookId` bigint(20) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `OpenDateSB` varchar(255) DEFAULT NULL,
  `DateExprice` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `passbook`
--

INSERT INTO `passbook` (`Id`, `TypePassbookId`, `Name`, `OpenDateSB`, `DateExprice`, `status`) VALUES
(1, 1, 'STK1.12525325202', '2019-03-21 02:10:25', '2019-04-21 02:10:25', 1),
(2, 1, 'STK2.12525325202', '2019-03-21 02:12:46', '2019-09-21 02:12:46', 1),
(3, 1, 'STK3.12525325202', '2019-03-21 12:19:08', '2019-04-21 12:19:08', 0),
(4, 1, 'STK4.12525325202', '2019-03-21 12:22:04', '2019-06-21 12:22:04', 0),
(5, 1, 'STK5.12525325201', '2019-03-21 12:42:38', '2019-09-21 12:42:38', 0),
(6, 1, 'STK6.23525230252', '2019-03-21 06:36:16', '2019-04-21 06:36:16', 1),
(7, 1, 'STK7.12525325202', '2019-03-21 09:23:46', '2019-06-21 09:23:46', 0),
(8, 3, 'STK8.12525325202', '2019-03-21 09:24:28', '2020-03-21 09:24:28', 0),
(9, 1, 'STK9.23525230252', '2019-03-21 09:26:37', '2019-04-21 09:26:37', 1),
(10, 1, 'STK10.12525325201', '2019-04-01 07:01:07', '2019-05-01 07:01:07', 1),
(11, 1, 'STK11.12525325201', '2019-04-01 07:03:59', '2020-01-01 07:03:59', 0),
(12, 1, 'STK12.12525325201', '2019-04-01 07:26:45', '2019-05-01 07:26:45', 0),
(13, 1, 'STK13.12525325201', '2019-04-03 07:17:05', '2019-05-03 07:17:05', 1);

-- --------------------------------------------------------

--
-- Table structure for table `savemoney`
--

CREATE TABLE `savemoney` (
  `DealID` bigint(20) NOT NULL,
  `Deposits` double NOT NULL,
  `DateDeposits` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `savemoney`
--

INSERT INTO `savemoney` (`DealID`, `Deposits`, `DateDeposits`) VALUES
(1, 1000000, '2019-03-21 02:10:25'),
(2, 100000, '2019-03-21 02:12:46'),
(3, 11111111, '2019-03-21 12:19:08'),
(4, 12321321, '2019-03-21 12:22:04'),
(5, 1225222, '2019-03-21 12:42:38'),
(6, 1000000, '2019-03-21 06:36:16'),
(7, 123213123, '2019-03-21 09:23:46'),
(8, 12323321, '2019-03-21 09:24:28'),
(9, 10000000, '2019-03-21 09:26:37'),
(16, 12312312, '2019-04-01 07:01:07'),
(17, 3123123, '2019-04-01 07:03:59'),
(18, 213213213, '2019-04-01 07:26:45'),
(19, 99965369, '2019-04-03 07:17:05');

-- --------------------------------------------------------

--
-- Table structure for table `typepassbook`
--

CREATE TABLE `typepassbook` (
  `Id` bigint(20) NOT NULL,
  `Name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `typepassbook`
--

INSERT INTO `typepassbook` (`Id`, `Name`) VALUES
(1, 'Tiet kiem co ky han'),
(2, 'Tiet kiem khong ky han'),
(3, 'Superkid'),
(4, 'Phat loc Online'),
(5, 'Tai Tam Tai Hien co bao hiem'),
(6, 'Tai Tam Tai Hien khong co bao hiem'),
(7, 'Tiet kiem An Loc');

-- --------------------------------------------------------

--
-- Table structure for table `withdraw`
--

CREATE TABLE `withdraw` (
  `DealID` bigint(20) NOT NULL,
  `DateWithdraw` varchar(255) DEFAULT NULL,
  `InterestReceive` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `withdraw`
--

INSERT INTO `withdraw` (`DealID`, `DateWithdraw`, `InterestReceive`) VALUES
(10, '2019-22-03 22:36:04', 306),
(11, '2019-22-03 22:36:53', 3081),
(12, '2019-22-03 22:38:45', 30803),
(13, '2019-22-03 22:38:49', 30803),
(14, '2019-22-03 22:43:19', 3080),
(15, '2019-22-03 22:51:40', 2778),
(20, '2019-03-04 07:20:43', 1562),
(21, '2019-03-04 07:26:55', 128820393);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accountib`
--
ALTER TABLE `accountib`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `banknumber`
--
ALTER TABLE `banknumber`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`MemberId`);

--
-- Indexes for table `deal`
--
ALTER TABLE `deal`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`MemberId`);

--
-- Indexes for table `interestpayment`
--
ALTER TABLE `interestpayment`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `interestrate`
--
ALTER TABLE `interestrate`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `passbook`
--
ALTER TABLE `passbook`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `savemoney`
--
ALTER TABLE `savemoney`
  ADD PRIMARY KEY (`DealID`);

--
-- Indexes for table `typepassbook`
--
ALTER TABLE `typepassbook`
  ADD PRIMARY KEY (`Id`);

--
-- Indexes for table `withdraw`
--
ALTER TABLE `withdraw`
  ADD PRIMARY KEY (`DealID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `banknumber`
--
ALTER TABLE `banknumber`
  MODIFY `Id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `deal`
--
ALTER TABLE `deal`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `interestpayment`
--
ALTER TABLE `interestpayment`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `interestrate`
--
ALTER TABLE `interestrate`
  MODIFY `Id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=59;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `Id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `passbook`
--
ALTER TABLE `passbook`
  MODIFY `Id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `typepassbook`
--
ALTER TABLE `typepassbook`
  MODIFY `Id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
