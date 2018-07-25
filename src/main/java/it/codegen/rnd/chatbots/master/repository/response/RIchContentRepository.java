package it.codegen.rnd.chatbots.master.repository.response;

import it.codegen.rnd.chatbots.master.model.entity.response.RichContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RIchContentRepository extends JpaRepository<RichContentEntity, Long>
{
}
