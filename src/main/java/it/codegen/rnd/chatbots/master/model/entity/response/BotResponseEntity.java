
package it.codegen.rnd.chatbots.master.model.entity.response;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import it.codegen.rnd.chatbots.master.model.entity.AuditEntity;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "RESPONSES")
@EntityListeners(AuditingEntityListener.class)
public class BotResponseEntity extends AuditEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "response_id")
	private Long id;

	@Column(name = "reply_name")
	private String replyName;

	@Column(name = "intent_name")
	private String intentName;

	@Column(name = "entity_names")
	private String entityNames;

	@NotNull
	private String type;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "botResponse", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	Set<UrlEntity> urlEntities;

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "botResponse", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	Set<RichContentEntity> richContents;

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getIntentName()
	{
		return intentName;
	}

	public void setIntentName( String intentName )
	{
		this.intentName = intentName;
	}

	public String getType()
	{
		return type;
	}

	public void setType( String type )
	{
		this.type = type;
	}

	public Set<UrlEntity> getUrlEntities()
	{
		return urlEntities;
	}

	public void setUrlEntities( Set<UrlEntity> urlEntities )
	{
		this.urlEntities = urlEntities;
	}

	public Set<RichContentEntity> getRichContents()
	{
		return richContents;
	}

	public void setRichContents( Set<RichContentEntity> richContents )
	{
		this.richContents = richContents;
	}

	public String getEntityNames()
	{
		return entityNames;
	}

	public void setEntityNames( String entityNames )
	{
		this.entityNames = entityNames;
	}

	public String getReplyName() { return replyName; }

	public void setReplyName( String replyName ) { this.replyName = replyName; }
}
