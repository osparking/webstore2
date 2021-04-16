package com.packt.webstore.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/") 
public class HomeController {
	@RequestMapping 
	public String welcome(Model model, RedirectAttributes reAttrs, Principal prin) {
		String msg1 = "환영합니다.";
		String msg2 = "세상에 하나뿐인 웹 가게";
		model.addAttribute("greeting", msg1);
		model.addAttribute("tagline", msg2);
		
		if (prin != null) {
			model.addAttribute("username", prin.getName());
		}
		reAttrs.addFlashAttribute("greeting", msg1);
		reAttrs.addFlashAttribute("tagline", msg2);
//		return "welcome";
//		return "redirect:/welcome/greeting";
		return "forward:/welcome/greeting";
	}
	@RequestMapping("/welcome/greeting")
	public String greeting() {
		return "welcome";
	}
}
