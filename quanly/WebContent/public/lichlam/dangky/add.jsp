<%@page import="model.bean.LuongCB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
        <div class="content">
            <div class="ttk">
                <h3>Thêm Lịch Làm</h3>
                <%
		        	String err = request.getParameter("err");       	
		        	if("1".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Có lỗi khi thêm</span>");
		        	}
		        	if("2".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Lịch làm này đã tồn tại !</span>");
		        	}
		        %>
                <form action="" class="addtk" method="post">
                    <label>Ngày Làm:</label>
                    <input type="date" id="date" name="date"><br><br>
                    <label>Giờ bắt đầu:</label>
                    <input type="time" id="timebd" name="timebd"><br><br>
                    <label>Giờ kết thúc:</label>
                    <input type="time" id="timekt" name="timekt"><br><br>
                    <label>Số lượng nhân viên:</label>
                    <input type="number" id="number" name="number"><br><br>
                    <input type="submit" value="Thêm" class="submit">
                  </form>    
            </div>
        </div>
<script>
    document.getElementById("lichlam").classList.add('active');
</script>
<%@include file="/templates/public/inc/footer.jsp" %>  