package it.codegen.rnd.chatbots.master.repository;

import it.codegen.rnd.chatbots.master.model.entity.IntentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntentRepository extends JpaRepository<IntentEntity, Long>
{
}
