<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
        <div class="content">
            <div class="ochon">
                <a href="<%=request.getContextPath()%>/luong/coban">
                    <img src="<%=request.getContextPath() %>/templates/public/img/luongcb.png" alt=""/>
                    <p>Lương Cơ Bản</p>
                </a>
            </div>
            <div class="ochon">
                <a href="<%=request.getContextPath() %>/luong/thuong">
                    <img src="<%=request.getContextPath() %>/templates/public/img/luongt.png" alt="" class="kt"/>
                    <p>Lương Thưởng</p>
                </a>
            </div>
            <div class="clr"></div>
        </div>
<script>
    document.getElementById("tienluong").classList.add('active');
</script>
<%@include file="/templates/public/inc/footer.jsp" %> 