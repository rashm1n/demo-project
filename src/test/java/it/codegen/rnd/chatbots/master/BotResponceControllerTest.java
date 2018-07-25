package it.codegen.rnd.chatbots.master;

import it.codegen.rnd.chatbots.master.Utils.ResponseSpecification;
import it.codegen.rnd.chatbots.master.Utils.SearchCriteria;
import it.codegen.rnd.chatbots.master.controller.BotResponseController;
import it.codegen.rnd.chatbots.master.controller.IntentController;
import it.codegen.rnd.chatbots.master.model.entity.response.*;
import it.codegen.rnd.chatbots.master.repository.response.BotResponseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BotResponceControllerTest
{

	@Autowired
	private BotResponseController botResponseController;

	@Autowired
	private IntentController intentController;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BotResponseRepository botResponseRepository;

	@Test
	public void createBotResponse()
	{

		BotResponseEntity entity1 = new BotResponseEntity();
		Set<String> entityNames = new HashSet<>();

		entity1.setReplyName( "TEST_REPLY" );
		entity1.setEntityNames( "entityName5$entityName6" );
		entity1.setIntentName( "ask_contact" );

		entity1.setType( "url_inclusion" );

		UrlEntity urlEntity1 = new UrlEntity();
		urlEntity1.setUrl( "the test urlllllllllllll" );
		urlEntity1.setBotResponse( entity1 );

		UrlEntity urlEntity2 = new UrlEntity();
		urlEntity2.setUrl( "the test urlllllllllllllllllll" );
		urlEntity2.setBotResponse( entity1 );

		Set<UrlEntity> items = new HashSet<>();
		items.add( urlEntity1 );
		items.add( urlEntity2 );

		entity1.setUrlEntities( items );
		ResponseEntity<BotResponseEntity> savedEntity = botResponseController.create( entity1 );

	}

	@Test
	public void getAll()
	{

		ResponseEntity<List<BotResponseEntity>> entities = botResponseController.getAll();
		System.out.println( entities );

	}

	@Test
	@Transactional
	public void getResponse()
	{

		ResponseSpecification specification1 = new ResponseSpecification( new SearchCriteria( "intentName", ":", "ask_contact" ) );
		ResponseSpecification specification2 = new ResponseSpecification( new SearchCriteria( "entityNames", ":", "entityName5$entityName6" ) );
		List<BotResponseEntity> results = botResponseRepository.findAll( Specification.where(specification1).and(specification2));
		System.out.println(results);

	}




	@Test
	public void createRichContent()
	{

		BotResponseEntity entity1 = new BotResponseEntity();
		Set<String> entityNames = new HashSet<>();

		entity1.setReplyName( "TEST_REPLY" );
		entity1.setEntityNames( "entityName3$entityName4" );
		entity1.setIntentName( "ask_contact" );

				       entity1.setType( "rich content" );

				RichContentEntity richContentEntity = new RichContentEntity();
				richContentEntity.setReplyType( "simple" );
				richContentEntity.setContentType( "sample" );
				richContentEntity.setDisplayText( "this is the test response" );
				richContentEntity.setTextToSpeech( "test speech text" );
				richContentEntity.setBotResponse( entity1 );

				CarouselEntity carouselEntity = new CarouselEntity();
				carouselEntity.setType( "view" );
				carouselEntity.setSelectionType( "none" );
				carouselEntity.setTitle( "test response" );
				carouselEntity.setRichContent( richContentEntity );

				CardEntity cardEntity = new CardEntity();
				cardEntity.setText( "test card title" );
				cardEntity.setType( "simple" );
				cardEntity.setCarousel( carouselEntity );
				cardEntity.setImageUrl( "testURL" );
				Set<String> imageUrls = new HashSet<>();
				imageUrls.add( "testURL" );

				Set<CardEntity> items = new HashSet<>();
				items.add( cardEntity );
				carouselEntity.setItems( items );

		//card entity for the one to one card

				CardEntity cardEntity2 = new CardEntity();
				cardEntity2.setText( "test card title 2" );
				cardEntity2.setType( "simple2" );
				cardEntity2.setRichContent( richContentEntity );
				cardEntity2.setImageUrl( "testURL2" );

				richContentEntity.setCardEntity( cardEntity2 );

		     richContentEntity.setCarouselEntity( carouselEntity );

				Set<RichContentEntity> richContentEntities = new HashSet<>();
				richContentEntities.add( richContentEntity );

				entity1.setRichContents( richContentEntities );

		ResponseEntity<BotResponseEntity> savedEntity = botResponseController.create( entity1 );

	}

}
