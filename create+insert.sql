-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 10, 2024 at 11:04 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `auto_pets`
--

-- --------------------------------------------------------

--
-- Table structure for table `fruit`
--

CREATE TABLE `fruit` (
  `id` int(11) NOT NULL,
  `pet_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `match`
--

CREATE TABLE `match` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `team_name` varchar(20) NOT NULL,
  `win` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `match`
--

INSERT INTO `match` (`id`, `user_id`, `team_name`, `win`) VALUES
(2, 1, 'gemink', 0),
(4, 1, 'geminl', 0),
(5, 1, 'gemink', 0),
(6, 1, 'gemink', 0),
(7, 1, 'gemink', 0),
(8, 1, 'gemink', 0),
(9, 1, 'gemink', 0),
(10, 1, 'gemink', 0),
(11, 1, 'gemini', 0),
(12, 1, 'gemink', 0),
(13, 1, 'gemink', 10),
(14, 1, 'gemink', 0),
(15, 1, 'gemink', 0),
(16, 1, 'gemink', 0),
(17, 2, 'jacksonTeam', 0),
(18, 2, 'jacksonteam', 0),
(19, 2, 'jackson', 1),
(20, 1, 'gemink', 0),
(21, 1, 'gemink', 0),
(22, 1, 'gemink', 0),
(23, 1, 'gemink', 0),
(24, 1, 'gemink', 0),
(25, 1, 'gemink', 0),
(26, 1, 'gemink', 0),
(27, 1, 'gemink', 0),
(28, 1, 'gemink', 0),
(29, 1, 'gemink', 0),
(30, 1, 'gemink', 0),
(31, 1, 'gemink', 0),
(32, 1, 'gemink', 0),
(33, 2, 'vk team', 2),
(34, 2, 'vk team', 3),
(36, 1, 'asdfg', 0),
(37, 3, 'Keren', 0),
(39, 4, 'jascon', 0),
(40, 4, 'jason', 0),
(42, 8, 'wymbro', 0),
(43, 4, 'jason team', 0),
(44, 7, 'artaw gemink', 0),
(45, 7, 'kekuatan', 0),
(46, 7, 'test merge', 0),
(47, 7, 'tes arena', 0),
(48, 7, 'test merge', 0),
(49, 7, 'test mergee', 0),
(50, 6, 'test battle', 0),
(51, 2, 'jackson', 2),
(52, 6, 'test replay', 0),
(53, 6, 'test replay', 0),
(54, 6, 'test replaty', 1),
(55, 2, 'tetete', 0);

-- --------------------------------------------------------

--
-- Table structure for table `pet`
--

CREATE TABLE `pet` (
  `id` int(11) NOT NULL,
  `round_id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `atk` int(11) NOT NULL,
  `hp` int(11) NOT NULL,
  `lv` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `pos` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pet`
--

INSERT INTO `pet` (`id`, `round_id`, `name`, `atk`, `hp`, `lv`, `exp`, `pos`) VALUES
(1, 1, 'HORSE', 2, 1, 1, 0, 0),
(2, 1, 'HORSE', 2, 1, 1, 0, 1),
(3, 1, 'HORSE', 2, 1, 1, 0, 2),
(4, 2, 'HORSE', 3, 2, 1, 1, 0),
(5, 2, 'HORSE', 2, 1, 1, 0, 1),
(6, 2, 'HORSE', 2, 1, 1, 0, 2),
(7, 2, 'CRICKET', 1, 2, 1, 0, 3),
(8, 2, 'ANT', 2, 2, 1, 0, 4),
(9, 5, 'HORSE', 3, 2, 1, 1, 0),
(10, 5, 'PIG', 4, 1, 1, 0, 4),
(11, 6, 'HORSE', 3, 2, 1, 1, 0),
(12, 6, 'HORSE', 2, 1, 1, 0, 1),
(13, 6, 'FISH', 2, 3, 1, 0, 2),
(14, 6, 'PIG', 4, 1, 1, 0, 3),
(15, 6, 'PIG', 4, 1, 1, 0, 4),
(16, 8, 'MOSQUITO', 2, 2, 1, 0, 2),
(17, 8, 'MOSQUITO', 2, 2, 1, 0, 3),
(18, 8, 'MOSQUITO', 2, 2, 1, 0, 4),
(19, 10, 'HORSE', 2, 1, 1, 0, 0),
(20, 10, 'BEAVER', 3, 2, 1, 0, 2),
(21, 10, 'ANT', 2, 2, 1, 0, 4),
(22, 11, 'HORSE', 2, 2, 1, 0, 0),
(23, 11, 'OTTER', 1, 3, 1, 0, 1),
(24, 11, 'BEAVER', 3, 2, 1, 0, 2),
(25, 11, 'PIG', 5, 2, 1, 1, 3),
(26, 11, 'ANT', 2, 2, 1, 0, 4),
(27, 12, 'HORSE', 2, 2, 1, 0, 0),
(28, 12, 'SWAN', 1, 2, 1, 0, 1),
(29, 12, 'PEACOCK', 2, 5, 1, 0, 2),
(30, 12, 'PIG', 7, 3, 2, 0, 3),
(31, 12, 'ANT', 3, 2, 1, 0, 4),
(32, 14, 'HORSE', 2, 1, 1, 0, 0),
(33, 14, 'BEAVER', 3, 2, 1, 0, 2),
(34, 14, 'ANT', 2, 2, 1, 0, 4),
(35, 15, 'HORSE', 2, 1, 1, 0, 0),
(36, 15, 'HORSE', 2, 1, 1, 0, 1),
(37, 15, 'BEAVER', 4, 3, 1, 1, 2),
(38, 15, 'PIG', 4, 1, 1, 0, 3),
(39, 15, 'ANT', 2, 2, 1, 0, 4),
(40, 16, 'HORSE', 3, 2, 1, 1, 0),
(41, 16, 'PEACOCK', 3, 6, 1, 1, 1),
(42, 16, 'BEAVER', 4, 3, 1, 1, 2),
(43, 16, 'PIG', 4, 1, 1, 0, 3),
(44, 16, 'ANT', 3, 3, 1, 1, 4),
(45, 17, 'HORSE', 3, 2, 1, 1, 0),
(46, 17, 'PEACOCK', 3, 6, 1, 1, 1),
(47, 17, 'BEAVER', 5, 4, 2, 0, 2),
(48, 17, 'PIG', 5, 2, 1, 1, 3),
(49, 17, 'ANT', 3, 3, 1, 1, 4),
(50, 18, 'HORSE', 5, 2, 1, 1, 0),
(51, 18, 'PEACOCK', 3, 6, 1, 1, 1),
(52, 18, 'ELEPHANT', 3, 7, 1, 0, 2),
(53, 18, 'PIG', 5, 2, 1, 1, 3),
(54, 18, 'ANT', 5, 3, 1, 1, 4),
(55, 19, 'HORSE', 5, 2, 1, 1, 0),
(56, 19, 'PEACOCK', 4, 7, 2, 0, 1),
(57, 19, 'CAMEL', 3, 4, 1, 0, 2),
(58, 19, 'ELEPHANT', 4, 8, 1, 1, 3),
(59, 19, 'PIG', 5, 2, 1, 1, 4),
(60, 20, 'SWAN', 2, 3, 1, 1, 0),
(61, 20, 'PEACOCK', 4, 7, 2, 0, 1),
(62, 20, 'CAMEL', 4, 5, 1, 1, 2),
(63, 20, 'ELEPHANT', 4, 8, 1, 1, 3),
(64, 20, 'PIG', 5, 2, 1, 1, 4),
(65, 21, 'SWAN', 2, 3, 1, 1, 0),
(66, 21, 'GIRAFFE', 1, 2, 1, 0, 1),
(67, 21, 'PEACOCK', 4, 7, 2, 0, 2),
(68, 21, 'CAMEL', 5, 6, 2, 0, 3),
(69, 21, 'ELEPHANT', 4, 8, 1, 1, 4),
(70, 22, 'SWAN', 2, 3, 1, 1, 0),
(71, 22, 'GIRAFFE', 1, 2, 1, 0, 1),
(72, 22, 'PEACOCK', 5, 8, 2, 1, 2),
(73, 22, 'CAMEL', 7, 8, 2, 1, 3),
(74, 22, 'ELEPHANT', 4, 8, 1, 1, 4),
(75, 24, 'FISH', 2, 3, 1, 0, 2),
(76, 24, 'PIGEON', 3, 1, 1, 0, 3),
(77, 24, 'PIGEON', 3, 1, 1, 0, 4),
(78, 25, 'BEAVER', 4, 3, 1, 1, 0),
(79, 25, 'OTTER', 1, 3, 1, 0, 1),
(80, 25, 'FISH', 2, 3, 1, 0, 2),
(81, 25, 'PIGEON', 3, 1, 1, 0, 3),
(82, 25, 'PIGEON', 3, 2, 1, 0, 4),
(83, 26, 'BEAVER', 5, 4, 2, 0, 0),
(84, 26, 'FISH', 2, 3, 1, 0, 1),
(85, 26, 'FLAMINGO', 3, 2, 1, 0, 2),
(86, 26, 'PIGEON', 4, 3, 1, 1, 3),
(87, 26, 'PIG', 4, 1, 1, 0, 4),
(88, 27, 'BEAVER', 5, 4, 2, 0, 0),
(89, 27, 'FISH', 2, 3, 1, 0, 1),
(90, 27, 'FLAMINGO', 3, 2, 1, 0, 2),
(91, 27, 'PIGEON', 4, 3, 1, 1, 3),
(92, 27, 'PIG', 4, 1, 1, 0, 4),
(93, 28, 'SWAN', 1, 2, 1, 0, 0),
(94, 28, 'FLAMINGO', 3, 2, 1, 0, 2),
(95, 28, 'ELEPHANT', 3, 7, 1, 0, 3),
(96, 28, 'PIG', 6, 1, 1, 0, 4),
(97, 30, 'MOSQUITO', 2, 2, 1, 0, 2),
(98, 30, 'HORSE', 2, 1, 1, 0, 3),
(99, 30, 'HORSE', 2, 1, 1, 0, 4),
(100, 31, 'OTTER', 1, 3, 1, 0, 2),
(101, 31, 'CRICKET', 1, 2, 1, 0, 3),
(102, 31, 'PIG', 4, 2, 1, 0, 4),
(103, 32, 'PIGEON', 3, 1, 1, 0, 0),
(104, 32, 'DUCK', 2, 3, 1, 0, 1),
(105, 32, 'OTTER', 2, 4, 1, 1, 2),
(106, 32, 'CRICKET', 1, 3, 1, 0, 3),
(107, 32, 'PIG', 4, 2, 1, 0, 4),
(108, 33, 'PIGEON', 3, 1, 1, 0, 0),
(109, 33, 'DUCK', 2, 3, 1, 0, 1),
(110, 33, 'OTTER', 2, 4, 1, 1, 2),
(111, 33, 'CRICKET', 2, 4, 1, 1, 3),
(112, 33, 'PIG', 4, 2, 1, 0, 4),
(113, 34, 'SPIDER', 2, 2, 1, 0, 0),
(114, 34, 'SWAN', 1, 3, 1, 0, 1),
(115, 34, 'OTTER', 2, 4, 1, 1, 2),
(116, 34, 'CRICKET', 2, 4, 1, 1, 3),
(117, 34, 'PEACOCK', 3, 7, 1, 1, 4),
(118, 35, 'SPIDER', 2, 2, 1, 0, 0),
(119, 35, 'SWAN', 2, 4, 1, 1, 1),
(120, 35, 'CRICKET', 2, 4, 1, 1, 2),
(121, 35, 'GIRAFFE', 1, 2, 1, 0, 3),
(122, 35, 'PEACOCK', 4, 8, 2, 0, 4),
(123, 36, 'SPIDER', 2, 2, 1, 0, 0),
(124, 36, 'SWAN', 3, 5, 2, 0, 1),
(125, 36, 'CRICKET', 2, 4, 1, 1, 2),
(126, 36, 'GIRAFFE', 3, 4, 2, 0, 3),
(127, 36, 'PEACOCK', 5, 9, 2, 0, 4),
(128, 37, 'OTTER', 1, 3, 1, 0, 2),
(129, 37, 'CRICKET', 1, 3, 1, 0, 3),
(130, 37, 'ANT', 2, 2, 1, 0, 4),
(131, 38, 'DUCK', 2, 3, 1, 0, 0),
(132, 38, 'FISH', 2, 3, 1, 0, 1),
(133, 38, 'OTTER', 1, 3, 1, 0, 2),
(134, 38, 'CRICKET', 1, 3, 1, 0, 3),
(135, 38, 'ANT', 3, 3, 1, 1, 4),
(136, 39, 'DUCK', 2, 3, 1, 0, 0),
(137, 39, 'FISH', 3, 4, 1, 1, 1),
(138, 39, 'OTTER', 1, 3, 1, 0, 2),
(139, 39, 'CRICKET', 1, 3, 1, 0, 3),
(140, 39, 'ANT', 5, 5, 2, 1, 4),
(141, 40, 'SWAN', 2, 4, 1, 0, 0),
(142, 40, 'FISH', 4, 5, 2, 0, 1),
(143, 40, 'OTTER', 1, 3, 1, 0, 2),
(144, 40, 'CRICKET', 2, 4, 1, 1, 3),
(145, 40, 'ANT', 6, 6, 2, 1, 4),
(146, 41, 'SWAN', 2, 4, 1, 0, 0),
(147, 41, 'FISH', 6, 7, 2, 2, 1),
(148, 41, 'CRICKET', 2, 4, 1, 1, 2),
(149, 41, 'PEACOCK', 2, 5, 1, 0, 3),
(150, 41, 'ANT', 6, 6, 2, 1, 4),
(151, 42, 'SWAN', 5, 7, 1, 1, 0),
(152, 42, 'FISH', 7, 8, 3, 0, 1),
(153, 42, 'WORM', 1, 2, 1, 0, 2),
(154, 42, 'PEACOCK', 2, 5, 1, 0, 3),
(155, 42, 'ANT', 6, 6, 2, 1, 4),
(156, 43, 'SWAN', 5, 7, 1, 1, 0),
(157, 43, 'FISH', 7, 8, 3, 0, 1),
(158, 43, 'GIRAFFE', 2, 3, 1, 1, 2),
(159, 43, 'PEACOCK', 2, 5, 1, 0, 3),
(160, 43, 'CAMEL', 3, 4, 1, 0, 4),
(161, 44, 'SWAN', 6, 8, 2, 0, 0),
(162, 44, 'FISH', 7, 8, 3, 0, 1),
(163, 44, 'GIRAFFE', 3, 4, 2, 0, 2),
(164, 44, 'PEACOCK', 3, 6, 1, 0, 3),
(165, 44, 'CAMEL', 3, 4, 1, 0, 4),
(166, 45, 'SWAN', 7, 9, 2, 1, 0),
(167, 45, 'FISH', 7, 8, 3, 0, 1),
(168, 45, 'GIRAFFE', 3, 4, 2, 0, 2),
(169, 45, 'PEACOCK', 5, 8, 1, 1, 3),
(170, 45, 'CAMEL', 4, 5, 1, 0, 4),
(171, 46, 'SWAN', 8, 10, 2, 2, 0),
(172, 46, 'FISH', 7, 8, 3, 0, 1),
(173, 46, 'GIRAFFE', 4, 5, 2, 1, 2),
(174, 46, 'PEACOCK', 6, 9, 1, 1, 3),
(175, 46, 'CAMEL', 5, 6, 1, 0, 4),
(176, 51, 'PIGEON', 3, 1, 1, 0, 0),
(177, 51, 'HORSE', 2, 1, 1, 0, 3),
(178, 51, 'BEAVER', 3, 2, 1, 0, 4),
(179, 52, 'PIGEON', 4, 2, 1, 1, 0),
(180, 52, 'CRICKET', 1, 2, 1, 0, 2),
(181, 52, 'HORSE', 2, 1, 1, 0, 3),
(182, 52, 'BEAVER', 4, 3, 1, 1, 4),
(183, 53, 'PIGEON', 4, 2, 1, 1, 0),
(184, 53, 'CRICKET', 1, 2, 1, 0, 2),
(185, 53, 'HORSE', 2, 1, 1, 0, 3),
(186, 53, 'BEAVER', 4, 3, 1, 1, 4),
(187, 54, 'PIGEON', 4, 2, 1, 1, 0),
(188, 54, 'CRICKET', 1, 2, 1, 0, 2),
(189, 54, 'HORSE', 2, 1, 1, 0, 3),
(190, 54, 'BEAVER', 4, 3, 1, 1, 4),
(191, 55, 'ANT', 2, 2, 1, 0, 0),
(192, 55, 'CRICKET', 1, 2, 1, 0, 3),
(193, 55, 'HORSE', 2, 1, 1, 0, 4),
(194, 56, 'ANT', 2, 2, 1, 0, 0),
(195, 56, 'PIG', 4, 1, 1, 0, 1),
(196, 56, 'DUCK', 2, 3, 1, 0, 2),
(197, 56, 'CRICKET', 1, 2, 1, 0, 3),
(198, 56, 'HORSE', 2, 1, 1, 0, 4),
(199, 58, 'OTTER', 1, 3, 1, 0, 1),
(200, 58, 'PIGEON', 3, 2, 1, 0, 4),
(201, 59, 'OTTER', 1, 3, 1, 0, 1),
(202, 59, 'PIGEON', 3, 2, 1, 0, 4),
(203, 61, 'BEAVER', 3, 2, 1, 0, 0),
(204, 61, 'HORSE', 2, 1, 1, 0, 2),
(205, 61, 'PIG', 4, 1, 1, 0, 4),
(206, 62, 'BEAVER', 3, 2, 1, 0, 0),
(207, 62, 'HORSE', 2, 1, 1, 0, 2),
(208, 62, 'PIG', 4, 1, 1, 0, 4),
(209, 63, 'HORSE', 2, 1, 1, 0, 0),
(210, 63, 'FISH', 2, 3, 1, 0, 2),
(211, 63, 'PIGEON', 3, 1, 1, 0, 4),
(212, 64, 'HORSE', 2, 1, 1, 0, 0),
(213, 64, 'CRICKET', 1, 2, 1, 0, 2),
(214, 64, 'CRICKET', 1, 2, 1, 0, 4),
(215, 65, 'HORSE', 2, 1, 1, 0, 0),
(216, 65, 'DUCK', 2, 3, 1, 0, 2),
(217, 65, 'CRICKET', 1, 2, 1, 0, 4),
(218, 66, 'PIGEON', 3, 1, 1, 0, 2),
(219, 66, 'ANT', 2, 2, 1, 0, 3),
(220, 66, 'MOSQUITO', 2, 2, 1, 0, 4),
(221, 67, 'PIGEON', 3, 1, 1, 0, 2),
(222, 67, 'FISH', 2, 3, 1, 0, 3),
(223, 67, 'BEAVER', 3, 2, 1, 0, 4),
(224, 68, 'HORSE', 2, 1, 1, 0, 0),
(225, 68, 'CRICKET', 1, 2, 1, 0, 3),
(226, 68, 'PIGEON', 3, 1, 1, 0, 4),
(227, 70, 'BEAVER', 3, 2, 1, 0, 2),
(228, 70, 'CRICKET', 1, 2, 1, 0, 3),
(229, 70, 'FISH', 2, 3, 1, 0, 4),
(230, 71, 'FISH', 3, 4, 1, 1, 1),
(231, 71, 'BEAVER', 4, 3, 1, 1, 2),
(232, 71, 'CRICKET', 1, 2, 1, 0, 3),
(233, 71, 'ANT', 2, 2, 1, 0, 4),
(234, 73, 'MOSQUITO', 2, 2, 1, 0, 2),
(235, 73, 'DUCK', 2, 3, 1, 0, 3),
(236, 73, 'PIGEON', 3, 1, 1, 0, 4),
(237, 75, 'HORSE', 2, 1, 1, 0, 0),
(238, 75, 'CRICKET', 1, 2, 1, 0, 3),
(239, 75, 'PIG', 4, 1, 1, 0, 4),
(240, 77, 'HORSE', 2, 1, 1, 0, 0),
(241, 77, 'HORSE', 2, 1, 1, 0, 1),
(242, 77, 'FISH', 2, 3, 1, 0, 4),
(243, 79, 'MOSQUITO', 2, 2, 1, 0, 0),
(244, 79, 'OTTER', 1, 3, 1, 0, 3),
(245, 79, 'HORSE', 2, 2, 1, 0, 4),
(246, 80, 'MOSQUITO', 2, 2, 1, 0, 0),
(247, 80, 'ANT', 2, 2, 1, 0, 1),
(248, 80, 'PIGEON', 3, 1, 1, 0, 2),
(249, 80, 'OTTER', 1, 3, 1, 0, 3),
(250, 80, 'HORSE', 3, 3, 1, 1, 4),
(251, 81, 'HEDGEHOG', 3, 2, 1, 0, 0),
(252, 81, 'ANT', 2, 2, 1, 0, 1),
(253, 81, 'PIGEON', 3, 1, 1, 0, 2),
(254, 81, 'OTTER', 1, 3, 1, 0, 3),
(255, 81, 'HORSE', 3, 3, 1, 1, 4),
(256, 83, 'PIGEON', 3, 1, 1, 0, 0),
(257, 83, 'MOSQUITO', 2, 2, 1, 0, 1),
(258, 83, 'PIG', 4, 1, 1, 0, 4),
(259, 84, 'PIGEON', 3, 1, 1, 0, 0),
(260, 84, 'MOSQUITO', 2, 2, 1, 0, 1),
(261, 84, 'DUCK', 2, 3, 1, 0, 2),
(262, 84, 'CRICKET', 2, 3, 1, 1, 3),
(263, 84, 'PIG', 4, 1, 1, 0, 4),
(264, 85, 'PIGEON', 3, 1, 1, 0, 0),
(265, 85, 'MOSQUITO', 2, 2, 1, 0, 1),
(266, 85, 'DUCK', 2, 3, 1, 0, 2),
(267, 85, 'CRICKET', 2, 3, 1, 1, 3),
(268, 85, 'PIG', 5, 2, 1, 1, 4),
(269, 86, 'PIGEON', 3, 1, 1, 0, 0),
(270, 86, 'MOSQUITO', 2, 2, 1, 0, 1),
(271, 86, 'DUCK', 2, 3, 1, 0, 2),
(272, 86, 'CRICKET', 2, 3, 1, 1, 3),
(273, 86, 'PIG', 5, 2, 1, 1, 4),
(274, 87, 'PIGEON', 3, 1, 1, 0, 0),
(275, 87, 'MOSQUITO', 2, 2, 1, 0, 1),
(276, 87, 'DUCK', 2, 3, 1, 0, 2),
(277, 87, 'CRICKET', 2, 3, 1, 1, 3),
(278, 87, 'PIG', 5, 2, 1, 1, 4),
(279, 88, 'PIGEON', 3, 1, 1, 0, 0),
(280, 88, 'MOSQUITO', 2, 2, 1, 0, 1),
(281, 88, 'DUCK', 2, 3, 1, 0, 2),
(282, 88, 'CRICKET', 2, 3, 1, 1, 3),
(283, 88, 'PIG', 5, 2, 1, 1, 4),
(284, 89, 'PIGEON', 3, 1, 1, 0, 0),
(285, 89, 'MOSQUITO', 2, 2, 1, 0, 1),
(286, 89, 'DUCK', 2, 3, 1, 0, 2),
(287, 89, 'CRICKET', 2, 3, 1, 1, 3),
(288, 89, 'PIG', 5, 2, 1, 1, 4),
(289, 90, 'PIGEON', 3, 1, 1, 0, 0),
(290, 90, 'MOSQUITO', 2, 2, 1, 0, 1),
(291, 90, 'DUCK', 2, 3, 1, 0, 2),
(292, 90, 'CRICKET', 2, 3, 1, 1, 3),
(293, 90, 'PIG', 5, 2, 1, 1, 4),
(294, 92, 'MOSQUITO', 2, 3, 1, 0, 0),
(295, 92, 'OTTER', 1, 3, 1, 0, 1),
(296, 94, 'MOSQUITO', 2, 3, 1, 0, 0),
(297, 94, 'OTTER', 1, 3, 1, 0, 1),
(298, 95, 'MOSQUITO', 2, 3, 1, 0, 0),
(299, 95, 'OTTER', 1, 3, 1, 0, 1),
(300, 97, 'DUCK', 2, 3, 1, 0, 3),
(301, 99, 'PIGEON', 3, 1, 1, 0, 4),
(302, 100, 'MOSQUITO', 2, 2, 1, 0, 0),
(303, 100, 'PIGEON', 3, 1, 1, 0, 2),
(304, 100, 'ANT', 2, 2, 1, 0, 3),
(305, 100, 'PIGEON', 3, 1, 1, 0, 4),
(306, 103, 'MOSQUITO', 2, 2, 1, 0, 0),
(307, 103, 'DUCK', 2, 3, 1, 0, 3),
(308, 103, 'DUCK', 2, 3, 1, 0, 4),
(309, 104, 'MOSQUITO', 2, 2, 1, 0, 0),
(310, 104, 'HORSE', 2, 1, 1, 0, 2),
(311, 104, 'DUCK', 2, 3, 1, 0, 3),
(312, 104, 'DUCK', 2, 3, 1, 0, 4),
(313, 107, 'BEAVER', 3, 2, 1, 0, 2),
(314, 107, 'PIGEON', 3, 1, 1, 0, 4),
(315, 108, 'MOSQUITO', 2, 2, 1, 0, 0),
(316, 108, 'BEAVER', 3, 2, 1, 0, 1),
(317, 108, 'PIGEON', 3, 1, 1, 0, 3),
(318, 108, 'PIGEON', 4, 2, 1, 1, 4),
(319, 110, 'FISH', 2, 3, 1, 0, 3),
(320, 110, 'FISH', 2, 3, 1, 0, 4),
(321, 111, 'MOSQUITO', 2, 2, 1, 0, 4),
(322, 112, 'PIGEON', 3, 1, 1, 0, 4),
(323, 114, 'PIGEON', 3, 1, 1, 0, 3),
(324, 114, 'PIGEON', 3, 1, 1, 0, 4),
(325, 115, 'PIGEON', 4, 2, 1, 1, 3),
(326, 115, 'PIGEON', 4, 2, 1, 1, 4),
(327, 116, 'PIGEON', 4, 2, 1, 1, 3),
(328, 116, 'PIGEON', 4, 2, 1, 1, 4),
(329, 117, 'PIGEON', 6, 4, 3, 0, 4),
(330, 120, 'DUCK', 2, 3, 1, 0, 0),
(331, 120, 'HORSE', 2, 1, 1, 0, 1),
(332, 120, 'ANT', 2, 2, 1, 0, 2),
(333, 121, 'DUCK', 2, 3, 1, 0, 0),
(334, 121, 'HORSE', 3, 3, 1, 1, 1),
(335, 121, 'ANT', 2, 2, 1, 0, 2),
(336, 121, 'ANT', 2, 2, 1, 0, 3),
(337, 121, 'OTTER', 1, 3, 1, 0, 4),
(338, 123, 'FISH', 2, 3, 1, 0, 0),
(339, 123, 'PIG', 4, 1, 1, 0, 2),
(340, 123, 'MOSQUITO', 2, 2, 1, 0, 4),
(341, 125, 'PIGEON', 3, 2, 1, 0, 2),
(342, 125, 'OTTER', 1, 3, 1, 0, 4),
(343, 126, 'PIGEON', 3, 2, 1, 0, 2),
(344, 126, 'OTTER', 1, 3, 1, 0, 4),
(345, 127, 'PIGEON', 3, 2, 1, 0, 2),
(346, 127, 'OTTER', 1, 3, 1, 0, 4),
(347, 131, 'OTTER', 1, 3, 1, 0, 0),
(348, 131, 'DUCK', 2, 4, 1, 0, 2),
(349, 131, 'FISH', 2, 3, 1, 0, 4),
(350, 133, 'PIGEON', 3, 1, 1, 0, 0),
(351, 133, 'BEAVER', 3, 2, 1, 0, 3),
(352, 133, 'PIG', 4, 1, 1, 0, 4),
(353, 134, 'PIGEON', 3, 1, 1, 0, 0),
(354, 134, 'HORSE', 2, 1, 1, 0, 2),
(355, 134, 'BEAVER', 3, 2, 1, 0, 3),
(356, 134, 'PIG', 4, 1, 1, 0, 4),
(357, 135, 'HORSE', 2, 1, 1, 0, 2),
(358, 135, 'BEAVER', 3, 2, 1, 0, 3),
(359, 135, 'PIG', 4, 1, 1, 0, 4);

-- --------------------------------------------------------

--
-- Table structure for table `round`
--

CREATE TABLE `round` (
  `id` int(11) NOT NULL,
  `match_id` int(11) NOT NULL,
  `enm_round_id` int(11) NOT NULL,
  `round` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `round`
--

INSERT INTO `round` (`id`, `match_id`, `enm_round_id`, `round`) VALUES
(1, 2, -1, 1),
(2, 2, -1, 2),
(5, 4, -1, 1),
(6, 4, -1, 2),
(8, 5, -1, 1),
(10, 7, -1, 1),
(11, 7, -1, 2),
(12, 7, -1, 3),
(14, 8, -1, 1),
(15, 8, -1, 2),
(16, 8, -1, 3),
(17, 8, -1, 4),
(18, 8, -1, 5),
(19, 8, -1, 6),
(20, 8, -1, 7),
(21, 8, -1, 8),
(22, 8, -1, 9),
(24, 9, -1, 1),
(25, 9, -1, 2),
(26, 9, -1, 3),
(27, 9, -1, 4),
(28, 9, -1, 5),
(30, 11, -1, 1),
(31, 12, -1, 1),
(32, 12, -1, 2),
(33, 12, -1, 3),
(34, 12, -1, 4),
(35, 12, -1, 5),
(36, 12, -1, 6),
(37, 13, -1, 1),
(38, 13, -1, 2),
(39, 13, -1, 3),
(40, 13, -1, 4),
(41, 13, -1, 5),
(42, 13, -1, 6),
(43, 13, -1, 7),
(44, 13, -1, 8),
(45, 13, -1, 9),
(46, 13, -1, 10),
(51, 18, 5, 1),
(52, 18, 32, 2),
(53, 18, 16, 3),
(54, 18, 27, 4),
(55, 19, 31, 1),
(56, 19, 32, 2),
(58, 19, 47, 1),
(59, 19, 56, 2),
(61, 20, -1, 1),
(62, 20, -1, 2),
(63, 21, 14, 1),
(64, 22, 30, 1),
(65, 23, 24, 1),
(66, 24, 5, 1),
(67, 25, 37, 1),
(68, 26, 1, 1),
(70, 28, 63, 1),
(71, 28, 62, 2),
(73, 29, 64, 1),
(75, 30, 64, 1),
(77, 32, 30, 1),
(79, 33, 64, 1),
(80, 33, 62, 2),
(81, 33, 72, 3),
(83, 34, 66, 1),
(84, 34, 74, 2),
(85, 34, 72, 3),
(86, 34, 17, 4),
(87, 34, 18, 5),
(88, 34, 19, 6),
(89, 34, 20, 7),
(90, 34, 44, 8),
(92, 37, 37, 1),
(94, 37, 2, 2),
(95, 37, 72, 3),
(97, 40, 91, 1),
(99, 40, 66, 1),
(100, 40, 38, 2),
(103, 43, 8, 1),
(104, 43, 32, 2),
(107, 45, 69, 1),
(108, 45, 25, 2),
(110, 45, 68, 1),
(111, 45, 110, 1),
(112, 46, 61, 1),
(114, 47, 112, 1),
(115, 47, 104, 2),
(116, 47, 33, 3),
(117, 47, 86, 4),
(120, 49, 83, 1),
(121, 49, 115, 2),
(123, 50, 83, 1),
(125, 51, 91, 1),
(126, 51, 74, 2),
(127, 51, 16, 3),
(131, 54, 51, 1),
(133, 55, 66, 1),
(134, 55, 52, 2),
(135, 55, 81, 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `password`) VALUES
(1, 'tester', 'tester'),
(2, 'jackson', 'jackson'),
(3, 'Vitos', 'Vito123'),
(4, 'jason', 'jason'),
(5, 'artaa', 'artaa'),
(6, 'artaj', 'artaj'),
(7, 'artaw', 'artaw'),
(8, 'artacau', 'artacau');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fruit`
--
ALTER TABLE `fruit`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pet_id` (`pet_id`);

--
-- Indexes for table `match`
--
ALTER TABLE `match`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `pet`
--
ALTER TABLE `pet`
  ADD PRIMARY KEY (`id`),
  ADD KEY `round_id` (`round_id`);

--
-- Indexes for table `round`
--
ALTER TABLE `round`
  ADD PRIMARY KEY (`id`),
  ADD KEY `match_id` (`match_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fruit`
--
ALTER TABLE `fruit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `match`
--
ALTER TABLE `match`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;

--
-- AUTO_INCREMENT for table `pet`
--
ALTER TABLE `pet`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=360;

--
-- AUTO_INCREMENT for table `round`
--
ALTER TABLE `round`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=137;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fruit`
--
ALTER TABLE `fruit`
  ADD CONSTRAINT `fruit_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`);

--
-- Constraints for table `match`
--
ALTER TABLE `match`
  ADD CONSTRAINT `match_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `pet`
--
ALTER TABLE `pet`
  ADD CONSTRAINT `pet_ibfk_1` FOREIGN KEY (`round_id`) REFERENCES `round` (`id`);

--
-- Constraints for table `round`
--
ALTER TABLE `round`
  ADD CONSTRAINT `round_ibfk_1` FOREIGN KEY (`match_id`) REFERENCES `match` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
