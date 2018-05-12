package domain;

public enum FriendAssociationEnum {
	
	FRIEND("friend"),
	FOLLOW("follow"),
	BLOCK("block");
	
	String associationStatus;

	FriendAssociationEnum(String associationStatus) {
		this.associationStatus = associationStatus;
	}
	
	public String getAssociationStatus() {
		return associationStatus;
	}
	
}
