package service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface Action {

	// 추상 메소드
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
