package it.codegen.rnd.chatbots.master.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.codegen.rnd.chatbots.master.model.entity.response.BotResponseEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ENTITIES")
@EntityListeners(AuditingEntityListener.class)
public class TagEntity extends AuditEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	@NotEmpty
	private String name;

	@OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	private List<TagDataLabelEntity> entityDataLabels;

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

	public List<TagDataLabelEntity> getEntityDataLabels()
	{
		return entityDataLabels;
	}

	public void setEntityDataLabels( List<TagDataLabelEntity> entityDataLabels )
	{
		this.entityDataLabels = entityDataLabels;
	}

	public void addEntityDataLabel( TagDataLabelEntity tagDataLabel )
	{
		if ( this.entityDataLabels == null )
		{
			this.entityDataLabels = new ArrayList<>();
		}
		this.entityDataLabels.add( tagDataLabel );
	}
}
