package it.codegen.rnd.chatbots.master.controller;

import it.codegen.rnd.chatbots.master.model.entity.IntentEntity;
import it.codegen.rnd.chatbots.master.repository.IntentRepository;
import it.codegen.rnd.chatbots.master.service.IntentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class IntentController
{
	@Autowired
	private IntentRepository intentRepository;

	@Autowired
	private IntentService intentService;

	@PostMapping("/intents")
	public ResponseEntity<IntentEntity> create( @RequestBody IntentEntity entity )
	{
		IntentEntity savedEntity = intentService.save( entity );
		if ( savedEntity != null && savedEntity.getId() > 0L )
		{
			return ResponseEntity.ok( savedEntity );
		}
		else
		{
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}

	@GetMapping("/intents")
	public ResponseEntity<List<IntentEntity>> getAll()
	{
		List<IntentEntity> entities = intentRepository.findAll();
		if ( entities != null && entities.size() > 0 )
		{
			return ResponseEntity.ok( entities );
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/intents/{id}")
	public ResponseEntity<IntentEntity> getOne( @PathVariable Long id )
	{
		Optional<IntentEntity> entity = intentRepository.findById( id );
		if ( entity.get() != null )
		{
			return ResponseEntity.ok( entity.get() );
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/intents/{id}")
	public ResponseEntity<IntentEntity> update( @PathVariable Long id, @RequestBody IntentEntity entity )
	{
		IntentEntity savedEntity = intentService.update( id, entity );
		if ( savedEntity == null )
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok( savedEntity );
		}

		//		Optional<IntentEntity> dbEntity = intentRepository.findById( id );
		//		if ( dbEntity.get() != null )
		//		{
		//			dbEntity.get().setName( entity.getName() );
		//			IntentEntity savedEntity = intentService.save( entity );
		//			if ( savedEntity != null )
		//			{
		//				return ResponseEntity.ok( savedEntity );
		//			}
		//			else
		//			{
		//				return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
		//			}
		//		}
		//		else
		//		{
		//			return ResponseEntity.notFound().build();
		//		}
	}

	@DeleteMapping("/intents/{id}")
	public ResponseEntity delete( @PathVariable Long id )
	{
		Optional<IntentEntity> dbEntity = intentRepository.findById( id );
		if ( dbEntity.get() != null )
		{
			intentRepository.delete( dbEntity.get() );
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

}
