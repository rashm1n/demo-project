package it.codegen.rnd.chatbots.master.service;

import it.codegen.rnd.chatbots.nlp.pipeline.OfflineNLPPipeline;
import it.codegen.rnd.chatbots.nlp.response.NLPResponse;

public interface NLPPipelineService
{

	OfflineNLPPipeline getPipeline();

	NLPResponse run(String query);

}
