
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

import domain.Actor;
import domain.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

	@Query("select m.sender from Message m where m.subject like '%?1%' or m.body like '%?1%'")
	List<Actor> getActorsWhoSendMessagesWithWord(String word);

}
