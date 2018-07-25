package it.codegen.rnd.chatbots.master.training;

import it.codegen.rnd.chatbots.master.model.entity.IntentEntity;
import it.codegen.rnd.chatbots.master.repository.IntentRepository;
import it.codegen.rnd.chatbots.master.service.IntentClassifierTrainerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IntentClassiferTrainingTest
{

	@Autowired
	private IntentClassifierTrainerService intentClassifierTrainerService;

	@Autowired
	private IntentRepository intentRepository;

	@Test
	public void trainingTest()
	{
		intentClassifierTrainerService.train();
	}

}
