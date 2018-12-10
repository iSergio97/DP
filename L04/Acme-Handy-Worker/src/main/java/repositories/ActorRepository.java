
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import java.util.*;

@Repository
//@Transactional(readOnly = true)
//Investigar y justificar el @Transactional encima de los métodos
//Usar flush para probar los fallos
//Los servicios se sacan de los RF y RNF
public interface ActorRepository extends JpaRepository<Actor, Integer> {

	@Query("select a from Actor a where a.userAccount.id = ?1")
	Actor findByUserAccountId(int id);

	@Query("select a from Actor a where a.name like '%?1%' or a.middleName like '%?1%' or a.surname like '%?1%' or a.photo like '%?1%' or a.photo like '%?1%' or a.email like '%?1%' or a.phoneNumber like '%?1%' or a.address like '%?1%' or a.userAccount.username like '%?1%'")
	List<Actor> getActorsWithWord(String word);

}
