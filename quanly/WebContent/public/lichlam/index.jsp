<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
        <div class="content">
            <div class="ochon">
                <a href="<%=request.getContextPath()%>/lichlam/dangky">
                    <img src="<%=request.getContextPath() %>/templates/public/img/dangkylichlam.png" alt="" class="dkll"/>
                    <p>Đăng Ký Lịch làm</p>
                </a>
            </div>
            <div class="ochon">
                <a href="<%=request.getContextPath()%>/lichlam/xem">
                    <img src="<%=request.getContextPath() %>/templates/public/img/xemlichlam.png" alt="" class="xll"/>
                    <p>Xem Lịch Làm</p>
                </a>
            </div>
            <div class="clr"></div>
        </div>
<script>
    document.getElementById("lichlam").classList.add('active');
</script>
<%@include file="/templates/public/inc/footer.jsp" %> 