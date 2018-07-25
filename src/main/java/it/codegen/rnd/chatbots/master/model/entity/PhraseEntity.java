package it.codegen.rnd.chatbots.master.model.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Embeddable
public class PhraseEntity extends EmbeddableSuper
{
	private String phrase;

	@NotNull
	@ManyToOne
	private TagEntity entity;

	public String getPhrase()
	{
		return phrase;
	}

	public void setPhrase( String phrase )
	{
		this.phrase = phrase;
	}

	public TagEntity getEntity()
	{
		return entity;
	}

	public void setEntity( TagEntity entity )
	{
		this.entity = entity;
	}
}
