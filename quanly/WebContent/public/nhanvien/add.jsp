<%@page import="model.bean.LuongCB"%>
<%@page import="model.bean.TaiKhoan"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
        <div class="content">
            <div class="ttk">
                <h3>Thêm Nhân Viên</h3>
                <%
		        	String err = request.getParameter("err");
		        	String taikhoan = request.getParameter("taikhoan");
		        	String gioitinh = request.getParameter("gioitinh");
		        	String address = request.getParameter("address");
		        	String chucvu = request.getParameter("chucvu");
		        	if("1".equals(err)){
		        		out.print("<span style=\"background: yellow; color: red; font-weight: bold; padding: 5px;\">Có lỗi khi thêm</span>");
		        	}
		        %>
                <form action="" class="addtk" method="post">
                	<label>Tên nhân viên:</label>
                	<select name="taikhoan" id="taikhoan">
                		<%
                			ArrayList<TaiKhoan> chuaTK = (ArrayList<TaiKhoan>) request.getAttribute("chuaTK");
                			if(chuaTK != null && chuaTK.size() > 0){
                				for(TaiKhoan itemTK: chuaTK){
                		%>
                        <option value="<%=itemTK.getId() %>" <% if(taikhoan != null && Integer.parseInt(taikhoan) == itemTK.getId() ){out.print("selected");} %>><%=itemTK.getFullname() %></option>
                        <% } } %>
                    </select>
                    <label>Giới tính:</label>
                    <select name="gioitinh" id="gioitinh">
                        <option value="Nam" <% if(gioitinh != null && "Nam".equals(gioitinh)){out.print("selected");} %>>Nam</option>
                        <option value="Nữ" <% if(gioitinh != null && "Nữ".equals(gioitinh)){out.print("selected");} %>>Nữ</option>
                    </select>
                    <label>Địa chỉ:</label>
                    <input type="text" id="address" name="address" placeholder="Nhập địa chỉ..." value="<%if(address != null){out.print(address);} %>"><br><br>
                    <label>Vị trí:</label>
                    <select name="chucvu" id="chucvu">
                    	<%
                			ArrayList<LuongCB> listLCB = (ArrayList<LuongCB>) request.getAttribute("listLCB");
                			if(listLCB != null && listLCB.size() > 0){
                				for(LuongCB itemL: listLCB){
                		%>
                        <option value="<%=itemL.getId() %>" <% if(chucvu != null && Integer.parseInt(chucvu) == itemL.getId()){out.print("selected");} %>><%=itemL.getJob() %></option>
                        <% } } %>
                    </select>
                    <input type="submit" value="Thêm" class="submit">
                  </form>    
            </div>
        </div>
<script>
    document.getElementById("nhanvien").classList.add('active');
</script>
<%@include file="/templates/public/inc/footer.jsp" %>  