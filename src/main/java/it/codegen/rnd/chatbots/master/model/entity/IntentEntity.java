package it.codegen.rnd.chatbots.master.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "INTENTS")
@EntityListeners(AuditingEntityListener.class)
public class IntentEntity extends AuditEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	private String name;

	@OneToMany(mappedBy = "intent", orphanRemoval = true)
	List<QueryEntity> queries;

	public Long getId()
	{
		return id;
	}

	public void setId( Long id )
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName( String name )
	{
		this.name = name;
	}

	public List<QueryEntity> getQueries()
	{
		return queries;
	}

	public void setQueries( List<QueryEntity> queries )
	{
		this.queries = queries;
	}

	public void addQuery( QueryEntity query )
	{
		if ( queries == null )
		{
			queries = new ArrayList<>();
		}
		queries.add( query );
	}
}
