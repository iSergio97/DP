
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.EndorserRecord;
import domain.HandyWorker;

@Repository
public interface EndorserRecordRepository extends JpaRepository<EndorserRecord, Integer> {

	@Query("select e.curriculum.handyWorker from EndorserRecord e where e.endorserFullName like '%?1%' or e.endorserEmail like '%?1%' or e.endorserPhoneNumber like '%?1%' or e.endorserLinkedIn like '%?1%' or e.comments like '%?1%'")
	List<HandyWorker> getHandyWorkersWithEndorserRecordsWithWord(String word);

}
