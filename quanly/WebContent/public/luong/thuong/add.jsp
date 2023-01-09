<%@page import="model.bean.LuongCB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
        <div class="content">
            <div class="ttk">
                <h3>Thêm Thưởng</h3>
                <%
		        	String err = request.getParameter("err");
		        	String name = request.getParameter("name");
		        	String time = request.getParameter("time");
		        	String money = request.getParameter("money");       	
		        	if("1".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Có lỗi khi thêm</span>");
		        	}
		        	if("2".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Lương vị trí này đã tồn tại !</span>");
		        	}
		        %>
                <form action="" class="addtk" method="post">
                    <label>Tên Lương Thưởng:</label>
                    <input type="text" id="name" name="name" placeholder="Nhập tên lương thưởng..." value="<%if(name != null){out.print(name);} %>"><br><br>
                    <label>Thời Gian Đã Làm:</label>
                    <input type="number" id="time" name="time" placeholder="Nhập thời gian đã làm..." value="<%if(time != null){out.print(time);} %>"><br><br>
                    <label>Lương Thưởng Thêm:</label>
                    <input type="number" id="money" name="money" placeholder="Nhập lương thưởng thêm..." value="<%if(money != null){out.print(money);} %>"><br><br>
                    <input type="submit" value="Thêm" class="submit">
                  </form>    
            </div>
        </div>
<script>
    document.getElementById("tienluong").classList.add('active');
</script>
<%@include file="/templates/public/inc/footer.jsp" %>  