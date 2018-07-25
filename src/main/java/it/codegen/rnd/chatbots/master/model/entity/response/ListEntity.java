package it.codegen.rnd.chatbots.master.model.entity.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.codegen.rnd.chatbots.master.model.entity.AuditEntity;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "LIST_TYPE")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListEntity extends AuditEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "list_id")
	private Long id;

	@NotBlank
	private String type;

	private String selectionType;

	private String title;

	@ElementCollection
	@CollectionTable(name = "LIST_ITEMS", joinColumns = @JoinColumn(name = "i_list_id"))
	@JsonBackReference
	private List<ListItemEntity> items;

	@ElementCollection
	@CollectionTable(name = "BUTTONS", joinColumns = @JoinColumn(name = "b_list_id"))
	@JsonBackReference
	private List<ListItemEntity> buttons;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rich_content_fk", nullable = false)
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

	public List<ListItemEntity> getItems()
	{
		return items;
	}

	public void setItems( List<ListItemEntity> items )
	{
		this.items = items;
	}

//	public List<ListItemEntity> getButtons()
//	{
//		return buttons;
//	}
//
//	public void setButtons( List<ListItemEntity> buttons )
//	{
//		this.buttons = buttons;
//	}

	public RichContentEntity getRichContent()
	{
		return richContent;
	}

	public void setRichContent( RichContentEntity richContent )
	{
		this.richContent = richContent;
	}
}
