package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.TaiKhoan;
import model.dao.TaiKhoanDAO;


public class PublicDelTaiKhoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TaiKhoanDAO taiKhoanDAO;   

    public PublicDelTaiKhoanController() {
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
		
		if(!"Admin".equals(TKLogin.getPosition())){
			response.sendRedirect(request.getContextPath() + "/taikhoan?err=5");
			return;
		}else{
			TaiKhoan taiKhoan = taiKhoanDAO.getItem(id);
			if("Admin".equals(taiKhoan.getPosition())){
				response.sendRedirect(request.getContextPath() + "/taikhoan?err=5");
				return;
			}else{
				if(taiKhoanDAO.delItem(id) > 0){
					//thành công
					
					//xóa ảnh
					final String dirPathName = request.getServletContext().getRealPath("/files");
					String picture = taiKhoan.getPicture();
					if(!picture.isEmpty()){
						String filePathName = dirPathName + File.separator + picture;
						File file = new File(filePathName);
						if(file.exists()){
							file.delete();
						}
					}
					
					
					response.sendRedirect(request.getContextPath() + "/taikhoan?msg=3");
					return;
				}else{
					//thất bại
					response.sendRedirect(request.getContextPath() + "/taikhoan?err=2");
					return;
				}
			}
		}
		
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
