package it.codegen.rnd.chatbots.master;

import it.codegen.rnd.chatbots.master.controller.IntentController;
import it.codegen.rnd.chatbots.master.model.entity.IntentEntity;
import it.codegen.rnd.chatbots.master.model.entity.PhraseEntity;
import it.codegen.rnd.chatbots.master.model.entity.QueryEntity;
import it.codegen.rnd.chatbots.master.model.entity.TagEntity;
import it.codegen.rnd.chatbots.master.repository.IntentRepository;
import it.codegen.rnd.chatbots.master.repository.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntentControllerTest
{
	@Autowired
	private IntentController intentController;

	@Autowired
	private IntentRepository intentRepository;

	@Autowired
	private TagRepository tagRepository;

	@Test
	public void saveIntentTest()
	{
		IntentEntity entity = new IntentEntity();
		entity.setName( "TEST_RESPONSE" );
		IntentEntity savedIntent = intentController.create( entity ).getBody();
		assertThat( savedIntent.getId() ).isGreaterThan( 0 );
		intentRepository.delete( savedIntent );
	}

	@Test
	public void saveIntentWithQueriesTest()
	{
		IntentEntity entity = new IntentEntity();
		entity.setName( "FLT_CONTACTS" );

		TagEntity tag1 = new TagEntity();
		tag1.setName( "inclusion" );
		tag1 = tagRepository.save( tag1 );
		TagEntity tag2 = new TagEntity();
		tag2.setName( "type" );
		tag2 = tagRepository.save( tag2 );

		QueryEntity queryEntity = new QueryEntity();
		queryEntity.setQuery( "what comes with annual pass" );
		PhraseEntity phrase1 = new PhraseEntity();
		phrase1.setPhrase( "comes with" );
		phrase1.setEntity( tag1 );
		PhraseEntity phrase2 = new PhraseEntity();
		phrase2.setPhrase( "annual pass" );
		phrase2.setEntity( tag2 );
		queryEntity.addPhrase( phrase1 );
		queryEntity.addPhrase( phrase2 );

		entity.addQuery( queryEntity );

		IntentEntity savedIntent = intentController.create( entity ).getBody();
		assertThat( savedIntent.getId() ).isGreaterThan( 0 );
		intentRepository.delete( savedIntent );
		tagRepository.delete( tag1 );
		tagRepository.delete( tag2 );
	}

	@Test
	public void getAllTest()
	{
		IntentEntity entity = new IntentEntity();
		entity.setName( "FLT_CONTACTS" );
		IntentEntity savedIntent = intentController.create( entity ).getBody();
		assertThat( intentController.getAll().getBody().size() ).isGreaterThan( 0 );
		intentRepository.delete( savedIntent );
	}

	@Test
	public void getOneTest()
	{
		IntentEntity entity = new IntentEntity();
		entity.setName( "FLT_CONTACTS" );
		IntentEntity savedIntent = intentController.create( entity ).getBody();
		assertThat( intentController.getOne( savedIntent.getId() ).getBody().getId() ).isEqualTo( savedIntent.getId() );
		intentRepository.delete( savedIntent );
	}

	@Test
	public void updateTest()
	{
		String changedName = "FLT_CONTACTS_ENTITY";

		IntentEntity entity = new IntentEntity();
		entity.setName( "FLT_CONTACTS" );
		entity = intentController.create( entity ).getBody();

		IntentEntity updateEntity = new IntentEntity();
		updateEntity.setId( entity.getId() );
		updateEntity.setName( changedName );
		updateEntity = intentController.update( updateEntity.getId(), updateEntity ).getBody();
		assertThat( updateEntity.getName() ).isEqualTo( changedName );

		intentRepository.delete( updateEntity );
	}

	@Test
	public void deleteTest()
	{
		IntentEntity entity = new IntentEntity();
		entity.setName( "FLT_CONTACTS" );
		entity = intentController.create( entity ).getBody();
		assertThat( entity.getId() ).isGreaterThan( 0L );
		assertThat( intentController.delete( entity.getId() ).getStatusCode() ).isEqualTo( HttpStatus.OK );
		assertThat( intentRepository.findById( entity.getId() ) ).isEqualTo( Optional.empty() );
	}
}
