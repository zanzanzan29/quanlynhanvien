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
import model.bean.TaiKhoan;
import model.dao.LuongCBDAO;


public class PublicAddLuongCBController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LuongCBDAO luongCBDAO;   

    public PublicAddLuongCBController() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/public/luong/luongcoban/add.jsp");
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
		String job = request.getParameter("job");
		int money = Integer.parseInt(request.getParameter("money"));
		ArrayList<LuongCB> listLCB = luongCBDAO.getItems();
		for (LuongCB item : listLCB) {
			if(job.equals(item.getJob())){
				RequestDispatcher rd = request.getRequestDispatcher("/public/luong/luongcoban/add.jsp?err=2");
				rd.forward(request, response);
				return;
			}
		}
		LuongCB item = new LuongCB(0, job, money);
		if(luongCBDAO.addItem(item) > 0){
			// thành công
			
			response.sendRedirect(request.getContextPath() + "/luong/coban?msg=1");
			return;
		}else{
			// thất bại
			
			RequestDispatcher rd = request.getRequestDispatcher("/public/luong/luongcoban/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
