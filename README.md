# friendDemo

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
      "a.com",
      "b.com"
    ]
}
```

```
The API will return the following response for successful creation
{
  "success": true
}
```

**2. As a user, I need an API to retrieve the friends list for an email address.**
* This is a POST request with the following context path (http://localhost:8090/friend/list)
* If there is no friends found. 
API will response with the following message: "No Friend Found"
```
The API should receive the following JSON request:
{
  "friend": "a.com"
}
```

```
The API will return the following response for successful listing:
{
    "success": true,
    "friends": [
        "b.com"
    ],
    "count": 1
}
```

**3. As a user, I need an API to retrieve the common friends list between two email addresses.**
* This is a POST request with the following context path (http://localhost:8090/friend/findCommonFriend)
* If user do not input 2 friends, the  API will response with the following message: "Can only find common friend between 2 friends"
```
The API should receive the following JSON request:
{
  "friends":
    [
      "a.com",
      "c.com"
    ]
}
```
```
The API will return the following response if there's a common friend:
{
    "success": true,
    "friends": [
        "b.com"
    ],
    "count": 1
}

```

**4. As a user, I need an API to subscribe to updates for an email address.**
* This is a POST request with the following context path (http://localhost:8090/friend/subscribe)
```
The API should receive the following JSON request:
{
	"requestor": "x.com",
	"target": "a.com"
}
```
```
The API will return the following response:
{
    "success": true
}
```

**5. As a user, I need an API to block updates for an email address.**
* This is a POST request with the following context path (http://localhost:8090/friend/block)
```
The API should receive the following JSON request:
{
	"requestor": "a.com",
	"target": "z.com"
}
```
```
The API will return the following response:
{
    "success": true
}
```

**6. As a user, I need an API to retrieve all email addresses that can receive updates for an email address.**
* This is a POST request with the following context path (http://localhost:8090/friend/broadcast)
```
The API should receive the following JSON request:
{
	"sender": "a.com",
	"text": "hello world t.com"
}
```
```
The API should receive the following JSON request:
{
{
    "success": true,
    "recipients": [
        "b.com",
        "t.com"
    ]
}
}
```

**Docker**
The latest docker image has been pushed to docker hub. To run the docker image, enter the following command in your docker terminal:
```
docker run -p 8080:8080 cheeregoer/frienddemo
```
or pull the image via the command:
```
docker pull cheeregoer/frienddemo
```

Docker file can be found in /docker directory


