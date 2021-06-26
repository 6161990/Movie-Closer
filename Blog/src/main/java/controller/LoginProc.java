package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dto.MemberBean;



@WebServlet("/loginProc")
public class LoginProc extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		reqPro(request,response);
	}
	private void reqPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("UTF-8");
	    
		//전송정보 얻기
	    String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		MemberDAO mdao = new MemberDAO();
		MemberBean member = mdao.getLoginValid(id, pass);
		
		HttpSession session = request.getSession();	
		
		if(member==null){
		   request.setAttribute("msg", "아이디 또는 비밀번호가 맞지않습니다.");
		   RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
	       dis.forward(request, response);
		}else{ //일치하는 회원이 있다면 세션에 setting
	  //   mdao.insertLoginMember(id,pass);
		   session.setAttribute("member", member);
	       request.setAttribute("id", id);
		   response.sendRedirect("index.jsp");
	    }
	}
}
