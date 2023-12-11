package com.kh.user.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.user.dao.UserDAO;
import com.kh.user.dto.UserDTO;

@WebServlet("/SelectUserServlet")
public class SelectUserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		UserDAO dao = new UserDAO();
		UserDTO dto = dao.selectUser(userNo);
		request.setAttribute("User", dto);
		RequestDispatcher rd = null;
		
		if (dto != null) {
			rd = request.getRequestDispatcher("searchSuccess.jsp");
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("searchFail.jsp");
			rd.forward(request, response);
		}
	}
}
