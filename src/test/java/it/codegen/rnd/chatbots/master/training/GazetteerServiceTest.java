package it.codegen.rnd.chatbots.master.training;

import it.codegen.rnd.chatbots.master.CommonTest;
import it.codegen.rnd.chatbots.master.service.GazetteerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GazetteerServiceTest extends CommonTest
{
	@Autowired
	GazetteerService gazetteerService;

	@Test
	public void buildTest()
	{
		gazetteerService.build();
	}
}
