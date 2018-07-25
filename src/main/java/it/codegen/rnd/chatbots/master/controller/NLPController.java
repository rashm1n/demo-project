package it.codegen.rnd.chatbots.master.controller;

import it.codegen.rnd.chatbots.master.service.NLPPipelineService;
import it.codegen.rnd.chatbots.nlp.response.NLPResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NLPController
{
	@Autowired
	private NLPPipelineService nlpPipelineService;

	@GetMapping("response")
	public ResponseEntity<NLPResponse> processQuery( @RequestParam String query )
	{
		NLPResponse response = nlpPipelineService.run( query );
		if ( response == null )
		{
			return new ResponseEntity( HttpStatus.INTERNAL_SERVER_ERROR );
		}
		else
		{
			return ResponseEntity.ok( response );
		}
	}
}
