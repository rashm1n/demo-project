package it.codegen.rnd.chatbots.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = { "it.codegen.rnd.chatbots.master" })
public class ChatBotMasterApplication
{
//	private static final String NLP_CONFIG_PATH_NAME = "nlp.config.path";
	private static final String NLP_CONFIG_PATH_VALUE = "config/";

	public static void main( String[] args )
	{
//		System.setProperty( NLP_CONFIG_PATH_NAME, NLP_CONFIG_PATH_VALUE );
		SpringApplication.run( ChatBotMasterApplication.class, args );

	}
}
