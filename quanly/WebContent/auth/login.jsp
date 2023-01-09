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
        <h2>Quản Lý Nhân Viên</h2>
        <div class="giaodien">
            <div class="otron"></div>
            <img src="<%=request.getContextPath() %>/templates/public/img/logo.png" alt="logo" />
            <a href="">Quên mật khẩu ?</a>
        </div>
        <div class="dn">
            <h3>ĐĂNG NHẬP</h3>
            <div>
            	<%
	            	String err = request.getParameter("err");
	            	if("1".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Tên tài khoản hoặc mật khẩu không đúng !</span>");
		        	}
            	%>
                <form action="" method="post">
                    <i class="fa fa-user icu"></i>
                    <input type="text" id="username" name="username" placeholder="Tài Khoản" class="cach"><br><br>
                    <i class="fa fa-unlock-alt icu"></i>
                    <input type="password" id="password" name="password" placeholder="Mật Khẩu" class="cach"><br><br>
                    <input type="submit" value="Đăng Nhập" class="submit">
                </form> 
            </div>
        </div>
        <div class="clr"></div>
<%@include file="/templates/public/inc/footer.jsp" %>   