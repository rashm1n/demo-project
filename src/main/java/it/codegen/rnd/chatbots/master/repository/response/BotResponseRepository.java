package it.codegen.rnd.chatbots.master.repository.response;
import it.codegen.rnd.chatbots.master.model.entity.response.BotResponseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BotResponseRepository extends JpaRepository<BotResponseEntity, Long> , JpaSpecificationExecutor<BotResponseEntity>
{
}
