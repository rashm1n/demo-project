package it.codegen.rnd.chatbots.master;

import it.codegen.rnd.chatbots.master.controller.TagController;
import it.codegen.rnd.chatbots.master.model.entity.TagDataLabelEntity;
import it.codegen.rnd.chatbots.master.model.entity.TagDataValueEntity;
import it.codegen.rnd.chatbots.master.model.entity.TagEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntityControllerTest extends CommonTest
{
	@Autowired
	private TagController tagController;

	@Test
	public void saveEntityTest()
	{
		TagEntity tag = getNewTag();
		assertThat( tag ).isNotNull();
		assertThat( tag.getId() ).isGreaterThan( 0L );
	}

	@Test
	public void updateEntityTest()
	{
		TagEntity tag = tagController.getOne( getNewTag().getId() ).getBody();
		assertThat( tag ).isNotNull();

		tag.setName( "type_new" );
		TagDataLabelEntity label1 = new TagDataLabelEntity();
		label1.setLabel( "type.annual_pass.GOLD" );
		TagDataValueEntity value1 = new TagDataValueEntity();
		value1.setValue( "gold" );
		label1.addTagDataValue( value1 );
		TagDataValueEntity value2 = new TagDataValueEntity();
		value2.setValue( "gold pass" );
		label1.addTagDataValue( value2 );

		tag.addEntityDataLabel( label1 );
		tagController.update( tag.getId(), tag );
	}

	@Test
	public void deleteTest()
	{
		deleteTag( 5464L );
	}

	public TagEntity getNewTag()
	{
		TagDataLabelEntity label1 = new TagDataLabelEntity();
		label1.setLabel( "type.annual_pass.PLATINUM" );
		TagDataValueEntity value1 = new TagDataValueEntity();
		value1.setValue( "platinum" );
		label1.addTagDataValue( value1 );
		TagDataValueEntity value2 = new TagDataValueEntity();
		value2.setValue( "platinum pass" );
		label1.addTagDataValue( value2 );

		TagDataLabelEntity label2 = new TagDataLabelEntity();
		label2.setLabel( "type.annual_pass" );
		TagDataValueEntity value3 = new TagDataValueEntity();
		value3.setValue( "annual pass" );
		label2.addTagDataValue( value3 );
		TagDataValueEntity value4 = new TagDataValueEntity();
		value4.setValue( "pass" );
		label2.addTagDataValue( value4 );

		TagEntity tag = new TagEntity();
		tag.setName( "type" );
		tag.addEntityDataLabel( label1 );
		tag.addEntityDataLabel( label2 );

		return tagController.create( tag ).getBody();
	}

	public void deleteTag( Long id )
	{
		assertThat( tagController.delete( id ).getStatusCode() ).isEqualTo( HttpStatus.OK );
	}

}
