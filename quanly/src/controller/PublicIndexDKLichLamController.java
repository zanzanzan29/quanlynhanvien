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

import model.bean.DKLichLam;
import model.bean.LichLam;
import model.bean.LuongCB;
import model.bean.TaiKhoan;
import model.dao.DKLichLamDAO;
import model.dao.LichLamDAO;
import model.dao.LuongCBDAO;


public class PublicIndexDKLichLamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private DKLichLamDAO dkLichLamDAO;
    private LichLamDAO lichLamDAO;
    
    public PublicIndexDKLichLamController() {
        super();
        dkLichLamDAO = new DKLichLamDAO();
        lichLamDAO = new LichLamDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan TKLogin = (TaiKhoan) session.getAttribute("TKLogin");
		if(TKLogin == null ){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		
		long millis=System.currentTimeMillis();   
		java.sql.Date date=new java.sql.Date(millis);
		int id_tk = TKLogin.getId();
		ArrayList<DKLichLam> listDKLL = dkLichLamDAO.getItems(date);
		ArrayList<LichLam> listLL = lichLamDAO.getItems(id_tk);
		for (LichLam lichLam : listLL) {
			System.out.println(lichLam);
		}
		request.setAttribute("listLL", listLL);
		request.setAttribute("listDKLL", listDKLL);
		RequestDispatcher rd = request.getRequestDispatcher("/public/lichlam/dangky/index.jsp");
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
		
		String[] id_dk = request.getParameterValues("id_dk");
		int check = 0;
		for(String kt: id_dk){
			int did = Integer.valueOf(kt);
			DKLichLam dkLichLam = new DKLichLam(did, null, null, null, 0);
			TaiKhoan taiKhoan = new TaiKhoan(TKLogin.getId(), null, null, null, null, null);
			LichLam item = new LichLam(0, dkLichLam, taiKhoan, 0);
			if(lichLamDAO.addItem(item) > 0){
				check++;
			}
		}
		if(check != 0){
			response.sendRedirect(request.getContextPath() + "/lichlam/dangky?msg=1");
			return;
		}else{
			response.sendRedirect(request.getContextPath() + "/lichlam/dangky?err=3");
			return;
		}
	}

}
