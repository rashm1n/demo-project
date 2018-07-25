package it.codegen.rnd.chatbots.master.service;

import it.codegen.rnd.chatbots.nlp.pipeline.OfflineNLPPipeline;
import it.codegen.rnd.chatbots.nlp.request.NLPRequest;
import it.codegen.rnd.chatbots.nlp.response.NLPResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NLPPipelineOfflineImpl implements NLPPipelineService
{
	private static OfflineNLPPipeline offlineNLPPipeline;

	public NLPPipelineOfflineImpl()
	{
		offlineNLPPipeline = OfflineNLPPipeline.getInstance();
	}

	@Override
	public OfflineNLPPipeline getPipeline()
	{
		return offlineNLPPipeline;
	}

	@Override
	public NLPResponse run( String query )
	{
		NLPRequest nlpRequest = new NLPRequest();
		nlpRequest.setQuery( query );
		return offlineNLPPipeline.processQueryRequest( nlpRequest );
	}
}
