package it.codegen.rnd.chatbots.master.service;

import it.codegen.rnd.chatbots.nlp.service.LexicalAnalyzerService;
import it.codegen.rnd.logging.SystemLogger;
import it.codegen.rnd.sense.SenseNLPEngine;
import it.codegen.rnd.sense.core.nlp.Language;
import it.codegen.rnd.sense.engine.conf.Config;
import it.codegen.rnd.sense.engine.config.ConfigurationManager;
import it.codegen.rnd.sense.exception.SenseException;
import it.codegen.rnd.sense.logging.SenseLogger;
import it.codegen.rnd.sense.ml.impl.OpenNlpTrainingParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class NERTrainerServiceSenseImpl implements NERTrainerService
{
	@Autowired
	private IOBTagService iobTagService;

	private final String TRAINING_CSV_PATH;

	public NERTrainerServiceSenseImpl()
	{
		TRAINING_CSV_PATH = ConfigurationManager.getInstance().getFolderPath() + File.separator +
				ConfigurationManager.getInstance().getProperty( Config.LEXICAL_IDENTIFIER_TRAINING_SET_PATH );
	}

	public void train()
	{
		File tempFile = null;
		try
		{
			tempFile = iobTagService.toCSV(TRAINING_CSV_PATH);
			LexicalAnalyzerService.getInstance().refresh();

			//			senseEngine.createOpenNlpModelCSV( "botModel", Language.ENGLISH, tempFile.getPath(), null,
			//					nerModelLocation, getTrainingParameters() );
		}
		catch ( IOException e )
		{
			SystemLogger.logEventErrorMessege( e );
		}
		finally
		{
			if ( tempFile != null )
			{
				tempFile.delete();
			}
		}
	}

	private OpenNlpTrainingParameters getTrainingParameters()
	{
		OpenNlpTrainingParameters openNlpTrainingParameters = OpenNlpTrainingParameters.getDefault();
		openNlpTrainingParameters.setIterations( 300 );
		openNlpTrainingParameters.setCutoff( 0 );
		openNlpTrainingParameters.setAlgorithm( OpenNlpTrainingParameters.ALGORITHM.MAXENT );
		return openNlpTrainingParameters;
	}

	private Properties readProperties( String propertyFileLocation )
	{
		Properties props = new Properties();
		InputStream input = null;

		try
		{
			input = new FileInputStream( propertyFileLocation );
			props.load( input );

		}
		catch ( IOException e )
		{
			SenseLogger.logErrorMessage( this, e.getMessage(), e );
		}
		finally
		{
			if ( input != null )
			{
				try
				{
					input.close();
				}
				catch ( IOException e )
				{
					SenseLogger.logErrorMessage( this, e.getMessage(), e );
				}
			}
		}
		return props;

	}

}
