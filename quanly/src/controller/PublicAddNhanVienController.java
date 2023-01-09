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


public class PublicAddNhanVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LuongCBDAO luongCBDAO;
    private NhanVienDAO nhanVienDAO;
    private TaiKhoanDAO taiKhoanDAO;

    public PublicAddNhanVienController() {
        super();
        nhanVienDAO = new NhanVienDAO();
        taiKhoanDAO = new TaiKhoanDAO();
        luongCBDAO = new LuongCBDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan TKLogin = (TaiKhoan) session.getAttribute("TKLogin");
		if(TKLogin == null ){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		ArrayList<TaiKhoan> listTK = taiKhoanDAO.getItemsNV(); 
		ArrayList<TaiKhoan> chuaTK = new ArrayList<>();
		ArrayList<NhanVien> listNV = nhanVienDAO.getItems();
		for (TaiKhoan taiKhoan: listTK) {
			int a = 0;
			for (NhanVien nhanVien : listNV) {
				if(nhanVien.getTaiKhoan().getId() == taiKhoan.getId()){
					a++;
				}
			}
			if(a == 0){
				chuaTK.add(taiKhoan);
			}
		}
		ArrayList<LuongCB> listLCB = luongCBDAO.getItems();
		if(chuaTK.size() <= 0 || listLCB.size() <= 0){
			response.sendRedirect(request.getContextPath() + "/nhanvien?err=3");
			return;
		}
		request.setAttribute("chuaTK", chuaTK);
		request.setAttribute("listLCB", listLCB);
		RequestDispatcher rd = request.getRequestDispatcher("/public/nhanvien/add.jsp");
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
		int taikhoan = Integer.parseInt(request.getParameter("taikhoan"));
    	String gioitinh = request.getParameter("gioitinh");
    	String address = request.getParameter("address");
    	int chucvu = Integer.parseInt(request.getParameter("chucvu"));
		LuongCB luongCB = new LuongCB(chucvu, null, 0);
		TaiKhoan tKhoan = new TaiKhoan(taikhoan, null, null, null, null, null);
		NhanVien item = new NhanVien(0, gioitinh, address, luongCB, tKhoan);
		if(nhanVienDAO.addItem(item) > 0){
			// thành công
			
			response.sendRedirect(request.getContextPath() + "/nhanvien?msg=1");
			return;
		}else{
			// thất bại
			ArrayList<TaiKhoan> listTK = taiKhoanDAO.getItemsNV(); 
			ArrayList<TaiKhoan> chuaTK = new ArrayList<>();
			ArrayList<NhanVien> listNV = nhanVienDAO.getItems();
			for (TaiKhoan taiKhoan: listTK) {
				int a = 0;
				for (NhanVien nhanVien : listNV) {
					if(nhanVien.getTaiKhoan().getId() == taiKhoan.getId()){
						a++;
					}
				}
				if(a == 0){
					chuaTK.add(taiKhoan);
				}
			}
			ArrayList<LuongCB> listLCB = luongCBDAO.getItems();
			if((chuaTK.size() <= 0 || listLCB.size() <= 0) ){
				response.sendRedirect(request.getContextPath() + "/nhanvien?err=3");
				return;
			}
			request.setAttribute("chuaTK", chuaTK);
			request.setAttribute("listLCB", listLCB);
			
			RequestDispatcher rd = request.getRequestDispatcher("/public/nhanvien/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
