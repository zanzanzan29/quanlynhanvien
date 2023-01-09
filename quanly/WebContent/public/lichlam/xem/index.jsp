<%@page import="model.bean.LichLam"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
        <div class="content">
            <div class="container">

                <div class="calendar-container">
                    <h4>LỊCH THÁNG 1/2023</h4>
                  <table class="calendar">
                    
                    <thead>
              
                      <tr>
              
                        <td>Thứ 2</td>
                        <td>Thứ 3</td>
                        <td>Thứ 4</td>
                        <td>Thứ 5</td>
                        <td>Thứ 6</td>
                        <td>Thứ 7</td>
                        <td>CN</td>
              
                      </tr>
              
                    </thead>
              
                    <tbody>
              		<%
              			int z = 1;
              			int x = 25;
              			int dem = 0;
              			for(int i = 1;i<= 6;i++){
              				
              		%>
                      <tr>
                      <%
                      	ArrayList<LichLam> listLL = (ArrayList<LichLam>) request.getAttribute("listLL");
                      	for(int j = 1;j<= 7;j++){ 
                      		z++;
                      		x++;
              				if(x > 31){
              					x = 1;
              				}
              				int ht = 3;
              				
              				for(LichLam itLam: listLL){
              					if(itLam.getDkLichLam().getNumber() == x){
              						if(itLam.getStatus() == 0){
              							ht = 0;
                  						break;
              						}else if(itLam.getStatus() == 1){
              							ht = 1;
                  						break;
              						}else{
              							ht = 2;
              							dem++;
              							break;
              						}
              						
              					}else{
              						ht = 3;
              					}
              				}
              			if( z < 8 || z > 38){
                      %>
                      	<td></td>
                      <%continue;  } %>
                        <td class="<%if( ht == 0){out.print("oxanh");}%> "><%if( ht == 0){
                        	%>
                        	<a href="javascript:void(0)" onclick="return getXem(<%=x %>)"><%=x %></a>
                        	<%
                        }else{%><%=x %><% if(ht == 1){ %><img src="<%=request.getContextPath() %>/templates/public/img/dautich.png"/><%} %><% if(ht == 2){ %><img src="<%=request.getContextPath() %>/templates/public/img/daux.png"/><%} %><% } %></td>
                      <% } %>
                      </tr>
              		<% } %>
                      
              
                    </tbody>
              
                  </table>
              
              
                </div> <!-- end calendar-container -->
                <div class="chuthich">
                    <p><img src="<%=request.getContextPath() %>/templates/public/img/dautich.png"/> : Những ngày đã làm việc</p>
                    <p><img src="<%=request.getContextPath() %>/templates/public/img/daux.png"/> : Những ngày đã vắng</p>
                    <p><span>kí tự đỏ</span> : Những ngày có lịch làm</p>
                    <div class="othongbao">
                        <p class="e">Ngày:</p>
                        <p>Thời Gian:</p>
                        <span>-----------------------------------------</span>
                        <p>Số ngày đã vắng: <%=dem %></p>
                    </div>
                </div>
                <script src="https://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
	              								<script type="text/javascript">
												function getXem(ngay){
													$.ajax({
														url: '<%=request.getContextPath()%>/lichlam/xem',
														type: 'POST',
														cache: false,
														data: {
															angay:ngay
														},
														success: function(data){
															$(".othongbao").html(data);
														},
														error: function (){
															alert('Có lỗi xảy ra');
														}
													});
													return false;
												}
											</script>
              </div> <!-- end container -->
        </div>
<script>
    document.getElementById("lichlam").classList.add('active');
</script>
<style>
	a{
	color: red;
	}
</style>
<%@include file="/templates/public/inc/footer.jsp" %> 