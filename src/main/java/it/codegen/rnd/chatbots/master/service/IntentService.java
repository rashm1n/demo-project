package it.codegen.rnd.chatbots.master.service;

import it.codegen.rnd.chatbots.master.model.entity.IntentEntity;
import org.springframework.stereotype.Service;

@Service
public interface IntentService
{

	public IntentEntity save( IntentEntity entity );

	public IntentEntity update( Long id, IntentEntity entity );

}
