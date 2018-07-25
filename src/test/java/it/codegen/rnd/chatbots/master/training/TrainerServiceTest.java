package it.codegen.rnd.chatbots.master.training;

import it.codegen.rnd.chatbots.master.model.entity.IOBTagEntity;
import it.codegen.rnd.chatbots.master.model.entity.IntentEntity;
import it.codegen.rnd.chatbots.master.model.entity.QueryEntity;
import it.codegen.rnd.chatbots.master.repository.IOBTagRepository;
import it.codegen.rnd.chatbots.master.repository.IntentRepository;
import it.codegen.rnd.chatbots.master.repository.QueryRepository;
import it.codegen.rnd.chatbots.master.service.NERTrainerServiceSenseImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainerServiceTest
{
	@Autowired
	IOBTagRepository iobTagRepository;

	@Autowired
	QueryRepository queryRepository;

	@Autowired
	IntentRepository intentRepository;

	@Autowired
	NERTrainerServiceSenseImpl trainerService;

	@Test
	public void saveIOBTagTest()
	{
		IntentEntity intentEntity = new IntentEntity();
		intentEntity.setName( "ask_inclusion" );
		intentEntity = intentRepository.save( intentEntity );

		QueryEntity q1 = new QueryEntity();
		q1.setQuery( "What do you provide with an annual pass?" );
		q1.setIntent( intentEntity );

		QueryEntity q2 = new QueryEntity();
		q2.setQuery( "Can you help me with my annual pass benifits ?" );
		q2.setIntent( intentEntity );

		q1 = queryRepository.save( q1 );
		q2 = queryRepository.save( q2 );

		IOBTagEntity tag1 = new IOBTagEntity();
		tag1.setTokens( "What do you provide with an annual pass ?" );
		tag1.setTags( "B-wh O O B-inclusion I-inclusion O B-type I-type O" );
		tag1.setQuery( q1 );
		tag1.setIntent( intentEntity.getName() );

		IOBTagEntity tag2 = new IOBTagEntity();
		tag2.setTokens( "can you help me with my annual pass benifits?" );
		tag2.setTags( "O O O O O O B-type I-type B-inclusion O" );
		tag2.setQuery( q2 );
		tag2.setIntent( intentEntity.getName() );

		iobTagRepository.save( tag1 );
		iobTagRepository.save( tag2 );
	}

	@Test
	public void trainModelTest()
	{
		trainerService.train();
	}
}
