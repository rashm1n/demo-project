package it.codegen.rnd.chatbots.master.model.entity.response;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.codegen.rnd.chatbots.master.model.entity.AuditEntity;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "RICH_CONTENTS")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RichContentEntity extends AuditEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "rich_content_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "response_fk", referencedColumnName = "response_id")
	@JsonBackReference
	@JsonIgnore
	private BotResponseEntity botResponse;

	@NotNull
	private String replyType;
	private String displayText;
	private String textToSpeech;

	@NotNull
	private String contentType;

	@OneToOne(fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			mappedBy = "richContent")
	@JsonManagedReference
	private CarouselEntity carouselEntity;

	@OneToOne(fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			mappedBy = "richContent")
	@JsonManagedReference
	private CardEntity cardEntity;

	@OneToOne(fetch = FetchType.EAGER,
			cascade = CascadeType.ALL,
			mappedBy = "richContent")
	@JsonManagedReference
	private ListEntity listEntity;

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getReplyType()
	{
		return replyType;
	}

	public void setReplyType( String replyType )
	{
		this.replyType = replyType;
	}

	public String getDisplayText()
	{
		return displayText;
	}

	public void setDisplayText( String displayText )
	{
		this.displayText = displayText;
	}

	public String getTextToSpeech()
	{
		return textToSpeech;
	}

	public void setTextToSpeech( String textToSpeech )
	{
		this.textToSpeech = textToSpeech;
	}

	public String getContentType()
	{
		return contentType;
	}

	public void setContentType( String contentType )
	{
		this.contentType = contentType;
	}

	public CarouselEntity getCarouselEntity()
	{
		return carouselEntity;
	}

	public void setCarouselEntity( CarouselEntity carouselEntity )
	{
		this.carouselEntity = carouselEntity;
	}

	public ListEntity getListEntity()
	{
		return listEntity;
	}

	public void setListEntity( ListEntity listEntity )
	{
		this.listEntity = listEntity;
	}

	public BotResponseEntity getBotResponse()
	{
		return botResponse;
	}

	public void setBotResponse( BotResponseEntity botResponseR )
	{
		this.botResponse = botResponseR;
	}

	public CardEntity getCardEntity()
	{
		return cardEntity;
	}

	public void setCardEntity( CardEntity cardEntity )
	{
		this.cardEntity = cardEntity;
	}
}
