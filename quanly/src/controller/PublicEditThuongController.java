package controller;

import java.io.File;
import java.io.IOException;

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
import model.bean.TaiKhoan;
import model.bean.Thuong;
import model.dao.LuongCBDAO;
import model.dao.ThuongDAO;


public class PublicEditThuongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ThuongDAO thuongDAO;

    public PublicEditThuongController() {
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
		Thuong itemT = thuongDAO.getItem(id);
		request.setAttribute("itemT", itemT);
		RequestDispatcher rd = request.getRequestDispatcher("/public/luong/thuong/edit.jsp");
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
			response.sendRedirect(request.getContextPath() + "/luong/thuong?err=1");
			return;
		}
		String name = request.getParameter("name");
		int time = Integer.parseInt(request.getParameter("time"));
		int money = Integer.parseInt(request.getParameter("money"));
		
		Thuong thuong = new Thuong(id, name, time, money);
		if(thuongDAO.editItem(thuong) > 0){
			response.sendRedirect(request.getContextPath() + "/luong/thuong?msg=2");
			return;
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/public/luong/thuong/edit.jsp?err=1");
			rd.forward(request, response);
		}
	}

}
