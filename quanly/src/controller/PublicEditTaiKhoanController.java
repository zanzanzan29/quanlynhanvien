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

import util.FileUtil;
import util.StringUtil;
import model.bean.TaiKhoan;
import model.dao.TaiKhoanDAO;

@MultipartConfig
public class PublicEditTaiKhoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TaiKhoanDAO taiKhoanDAO;   

    public PublicEditTaiKhoanController() {
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
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			response.sendRedirect(request.getContextPath() + "/taikhoan?err=1");
			return;
		}
		TaiKhoan itemTK = taiKhoanDAO.getItem(id);
		if(!"Admin".equals(TKLogin.getPosition())){
			if(TKLogin.getId() == itemTK.getId()){
				request.setAttribute("itemTK", itemTK);
				RequestDispatcher rd = request.getRequestDispatcher("/public/taikhoan/edit.jsp");
				rd.forward(request, response);
				return;
			}else{
				response.sendRedirect(request.getContextPath() + "/taikhoan?err=4");
				return;
			}
		}
		request.setAttribute("itemTK", itemTK);
		RequestDispatcher rd = request.getRequestDispatcher("/public/taikhoan/edit.jsp");
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
			response.sendRedirect(request.getContextPath() + "/taikhoan?err=1");
			return;
		}
		String password = request.getParameter("password");
		String fullname = request.getParameter("fullname");
		String position = request.getParameter("position");
		TaiKhoan itemTK = taiKhoanDAO.getItem(id);
		if("".equals(password)){
			password = itemTK.getPassword();
		}else{
			password = StringUtil.md5(password);
		}
		
		Part filePart = request.getPart("picture");
		// tạo thư mục lưu ảnh
		final String dirPathName = request.getServletContext().getRealPath("/files");
		File dirFile = new File(dirPathName);
		if(!dirFile.exists()){
			dirFile.mkdirs();
		}
		
		// lấy tên file từ part
		String fileName = FileUtil.getName(filePart);
		// đổi tên file
		String picture = "";
		if(fileName.isEmpty()){
			picture = itemTK.getPicture();
		} else{
			picture = FileUtil.rename(fileName);
		}
		// đường dẫn file
		String filePathName = dirPathName + File.separator + picture;
		
		TaiKhoan taiKhoan = new TaiKhoan(id, null, password, fullname, picture, position);
		if(taiKhoanDAO.editItem(taiKhoan) > 0){
			if(!fileName.isEmpty()){
				//xóa file cũ
				String oldFilePathName = dirPathName + File.separator + itemTK.getPicture();
				File oldFile = new File(oldFilePathName);
				if(oldFile.exists()){
					oldFile.delete();
				}
				
				//ghi file
				filePart.write(filePathName);
			}
			response.sendRedirect(request.getContextPath() + "/taikhoan?msg=2");
			return;
		}else{
			RequestDispatcher rd = request.getRequestDispatcher("/public/taikhoan/edit.jsp?err=1");
			rd.forward(request, response);
		}
	}

}
