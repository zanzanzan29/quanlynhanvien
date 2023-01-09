<%@page import="model.bean.Thuong"%>
<%@page import="model.bean.LuongCB"%>
<%@page import="model.bean.TaiKhoan"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
        <div class="content">
            <div class="bangtk">
            	<%
                    	TaiKhoan TKLog = (TaiKhoan) session.getAttribute("TKLogin");
                    	if("Admin".equals(TKLog.getPosition())){
                %>
                <p><a href="<%=request.getContextPath()%>/luong/thuong/add">Thêm</a></p>
                <% } %>
                <%
		        	String err = request.getParameter("err");
                	String msg = request.getParameter("msg");
                	if("1".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Thêm thưởng thành công</span>");
		        	}
                	if("2".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Sửa thưởng thành công</span>");
		        	}
                	if("3".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Xóa thưởng thành công</span>");
		        	}
                	if("1".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">ID không tồn tại !</span>");
		        	}
                	if("2".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Xóa thưởng thất bại !</span>");
		        	}
		        %>
                <table>
                    <tr>
                        <th>#</th>
                        <th>Tên Lương Thưởng</th>
                        <th>Thời Gian Đã Làm</th>
                        <th>Lương Thưởng Thêm</th>
                        <th>Chức Năng</th>
                    </tr>
                    <%
                    	ArrayList<Thuong> listT = (ArrayList<Thuong>) request.getAttribute("listT");
                    	if(listT != null && listT.size() > 0){
                    		for(Thuong itemt : listT){
                    %>
                    <tr>
                        <td><%=itemt.getId() %></td>
                        <td><%=itemt.getName() %></td>
                        <td><%=itemt.getTime() %></td>
                        <td><%=itemt.getMoney() %> vnđ</td>
                        <td>
                        	<% if("Admin".equals(TKLog.getPosition())){ %>
                            <a href="<%=request.getContextPath()%>/luong/thuong/del?id=<%=itemt.getId() %>" class="xoa" onclick="return confirm('Bạn có chắc muốn xóa?')" style="font-size: 17px;">Xóa <i class="fa fa-trash"></i></a>
                            <% } %>
                            <% if("Admin".equals(TKLog.getPosition())){ %>
                            <a href="<%=request.getContextPath()%>/luong/thuong/edit?id=<%=itemt.getId() %>" class="sua" style="font-size: 17px;">Sửa <i class="fa fa-pencil"></i></a>
                        	<% } %>
                        </td>
                    </tr>
                    <% } }else{ %>
                    <tr>
                    	<td colspan="5" align="center">Chưa có thưởng</td>
                    </tr>
                    <% } %>
                </table>    
            </div>
        </div>
<script>
    document.getElementById("tienluong").classList.add('active');
</script>
<%@include file="/templates/public/inc/footer.jsp" %>        