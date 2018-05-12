package exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class FriendExistException extends Exception {
	
	@Autowired
	MessageSource messageSource;
	
	public FriendExistException(String message){
		super(message);
	}
}
