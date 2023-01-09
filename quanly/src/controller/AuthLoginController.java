package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.StringUtil;
import model.bean.TaiKhoan;
import model.dao.TaiKhoanDAO;


public class AuthLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TaiKhoanDAO taiKhoanDAO; 

    public AuthLoginController() {
        super();
        taiKhoanDAO = new TaiKhoanDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan TKLogin = (TaiKhoan) session.getAttribute("TKLogin");
		if(TKLogin != null ){
			response.sendRedirect(request.getContextPath() + "/nhanvien");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/auth/login.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan TKLogin = (TaiKhoan) session.getAttribute("TKLogin");
		if(TKLogin != null ){
			response.sendRedirect(request.getContextPath() + "/nhanvien");
			return;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = StringUtil.md5(password);
		TaiKhoan taiKhoan = taiKhoanDAO.kiemtra(username,password);
		if(taiKhoan != null){
			session.setAttribute("TKLogin", taiKhoan);
			response.sendRedirect(request.getContextPath() + "/nhanvien");
			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/login?err=1");
			return;
		}
	}

}
