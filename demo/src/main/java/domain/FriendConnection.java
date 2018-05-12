package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendConnection implements Serializable{
	
	private static final long serialVersionUID = -5518612432976379993L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String friendEmail;
	private String associatedFriendEmail;
	private String associationNature;
	

	public FriendConnection(String friendEmail, String associatedFriendEmail, String associationNature) {
		this.friendEmail = friendEmail;
		this.associatedFriendEmail = associatedFriendEmail;
		this.associationNature = associationNature;
	}
}
