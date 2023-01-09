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
import model.bean.Thuong;
import model.dao.LuongCBDAO;
import model.dao.ThuongDAO;


public class PublicAddThuongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ThuongDAO thuongDAO;

    public PublicAddThuongController() {
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
		RequestDispatcher rd = request.getRequestDispatcher("/public/luong/thuong/add.jsp");
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
		String name = request.getParameter("name");
		int time = Integer.parseInt(request.getParameter("time"));
		int money = Integer.parseInt(request.getParameter("money"));
		ArrayList<Thuong> listT = thuongDAO.getItems();
		for (Thuong item : listT) {
			if(name.equals(item.getName())){
				RequestDispatcher rd = request.getRequestDispatcher("/public/luong/thuong/add.jsp?err=2");
				rd.forward(request, response);
				return;
			}
		}
		Thuong item = new Thuong(0, name, time, money);
		if(thuongDAO.addItem(item) > 0){
			// thành công
			
			response.sendRedirect(request.getContextPath() + "/luong/thuong?msg=1");
			return;
		}else{
			// thất bại
			
			RequestDispatcher rd = request.getRequestDispatcher("/public/luong/thuong/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
