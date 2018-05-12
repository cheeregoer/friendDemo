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
@AllArgsConstructor
@NoArgsConstructor
public class Friend implements Serializable{

	private static final long serialVersionUID = -2725950178176259242L;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	private Long id;
	private String email;
	
}
