package web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import api.JsonRequest;
import api.JsonResponse;
import service.FriendConnectionService;

@RequestMapping("/friend")
@RestController
public class FriendController {
	@Autowired
	MessageSource messageSource;

	@Autowired
	FriendConnectionService friendConnectionService;
	
	/*
	 * condition to check if 
	 * 1) there is any existing friend connection
	 * 2) each of them as block connection from each other
	 */
	@RequestMapping(value="/createFriendConnection", method = RequestMethod.POST)
	public JsonResponse createFriendConnection ( @RequestBody JsonRequest jsonRequest) {
		
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(false);

		if(jsonRequest!= null) {
			List<String> friendList = jsonRequest.getFriends();
			if(friendList.size()>2) {
				jsonResponse.setStatusMessage(messageSource.getMessage("friend.connection", null, null));
				return jsonResponse;
			}
			boolean added = friendConnectionService.addFriendConnection(friendList.get(0), friendList.get(1));
			if(added)
				jsonResponse.setSuccess(true);
			else
				jsonResponse.setStatusMessage(messageSource.getMessage("friend.exist", null, null));
		}
		return jsonResponse;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public JsonResponse retrieveFriendsList ( @RequestBody JsonRequest jsonRequest) {
		String friend = jsonRequest.getFriend();
		List<String> friendList = friendConnectionService.retrieveFriendList(friend);
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(false);
		if(!friendList.isEmpty()){
			jsonResponse.setSuccess(true);
			jsonResponse.setFriends(friendList);
			jsonResponse.setCount(friendList.size());
		}
		else {
			jsonResponse.setStatusMessage(messageSource.getMessage("friend.not.found", null, null));
		}
		
		return jsonResponse;
		
	}
	
	@RequestMapping(value="/findCommon", method = RequestMethod.POST)
	public JsonResponse retrieveCommonFriendList( @RequestBody JsonRequest jsonRequest) {
		List<String> friendsList = jsonRequest.getFriends();
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(false);
		if(friendsList.size()==2) {
			List<String> commonFriendList = friendConnectionService.retrieveCommonFriendList(friendsList.get(0), friendsList.get(1));
			jsonResponse.setSuccess(true);
			jsonResponse.setFriends(commonFriendList);
			jsonResponse.setCount(commonFriendList.size());
		}
		
		return jsonResponse;
	}
	
	@RequestMapping(value="/subscribe", method = RequestMethod.POST)
	public JsonResponse subscribe( @RequestBody JsonRequest jsonRequest) {
		String requestor = jsonRequest.getRequestor();
		String target = jsonRequest.getTarget();
		JsonResponse jsonResponse = new JsonResponse();
		jsonResponse.setSuccess(false);
		if(friendConnectionService.createSubscribeConnection(requestor, target))
			jsonResponse.setSuccess(true);
		
		return jsonResponse;
	}
	
	/**
	 * 
	Suppose "andy@example.com" blocks "john@example.com":

	if they are connected as friends, then "andy" will no longer receive notifications from "john"
	if they are not connected as friends, then no new friends connection can be added
	 */
	@RequestMapping(value="/block", method = RequestMethod.POST)
	public JsonResponse block( @RequestBody JsonRequest jsonRequest) {
		String requestor = jsonRequest.getRequestor();
		String target = jsonRequest.getTarget();
		
		JsonResponse jsonResponse = new JsonResponse();
		if(friendConnectionService.createBlockConnection(requestor, target))
			jsonResponse.setSuccess(true);
		
		return jsonResponse;
	}
	
	
	/*
	 * I need an API to retrieve all email addresses that can receive updates for an email address.

		Eligibility for receiving updates for i.e. "john@example.com" either of the below:

		has a friend connection with sender
		has subscribed to updates from sender
		has not blocked updates from "john@example.com"
		has been @mentioned in the update
	 */
	@RequestMapping(value="/broadcast",method = RequestMethod.POST)
	public JsonResponse broadcastEmail( @RequestBody JsonRequest jsonRequest) {
		String sender = jsonRequest.getSender();
		String text = jsonRequest.getText();
		List<String> broadcastList = friendConnectionService.getBroadcastEmailList(sender, text);
		
		JsonResponse jsonResponse = new JsonResponse();
		
		if(!broadcastList.isEmpty()){
			jsonResponse.setRecipients(broadcastList);
			jsonResponse.setSuccess(true);
		}
		return jsonResponse;
	}
}
