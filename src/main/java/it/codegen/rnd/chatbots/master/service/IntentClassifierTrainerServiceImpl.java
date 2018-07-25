package it.codegen.rnd.chatbots.master.service;

import it.codegen.rnd.chatbots.master.model.entity.IOBTagEntity;
import it.codegen.rnd.chatbots.master.model.entity.IntentEntity;
import it.codegen.rnd.chatbots.master.repository.IOBTagRepository;
import it.codegen.rnd.chatbots.master.repository.IntentRepository;
import it.codegen.rnd.chatbots.nlp.core.config.NLPConfig;
import it.codegen.rnd.chatbots.nlp.service.IntentClassificationService;
import it.codegen.rnd.sense.engine.config.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

@Service
public class IntentClassifierTrainerServiceImpl implements IntentClassifierTrainerService
{

	@Autowired
	private IOBTagRepository iobTagRepository;

	@Autowired
	private IntentRepository intentRepository;

	@Override
	public void train()
	{
		try
		{
			getIntentIdMap();
			toCSV();
			IntentClassificationService.getInstance().refresh();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}

	public File toCSV() throws IOException
	{
		String fileName = ConfigurationManager.getInstance().getFolderPath() + File.separator +
				ConfigurationManager.getInstance().getProperty( NLPConfig.INTENT_CLASSIFIER_TRAINING_SET_PATH );
		File tempFile = new File( fileName );
		PrintWriter pw = new PrintWriter( tempFile );
		StringBuilder sb = new StringBuilder();
		Map<String, Integer> intentIdMap = getIntentIdMap();
		for ( IOBTagEntity iobTagEntity : iobTagRepository.findAll() )
		{
			sb.append( iobTagEntity.getTokens() );
			sb.append( ",null," );
			sb.append( iobTagEntity.getTags() );
			sb.append( "," );
			sb.append( intentIdMap.get( iobTagEntity.getIntent() ) );
			sb.append( "\n" );
		}
		pw.write( sb.toString() );
		pw.close();
		return tempFile;
	}

	private Map<String, Integer> getIntentIdMap() throws IOException
	{
		Map<String, Integer> intents = new LinkedHashMap<>();
		int i = 0;
		for ( IOBTagEntity iobTagEntity : iobTagRepository.findAll() )
		{
			if ( intents.get( iobTagEntity.getIntent() ) == null )
			{
				intents.put( iobTagEntity.getIntent(), i );
				i++;
			}
		}

		// writing to a file
		File intentMapFile = new File( ConfigurationManager.getInstance().getFolderPath() + File.separator +
				ConfigurationManager.getInstance().getProperty( NLPConfig.ID_INTENT_MAP_LOCATION ) );
		PrintWriter pw = new PrintWriter( intentMapFile );
		StringBuilder sb = new StringBuilder();
		intents.forEach( ( key, val ) -> {
			sb.append( val + "," + key + "\n" );
		} );
		pw.write( sb.toString() );
		pw.close();
		return intents;
	}
}
