package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AdminDAO;
import entity.Admin;
import factory.DAOFactory;


public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		AdminDAO dao = (AdminDAO) DAOFactory.getInstance("AdminDAO");
//		获取请求资源路径
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));

		if ("/list".equals(action)) {
			try {
				//获取页容以及页码pageSize page
				ServletConfig config = getServletConfig();
				int pageSize = Integer.parseInt(config.getInitParameter("pageSize"));
				//获取页码list.do?page=2
				String pageStr = request.getParameter("page");
				int page = pageStr == null ? 1 : Integer.parseInt(pageStr);						
				List<Admin> admins = dao.findAll(page,pageSize);
//				获取总页数
				int totalPages = dao.getTotalPages(pageSize);				
//				1.将数据绑定在request上
				request.setAttribute("page", page);
				request.setAttribute("pageSize", pageSize);
				request.setAttribute("totalPages", totalPages);
				request.setAttribute("admins", admins);
//				2.将数据交给jsp去展示
//				2.1获取转发器
				RequestDispatcher rd = request.getRequestDispatcher("list.jsp");
				// 2.2转发
				rd.forward(request, response);

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("/add".equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String realname = request.getParameter("realname");
			Admin admin = new Admin(username, password, realname);
			try {
				dao.add(admin);
				response.sendRedirect("list.do");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("/del".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				dao.del(id);
				response.sendRedirect("list.do");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("/load".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			try {
				Admin admin = dao.findById(id);
				request.setAttribute("admin", admin);
				request.getRequestDispatcher("update.jsp").
				forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("/update".equals(action)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String realname = request.getParameter("realname");
			Admin admin = new Admin(id, username, password, realname);
			try {
				dao.update(admin);
				response.sendRedirect("list.do");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if("/login".equals(action)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			try {
				Admin admin = dao.findByUsername(username);
				if(admin != null && admin.getPassword().equals(password)) {
//					登陆成功
					response.sendRedirect("list.do");
				}else {
//					登录失败
					request.setAttribute("login_msg", "用户名或者密码错误");
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
