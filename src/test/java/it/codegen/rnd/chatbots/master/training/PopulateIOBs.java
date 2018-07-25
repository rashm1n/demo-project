package it.codegen.rnd.chatbots.master.training;

import it.codegen.rnd.chatbots.master.model.entity.IOBTagEntity;
import it.codegen.rnd.chatbots.master.repository.IOBTagRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PopulateIOBs
{

	@Autowired
	IOBTagRepository iobTagRepository;

	private String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

	@Test
	public void populate()
	{
		String filename = "D:\\git\\rnd\\disney-chat-bot\\chat-bots-common\\configs\\LexicalAnalyzer\\FullTrainingSet.csv";
		BufferedReader br = null;
		String line = "";
		try
		{

			br = new BufferedReader( new FileReader( filename ) );
			while ( ( line = br.readLine() ) != null )
			{
				IOBTagEntity entity = new IOBTagEntity();
//				String[] strs = line.split( "\"" );
//				if ( strs.length > 2 )
//				{
//					entity.setTokens( strs[1] );
//					line = strs[2];
//					String[] values = line.split( "," );
//					entity.setTags( values[1] );
//					entity.setIntent( values[2] );
//				}
//				else
//				{
//					String[] values = line.split( "," );
//					entity.setTokens( values[0] );
//					entity.setTags( values[1] );
//					entity.setIntent( values[2] );
//				}

				String[] values = line.split( cvsSplitBy );
				entity.setTokens( values[0] );
				entity.setTags( values[1] );
				entity.setIntent( values[2] );
				System.out.println( entity.getTokens() );
				System.out.println( entity.getTags() );
				System.out.println( entity.getIntent() );
				System.out.println( "-------------------------------------------------------------------" );
				iobTagRepository.save( entity );
			}

		}
		catch ( FileNotFoundException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		finally
		{
			if ( br != null )
			{
				try
				{
					br.close();
				}
				catch ( IOException e )
				{
					e.printStackTrace();
				}
			}
		}

	}

	public static void main( String args[] )
	{
		String s = "\"Yeah , I have one , it 's a platinum\",O O O O O O O O O B-type,provide_item";
		String[] strs = s.split( "\"" );
		//		System.out.println( strs[0] );
		System.out.println( strs[1] );

		String commaStrs[] = strs[2].split( "," );
		for ( String commaStr : commaStrs )
		{
			System.out.println( commaStr );
		}

	}
}


