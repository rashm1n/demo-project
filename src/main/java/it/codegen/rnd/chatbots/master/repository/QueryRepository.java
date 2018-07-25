package it.codegen.rnd.chatbots.master.repository;

import it.codegen.rnd.chatbots.master.model.entity.QueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QueryRepository extends JpaRepository<QueryEntity, Long>
{
}
