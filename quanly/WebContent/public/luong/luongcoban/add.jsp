<%@page import="model.bean.LuongCB"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
        <div class="content">
            <div class="ttk">
                <h3>Thêm Lương Cơ Bản</h3>
                <%
		        	String err = request.getParameter("err");
		        	String job = request.getParameter("job");
		        	String money = request.getParameter("money");       	
		        	if("1".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Có lỗi khi thêm</span>");
		        	}
		        	if("2".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Lương vị trí này đã tồn tại !</span>");
		        	}
		        %>
                <form action="" class="addtk" method="post">
                    <label>Vị Trí:</label>
                    <input type="text" id="job" name="job" placeholder="Nhập vị trí làm việc..." value="<%if(job != null){out.print(job);} %>"><br><br>
                    <label>Lương / 1 Giờ :</label>
                    <input type="number" id="money" name="money" placeholder="Nhập tiền lương..." value="<%if(money != null){out.print(money);} %>"><br><br>
                    <input type="submit" value="Thêm" class="submit">
                  </form>    
            </div>
        </div>
<script>
    document.getElementById("tienluong").classList.add('active');
</script>
<%@include file="/templates/public/inc/footer.jsp" %>  