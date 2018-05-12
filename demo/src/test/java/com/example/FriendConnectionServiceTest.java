package com.example;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import repo.FriendConnectionRepository;
import service.FriendConnectionService;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendConnectionServiceTest {

	@Autowired
	FriendConnectionRepository friendConnectionRepository;
	
	@Autowired
	FriendConnectionService friendConnectionService;
	
	private static final String ALPHA = "alpha@interview.com";
	private static final String BRAVO = "bravo@interview.com";
	private static final String CHARLIE = "charlie@interview.com";
	private static final String DELTA = "delta@interview.com";
	private static final String ECHO = "echo@interview.com";
	private static final String FOXTROT = "foxtrot@interview.com";
	private static final String GOLF = "golf@interview.com";
	
	private static final String ZULU = "zulu@interview.com";
	
	
	@Test
	@Sql("/FriendConnection.sql")
	public void getFriendList(){
		List<String> friendList = friendConnectionService.retrieveFriendList(ALPHA);
		assertThat(friendList).isNotNull();
		assertThat(friendList).hasSize(3);
		
	}
	
	@Test
	@Sql("/TruncateFriendConnection.sql")
	public void getFriendList_Empty() {
		List<String> friendList = friendConnectionService.retrieveFriendList(ZULU);
		assertThat(friendList).isEmpty();
	}
	
	@Test
	@Sql("/TruncateFriendConnection.sql")
	public void addFriend(){
		boolean added = friendConnectionService.addFriendConnection(ECHO, GOLF);
		assertThat(added).isTrue();
		List<String> friendList = friendConnectionService.retrieveFriendList(ECHO);
		assertThat(friendList).hasSize(1);
		assertThat(friendList.get(0)).isEqualTo(GOLF);
	}
	
	@Test
	@Sql("/FriendConnection.sql")
	public void addFriend_Exist() {
		boolean added = friendConnectionService.addFriendConnection(ALPHA, BRAVO);
		assertThat(added).isFalse();
	}
	
	@Test
	@Sql("/FriendConnection.sql")
	public void retrieveCommonFriendList(){
		List<String> commonFriendList = friendConnectionService.retrieveCommonFriendList(ALPHA, BRAVO);
		
		assertThat(commonFriendList).isNotEmpty();
		assertThat(commonFriendList).hasSize(1);
		assertThat(commonFriendList.get(0)).isEqualTo(CHARLIE);
	}
	
	@Test
	@Sql("/FriendConnection.sql")
	public void retrieveCommonFriendList_NotExist(){
		List<String> commonFriendList = friendConnectionService.retrieveCommonFriendList(ALPHA, GOLF);
		
		assertThat(commonFriendList).isEmpty();
		
	}
	
	@Test
	public void createSubscribeConnection(){
		boolean added = friendConnectionService.createSubscribeConnection(FOXTROT, ALPHA);
		
		assertThat(added).isTrue();
	}
	
	
}
