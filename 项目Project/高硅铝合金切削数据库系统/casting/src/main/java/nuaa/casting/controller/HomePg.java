package nuaa.casting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePg {
	@RequestMapping("/hello.do")
	public String printWelcome() {
		return "homepg";
	}
}
