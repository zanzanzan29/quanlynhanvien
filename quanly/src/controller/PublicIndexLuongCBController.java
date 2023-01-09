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
import model.dao.LuongCBDAO;


public class PublicIndexLuongCBController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private LuongCBDAO luongCBDAO;   

    public PublicIndexLuongCBController() {
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
		ArrayList<LuongCB> listLCB = luongCBDAO.getItems();
		request.setAttribute("listLCB", listLCB);
		RequestDispatcher rd = request.getRequestDispatcher("/public/luong/luongcoban/index.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
