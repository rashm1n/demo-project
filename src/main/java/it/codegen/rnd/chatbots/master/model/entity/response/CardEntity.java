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
@Table(name = "CARD_TYPE")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardEntity extends AuditEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "card_id")
	private Long id;

	@NotBlank
	private String type;

	private String title;

	private String subTitle;

	private String text;

	private String viewHTML;

	private String imageUrl;

	@ElementCollection
	@CollectionTable(name = "BUTTONS", joinColumns = @JoinColumn(name = "s_card_id"))
	@JsonManagedReference
	private Set<ListItemEntity> buttons;

	@ElementCollection
	@CollectionTable(name = "COORDINATES", joinColumns = @JoinColumn(name = "m_card_id"))
	@JsonManagedReference
	private Set<MarkerEntity> coordinates;

	@ManyToOne
	@JsonBackReference
	@JsonIgnore
	@JoinColumn(name = "carousel_fk", referencedColumnName = "carousel_id")
	private CarouselEntity carousel;

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

	public String getTitle()
	{
		return title;
	}

	public void setTitle( String title )
	{
		this.title = title;
	}

	public String getSubTitle()
	{
		return subTitle;
	}

	public void setSubTitle( String subTitle )
	{
		this.subTitle = subTitle;
	}

	public String getText()
	{
		return text;
	}

	public void setText( String text )
	{
		this.text = text;
	}

	public String getViewHTML()
	{
		return viewHTML;
	}

	public void setViewHTML( String viewHTML )
	{
		this.viewHTML = viewHTML;
	}

	public Set<ListItemEntity> getButtons()
	{
		return buttons;
	}

	public void setButtons( Set<ListItemEntity> buttons )
	{
		this.buttons = buttons;
	}

	public Set<MarkerEntity> getCoordinates()
	{
		return coordinates;
	}

	public void setCoordinates( Set<MarkerEntity> coordinates )
	{
		this.coordinates = coordinates;
	}

	public CarouselEntity getCarousel()
	{
		return carousel;
	}

	public void setCarousel( CarouselEntity carousel )
	{
		this.carousel = carousel;
	}

	public String getImageUrl()
	{
		return imageUrl;
	}

	public void setImageUrl( String imageUrl )
	{
		this.imageUrl = imageUrl;
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
