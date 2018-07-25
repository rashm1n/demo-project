package it.codegen.rnd.chatbots.master.controller;

import it.codegen.rnd.chatbots.master.Utils.ResponseSpecification;
import it.codegen.rnd.chatbots.master.Utils.SearchCriteria;
import it.codegen.rnd.chatbots.master.model.entity.response.BotResponseEntity;
import it.codegen.rnd.chatbots.master.repository.response.BotResponseRepository;
import it.codegen.rnd.chatbots.master.repository.response.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
public class BotResponseController
{

	@Autowired
	private BotResponseRepository botResponseRepository;

	@Autowired
	private UrlRepository urlRepository;

	@PersistenceContext
	private EntityManager entityManager;


	@PostMapping("/botResponse")
	public ResponseEntity<BotResponseEntity> create( @RequestBody BotResponseEntity entity )
	{

		try
		{
			BotResponseEntity savedEntity = botResponseRepository.save( entity );
			if ( savedEntity != null && savedEntity.getId() > 0L )
			{

				return ResponseEntity.ok( savedEntity );
			}
			else
			{
				return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
			}

		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}
		return null;
	}

	@GetMapping("/botResponses")
	public ResponseEntity<List<BotResponseEntity>> getAll()
	{

		try
		{

			List<BotResponseEntity> entities = botResponseRepository.findAll();
			if ( entities != null && entities.size() > 0 )
			{
				return ResponseEntity.ok( entities );
			}
			else
			{
				return ResponseEntity.notFound().build();
			}

		}
		catch ( Exception ex )
		{

			ex.printStackTrace();

		}

		return null;

	}

	@RequestMapping(value = "/botReply",method = RequestMethod.GET)
	public ResponseEntity<List<BotResponseEntity>> getReply(
			@RequestParam(value = "intentName", required = false) String intentName,
			@RequestParam(value = "entityNames", required = false) String entityNames )
	{

		try
		{
			ResponseSpecification specification1 = new ResponseSpecification( new SearchCriteria( "intentName", ":", intentName ) );
			ResponseSpecification specification2 = new ResponseSpecification( new SearchCriteria( "entityNames", ":", entityNames ) );
			List<BotResponseEntity> results = botResponseRepository.findAll( Specification.where( specification1 ).and( specification2 ) );
			if ( results != null && results.size() > 0 )
			{

				return ResponseEntity.ok( results );
			}
			else
			{
				return ResponseEntity.notFound().build();
			}

		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}

		return null;
	}

	@PutMapping("/botResponse")
	public ResponseEntity<BotResponseEntity> update( @RequestBody BotResponseEntity entity )
	{

		try
		{
			BotResponseEntity savedEntity = botResponseRepository.save( entity );
			if ( savedEntity != null && savedEntity.getId() > 0L )
			{

				return ResponseEntity.ok( savedEntity );
			}
			else
			{
				return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
			}

		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}

		return null;
	}

}
