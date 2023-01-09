<%@page import="model.bean.LichLam"%>
<%@page import="model.bean.NhanVien"%>
<%@page import="model.bean.Thuong"%>
<%@page import="model.bean.LuongCB"%>
<%@page import="model.bean.TaiKhoan"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
        <div class="content">
            <div class="bangtk" style="margin: 85px 178px;">
            	<%
                    	TaiKhoan TKLog = (TaiKhoan) session.getAttribute("TKLogin");
                    	if("Admin".equals(TKLog.getPosition())){
                %>
                <p><a href="<%=request.getContextPath()%>/nhanvien/add">Thêm</a></p>
                <% } %>
                <%
		        	String err = request.getParameter("err");
                	String msg = request.getParameter("msg");
                	if("1".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Thêm nhân viên thành công</span>");
		        	}
                	if("2".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Sửa nhân viên thành công</span>");
		        	}
                	if("3".equals(msg)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Xóa nhân viên thành công</span>");
		        	}
                	if("1".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">ID không tồn tại !</span>");
		        	}
                	if("2".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Xóa nhân viên thất bại !</span>");
		        	}
                	if("3".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Có lỗi xảy ra hoặc tất cả nhân viên đã có thông tin!</span>");
		        	}
		        %>
                <table>
                    <tr>
                        <th>Hình Ảnh</th>
                        <th>Tên Nhân Viên</th>
                        <th>Giới Tính</th>
                        <th>Địa Chỉ</th>
                        <th>Chức Vụ</th>
                        <th>Lương</th>
                        <th>Chức Năng</th>
                    </tr>
                    <%
                    	ArrayList<NhanVien> listNV = (ArrayList<NhanVien>) request.getAttribute("listNV");
                    	ArrayList<LichLam> listLL = (ArrayList<LichLam>) request.getAttribute("listLL");
                    	if(listNV != null && listNV.size() > 0){
                    		boolean kt = false;
                    		for(NhanVien itemNV : listNV){
                    			if(TKLog.getId() == itemNV.getTaiKhoan().getId() && "Quản Lý".equals(itemNV.getLuongCB().getJob())){
                    				kt = true;
                    			}
                    		}
                    		for(NhanVien itemNV : listNV){
                    			int tien = 0;
                    %>
                    <tr>
                        <td><img alt="anh" src="<%=request.getContextPath()%>/files/<%=itemNV.getTaiKhoan().getPicture()%> " style="width: 100px; height: 100px"></td>
                        <td><%=itemNV.getTaiKhoan().getFullname() %></td>
                        <td><%=itemNV.getGioitinh() %></td>
                        <td><%=itemNV.getAddress() %></td>
                        <td><%=itemNV.getLuongCB().getJob() %></td>
                        <% 
                        	for(LichLam lam: listLL){
                     		
                        	if(lam.getTaiKhoan().getId() == itemNV.getTaiKhoan().getId()){
                        		if(lam.getStatus() == 1){
                        			tien += (3.5 * itemNV.getLuongCB().getMoney());
                        		}
                        	}
                        	}
                        
                        %>
                        <td><%=tien %> vnđ</td>
                        <td>
                        	<% if("Admin".equals(TKLog.getPosition())){ %>
                            <a href="<%=request.getContextPath()%>/nhanvien/del?id=<%=itemNV.getId() %>" class="xoa" onclick="return confirm('Bạn có chắc muốn xóa?')" >Xóa <i class="fa fa-trash"></i></a>
                            <% } %>
                            <% if("Admin".equals(TKLog.getPosition()) || kt == true){ %>
                            <a href="<%=request.getContextPath()%>/nhanvien/edit?id=<%=itemNV.getId() %>" class="sua" >Sửa <i class="fa fa-pencil"></i></a>
                        	<% } %>
                        </td>
                    </tr>
                    <% } }else{ %>
                    <tr>
                    	<td colspan="7" align="center">Chưa có nhân viên</td>
                    </tr>
                    <% } %>
                </table>    
            </div>
        </div>
<script>
    document.getElementById("nhanvien").classList.add('active');
</script>
<%@include file="/templates/public/inc/footer.jsp" %>        