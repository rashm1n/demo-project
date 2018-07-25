package it.codegen.rnd.chatbots.master.service;

import it.codegen.rnd.chatbots.master.repository.TagRepository;
import it.codegen.rnd.chatbots.nlp.util.CommonUtils;
import it.codegen.rnd.sense.core.nlp.Language;
import it.codegen.rnd.sense.engine.conf.Config;
import it.codegen.rnd.sense.engine.conf.NERModel;
import it.codegen.rnd.sense.engine.config.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class GazetteerServiceImpl implements GazetteerService
{
	@Autowired
	private TagRepository tagRepository;

	private static String OUTPUT_FILE_PATH;

	public GazetteerServiceImpl()
	{
		String modelLocation = ConfigurationManager.getInstance().getFolderPath() + File.separator +
				ConfigurationManager.getInstance().getProperty( Config.LEXICAL_IDENTIFIER_MODEL_LOCATION );
		OUTPUT_FILE_PATH = modelLocation + Language.ENGLISH.getCode() + "-" + NERModel.GAZETTEER_MDICT
				.getFileName();
	}

	@Override
	public void build()
	{
		List<String> outputs = new ArrayList<>();
		tagRepository.findAll().forEach( tagEntity -> {
			tagEntity.getEntityDataLabels().forEach( tagDataLabelEntity -> {
				tagDataLabelEntity.getTagDataValues().forEach( tagDataValueEntity -> {
					outputs.add( tagDataValueEntity.getValue() + "\t" + tagEntity.getName() + "." + tagDataLabelEntity.getLabel() );
					if ( tagDataValueEntity.getValue().isEmpty() || tagEntity.getName().isEmpty() || tagDataLabelEntity.getLabel().isEmpty() )
					{
						System.out.println( tagDataValueEntity.getValue() + "\t" + tagEntity.getName() + "." + tagDataLabelEntity.getLabel() );
					}
				} );
			} );
		} );
		CommonUtils.saveOutput( OUTPUT_FILE_PATH, outputs );
	}

	public static void main( String args[] )
	{
		ArrayList<String> arrayList = new ArrayList<>();
		arrayList.add( "A" );
		arrayList.add( "B" );
		arrayList.add( "C" );
		arrayList.add( "D" );
		arrayList.add( "E" );

		arrayList.forEach( item -> {
			System.out.println( item );
		} );

	}

}
