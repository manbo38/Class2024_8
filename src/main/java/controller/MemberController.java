package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.Action;
import service.ActionForward;
import service.IdCheck;
import service.Login;
import service.MemberInsert;
import service.Update;
import service.UpdateMember;

@WebServlet("*.do")   // do 확장자로 요청하는 모든 요청을 받는다는 의미
public class MemberController extends HttpServlet {

	// doGet(), doPost() 메소드에서 공통적인 작업을 처리하는 메소드
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		System.out.println("requestURI:"+ requestURI);    // /model2member/MemberInsert.do
		System.out.println("contextPath:"+ contextPath);  // /model2member
		System.out.println("command:"+ command);          // /MemberInsert.do
		
		Action action = null;
		ActionForward forward = null;
		
		// 회원가입
		if(command.equals("/MemberInsert.do")) {
			try {
				action = new MemberInsert();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		// ID 중복검사(ajax)
		}else if(command.equals("/IdCheck.do")){
			try {
				action = new IdCheck();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		// 회원가입 폼
		}else if(command.equals("/MemberForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/memberform.jsp");
			
		// 로그인 폼
		}else if(command.equals("/LoginForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/loginform.jsp");
			
		// 로그인(회원인증)
		}else if(command.equals("/Login.do")){
			try {
				action = new Login();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		
		// 로그아웃
		}else if(command.equals("/Logout.do")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./member/logout.jsp");
			
		// 회원정보 수정 폼
		}else if(command.equals("/UpdateMember.do")) {
			try {
				action = new UpdateMember();
				forward = action.execute(request, response);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		// 회원정보 수정	
		}else if(command.equals("/Update.do")){
			try {
				action = new Update();
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();			
				}
		}
		
		// 포워딩 처리
		if(forward != null) {
			if(forward.isRedirect()) {  // redirect 방식으로 포워딩		
				response.sendRedirect(forward.getPath());
			}else {						// dispatcher 방식으로 포워딩		
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}	
		
	} // doProcess() end	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("get");	
		 
		doProcess(request, response);      // doProcess() 메소드 호출
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("post");

		doProcess(request, response);
	}

}






