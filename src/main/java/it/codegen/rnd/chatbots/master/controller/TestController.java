package it.codegen.rnd.chatbots.master.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
	@GetMapping("/test")
	public String test()
	{
		return "Endpoint is working.";
	}

}
