package it.codegen.rnd.chatbots.master.model.entity;

import javax.persistence.Embeddable;

@Embeddable
public class TagDataValueEntity extends EmbeddableSuper
{
	private String value;

	public String getValue()
	{
		return value;
	}

	public void setValue( String value )
	{
		this.value = value;
	}

}
