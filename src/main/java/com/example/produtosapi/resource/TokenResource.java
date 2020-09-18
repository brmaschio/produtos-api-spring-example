package com.example.produtosapi.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.produtosapi.conf.ProjectProperty;

@RestController
@RequestMapping("/tokens")
public class TokenResource {

	@Autowired
	private ProjectProperty propriedades;
	
	@DeleteMapping("/revoke")
	public void revoke(HttpServletRequest request, HttpServletResponse response) {
		
		Cookie refreshTokenCookie = new Cookie("refreshToken", null);
		refreshTokenCookie.setHttpOnly(true);
		refreshTokenCookie.setSecure(propriedades.getSeguranca().isEnableHttps());
		refreshTokenCookie.setPath(request.getContextPath() + "/oauth/token");
		refreshTokenCookie.setMaxAge(0);
		
		response.addCookie(refreshTokenCookie);
		response.setStatus(HttpStatus.NO_CONTENT.value());
		
	}
	
}
