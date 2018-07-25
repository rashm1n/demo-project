package it.codegen.rnd.chatbots.master.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ENTITY_DATA_LABELS")
@EntityListeners(AuditingEntityListener.class)
public class TagDataLabelEntity extends AuditEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String label;

	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "entity_id")
	private TagEntity entity;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "ENTITY_DATA_VALUES", joinColumns = @JoinColumn(name = "entity_data_label_id"))
	private List<TagDataValueEntity> tagDataValues;

	public String getLabel()
	{
		return label;
	}

	public void setLabel( String label )
	{
		this.label = label;
	}

	public TagEntity getEntity()
	{
		return entity;
	}

	public void setEntity( TagEntity entity )
	{
		this.entity = entity;
	}

	public List<TagDataValueEntity> getTagDataValues()
	{
		return tagDataValues;
	}

	public void setTagDataValues( List<TagDataValueEntity> tagDataValues )
	{
		this.tagDataValues = tagDataValues;
	}

	public void addTagDataValue( TagDataValueEntity tagDataValue )
	{
		if ( this.tagDataValues == null )
		{
			this.tagDataValues = new ArrayList<>();
		}
		this.tagDataValues.add( tagDataValue );
	}
}
