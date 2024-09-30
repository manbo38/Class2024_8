package service;

import dao.MemberDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.MemberDTO;

public class MemberInsert implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberInsert");
		
		request.setCharacterEncoding("utf-8");
		
		MemberDTO member = new MemberDTO();
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setName(request.getParameter("name"));
		member.setJumin1(request.getParameter("jumin1"));
		member.setJumin2(request.getParameter("jumin2"));
		member.setMailid(request.getParameter("mailid"));
		member.setDomain(request.getParameter("domain"));
		member.setTel1(request.getParameter("tel1"));
		member.setTel2(request.getParameter("tel2"));
		member.setTel3(request.getParameter("tel3"));
		member.setPhone1(request.getParameter("phone1"));
		member.setPhone2(request.getParameter("phone2"));
		member.setPhone3(request.getParameter("phone3"));
		member.setPost(request.getParameter("post"));
		member.setAddress(request.getParameter("address"));
		member.setGender(request.getParameter("gender"));
		
		String[] hobby = request.getParameterValues("hobby");
		String h = "";					// String h = "공부-게임-";
		for(String h1 : hobby) {
			h += h1 + "-";			
		}
		member.setHobby(h);
		member.setIntro(request.getParameter("intro"));
		
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.insert(member);		// insert SQL문 실행
		if(result == 1) System.out.println("회원가입 성공");
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);   			   // dispatcher 방식으로 포워딩
		forward.setPath("./member/loginform.jsp"); // 포워딩할 파일명
		
		return forward;
	}

}




