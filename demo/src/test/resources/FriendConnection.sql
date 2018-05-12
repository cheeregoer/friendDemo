truncate table friend_connection;
	

--friend connection
insert into friend_connection (id, friend_email,associated_friend_email, association_nature) values (null, 'alpha@interview.com', 'charlie@interview.com', 'friend');
insert into friend_connection (id, friend_email,associated_friend_email, association_nature) values (null, 'bravo@interview.com', 'alpha@interview.com', 'friend');
insert into friend_connection (id, friend_email,associated_friend_email, association_nature) values (null, 'bravo@interview.com', 'charlie@interview.com', 'friend');
insert into friend_connection (id, friend_email,associated_friend_email, association_nature) values (null, 'alpha@interview.com', 'delta@interview.com', 'friend');

--subscribe connection
insert into friend_connection (id, friend_email,associated_friend_email, association_nature) values (null, 'delta@interview.com', 'echo@interview.com', 'follow');

