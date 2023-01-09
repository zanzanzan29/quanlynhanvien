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
                <p><a href="<%=request.getContextPath()%>/taikhoan/add">Thêm</a></p>
                <% } %>
                <%
		        	String err = request.getParameter("err");
                	String msg = request.getParameter("msg");
                	if("1".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Thêm tài khoản thành công</span>");
		        	}
                	if("2".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Sửa tài khoản thành công</span>");
		        	}
                	if("3".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Xóa tài khoản thành công</span>");
		        	}
                	if("1".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">ID không tồn tại !</span>");
		        	}
                	if("2".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Xóa tài khoản thất bại !</span>");
		        	}
                	if("3".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Không có quyền thêm tài khoản !</span>");
		        	}
                	if("4".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Không có quyền sửa tài khoản !</span>");
		        	}
                	if("5".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Không có quyền xóa tài khoản !</span>");
		        	}
		        %>
                <table>
                    <tr>
                        <th>#</th>
                        <th>Tên Tài Khoản</th>
                        <th>Họ Tên</th>
                        <th>Chức Vụ</th>
                        <th>Chức Năng</th>
                    </tr>
                    <%
                    	ArrayList<TaiKhoan> taikhoan = (ArrayList<TaiKhoan>) request.getAttribute("taikhoan");
                    	if(taikhoan != null && taikhoan.size() > 0){
                    		for(TaiKhoan itemtk : taikhoan){
                    %>
                    <tr>
                        <td><%=itemtk.getId() %></td>
                        <td><%=itemtk.getUsername() %></td>
                        <td><%=itemtk.getFullname() %></td>
                        <td><%=itemtk.getPosition() %></td>
                        <td>
                        <%
                        	if("Admin".equals(TKLog.getPosition()) && !"Admin".equals(itemtk.getPosition())){
                        %>
                            <a href="<%=request.getContextPath()%>/taikhoan/del?id=<%=itemtk.getId() %>" class="xoa" onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa <i class="fa fa-trash"></i></a>
                        <% } %>
                        <% if("Admin".equals(TKLog.getPosition()) || itemtk.getId() == TKLog.getId()){ %>
                            <a href="<%=request.getContextPath()%>/taikhoan/edit?id=<%=itemtk.getId() %>" class="sua">Sửa <i class="fa fa-pencil"></i></a>
                        <% } %>
                        </td>
                    </tr>
                    <% } }else{ %>
                    <tr>
                    	<td colspan="5" align="center">Chưa có tài khoản</td>
                    </tr>
                    <% } %>
                </table>    
            </div>
        </div>
<script>
    document.getElementById("taikhoan").classList.add('active');
</script>
<%@include file="/templates/public/inc/footer.jsp" %>        