package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.TaiKhoan;
import model.dao.LuongCBDAO;
import model.dao.ThuongDAO;


public class PublicDelThuongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ThuongDAO thuongDAO; 

    public PublicDelThuongController() {
        super();
        thuongDAO = new ThuongDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan TKLogin = (TaiKhoan) session.getAttribute("TKLogin");
		if(TKLogin == null ){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/luong/thuong?err=1");
			return;
		}
		if(thuongDAO.delItem(id) > 0){
			//thành công
			
			response.sendRedirect(request.getContextPath() + "/luong/thuong?msg=3");
			return;
		}else{
			//thất bại
			response.sendRedirect(request.getContextPath() + "/luong/thuong?err=2");
			return;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
