package it.codegen.rnd.chatbots.master.repository.response;

import it.codegen.rnd.chatbots.master.model.entity.response.CarouselEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarouselRepository extends JpaRepository<CarouselEntity, Long>
{
}
