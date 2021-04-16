package com.packt.webstore.domain;

import java.util.ArrayList;
import java.util.List;

public class MainDriver {

	void testMethod(String... args) {
		System.out.println(args.length);
	}
	
	public void test() {
		List<String> roleTkns = new ArrayList<>();
		List<String> roles = new ArrayList<>();
		roleTkns.add("AD");
		roleTkns.add("US");
		
		roleTkns.forEach(tkn -> {
			String roleName = Role.valueOf(tkn).label;
			roles.add(Role.valueOf(tkn).label);
		});
		Object obj = roles.toArray(new String[roles.size()]);
//		testMethod(roles.toArray(new String[roles.size()]));
		System.out.println();		
	}
	
	public static void main(String[] args) {
		MainDriver driver = new MainDriver();
		driver.test();
	}
}