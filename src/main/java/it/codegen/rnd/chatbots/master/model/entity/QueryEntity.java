package it.codegen.rnd.chatbots.master.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "QUERIES")
@EntityListeners(AuditingEntityListener.class)
public class QueryEntity extends AuditEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty
	private String query;

	@NotNull
	@ManyToOne
	@JsonBackReference
	private IntentEntity intent;

	@ElementCollection
	@CollectionTable(name = "PHRASES", joinColumns = @JoinColumn(name = "query_id"))
	private List<PhraseEntity> phrases;

	@OneToOne(mappedBy = "query", cascade = CascadeType.ALL, orphanRemoval = true)
	private IOBTagEntity iobTags;

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getQuery()
	{
		return query;
	}

	public void setQuery( String query )
	{
		this.query = query;
	}

	public IntentEntity getIntent()
	{
		return intent;
	}

	public void setIntent( IntentEntity intent )
	{
		this.intent = intent;
	}

	public List<PhraseEntity> getPhrases()
	{
		return phrases;
	}

	public void setPhrases( List<PhraseEntity> phrases )
	{
		this.phrases = phrases;
	}

	public void addPhrase( PhraseEntity phrase )
	{
		if ( phrases == null )
		{
			phrases = new ArrayList<>();
		}
		phrases.add( phrase );
	}

	public IOBTagEntity getIobTags()
	{
		return iobTags;
	}

	public void setIobTags( IOBTagEntity iobTags )
	{
		this.iobTags = iobTags;
	}
}
