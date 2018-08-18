# HotelApi

Services For Guest CheckIn

GET  http://localhost:8080/admin/guest/all
Header -> Authorization =Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF
                                0IjoxNTM0MjQyMzA4LCJleHAiOjE1MzUxMDYzMDh9.mO4ba8G_jKZj19zkc9mAh7zwtX_
                                -uIHpWSY63YwHCuPtMjeii-Ks-amn_SU33k0l75ICJKskmsX_TMI2MstfRg
POST  http://localhost:8080/guest/checkIn
Header = Content-Type =application/json
Header Content/type /Application json
Body RAW Application Json
{
  
   "firstName":"Rishabh",
   "lastName": "M",
   "contact": "8756846722",
   "email":"rish93@msn.com"
}


POST localhost:8080/login/signup
Header Content/type /Application json
Body RAW Application Json
{
	"username":"admin",
	"password":"123"
}


POST localhost:8080/login/signin
Header Content/type /Application json
Body RAW Application Json
{
	"username":"admin",
	"password":"123"
}

GET  http://localhost:8080/admin/guest?firstName=Rishabh&contact=8756846722
Header -> Authorixzation =Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTM0MjQ
                                  zNTMwLCJleHAiOjE1MzUxMDc1MzB9.LWnuMGVd3WvuMnmIeDMVoX4jG0QQRqRNj6hD_o90C7S5A5  QkExvbpG64MZ-9DsK_VWfFVLeBm1yJnR8AndF7xw


Actuator Info is permitted to all
http://localhost:8080/actuator/info

Actuator Authorized

http://localhost:8080/actuator/metrics
http://localhost:8080/actuator/loggers
http://localhost:8080/actuator/health

Header  Authozation  {token}
