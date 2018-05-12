package service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import domain.FriendAssociationEnum;
import domain.FriendConnection;
import repo.FriendConnectionRepository;

@Service
public class FriendConnectionService {
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	FriendConnectionRepository friendConnectionRepository;
	
	
	public boolean addFriendConnection(String emailOne, String emailTwo) {
		List<FriendConnection> friendList= friendConnectionRepository.getFriendConnectionByAssociationNatureAndFriendEmail(FriendAssociationEnum.FRIEND.getAssociationStatus(), emailOne);
		if(friendList != null && !friendList.isEmpty()) {
			long count = friendList.stream()
			.filter(f -> f.getAssociatedFriendEmail().equalsIgnoreCase(emailTwo) || f.getFriendEmail().equalsIgnoreCase(emailTwo))
			.count();
			if(count==0 && !friendConnectionRepository.existByFriendEmailAndAssociatedEmailAndAssociationNature(emailOne, emailTwo, FriendAssociationEnum.BLOCK.getAssociationStatus())){
				createAssociation(emailOne, emailTwo, FriendAssociationEnum.FRIEND.getAssociationStatus());
				return true;
			}
		}
		else{
			if(!friendConnectionRepository.existByFriendEmailAndAssociatedEmailAndAssociationNature(emailOne, emailTwo, FriendAssociationEnum.BLOCK.getAssociationStatus())){
				createAssociation(emailOne, emailTwo, FriendAssociationEnum.FRIEND.getAssociationStatus());
			}
			return true;
		}
		return false;
	}
	
	public List<String> retrieveFriendList(String email) {
		
		List<FriendConnection> friendConnectionList = friendConnectionRepository.findByFriendEmailAndAssociationNature(email, FriendAssociationEnum.FRIEND.getAssociationStatus());
		
		HashSet<String> friendList = new HashSet<>();
		
		friendList = friendConnectionList.stream()
				.map(FriendConnection::getAssociatedFriendEmail)
				.collect(Collectors.toCollection(HashSet::new));
		
		List<FriendConnection> associatedFriendConnectionList = friendConnectionRepository.findByAssociatedFriendEmailAndAssociationNature(email, FriendAssociationEnum.FRIEND.getAssociationStatus());
		friendList.addAll(associatedFriendConnectionList.stream().map(FriendConnection::getFriendEmail).collect(Collectors.toCollection(HashSet::new)));
		
		return new ArrayList<String>(friendList);
		
	}

	public List<String> retrieveCommonFriendList(String emailA, String emailB) {
		List<String> friendA = retrieveFriendList(emailA);
		List<String> friendB = retrieveFriendList(emailB);
		List<String> commonFriend = new ArrayList<String>(friendA);
		commonFriend.retainAll(friendB);
		return commonFriend.stream().filter(f -> !f.equalsIgnoreCase(emailA) || !f.equalsIgnoreCase(emailB)).collect(Collectors.toList());
	}
	
	public boolean createSubscribeConnection(String requestor, String target) {
		boolean isFollowing = friendConnectionRepository.existByFriendEmailAndAssociatedEmailAndAssociationNature(requestor, target, FriendAssociationEnum.FOLLOW.getAssociationStatus());
		if(!isFollowing){
			createAssociation(requestor, target, FriendAssociationEnum.FOLLOW.getAssociationStatus());
			return true;
		}
		return false;
		
	}
	
	public List<String> getBroadcastEmailList(String sender, String text) {
		List<String> friendList = retrieveFriendList(sender);
		List<FriendConnection> subscribeList = friendConnectionRepository.findByAssociatedFriendEmailAndAssociationNature(sender, FriendAssociationEnum.FOLLOW.getAssociationStatus());
		List<String> blockList = friendConnectionRepository.getFriendConnectionByAssociationNatureAndFriendEmail(FriendAssociationEnum.BLOCK.getAssociationStatus(), sender).stream().map(FriendConnection::getAssociatedFriendEmail).collect(Collectors.toList());

		HashSet<String> allowedList = new HashSet<>(friendList);
		
		allowedList.addAll(subscribeList.stream().map(FriendConnection::getFriendEmail).collect(Collectors.toList()));
		allowedList.addAll(getMentionedUsers(sender, text));
		
		return allowedList.stream().filter(s -> !blockList.contains(s)).collect(Collectors.toList());
	}
	
	public boolean createBlockConnection(String requestor, String target) {
		boolean isBlock = friendConnectionRepository.existByFriendEmailAndAssociatedEmailAndAssociationNature(requestor, target, FriendAssociationEnum.BLOCK.getAssociationStatus());
		if(!isBlock) {
			createAssociation(requestor, target, FriendAssociationEnum.BLOCK.getAssociationStatus());
			return true;
		}
		return false;
	}
	
	private void createAssociation(String friendEmail, String associatedFriendEmail, String associationNature) {
		FriendConnection friend = new FriendConnection(friendEmail, associatedFriendEmail, associationNature);
		friendConnectionRepository.save(friend);
	}

	public List<String> getMentionedUsers(String sender, String text){
		HashSet<String> allUsers = new HashSet<String>();
		friendConnectionRepository.findAll().forEach(friendConnection->{
			allUsers.add(friendConnection.getAssociatedFriendEmail());
			allUsers.add(friendConnection.getFriendEmail());
		});
		return allUsers.stream().filter(user -> text.contains(user)).collect(Collectors.toList());
	}
}
