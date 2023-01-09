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
import model.bean.TaiKhoan;
import model.dao.LichLamDAO;
import model.dao.LuongCBDAO;


public class PublicIndexXemLichLamController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LichLamDAO lichLamDAO;
	
    public PublicIndexXemLichLamController() {
        super();
        lichLamDAO = new LichLamDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan TKLogin = (TaiKhoan) session.getAttribute("TKLogin");
		if(TKLogin == null ){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int id_tk = TKLogin.getId();
		ArrayList<LichLam> listLL = lichLamDAO.getItemsTT(id_tk);
		request.setAttribute("listLL", listLL);
		RequestDispatcher rd = request.getRequestDispatcher("/public/lichlam/xem/index.jsp");
		rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan TKLogin = (TaiKhoan) session.getAttribute("TKLogin");
		if(TKLogin == null ){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		int id_tk = TKLogin.getId();
		ArrayList<LichLam> listLL = lichLamDAO.getItemsTTSS(id_tk);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int ngay = Integer.parseInt(request.getParameter("angay"));
		int ht = 00;
		for(LichLam itLam: listLL){
			if(itLam.getStatus() == 2){
				ht++;
			}
		}
		for (LichLam lichLam : listLL) {
			if(ngay == lichLam.getDkLichLam().getNumber()){
				response.getWriter().print(
						"<p class=\"e\">Ngày: "+ lichLam.getDkLichLam().getDate() +"</p>"+
						"<p>Thời Gian: " + lichLam.getDkLichLam().getTimebd() + " đến " + lichLam.getDkLichLam().getTimekt() + "</p>"+
                        "<span>-----------------------------------------</span>"+
                        "<p>Số ngày đã vắng: " + ht + "</p>"
						);
			}
		}
		
	}

}
