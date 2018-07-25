package it.codegen.rnd.chatbots.master.Utils;

import it.codegen.rnd.chatbots.master.model.entity.response.BotResponseEntity;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ResponseSpecification implements Specification<BotResponseEntity>
{
	private SearchCriteria criteria;

	public ResponseSpecification( SearchCriteria criteria )
	{
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate( Root<BotResponseEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder )
	{

		if ( criteria.getOperation().equalsIgnoreCase( ">" ) )
		{
			return builder.greaterThanOrEqualTo(
					root.<String>get( criteria.getKey() ), criteria.getValue().toString() );
		}
		else if ( criteria.getOperation().equalsIgnoreCase( "<" ) )
		{
			return builder.lessThanOrEqualTo(
					root.<String>get( criteria.getKey() ), criteria.getValue().toString() );
		}
		else if ( criteria.getOperation().equalsIgnoreCase( ":" ) )
		{
			if ( root.get( criteria.getKey() ).getJavaType() == String.class )
			{
				return builder.like(
						root.<String>get( criteria.getKey() ), "%" + criteria.getValue() + "%" );
			}
			else
			{
				return builder.equal( root.get( criteria.getKey() ), criteria.getValue() );
			}
		}
		return null;
	}

}
