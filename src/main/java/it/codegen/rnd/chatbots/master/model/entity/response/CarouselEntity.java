package it.codegen.rnd.chatbots.master.model.entity.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.codegen.rnd.chatbots.master.model.entity.AuditEntity;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Table(name = "CAROUSEL_TYPE")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarouselEntity extends AuditEntity
{



	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "carousel_id")
	private Long id;

	@NotBlank
	private String type;

	private String selectionType;

	private String title;

	@OneToMany(mappedBy = "carousel", orphanRemoval = true , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	@JsonManagedReference
	private Set<CardEntity> items;

	@ElementCollection
	@CollectionTable(name = "BUTTONS", joinColumns = @JoinColumn(name = "b_carousel_id") )
	@JsonManagedReference
	private Set<ListItemEntity> buttons;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "rich_content_fk", referencedColumnName = "rich_content_id")
	@JsonBackReference
	@JsonIgnore
	private RichContentEntity richContent;

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public String getSelectionType()
	{
		return selectionType;
	}

	public void setSelectionType( String selectionType )
	{
		this.selectionType = selectionType;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public Set<CardEntity> getItems()
	{
		return items;
	}

	public void setItems( Set<CardEntity> items )
	{
		this.items = items;
	}

	public Set<ListItemEntity> getButtons()
	{
		return buttons;
	}

	public void setButtons( Set<ListItemEntity> buttons )
	{
		this.buttons = buttons;
	}

	public RichContentEntity getRichContent()
	{
		return richContent;
	}

	public void setRichContent( RichContentEntity richContent )
	{
		this.richContent = richContent;
	}
}
