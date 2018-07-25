package it.codegen.rnd.chatbots.master.controller;

import it.codegen.rnd.chatbots.master.service.GazetteerService;
import it.codegen.rnd.chatbots.master.service.IntentClassifierTrainerService;
import it.codegen.rnd.chatbots.master.service.NERTrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainController
{

	@Autowired
	private NERTrainerService nerTrainerService;

	@Autowired
	private IntentClassifierTrainerService intentClassifierTrainerService;

	@Autowired
	private GazetteerService gazetteerService;

	@PostMapping("/training")
	public ResponseEntity train()
	{
		gazetteerService.build();
		intentClassifierTrainerService.train();
		nerTrainerService.train();
		return ResponseEntity.ok().build();
	}

}
