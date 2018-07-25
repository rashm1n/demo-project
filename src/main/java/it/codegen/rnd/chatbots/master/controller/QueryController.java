package it.codegen.rnd.chatbots.master.controller;

import it.codegen.rnd.chatbots.master.model.entity.QueryEntity;
import it.codegen.rnd.chatbots.master.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class QueryController
{
	@Autowired
	private QueryRepository queryRepository;

	@PostMapping("/queries")
	public ResponseEntity<QueryEntity> createQuery( @RequestBody QueryEntity entity )
	{
		QueryEntity savedEntity = queryRepository.save( entity );
		if ( savedEntity != null && savedEntity.getId() > 0L )
		{
			return ResponseEntity.ok( savedEntity );
		}
		else
		{
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}

	@GetMapping("/queries")
	public ResponseEntity<List<QueryEntity>> getAll()
	{
		List<QueryEntity> entities = queryRepository.findAll();
		if ( entities != null && entities.size() > 0 )
		{
			return ResponseEntity.ok( entities );
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/queries/{id}")
	public ResponseEntity<QueryEntity> getOne( @PathVariable Long id )
	{
		Optional<QueryEntity> entity = queryRepository.findById( id );
		if ( entity.equals( Optional.empty() ) )
		{
			return ResponseEntity.notFound().build();
		}
		else
		{
			return ResponseEntity.ok( entity.get() );
		}
	}

	@PutMapping("/queries/{id}")
	public ResponseEntity<QueryEntity> update( @PathVariable Long id, @RequestBody QueryEntity entity )
	{
		Optional<QueryEntity> dbEntity = queryRepository.findById( id );
		if ( dbEntity.get() != null )
		{
			QueryEntity savedEntity = queryRepository.save( entity );
			if ( savedEntity != null )
			{
				return ResponseEntity.ok( savedEntity );
			}
			else
			{
				return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
			}
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/queries/{id}")
	public ResponseEntity delete( @PathVariable Long id )
	{
		Optional<QueryEntity> dbEntity = queryRepository.findById( id );
		if ( dbEntity.get() != null )
		{
			queryRepository.delete( dbEntity.get() );
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

}
