package it.codegen.rnd.chatbots.master.repository;

import it.codegen.rnd.chatbots.master.model.entity.IOBTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOBTagRepository extends JpaRepository<IOBTagEntity, Long>
{
}
