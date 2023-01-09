package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.TaiKhoan;
import model.dao.TaiKhoanDAO;
import util.FileUtil;
import util.StringUtil;

@MultipartConfig
public class PublicAddTaiKhoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TaiKhoanDAO taiKhoanDAO;  

    public PublicAddTaiKhoanController() {
        super();
        taiKhoanDAO = new TaiKhoanDAO();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		TaiKhoan TKLogin = (TaiKhoan) session.getAttribute("TKLogin");
		if(TKLogin == null ){
			response.sendRedirect(request.getContextPath() + "/login");
			return;
		}
		if(!"Admin".equals(TKLogin.getPosition())){
			response.sendRedirect(request.getContextPath() + "/taikhoan?err=3");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/public/taikhoan/add.jsp");
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		password = StringUtil.md5(password);
		String fullname = request.getParameter("fullname");
		String position = request.getParameter("position");
		ArrayList<TaiKhoan> taikhoan = taiKhoanDAO.getItems();
		for (TaiKhoan taiKhoan2 : taikhoan) {
			if(username.equals(taiKhoan2.getUsername())){
				RequestDispatcher rd = request.getRequestDispatcher("/public/taikhoan/add.jsp?err=2");
				rd.forward(request, response);
				return;
			}
		}
		Part filePart = request.getPart("picture");
		final String dirPathName = request.getServletContext().getRealPath("/files");
		File dirFile = new File(dirPathName);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		// lấy tên file từ part
		String fileName = FileUtil.getName(filePart);
		// đổi tên file
		String picture = FileUtil.rename(fileName);
		// đường dẫn file
		String filePathName = dirPathName + File.separator + picture;
		TaiKhoan item = new TaiKhoan(0, username, password, fullname, picture, position);
		if(taiKhoanDAO.addItem(item) > 0){
			// thành công
			
			//ghi file
			if(!fileName.isEmpty()){
				filePart.write(filePathName);
			}
			
			response.sendRedirect(request.getContextPath() + "/taikhoan?msg=1");
			return;
		}else{
			// thất bại
			
			RequestDispatcher rd = request.getRequestDispatcher("/public/taikhoan/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
	}

}
