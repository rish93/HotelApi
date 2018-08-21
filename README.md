# HotelApi


------------------------------------------------------------------------------------------
Services For Guest CheckIn
------------------------------------------------------------------------------------------

1a)
POST   http://localhost:8080/guest/checkIn
Header  Content-Type =application/json
Body RAW Application Json
i/p
 { 
 "firstName":"Rishabh",
 "lastName": "Mankatala", 
 "age":"24",
 "contact": "8736634414",
 "email":"rish93@msn.com"
 }

o/p
{
    "timestamp": 1534696015003,
    "message": "Checked In",
    "details": "Guest name Rishabh checkedIn"
}

1b) verify checkin same user, having name and contact number

POST   http://localhost:8080/guest/checkIn
Header  Content-Type =application/json
Body RAW Application Json
i/p
 { 
 "firstName":"Rishabh",
 "lastName": "Mankatala", 
 "age":"24",
 "contact": "8736634414",
 "email":"rish93@msn.com"
 }

o/p
{
    "timestamp": 1534696061999,
    "message": "Already CheckedIn",
    "details": "Guest with same name and contact already checked In, Contact admin to enable re checkin"
}


GET  http://localhost:8080/admin/guest/all
Header -> Authorization =Bearer {token}


POST  http://localhost:8080/guest/checkIn
Header = Content-Type =application/json
Body RAW Application Json
{
  
   "firstName":"Rishabh",
   "lastName": "M",
   "age":"24",
   "contact": "8756846722",
   "email":"rish93@msn.com"
}

1c) verify checkin with same contact but different name before admin checkout


POST  http://localhost:8080/guest/checkIn
Header = Content-Type =application/json
Body RAW Application Json
 {
 "firstName":"Prateek",
 "lastName": "M",
 "age":"24", 
 "contact": "8736634414", 
 "email":"rish93@msn.com" 
 }

o/p
{
    "timestamp": 1534696264417,
    "message": "Contact No already registered for checkIn not checked out",
    "details": " use another number or contact admin for checkOut"
}


------------------------------------------------------------------------------------------------
SERVICES FOR ADMIN (Authenticated)
-------------------------------------------------------------------------------------------------


-> Signup as admin to access admin service
i/p
POST localhost:8080/login/signup
Header Content/type /Application json
Body RAW Application Json
{
	"username":"admin",
	"password":"123"
}
o/p
{
    "timestamp": 1534695505549,
    "message": "Username admin1 registered",
    "details": "User with Admin privileges, needs token based authentication "
}

-> verify duplicate registration 
i/p
POST localhost:8080/login/signup
Header Content/type /Application json
Body RAW Application Json
{
	"username":"admin",
	"password":"123"
}
o/p
{
    "timestamp": 1534695376984,
    "message": "Username already taken",
    "details": "Enter unique Username"
}

-> Login to get the acces token for authenticated service

i/p
POST localhost:8080/login/signin
Header Content/type /Application json
Body RAW Application Json
{
	"username":"admin",
	"password":"123"
}

o/p
{
 "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTM0Njk1NTY1LCJleHAiOjE1MzU1NTk1NjV9.ZPLJ7YqK27JjGUbxQqDugZCOVEHuskDUkCPnWVcWF7HinnCTHuX0c2QBYFkXnG8vJnn7H24zyW12Kf_n5_vLUg",
    "tokenType": "Bearer"
}



1) Get all the checked in guest

http://localhost:8080/admin/guest/all
Header  Authorization = Bearer {Token}

o/p
[
    {
        "id": 1,
        "checkInTime": "19-08-2018 22:12:51",
        "firstName": "Prateek",
        "age": 24,
        "checkOutTime": null,
        "lastName": "M",
        "contact": "8736634414",
        "email": "rish93@msn.com"
    },
    {
        "id": 2,
        "checkInTime": "19-08-2018 22:13:35",
        "firstName": "Rishabh",
        "age": 24,
        "checkOutTime": null,
        "lastName": "M",
        "contact": "8756845677",
        "email": "rish93@msn.com"
    }
]
-------------------------------------------------------------------------------------------------

2) Checkout guest

PUT
http://localhost:8080/admin/guest/checkOut
Header  Authorization = Bearer {Token}

i/p
 { "firstName":"Rishabh", 
 "lastName": "M", 
 "age":"24", 
 "contact": "8756845677", 
 "email":"rish93@msn.com" 
 }


o/p
{
    "timestamp": 1534697123135,
    "message": "guest checked out",
    "details": "guest with name Rishabh checkedout"
}

---> verify admin service are authenticated

i/p
http://localhost:8080/admin/guest/all

o/p
{
    "timestamp": 1534695619195,
    "status": 401,
    "error": "Unauthorized",
    "message": "You're not authorized to access this resource.",
    "path": "/admin/guest/all"
}

i/p
http://localhost:8080/admin/guest/all
header  Authorization Bearer {token}

o/p
[
    {
        "id": 1,
        "checkInTime": "19-08-2018 21:51:54",
        "firstName": "PP",
        "age": 24,
        "checkOutTime": null,
        "lastName": "M",
        "contact": "8736634414",
        "email": "rish93@msn.com"
    }
]


#Filter guest

GET
http://localhost:8080/admin/guest/byAge?age=24
Header Authorization = Bearer {token}

GET
http://localhost:8080/admin/guest/bycheckInTime
Header Authorization = Bearer {token}

------------------------------------------------------------------------------------------------------
Actuator Info is permitted to all
GET
http://localhost:8080/actuator/info

Actuator Authorized

GET
http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/loggers
http://localhost:8080/actuator/health\
Header  Authozation  {token}


