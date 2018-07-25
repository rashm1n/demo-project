package it.codegen.rnd.chatbots.master.model.entity.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.codegen.rnd.chatbots.master.model.entity.EmbeddableSuper;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Embeddable
public class ListItemEntity extends EmbeddableSuper
{

	@NotBlank
	private String title;

	@NotBlank
	private String type;

	private String payload;

	private String actionUrl;


	@ManyToOne
	@JsonBackReference
	@JsonIgnore
	private CardEntity card;

	@ManyToOne
	@JsonBackReference
	@JsonIgnore
	private CarouselEntity carousel;


	@ManyToOne
	@JsonBackReference
	@JsonIgnore
	private ListEntity list;

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public String getPayload()
	{
		return payload;
	}

	public void setPayload( String payload )
	{
		this.payload = payload;
	}

	public String getActionUrl()
	{
		return actionUrl;
	}

	public void setActionUrl( String actionUrl )
	{
		this.actionUrl = actionUrl;
	}

	public CardEntity getCard()
	{
		return card;
	}

	public void setCard( CardEntity card )
	{
		this.card = card;
	}

	public CarouselEntity getCarousel()
	{
		return carousel;
	}

	public void setCarousel( CarouselEntity carousel )
	{
		this.carousel = carousel;
	}

	public ListEntity getList()
	{
		return list;
	}

	public void setList( ListEntity list )
	{
		this.list = list;
	}
}
