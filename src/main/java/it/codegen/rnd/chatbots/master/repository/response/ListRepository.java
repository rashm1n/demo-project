package it.codegen.rnd.chatbots.master.repository.response;

import it.codegen.rnd.chatbots.master.model.entity.response.ListEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, Long>
{
}
