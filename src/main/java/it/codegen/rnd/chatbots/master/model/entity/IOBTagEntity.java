package it.codegen.rnd.chatbots.master.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "IOB_TAGS")
@EntityListeners(AuditingEntityListener.class)
public class IOBTagEntity extends AuditEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	private String tokens;
	@NotEmpty
	private String tags;

	@NotEmpty
	private String intent;

	@OneToOne
	@JsonBackReference
	private QueryEntity query;

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public QueryEntity getQuery()
	{
		return query;
	}

	public void setQuery( QueryEntity query )
	{
		this.query = query;
	}

	public String getTokens()
	{
		return tokens;
	}

	public void setTokens( String tokens )
	{
		this.tokens = tokens;
	}

	public String getTags()
	{
		return tags;
	}

	public void setTags( String tags )
	{
		this.tags = tags;
	}

	public String getIntent()
	{
		return intent;
	}

	public void setIntent( String intent )
	{
		this.intent = intent;
	}
}
