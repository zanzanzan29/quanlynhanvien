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

import model.bean.LichLam;
import model.bean.TaiKhoan;
import model.dao.LichLamDAO;
import model.dao.TaiKhoanDAO;



public class PublicTaiKhoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TaiKhoanDAO taiKhoanDAO;  
    
    
    public PublicTaiKhoanController() {
        super();
        taiKhoanDAO = new TaiKhoanDAO();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan TKLogin = (TaiKhoan) session.getAttribute("TKLogin");
		if(TKLogin == null ){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		ArrayList<TaiKhoan> taikhoan = taiKhoanDAO.getItems();
		
		request.setAttribute("taikhoan", taikhoan);
		RequestDispatcher rd = request.getRequestDispatcher("/public/taikhoan/index.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
