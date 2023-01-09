<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Nhân Viên</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/style7.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/font-awesome-4.7.0/css/font-awesome.min.css">
    <link href='https://fonts.googleapis.com/css?family=Redressed' rel='stylesheet'>
</head>
<body>
    <div class="trang">
        <div class="header">
            <div class="logo">
                <img src="<%=request.getContextPath() %>/templates/public/img/pngegg 1.png" alt="logo"/>
                <h1><span>Quản Lý</span> Nhân Viên</h1>
            </div>
            <div class="menu">
                <p><a href="<%=request.getContextPath() %>/nhanvien" id="nhanvien" >Nhân viên</a></p>
                <p><a href="<%=request.getContextPath() %>/luong" id="tienluong">Tiền Lương</a></p>
                <p><a href="<%=request.getContextPath() %>/lichlam" id="lichlam">Lịch Làm</a></p>
                <p><a href="" id="gopy">Góp Ý</a></p>
                <p><a href="<%=request.getContextPath() %>/taikhoan" id="taikhoan">Tài Khoản</a></p>
                <p><a href="<%=request.getContextPath() %>/logout">Đăng Xuất <i class="fa fa-sign-out"></i></a></p>
            </div>
        </div>