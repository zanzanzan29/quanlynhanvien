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
import model.bean.LuongCB;
import model.bean.NhanVien;
import model.bean.TaiKhoan;
import model.dao.LichLamDAO;
import model.dao.LuongCBDAO;
import model.dao.NhanVienDAO;


public class PublicIndexNhanVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private NhanVienDAO nhanVienDAO;  
    private LichLamDAO lichLamDAO;

    public PublicIndexNhanVienController() {
        super();
        nhanVienDAO = new NhanVienDAO();
        lichLamDAO = new LichLamDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan TKLogin = (TaiKhoan) session.getAttribute("TKLogin");
		if(TKLogin == null ){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		ArrayList<NhanVien> listNV = nhanVienDAO.getItems();
		ArrayList<LichLam> listLL = lichLamDAO.getItems();
		request.setAttribute("listLL", listLL);
		request.setAttribute("listNV", listNV);
		RequestDispatcher rd = request.getRequestDispatcher("/public/nhanvien/index.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
