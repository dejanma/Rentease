-- phpMyAdmin SQL Dump
-- version 4.7.1
-- https://www.phpmyadmin.net/
--
-- Host: sql6.freesqldatabase.com
-- Generation Time: Jul 24, 2023 at 01:36 PM
-- Server version: 5.5.62-0ubuntu0.14.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sql6634208`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_kamar`
--

CREATE TABLE `t_kamar` (
  `id_kamar` int(10) NOT NULL,
  `nokamar` varchar(10) DEFAULT NULL,
  `hargasewa` decimal(10,0) DEFAULT NULL,
  `ketersediaan` tinyint(1) DEFAULT NULL,
  `inputBy` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_kamar`
--

INSERT INTO `t_kamar` (`id_kamar`, `nokamar`, `hargasewa`, `ketersediaan`, `inputBy`) VALUES
(17, '1', '9000000', 1, 'dede'),
(18, '2', '750000', 0, 'dede'),
(20, '1', '7000000', 0, 'najwan');

-- --------------------------------------------------------

--
-- Table structure for table `t_penghuni`
--

CREATE TABLE `t_penghuni` (
  `id_penghuni` int(10) NOT NULL,
  `nama` varchar(30) DEFAULT NULL,
  `nohp` varchar(15) DEFAULT NULL,
  `jeniskelamin` varchar(20) DEFAULT NULL,
  `inputBy` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_penghuni`
--

INSERT INTO `t_penghuni` (`id_penghuni`, `nama`, `nohp`, `jeniskelamin`, `inputBy`) VALUES
(11, 'deden', '321312', 'Laki-Laki', 'dede'),
(12, 'ryuna', '12312312213', 'Perempuan', 'dede'),
(13, 'sdfsd', '213321', 'Laki-Laki', 'najwan');

-- --------------------------------------------------------

--
-- Table structure for table `t_penyewaan`
--

CREATE TABLE `t_penyewaan` (
  `id_penyewaan` int(10) NOT NULL,
  `id_penghuni` int(10) DEFAULT NULL,
  `id_kamar` int(11) DEFAULT NULL,
  `durasi` int(10) DEFAULT NULL,
  `tgl_mulai` date DEFAULT NULL,
  `tgl_selesai` date DEFAULT NULL,
  `totalbiaya` decimal(10,0) DEFAULT NULL,
  `tgl_transaksi` date DEFAULT NULL,
  `inputBy` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_penyewaan`
--

INSERT INTO `t_penyewaan` (`id_penyewaan`, `id_penghuni`, `id_kamar`, `durasi`, `tgl_mulai`, `tgl_selesai`, `totalbiaya`, `tgl_transaksi`, `inputBy`) VALUES
(7, NULL, 17, 9, '2023-08-23', '2024-05-23', '7200000', '2023-07-23', 'dede'),
(8, NULL, 18, 10, '2023-07-23', '2024-05-23', '7500000', '2023-06-17', 'dede'),
(9, 12, 18, 24, '2023-09-09', '2025-09-09', '18000000', '2023-07-23', 'dede'),
(10, 13, 20, 9, '2023-07-23', '2024-04-23', '63000000', '2023-07-23', 'najwan');

-- --------------------------------------------------------

--
-- Table structure for table `t_users`
--

CREATE TABLE `t_users` (
  `username` varchar(10) NOT NULL,
  `password` char(60) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `t_users`
--

INSERT INTO `t_users` (`username`, `password`, `email`) VALUES
('admin', 'admin', 'admin'),
('dede', 'admin', 'dede@gmail.com'),
('ilham', 'ilham', 'ilham@gmail.com'),
('najwan', 'najwan', 'najwan@gmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_kamar`
--
ALTER TABLE `t_kamar`
  ADD PRIMARY KEY (`id_kamar`);

--
-- Indexes for table `t_penghuni`
--
ALTER TABLE `t_penghuni`
  ADD PRIMARY KEY (`id_penghuni`);

--
-- Indexes for table `t_penyewaan`
--
ALTER TABLE `t_penyewaan`
  ADD PRIMARY KEY (`id_penyewaan`),
  ADD KEY `id_penghuni` (`id_penghuni`),
  ADD KEY `id_kamar` (`id_kamar`),
  ADD KEY `username` (`inputBy`);

--
-- Indexes for table `t_users`
--
ALTER TABLE `t_users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_kamar`
--
ALTER TABLE `t_kamar`
  MODIFY `id_kamar` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `t_penghuni`
--
ALTER TABLE `t_penghuni`
  MODIFY `id_penghuni` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `t_penyewaan`
--
ALTER TABLE `t_penyewaan`
  MODIFY `id_penyewaan` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_penyewaan`
--
ALTER TABLE `t_penyewaan`
  ADD CONSTRAINT `t_penyewaan_ibfk_1` FOREIGN KEY (`id_penghuni`) REFERENCES `t_penghuni` (`id_penghuni`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `t_penyewaan_ibfk_2` FOREIGN KEY (`id_kamar`) REFERENCES `t_kamar` (`id_kamar`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
