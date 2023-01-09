<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
        <div class="content">
            <div class="ttk">
                <h3>Thêm Tài Khoản</h3>
                <%
		        	String err = request.getParameter("err");
		        	String username = request.getParameter("username");
		        	String fullname = request.getParameter("fullname");
		        	String position = request.getParameter("position");
		        	if("1".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Có lỗi khi thêm</span>");
		        	}
		        	if("2".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Tên đăng nhập đã tồn tại</span>");
		        	}
		        %>
                <form action="" class="addtk" role="form" method="post" enctype="multipart/form-data">
                    <label>Tên Tài Khoản:</label>
                    <input type="text" id="username" name="username" placeholder="Nhập tên tài khoản..." value="<%if(username != null){out.print(username);} %>"><br><br>
                    <label>Mật Khẩu:</label>
                    <input type="password" id="password" name="password" placeholder="Nhập mật khẩu..."><br><br>
                    <label>Họ và Tên:</label>
                    <input type="text" id="fullname" name="fullname" placeholder="Nhập họ và tên..." value="<%if(fullname != null){out.print(fullname);} %>"><br><br>
                    <label class="pic">Ảnh đại diện:</label>
                    <input type="file" id="picture" name="picture"><br><br>
                    <label>Chức Vụ:</label>
                    <select name="position" id="position">
                        <option value="Nhân Viên" <% if(position != null && "Nhân viên".equals(position)){out.print("selected");} %>>Nhân Viên</option>
                        <option value="Admin" <% if(position != null && "Admin".equals(position)){out.print("selected");} %>>Admin</option>
                    </select>
                    <input type="submit" value="Đăng Ký" class="submit">
                  </form>    
            </div>
        </div>
<script>
    document.getElementById("taikhoan").classList.add('active');
</script>
<%@include file="/templates/public/inc/footer.jsp" %>  