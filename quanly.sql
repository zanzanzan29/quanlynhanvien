-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th1 09, 2023 lúc 07:29 AM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

--
-- Cơ sở dữ liệu: `quanly`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `dklichlam`
--

CREATE TABLE `dklichlam` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `timebd` time NOT NULL,
  `timekt` time NOT NULL,
  `number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `dklichlam`
--

INSERT INTO `dklichlam` (`id`, `date`, `timebd`, `timekt`, `number`) VALUES
(1, '2023-01-04', '08:00:00', '11:30:00', 15),
(2, '2023-01-04', '13:00:00', '17:30:00', 20),
(4, '2023-01-05', '08:00:00', '11:30:00', 20),
(5, '2023-01-05', '13:00:00', '17:30:00', 25),
(6, '2023-01-01', '08:00:00', '11:30:00', 50),
(7, '2023-01-03', '13:00:00', '15:30:00', 10),
(8, '2023-01-13', '08:00:00', '11:30:00', 15),
(9, '2023-01-16', '13:00:00', '17:30:00', 20);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `lichlam`
--

CREATE TABLE `lichlam` (
  `id` int(11) NOT NULL,
  `id_dklichlam` int(11) NOT NULL,
  `id_taikhoan` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `lichlam`
--

INSERT INTO `lichlam` (`id`, `id_dklichlam`, `id_taikhoan`, `status`) VALUES
(1, 1, 5, 0),
(2, 4, 5, 0),
(3, 6, 5, 1),
(4, 7, 5, 1),
(5, 2, 2, 0),
(6, 5, 2, 0),
(7, 8, 2, 0),
(8, 9, 2, 0),
(9, 5, 6, 0),
(10, 9, 6, 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `luongcb`
--

CREATE TABLE `luongcb` (
  `id` int(11) NOT NULL,
  `job` varchar(255) NOT NULL,
  `money` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `luongcb`
--

INSERT INTO `luongcb` (`id`, `job`, `money`) VALUES
(1, 'Phục Vụ', 33000),
(2, 'Quản Lý', 65000),
(3, 'Pha Chế', 47000),
(4, 'Giao Hàng', 30000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

CREATE TABLE `nhanvien` (
  `id` int(11) NOT NULL,
  `gioitinh` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `id_luongcb` int(11) NOT NULL,
  `id_taikhoan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`id`, `gioitinh`, `address`, `id_luongcb`, `id_taikhoan`) VALUES
(3, 'Nam', 'Quảng Nam', 2, 5),
(8, 'Nam', 'ddd', 3, 6),
(9, 'Nam', '123', 1, 2);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

CREATE TABLE `taikhoan` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(255) NOT NULL,
  `picture` text NOT NULL,
  `position` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`id`, `username`, `password`, `fullname`, `picture`, `position`) VALUES
(1, 'admin', '247830941fee58a10ac3bdeab054e552', 'Trần Quang Trường', 'hình1', 'Admin'),
(2, 'tuan123', '247830941fee58a10ac3bdeab054e552', 'Phạm Văn Tuấn', 'top-10-dien-vien-dep-trai-nhat-man-anh-han-quoc-23120725-394409665179300.jpg', 'Nhân Viên'),
(5, 'truongday', '247830941fee58a10ac3bdeab054e552', 'Trần Quang Trường', 'sorw6pttuXD-84332769133700.jpg', 'Nhân Viên'),
(6, 'khai123', '247830941fee58a10ac3bdeab054e552', 'Trần Văn Khải', 'Lee_Si-Young-1982-p1-91621707309700.jpg', 'Nhân Viên');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `thuong`
--

CREATE TABLE `thuong` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `time` int(11) NOT NULL,
  `money` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `thuong`
--

INSERT INTO `thuong` (`id`, `name`, `time`, `money`) VALUES
(1, 'Cố Gắng', 4000, 10000),
(2, 'Chăm Chỉ', 6000, 20000),
(3, 'Siêng Năng', 8000, 30000);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `dklichlam`
--
ALTER TABLE `dklichlam`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `lichlam`
--
ALTER TABLE `lichlam`
  ADD PRIMARY KEY (`id`,`id_dklichlam`,`id_taikhoan`),
  ADD KEY `id_taikhoan` (`id_taikhoan`),
  ADD KEY `id_dklichlam` (`id_dklichlam`);

--
-- Chỉ mục cho bảng `luongcb`
--
ALTER TABLE `luongcb`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`id`,`id_luongcb`,`id_taikhoan`),
  ADD KEY `id_taikhoan` (`id_taikhoan`),
  ADD KEY `id_luongcb` (`id_luongcb`);

--
-- Chỉ mục cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `thuong`
--
ALTER TABLE `thuong`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `dklichlam`
--
ALTER TABLE `dklichlam`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `lichlam`
--
ALTER TABLE `lichlam`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT cho bảng `luongcb`
--
ALTER TABLE `luongcb`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `taikhoan`
--
ALTER TABLE `taikhoan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT cho bảng `thuong`
--
ALTER TABLE `thuong`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `lichlam`
--
ALTER TABLE `lichlam`
  ADD CONSTRAINT `lichlam_ibfk_1` FOREIGN KEY (`id_taikhoan`) REFERENCES `taikhoan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `lichlam_ibfk_2` FOREIGN KEY (`id_dklichlam`) REFERENCES `dklichlam` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`id_taikhoan`) REFERENCES `taikhoan` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `nhanvien_ibfk_2` FOREIGN KEY (`id_luongcb`) REFERENCES `luongcb` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;
