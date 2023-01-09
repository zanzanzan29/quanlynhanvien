package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import util.FileUtil;
import util.StringUtil;
import model.bean.LuongCB;
import model.bean.NhanVien;
import model.bean.TaiKhoan;
import model.dao.LuongCBDAO;
import model.dao.NhanVienDAO;
import model.dao.TaiKhoanDAO;


public class PublicEditNhanVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LuongCBDAO luongCBDAO;
    private NhanVienDAO nhanVienDAO;
    private TaiKhoanDAO taiKhoanDAO;

    public PublicEditNhanVienController() {
        super();
        luongCBDAO = new LuongCBDAO();
        nhanVienDAO = new NhanVienDAO();
        taiKhoanDAO = new TaiKhoanDAO();
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
			response.sendRedirect(request.getContextPath() + "/nhanvien?err=1");
			return;
		}
		NhanVien itemNV = nhanVienDAO.getItem(id);
		ArrayList<LuongCB> listLCB = luongCBDAO.getItems();
		ArrayList<TaiKhoan> chuaTK = taiKhoanDAO.getItems();
		request.setAttribute("chuaTK", chuaTK);
		request.setAttribute("listLCB", listLCB);
		request.setAttribute("itemNV", itemNV);
		RequestDispatcher rd = request.getRequestDispatcher("/public/nhanvien/edit.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan TKLogin = (TaiKhoan) session.getAttribute("TKLogin");
		if(TKLogin == null ){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		request.setCharacterEncoding("UTF-8");
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/nhanvien?err=1");
			return;
		}
    	String gioitinh = request.getParameter("gioitinh");
    	String address = request.getParameter("address");
    	int chucvu = Integer.parseInt(request.getParameter("chucvu"));
		
    	LuongCB luongCB = new LuongCB(chucvu, null, 0);
		TaiKhoan tKhoan = new TaiKhoan(0, null, null, null, null, null);
		NhanVien nhanVien = new NhanVien(id, gioitinh, address, luongCB, tKhoan);
		if(nhanVienDAO.editItem(nhanVien) > 0){
			response.sendRedirect(request.getContextPath() + "/nhanvien?msg=2");
			return;
		}else{
			ArrayList<LuongCB> listLCB = luongCBDAO.getItems();
			ArrayList<TaiKhoan> chuaTK = taiKhoanDAO.getItems();
			request.setAttribute("chuaTK", chuaTK);
			request.setAttribute("listLCB", listLCB);
			RequestDispatcher rd = request.getRequestDispatcher("/public/nhanvien/edit.jsp?err=1");
			rd.forward(request, response);
		}
	}

}
