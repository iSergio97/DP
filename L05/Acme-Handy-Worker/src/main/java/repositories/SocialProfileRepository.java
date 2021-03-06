
package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;
import domain.SocialProfile;

@Repository
public interface SocialProfileRepository extends JpaRepository<SocialProfile, Integer> {

	@Query("select s.actor from SocialProfile s where s.nickName like '%?1%' or s.socialNetworkName like '%?1%' or s.link like '%?1%'")
	List<Actor> getActorsWithSocialProfilesWithWord(String word);

}
