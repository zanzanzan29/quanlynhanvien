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

import model.bean.LuongCB;
import model.bean.TaiKhoan;
import model.bean.Thuong;
import model.dao.LuongCBDAO;
import model.dao.ThuongDAO;


public class PublicIndexThuongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ThuongDAO thuongDAO;   

    public PublicIndexThuongController() {
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
		ArrayList<Thuong> listT = thuongDAO.getItems();
		request.setAttribute("listT", listT);
		RequestDispatcher rd = request.getRequestDispatcher("/public/luong/thuong/index.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
