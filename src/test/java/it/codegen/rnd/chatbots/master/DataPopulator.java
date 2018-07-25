package it.codegen.rnd.chatbots.master;

import it.codegen.rnd.chatbots.master.model.entity.IntentEntity;
import it.codegen.rnd.chatbots.master.repository.IntentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataPopulator
{
	@Autowired
	private IntentRepository intentRepository;

	@Test
	public void IntentDataPopulator()
	{
		IntentEntity entity1 = new IntentEntity();
		entity1.setName( "FLT_CONTACTS" );

		IntentEntity entity2 = new IntentEntity();
		entity2.setName( "FLT_BAGGAGE_INFO" );

		IntentEntity entity3 = new IntentEntity();
		entity3.setName( "FLT_POLICY" );

		intentRepository.save( entity1 );
		intentRepository.save( entity2 );
		intentRepository.save( entity3 );

	}
}
