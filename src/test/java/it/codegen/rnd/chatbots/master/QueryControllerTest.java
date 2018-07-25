package it.codegen.rnd.chatbots.master;

import it.codegen.rnd.chatbots.master.controller.QueryController;
import it.codegen.rnd.chatbots.master.model.entity.IntentEntity;
import it.codegen.rnd.chatbots.master.model.entity.PhraseEntity;
import it.codegen.rnd.chatbots.master.model.entity.QueryEntity;
import it.codegen.rnd.chatbots.master.model.entity.TagEntity;
import it.codegen.rnd.chatbots.master.repository.IntentRepository;
import it.codegen.rnd.chatbots.master.repository.QueryRepository;
import it.codegen.rnd.chatbots.master.repository.TagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryControllerTest
{

	@Autowired
	private QueryController controller;

	@Autowired
	private IntentRepository intentRepository;

	@Autowired
	private TagRepository tagRepository;

	@Autowired
	private QueryRepository queryRepository;

	@Test
	public void createQueryTest()
	{
		IntentEntity intent = new IntentEntity();
		intent.setName( "ask_inclusion" );
		intent = intentRepository.save( intent );

		TagEntity tagInclusion = new TagEntity();
		tagInclusion.setName( "inclusion" );
		tagInclusion = tagRepository.save( tagInclusion );

		TagEntity tagType = new TagEntity();
		tagType.setName( "type" );
		tagType = tagRepository.save( tagType );

		// Creating query
		QueryEntity query = new QueryEntity();
		query.setQuery( "What comes with annual pass?" );
		query.setIntent( intent );

		PhraseEntity phrase1 = new PhraseEntity();
		phrase1.setPhrase( "comes with" );
		phrase1.setEntity( tagInclusion );

		PhraseEntity phrase2 = new PhraseEntity();
		phrase2.setPhrase( "annual pass" );
		phrase2.setEntity( tagType );

		query.addPhrase( phrase1 );
		query.addPhrase( phrase2 );

		query = controller.createQuery( query ).getBody();

		assertThat( query.getId() ).isGreaterThan( 0L );

		queryRepository.delete( query );
		tagRepository.delete( tagInclusion );
		tagRepository.delete( tagType );
		intentRepository.delete( intent );
	}

}
