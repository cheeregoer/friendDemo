#### User Stories
**1. As a user, I need an API to create a friend connection between two email addresses.**
* This is a POST request with the following context path (http://localhost:8090/friend/createFriendConnection)
* If there is more than 3 friend list being made, 
API will response with the following message: "1 connection make at a time"
```
The API should receive the following JSON request:
{
  "friends":
    [
      "Alpha@email.com",
      "jm@example.com"
    ]
}
```

**2. As a user, I need an API to retrieve the friends list for an email address.**
* This is a POST request with the following context path (http://localhost:8090/friend/findFriend)
* If there is no friends found. 
API will response with the following message: "No friendfound."
```
The API should receive the following JSON request:
{
  "email": "Alpha@email.com"
}
```

**3. As a user, I need an API to retrieve the common friends list between two email addresses.**
* This is a POST request with the following context path (http://localhost:8090/friend/findCommonFriend)
```
The API should receive the following JSON request:
{
  "friends":
    [
      "jm@example.com",
      "Alpha@email.com"
    ]
}
```

**4. As a user, I need an API to subscribe to updates for an email address.**
* This is a POST request with the following context path (http://localhost:8090/friend/subscribe)
```
The API should receive the following JSON request:
{
  "requestor": "jm@example.com",
  "target": "Alpha@email.com"
}
```


**5. As a user, I need an API to block updates for an email address.**
* This is a POST request with the following context path (http://localhost:8090/friend/block)
```
The API should receive the following JSON request:
{
  "requestor": "cy@example.com",
  "target": "Alpha@email.com"
}
```

**6. As a user, I need an API to retrieve all email addresses that can receive updates for an email address.**
* This is a POST request with the following context path (http://localhost:8090/friend/broadcast)
```
The API should receive the following JSON request:
{
  "sender":  "Alpha@email.com",
  "text": "Hello World! All my Friends"
}
```
# friendDemo
