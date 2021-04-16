package com.packt.webstore.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class PromoCodeInterceptor extends HandlerInterceptorAdapter {
	private String promoCode;
	private String offerRedirect;
	private String errorRedirect;
	
	@Override
	public boolean preHandle(HttpServletRequest request, 
			HttpServletResponse response, Object handler) 
					throws IOException {
		String givenPromoCode = (String)request.getParameter("promo");
		
		System.out.println("request.getContextPath(): " + 
				request.getContextPath());
		System.out.println("request.getRequestURI(): " + 
				request.getRequestURI());
		System.out.println("request.getRequestURL(): " + 
				request.getRequestURL());
		
		if (promoCode.equals(givenPromoCode)) {
			response.sendRedirect(request.getContextPath() 
					+ "/" + offerRedirect);
		} else {
			response.sendRedirect(request.getContextPath() 
					+ errorRedirect);
		}
		return false;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public void setOfferRedirect(String offerRedirect) {
		this.offerRedirect = offerRedirect;
	}

	public void setErrorRedirect(String errorRedirect) {
		this.errorRedirect = errorRedirect;
	}
	
}
