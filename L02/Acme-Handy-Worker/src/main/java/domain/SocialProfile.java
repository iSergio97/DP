
package domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

public class SocialProfile {

	private String nickName;
	private String socialNetworkName;
	private String link;

	private Actor actor;

	@NotNull
	@NotBlank
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(final String nickName) {
		this.nickName = nickName;
	}

	@NotNull
	@NotBlank
	public String getSocialNetworkName() {
		return this.socialNetworkName;
	}

	public void setSocialNetworkName(final String socialNetworkName) {
		this.socialNetworkName = socialNetworkName;
	}

	@NotNull
	@URL
	public String getLink() {
		return this.link;
	}

	public void setLink(final String link) {
		this.link = link;
	}

	@NotNull
	@Valid
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

}
