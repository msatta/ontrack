#Exercise 7

How would you design a REST API for an address book? What endpoints will it have (feel free to provide sample curl requests)?
How would you handle errors?

Assuming that the information that available in this API are something like:

```json
{
    "id":123,
    "name":"Michele",
    "surname":"Satta",
    "email":"michele.satta@edgecasesoftware.co.uk",
    ...
}
```

I would create a simple CRUD interface:

###Creation of a new entry  
[POST] /api/v1/addressbook/person/
Result status:
- 201 : Resource created
- 422 : Missing mandatory information or request not processable 

###Get all entries  
[GET] /api/v1/addressbook/person/
Result status:
- 200 : get list of entries  

Note: it's possible to implement some kind of pagination and it should be possible, using the querystring, to filter the results.
Eg: ```/api/v1/addressbook/person?email=michele.satta@edgecasesoftware.co.uk&size=100&page=1```

###Get a single entry  
[GET] /api/v1/addressbook/person/{userId}
Result status:
- 200: get the single user data
- 404: user not present

###Modify and entry
[PUT] /api/v1/addressbook/person/{userId}
Result status:
- 200: get the single user data
- 422 : Missing mandatory information or request not processable 

###Delete an entry  
[DELETE] /api/v1/addressbook/person/{userId}
Result status:
- 204: element deleted
- 404: user not present

Note: All exception should be handled with a specific response and maybe an error code