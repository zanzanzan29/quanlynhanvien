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
                <p><a href="<%=request.getContextPath()%>/luong/coban/add">Thêm</a></p>
                <% } %>
                <%
		        	String err = request.getParameter("err");
                	String msg = request.getParameter("msg");
                	if("1".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Thêm lương thành công</span>");
		        	}
                	if("2".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Sửa lương thành công</span>");
		        	}
                	if("3".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Xóa lương thành công</span>");
		        	}
                	if("1".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">ID không tồn tại !</span>");
		        	}
                	if("2".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Xóa lương thất bại !</span>");
		        	}
		        %>
                <table>
                    <tr>
                        <th>#</th>
                        <th>Vị Trí</th>
                        <th>Lương/1 Giờ</th>
                        <th>Chức Năng</th>
                    </tr>
                    <%
                    	ArrayList<LuongCB> listLCB = (ArrayList<LuongCB>) request.getAttribute("listLCB");
                    	if(listLCB != null && listLCB.size() > 0){
                    		for(LuongCB itemlcb : listLCB){
                    %>
                    <tr>
                        <td><%=itemlcb.getId() %></td>
                        <td><%=itemlcb.getJob() %></td>
                        <td><%=itemlcb.getMoney() %> vnđ</td>
                        <td>
                        	<% if("Admin".equals(TKLog.getPosition())){ %>
                            <a href="<%=request.getContextPath()%>/luong/coban/del?id=<%=itemlcb.getId() %>" class="xoa" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa <i class="fa fa-trash"></i></a>
                            <% } %>
                            <% if("Admin".equals(TKLog.getPosition())){ %>
                            <a href="<%=request.getContextPath()%>/luong/coban/edit?id=<%=itemlcb.getId() %>" class="sua">Sửa <i class="fa fa-pencil"></i></a>
                        	<% } %>
                        </td>
                    </tr>
                    <% } }else{ %>
                    <tr>
                    	<td colspan="4" align="center">Chưa có lương cơ bản</td>
                    </tr>
                    <% } %>
                </table>    
            </div>
        </div>
<script>
    document.getElementById("tienluong").classList.add('active');
</script>
<%@include file="/templates/public/inc/footer.jsp" %>        