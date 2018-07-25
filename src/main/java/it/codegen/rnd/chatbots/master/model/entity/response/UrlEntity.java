package it.codegen.rnd.chatbots.master.model.entity.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.codegen.rnd.chatbots.master.model.entity.AuditEntity;
import org.apache.htrace.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "URL_RESPONSES")
@EntityListeners(AuditingEntityListener.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UrlEntity extends AuditEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String url;

	@ManyToOne
	@JoinColumn(name = "response_fk", referencedColumnName = "response_id")
	@JsonBackReference
	@JsonIgnore
	private BotResponseEntity botResponse;

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl( String url )
	{
		this.url = url;
	}

	public BotResponseEntity getBotResponse()
	{
		return botResponse;
	}

	public void setBotResponse( BotResponseEntity botResponse )
	{
		this.botResponse = botResponse;
	}
}
