package it.codegen.rnd.chatbots.master.service;

import it.codegen.rnd.chatbots.master.model.entity.IOBTagEntity;
import it.codegen.rnd.chatbots.master.model.entity.PhraseEntity;
import it.codegen.rnd.chatbots.master.model.entity.QueryEntity;
import it.codegen.rnd.chatbots.master.model.entity.TagEntity;
import it.codegen.rnd.chatbots.master.repository.IOBTagRepository;
import it.codegen.rnd.chatbots.master.repository.QueryRepository;
import it.codegen.rnd.logging.SystemLogger;
import it.codegen.rnd.sense.SenseNLPEngine;
import it.codegen.rnd.sense.core.Pipeline;
import it.codegen.rnd.sense.core.impl.SimplePipeline;
import it.codegen.rnd.sense.core.impl.SimplePipelineInput;
import it.codegen.rnd.sense.core.impl.SimplePipelineOutput;
import it.codegen.rnd.sense.core.impl.SimpleProcessedElement;
import it.codegen.rnd.sense.core.model.SpanType;
import it.codegen.rnd.sense.core.model.impl.SimpleAnnotation;
import it.codegen.rnd.sense.core.nlp.tool.simple.SimpleTokenizer;
import it.codegen.rnd.sense.engine.conf.Config;
import it.codegen.rnd.sense.engine.config.ConfigurationManager;
import it.codegen.rnd.sense.exception.SenseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class IOBTagService
{
	@Autowired
	private IOBTagRepository iobTagRepository;

	private static final String LOG_TOKEN = "IBO_TAG";
	private static final String TAG_PREFIX_B = "B-";
	private static final String TAG_PREFIX_I = "I-";
	private static final String TAG_PREFIX_O = "O";
	private static final String CH_SPACE = " ";
	private static SenseNLPEngine senseEngine;
	private static Pipeline pipeline;

	@Autowired
	private QueryRepository queryRepository;

	@PostConstruct
	public void init()
	{
		try
		{
			senseEngine = new SenseNLPEngine( new Properties() );
			senseEngine.init();
			String tokenizerModelPath = ConfigurationManager.getInstance().getProperty( Config.TOKENIZER_MODEL_PATH );
			pipeline = senseEngine.createPipeline( SimplePipeline.KEY
					, new SimpleTokenizer( new File( tokenizerModelPath ) )
			);
			pipeline.init();
		}
		catch ( SenseException e )
		{
			SystemLogger.logErrorMessege( LOG_TOKEN, e );
		}
	}

	public IOBTagEntity convertToIOB( QueryEntity entity )
	{
		if ( entity.getPhrases() == null )
		{
			return null;
		}

		IOBTagEntity iobTagEntity = new IOBTagEntity();
		if ( entity.getIobTags() != null )
		{
			iobTagEntity.setId( entity.getIobTags().getId() );
		}
		iobTagEntity.setIntent( entity.getIntent().getName() );
		iobTagEntity.setQuery( entity );
		iobTagEntity.setTokens( "" );
		iobTagEntity.setTags( "" );

		List<String> phraseTokens = new ArrayList<>();
		List<String> phraseTags = new ArrayList<>();

		entity.getPhrases().forEach( phraseEntity -> {
			SimplePipelineOutput pipelineOutput = new SimplePipelineOutput();
			pipeline.run( new SimplePipelineInput( new SimpleProcessedElement[] { new
					SimpleProcessedElement( ( phraseEntity.getPhrase() ).toLowerCase() ) } ), pipelineOutput );
			if ( pipelineOutput.getData() != null && pipelineOutput.getData().size() > 0 )
			{
				int count = 0;
				for ( SimpleAnnotation simpleAnnotation : pipelineOutput.getData().get( 0 ).getAnnotations( SpanType.TOKEN ) )
				{
					phraseTokens.add( simpleAnnotation.getText() );
					if ( count == 0 )
					{
						phraseTags.add( TAG_PREFIX_B + phraseEntity.getEntity().getName() );
					}
					else
					{
						phraseTags.add( TAG_PREFIX_I + phraseEntity.getEntity().getName() );
					}

					count++;
				}
			}
		} );

		SimplePipelineOutput pipelineOutput = new SimplePipelineOutput();
		pipeline.run( new SimplePipelineInput( new SimpleProcessedElement[] { new
				SimpleProcessedElement( ( entity.getQuery() ).toLowerCase() ) } ), pipelineOutput );
		if ( pipelineOutput.getData() != null && pipelineOutput.getData().size() > 0 )
		{
			int index = 0;
			for ( SimpleAnnotation simpleAnnotation : pipelineOutput.getData().get( 0 ).getAnnotations( SpanType.TOKEN ) )
			{
				iobTagEntity.setTokens( iobTagEntity.getTokens() + simpleAnnotation.getText() + CH_SPACE );
				if ( phraseTokens.size() > index && phraseTokens.get( index ).equals( simpleAnnotation.getText() ) )
				{
					iobTagEntity.setTags( iobTagEntity.getTags() + phraseTags.get( index ) + CH_SPACE );
					index++;
				}
				else
				{
					iobTagEntity.setTags( iobTagEntity.getTags() + TAG_PREFIX_O + CH_SPACE );
				}
			}
		}

		iobTagEntity.setTokens( iobTagEntity.getTokens().trim() );
		iobTagEntity.setTags( iobTagEntity.getTags().trim() );

		return iobTagEntity;
	}

	public IOBTagEntity save( QueryEntity queryEntity )
	{
		IOBTagEntity entity = convertToIOB( queryEntity );
		if ( entity == null )
		{
			return null;
		}
		return iobTagRepository.save( entity );
	}

	private void convertFromIOB( List<IOBTagEntity> tags )
	{
		// TODO convert query to IOB tags
	}

	public File toCSV( String filepath ) throws IOException
	{
		File tempFile = new File( filepath );
		PrintWriter pw = new PrintWriter( new File( tempFile.getPath() ) );
		StringBuilder sb = new StringBuilder();

		for ( IOBTagEntity iobTagEntity : iobTagRepository.findAll() )
		{
			sb.append( iobTagEntity.getTokens() );
			sb.append( "," );
			sb.append( iobTagEntity.getTags() );
			sb.append( "," );
			sb.append( iobTagEntity.getIntent() );
			sb.append( "\n" );
		}
		pw.write( sb.toString() );
		pw.close();
		return tempFile;
	}

	//	public static void main( String args[] )
	//	{
	//		IOBTagService iobTagService = new IOBTagService();
	//
	//		TagEntity t1 = new TagEntity();
	//		t1.setName( "wh" );
	//		TagEntity t2 = new TagEntity();
	//		t2.setName( "inclusion" );
	//		TagEntity t3 = new TagEntity();
	//		t3.setName( "type" );
	//
	//		PhraseEntity p1 = new PhraseEntity();
	//		p1.setPhrase( "what" );
	//		p1.setEntity( t1 );
	//		PhraseEntity p2 = new PhraseEntity();
	//		p2.setPhrase( "comes with" );
	//		p2.setEntity( t2 );
	//		PhraseEntity p3 = new PhraseEntity();
	//		p3.setPhrase( "annual pass" );
	//		p3.setEntity( t3 );
	//
	//		QueryEntity queryEntity = new QueryEntity();
	//		queryEntity.addPhrase( p1 );
	//		queryEntity.addPhrase( p2 );
	//		queryEntity.addPhrase( p3 );
	//		queryEntity.setQuery( "What's comes with an annual pass?" );
	//
	//		IOBTagEntity entity = iobTagService.convertToIOB( queryEntity );
	//		System.out.println( entity.getTokens() );
	//		System.out.println( entity.getTags() );
	//	}
}
