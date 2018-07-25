package it.codegen.rnd.chatbots.master.model.entity.response;
import com.fasterxml.jackson.annotation.JsonBackReference;
import it.codegen.rnd.chatbots.master.model.entity.EmbeddableSuper;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Embeddable
public class MarkerEntity extends EmbeddableSuper
{
	@NotBlank
	private String name;
	private double location;

	@ManyToOne
	@JoinColumn(name = "card_fk", referencedColumnName = "card_id")
	@JsonBackReference
	@JsonIgnore
	private CardEntity card;

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public double getLocation()
	{
		return location;
	}

	public void setLocation( double location )
	{
		this.location = location;
	}

	public CardEntity getCard()
	{
		return card;
	}

	public void setCard( CardEntity card )
	{
		this.card = card;
	}
}
