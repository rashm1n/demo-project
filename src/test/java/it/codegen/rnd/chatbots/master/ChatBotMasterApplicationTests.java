package it.codegen.rnd.chatbots.master;

import it.codegen.rnd.chatbots.master.controller.TestController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatBotMasterApplicationTests
{

	@Autowired
	private TestController controller;

	@Test
	public void contextLoads()
	{
		assertThat( controller.test() ).isEqualTo( "Endpoint is working." );

	}

}
