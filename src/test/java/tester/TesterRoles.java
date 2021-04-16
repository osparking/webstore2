package tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.packt.webstore.domain.Role;

public class TesterRoles {
	static void testMethod(String...args) {
		System.out.println(args.length);
	}
	
	public static void main(String...args) {
		List<String> roleTkns = new ArrayList<>();
		List<String> roles = new ArrayList<>();
		roleTkns.add("AD");
		roleTkns.add("US");
		
		roleTkns.forEach(tkn -> {
			String roleName = Role.valueOf(tkn).label;
			roles.add(Role.valueOf(tkn).label);
		});
//		testMethod(roles.toArray()); wrong
		testMethod(roles.toArray(new String[roles.size()]));
	}
}
