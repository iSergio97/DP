
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.HandyWorker;
import domain.MiscellaneousRecord;

@Repository
public interface MiscellaneousRecordRepository extends JpaRepository<MiscellaneousRecord, Integer> {

	@Query("select m.curriculum.handyWorker from MiscellaneousRecord m where m.title like '%?1%' or m.attachment like '%?1%' or m.comments like '%?1%'")
	List<HandyWorker> getHandyWorkersWithMiscellaneousRecordsWithWord(String word);

}
