-- phpMyAdmin SQL Dump
-- version 4.9.5deb2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Waktu pembuatan: 09 Jan 2022 pada 17.41
-- Versi server: 10.3.31-MariaDB-0ubuntu0.20.04.1
-- Versi PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flaskdb`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `dosen`
--

CREATE TABLE `dosen` (
  `id_dosen` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nama_dosen` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `dosen`
--

INSERT INTO `dosen` (`id_dosen`, `username`, `password`, `nama_dosen`) VALUES
(1, 'ardi', '123', 'Ardi Susanto, S.Kom., M.Cs.'),
(2, 'fikri', '123', 'M. Fikri Hidayattullah, S.T., M.Kom.');

-- --------------------------------------------------------

--
-- Struktur dari tabel `hasil_deteksi`
--

CREATE TABLE `hasil_deteksi` (
  `id_deteksi` int(4) NOT NULL,
  `id_perkuliahan` int(4) NOT NULL,
  `presentasi_bersemangat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `hasil_deteksi`
--

INSERT INTO `hasil_deteksi` (`id_deteksi`, `id_perkuliahan`, `presentasi_bersemangat`) VALUES
(32, 13, '100%'),
(33, 13, '0%'),
(34, 13, '0%'),
(35, 10, '0%'),
(36, 10, '0%'),
(37, 10, '0%'),
(38, 10, '100%'),
(39, 10, '0%'),
(40, 10, '0%'),
(41, 10, '100%'),
(42, 10, '100%'),
(43, 10, '100%'),
(44, 10, '0%'),
(45, 10, '100%'),
(46, 10, '0%'),
(47, 12, '100%'),
(49, 12, '100%'),
(50, 12, '50%'),
(51, 14, '0%'),
(52, 14, '50%'),
(53, 14, '50%'),
(54, 14, '50%'),
(55, 14, '50%'),
(56, 14, '75%'),
(57, 14, '50%'),
(58, 14, '50%'),
(59, 14, '33%'),
(60, 14, '50%'),
(61, 14, '40%'),
(63, 15, '0%'),
(64, 15, '0%'),
(65, 15, '0%'),
(66, 15, '100%'),
(67, 15, '50%');

-- --------------------------------------------------------

--
-- Struktur dari tabel `perkuliahan`
--

CREATE TABLE `perkuliahan` (
  `id_perkuliahan` int(4) NOT NULL,
  `nama_dosen` varchar(50) NOT NULL,
  `mata_kuliah` varchar(50) NOT NULL,
  `kelas` varchar(4) NOT NULL,
  `tanggal` date NOT NULL,
  `waktu` time NOT NULL,
  `ip_address` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `perkuliahan`
--

INSERT INTO `perkuliahan` (`id_perkuliahan`, `nama_dosen`, `mata_kuliah`, `kelas`, `tanggal`, `waktu`, `ip_address`) VALUES
(10, 'M. Fikri Hidayattullah, S.T., M.Kom.', 'MACHINE LEARNING', '5 C', '2021-12-26', '15:08:00', '0'),
(12, 'Sharfina Febbi Handayani, M.Kom.', 'FRAMEWORK PROGRAMMING', '5 C', '2021-12-26', '16:34:00', '0'),
(13, 'Ardi Susanto, S.Kom., M.Cs.', 'PENGUJIAN PERANGKAT LUNAK', '5 B', '2021-12-26', '16:44:00', '0'),
(14, 'Ardi Susanto, S.Kom., M.Cs.', 'PENGUJIAN PERANGKAT LUNAK', '5 B', '2021-12-26', '21:52:00', '192.168.1.19'),
(15, 'Dwi Intan Af\'idah, M.Kom.', 'PEMROGRAMAN SISTEM CERDAS 1', '5 C', '2021-12-26', '19:41:00', '192.168.1.14');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id`, `name`, `email`, `password`) VALUES
(5, 'mifta', 'mifta@gmail.com', '$2b$12$n8DuSWEEE8Wccqe0qO57V.1VVu0VAZjcdw/ZheiZd9oihJd06dBsK'),
(6, 'kiprat', 'kiprat@gmail.com', '$2b$12$wTNKi3gZmK0LMI4.KQjh.uAvQ0u6Q1biBhfW.8nB9s7FkjqgVy5nG'),
(11, 'Afan Risandi', 'risandiafan@gmail.com', '$2b$12$/JmR5W9dw45yFaYQYuDgQumyj92CGE7U4tcVkxLnnD/bH7ufLMeh6');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `dosen`
--
ALTER TABLE `dosen`
  ADD PRIMARY KEY (`id_dosen`);

--
-- Indeks untuk tabel `hasil_deteksi`
--
ALTER TABLE `hasil_deteksi`
  ADD PRIMARY KEY (`id_deteksi`);

--
-- Indeks untuk tabel `perkuliahan`
--
ALTER TABLE `perkuliahan`
  ADD PRIMARY KEY (`id_perkuliahan`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `dosen`
--
ALTER TABLE `dosen`
  MODIFY `id_dosen` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `hasil_deteksi`
--
ALTER TABLE `hasil_deteksi`
  MODIFY `id_deteksi` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT untuk tabel `perkuliahan`
--
ALTER TABLE `perkuliahan`
  MODIFY `id_perkuliahan` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
