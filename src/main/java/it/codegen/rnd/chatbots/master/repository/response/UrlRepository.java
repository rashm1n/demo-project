package it.codegen.rnd.chatbots.master.repository.response;

import it.codegen.rnd.chatbots.master.model.entity.response.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<UrlEntity, Long>
{
}
