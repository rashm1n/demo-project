package it.codegen.rnd.chatbots.master.controller;

import it.codegen.rnd.chatbots.master.model.entity.IntentEntity;
import it.codegen.rnd.chatbots.master.model.entity.TagEntity;
import it.codegen.rnd.chatbots.master.repository.IntentRepository;
import it.codegen.rnd.chatbots.master.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class TagController
{

	@Autowired
	private TagRepository tagRepository;

	@PostMapping("/entities")
	public ResponseEntity<TagEntity> create( @RequestBody TagEntity entity )
	{
		if ( entity.getEntityDataLabels() != null )
		{
			entity.getEntityDataLabels().forEach( labelEntity -> {
				labelEntity.setEntity( entity );
			} );
		}
		TagEntity savedEntity = tagRepository.save( entity );
		if ( savedEntity != null && savedEntity.getId() > 0L )
		{
			return ResponseEntity.ok( savedEntity );
		}
		else
		{
			return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}

	@GetMapping("/entities")
	public ResponseEntity<List<TagEntity>> getAll()
	{
		List<TagEntity> entities = tagRepository.findAll();
		if ( entities != null && entities.size() > 0 )
		{
			return ResponseEntity.ok( entities );
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/entities/{id}")
	public ResponseEntity<TagEntity> getOne( @PathVariable Long id )
	{
		Optional<TagEntity> entity = tagRepository.findById( id );
		if ( entity.get() != null )
		{
			return ResponseEntity.ok( entity.get() );
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/entities/{id}")
	public ResponseEntity<TagEntity> update( @PathVariable Long id, @RequestBody TagEntity entity )
	{
		Optional<TagEntity> dbEntity = tagRepository.findById( id );
		if ( !dbEntity.equals( Optional.empty() ) )
		{
			if ( entity.getEntityDataLabels() != null )
			{
				entity.getEntityDataLabels().forEach( labelEntity -> {
					labelEntity.setEntity( entity );
				} );
			}
			TagEntity savedEntity = tagRepository.save( entity );
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

	@DeleteMapping("/entities/{id}")
	public ResponseEntity delete( @PathVariable Long id )
	{
		Optional<TagEntity> dbEntity = tagRepository.findById( id );
		if ( !dbEntity.equals( Optional.empty() ) )
		{
			tagRepository.delete( dbEntity.get() );
			return ResponseEntity.ok().build();
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}

}
