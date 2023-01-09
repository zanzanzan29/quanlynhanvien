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
import model.dao.LuongCBDAO;


public class PublicEditLuongCBController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LuongCBDAO luongCBDAO;   

    public PublicEditLuongCBController() {
        super();
        luongCBDAO = new LuongCBDAO();
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
			response.sendRedirect(request.getContextPath() + "/luong/coban?err=1");
			return;
		}
		LuongCB itemLCB = luongCBDAO.getItem(id);
		request.setAttribute("itemLCB", itemLCB);
		RequestDispatcher rd = request.getRequestDispatcher("/public/luong/luongcoban/edit.jsp");
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
			response.sendRedirect(request.getContextPath() + "/luong/coban?err=1");
			return;
		}
		String job = request.getParameter("job");
		int money = Integer.parseInt(request.getParameter("money"));
		
		LuongCB luongCB = new LuongCB(id, job, money);
		if(luongCBDAO.editItem(luongCB) > 0){
			response.sendRedirect(request.getContextPath() + "/luong/coban?msg=2");
			return;
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/public/luong/luongcoban/edit.jsp?err=1");
			rd.forward(request, response);
		}
	}

}
