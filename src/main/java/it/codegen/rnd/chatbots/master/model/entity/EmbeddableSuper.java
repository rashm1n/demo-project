package it.codegen.rnd.chatbots.master.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class EmbeddableSuper
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	public EmbeddableSuper()
	{
	}

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}
}
