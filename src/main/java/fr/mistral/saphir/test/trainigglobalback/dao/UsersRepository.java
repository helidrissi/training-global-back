package fr.mistral.saphir.test.trainigglobalback.dao;



import fr.mistral.saphir.test.trainigglobalback.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity,Long> {


    UsersEntity findByEmail(String email);
}
