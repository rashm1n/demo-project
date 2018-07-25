package it.codegen.rnd.chatbots.master;

import it.codegen.rnd.chatbots.master.model.entity.TagDataLabelEntity;
import it.codegen.rnd.chatbots.master.model.entity.TagDataValueEntity;
import it.codegen.rnd.chatbots.master.model.entity.TagEntity;
import it.codegen.rnd.chatbots.master.repository.TagRepository;
import it.codegen.rnd.chatbots.nlp.service.IntentClassificationService;
import it.codegen.rnd.chatbots.nlp.util.CommonUtils;
import it.codegen.rnd.logging.SystemLogger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PopulateEntities extends CommonTest
{
	private static final String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

	@Autowired
	private TagRepository tagRepository;

	@Test
	public void populateFromGazetteerFile()
	{
		String line;
		Map<Integer, List<String>> tempGazetteerMap = new HashMap<>();
		Map<String, List<String>> gazetteerMap = new HashMap<>();
		boolean mapInitialized = false;
		String[] keyNames = new String[0];
		List<String> outputs = new ArrayList<>();
		Map<String, Map<String, ArrayList<String>>> entities = new HashMap<>();

		try (BufferedReader br = new BufferedReader( new FileReader( "D:\\git\\rnd\\chat\\chat-bot-master\\config\\LexicalAnalyzer\\Gazetteers.csv" ) ))
		{
			while ( ( line = br.readLine() ) != null )
			{
				String[] lineSplits = line.split( cvsSplitBy );
				if ( !mapInitialized )
				{
					keyNames = lineSplits.clone();
					for ( int i = 0; i < lineSplits.length; i++ )
					{
						tempGazetteerMap.put( i, new ArrayList<>() );
					}
					mapInitialized = true;
				}
				else
				{
					for ( int i = 0; i < lineSplits.length; i++ )
					{
						if ( !"".equals( lineSplits[i] ) )
						{
							tempGazetteerMap.get( i ).add( lineSplits[i] );
						}
					}
				}
			}
			for ( Map.Entry<Integer, List<String>> entry : tempGazetteerMap.entrySet() )
			{
				gazetteerMap.put( keyNames[entry.getKey()], entry.getValue() );
			}

			for ( Map.Entry<Integer, List<String>> entry : tempGazetteerMap.entrySet() )
			{
				String label = keyNames[entry.getKey()];
				String entityName = label.split( "\\." )[0];
				String entityLabel = label.substring( label.indexOf( "." ) + 1 );
				if ( entities.get( entityName ) == null )
				{
					entities.put( entityName, new HashMap<>() );
				}
				if ( entities.get( entityName ).get( entityLabel ) == null )
				{
					entities.get( entityName ).put( entityLabel, new ArrayList<>() );
				}
				for ( String word : entry.getValue() )
				{
					System.out.println( word );
					entities.get( entityName ).get( entityLabel ).add( word );
					String output = word + "\t" + label;
					outputs.add( output );
				}
			}
			//			CommonUtils.saveOutput( outputFilePath, outputs );

			entities.forEach( ( key, val ) -> {
				TagEntity entity = new TagEntity();
				entity.setName( key );
				final StringBuilder vv = new StringBuilder();
				val.forEach( ( label, list ) -> {
					TagDataLabelEntity tagDataLabelEntity = new TagDataLabelEntity();
					tagDataLabelEntity.setEntity( entity );
					tagDataLabelEntity.setLabel( label );
					vv.append( key + "." + label + " -- " );
					list.forEach( value -> {
						TagDataValueEntity tagDataValueEntity = new TagDataValueEntity();
						tagDataValueEntity.setValue( value );
						tagDataLabelEntity.addTagDataValue( tagDataValueEntity );
						vv.append( value + "," );
					} );
					entity.addEntityDataLabel( tagDataLabelEntity );
					vv.append( "\n" );
				} );
				tagRepository.save( entity );
				System.out.println( vv.toString() );
			} );

		}
		catch ( IOException e )
		{
			SystemLogger.logErrorMessege( IntentClassificationService.class, e );
		}
	}

	public static void main( String args[] )
	{
		new PopulateEntities().populateFromGazetteerFile();
	}

}
