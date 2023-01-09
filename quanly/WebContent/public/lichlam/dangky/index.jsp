<%@page import="model.bean.LichLam"%>
<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalDate"%>
<%@page import="model.bean.DKLichLam"%>
<%@page import="model.bean.LuongCB"%>
<%@page import="model.bean.TaiKhoan"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
        <div class="content">
        <form action="" method="post">
            <div class="bangtk" style="margin-bottom: 0px">
                <%
		        	String err = request.getParameter("err");
                	String msg = request.getParameter("msg");
                	if("1".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Đăng ký lịch làm thành công</span>");
		        	}
                	if("2".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Sửa lịch làm thành công</span>");
		        	}
                	if("3".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Xóa lịch làm thành công</span>");
		        	}
                	if("1".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">ID không tồn tại !</span>");
		        	}
                	if("2".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Xóa lịch làm thất bại !</span>");
		        	}
                	if("3".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Đăng ký lịch làm thất bại !</span>");
		        	}
		        %>
                <table>
                    <tr>
                        <th>Chọn</th>
                        <th>Ngày</th>
                        <th>Bắt Đầu - Kết Thúc</th>
                    </tr>
                    <%
                    	ArrayList<DKLichLam> listDKLL = (ArrayList<DKLichLam>) request.getAttribute("listDKLL");
                    	ArrayList<LichLam> listLL = (ArrayList<LichLam>) request.getAttribute("listLL");
                    	if(listDKLL != null && listDKLL.size() > 0){
                    		for(DKLichLam itemdkll : listDKLL){
                    			int t = 0;
                    			for(LichLam itLam : listLL){
                    				if(itLam.getDkLichLam().getId() == itemdkll.getId()){
                    					t++;
                    				}
                    			}
                    			if( t == 0){
                    %>
                    <tr>
                        <td>
                        <%
		                    	TaiKhoan TKLog = (TaiKhoan) session.getAttribute("TKLogin");
		                    	if("Nhân Viên".equals(TKLog.getPosition())){
		                %>
                        <input type="checkbox" name="id_dk" value="<%=itemdkll.getId()%>" class="id_tk">
                        <% } %></td>
                        <td><%=itemdkll.getDate() %></td>
                        <td><%=itemdkll.getTimebd() %> - <%=itemdkll.getTimekt() %></td>
                    </tr>
                    <%} } }else{ %>
                    <tr>
                    	<td colspan="3" align="center">Chưa có lịch làm</td>
                    </tr>
                    <% } %>
                </table>    
            </div>
            <%
		                    	TaiKhoan TKLog = (TaiKhoan) session.getAttribute("TKLogin");
		                    	if("Nhân Viên".equals(TKLog.getPosition())){
		                %>
            <input type="submit" class="submit" name="submit" value="Đăng Ký">
            <% } %>
            </form>
        </div>
<script>
    document.getElementById("lichlam").classList.add('active');
</script>
<style>
	.submit{
	margin-left: 1045px;
	margin-bottom: 100px; 
	margin-top: 20px; 
	background: #008000; 
	font-size: 25px;
	color: white;
	border-radius:5px
	}
	.submit:HOVER {
	background: #006400;
	}
	.id_tk{
	width: 30px !important;
	height: 30px;
	}
</style>
<%@include file="/templates/public/inc/footer.jsp" %>        