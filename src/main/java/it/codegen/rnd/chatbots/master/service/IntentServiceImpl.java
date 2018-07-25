package it.codegen.rnd.chatbots.master.service;

import it.codegen.rnd.chatbots.master.model.entity.IntentEntity;
import it.codegen.rnd.chatbots.master.model.entity.QueryEntity;
import it.codegen.rnd.chatbots.master.repository.IOBTagRepository;
import it.codegen.rnd.chatbots.master.repository.IntentRepository;
import it.codegen.rnd.chatbots.master.repository.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntentServiceImpl implements IntentService
{
	@Autowired
	private IntentRepository intentRepository;

	@Autowired
	private QueryRepository queryRepository;

	@Autowired
	private IOBTagService iobTagService;

	@Autowired
	private IOBTagRepository iobTagRepository;

	@Override
	public IntentEntity save( IntentEntity entity )
	{
		IntentEntity savedEntity = intentRepository.save( entity );
		if ( savedEntity != null && savedEntity.getId() > 0L )
		{
			if ( savedEntity.getQueries() != null )
			{
				savedEntity.getQueries().forEach( queryEntity -> {
					queryEntity.setIntent( savedEntity );
					queryEntity.setIobTags( iobTagService.convertToIOB( queryEntity ) );
					queryRepository.save( queryEntity );
				} );
			}
			return savedEntity;
		}
		else
		{
			return null;
		}
	}

	@Override
	public IntentEntity update( Long id, IntentEntity entity )
	{
		try
		{
			if ( entity.getQueries() != null )
			{
				entity.getQueries().forEach( queryEntity -> {
					queryEntity.setIntent( entity );
					queryEntity.setIobTags( iobTagService.convertToIOB( queryEntity ) );
					QueryEntity savedQueryEntity = queryRepository.save( queryEntity );
				} );
			}

			return intentRepository.save( entity );
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}
		return null;
	}
}
