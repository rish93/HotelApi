#start script by-->  sh script.sh
#replace auth token by login response token

Echo Building project

Gradle build

cd build/libs

rm output.log
Echo running hotelbackoffice jars

nohup java -jar hotelbackoffice-0.0.1-SNAPSHOT.jar &> output.log &

#tail -f output.log
# ps -ef | grep java
# kill -9 {port nohup}

#Service for admin Signup and Login

Echo Signing up Admin
curl -X POST "http://localhost:8080/login/signup" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"123\", \"username\": \"admin\"}"

Echo Login Admin to get Auth token
curl -X POST "http://localhost:8080/login/signin" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"123\", \"username\": \"admin\"}"


#Service for guest

Echo Checkin Guest
curl -X POST "http://localhost:8080/guest/checkIn" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"firstName\": \"Walter\", \"age\": 24, \"lastName\": \"White\", \"contact\": 8756846722, \"email\": \"rish93@gmail.com\"}"

curl -X POST "http://localhost:8080/guest/checkIn" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"firstName\": \"Rishabh\", \"age\": 24, \"lastName\": \"Mankatala\", \"contact\": 8867620765, \"email\": \"rish93@gmail.com\"}"

Echo verifying checkin with same name and contact, before admin does explicit checkOut
curl -X POST "http://localhost:8080/guest/checkIn" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"firstName\": \"Walter\", \"age\": 24, \"lastName\": \"White\", \"contact\": 8756846722, \"email\": \"rish93@gmail.com\"}"

Echo verifying checkin with Different name and  same contact, before admin does explicit checkOut
curl -X POST "http://localhost:8080/guest/checkIn" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"firstName\": \"Skyler\", \"age\": 24, \"lastName\": \"White\", \"contact\": 8756846722, \"email\": \"rish93@gmail.com\"}"



#Service for admin

Echo verifying duplicate admin 
curl -X POST "http://localhost:8080/login/signup" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"password\": \"123\", \"username\": \"admin\"}"
 
 

 
#CheckOutGuest (Admin)

curl -X PUT "http://localhost:8080/admin/guest/checkOut" -H "accept: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTM0NTM5MjQ1LCJleHAiOjE1MzU0MDMyNDV9.yk-k7drDi9ibQ6VLEkGqdVkMJxDj8DeWp60kRvBEU2kLELSVWwe0gsT8HNFZTWryx2Buc4_H1loCid06tckHIA" -H "Content-Type: application/json" -d "{ \"firstName\": \"Walter\", \"age\": 24, \"lastName\": \"White\", \"contact\": 8756846722, \"email\": \"rish93@gmail.com\"}"


#Guest by Last Name
curl -X GET "http://localhost:8080/admin/guest/byLastName?lastName=White" -H "accept: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTM0NTM5MjQ1LCJleHAiOjE1MzU0MDMyNDV9.yk-k7drDi9ibQ6VLEkGqdVkMJxDj8DeWp60kRvBEU2kLELSVWwe0gsT8HNFZTWryx2Buc4_H1loCid06tckHIA"

#Guest by first name
curl -X GET "http://localhost:8080/admin/guest/byFirstName?firstName=Walter" -H "accept: application/json" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTM0NTM5MjQ1LCJleHAiOjE1MzU0MDMyNDV9.yk-k7drDi9ibQ6VLEkGqdVkMJxDj8DeWp60kRvBEU2kLELSVWwe0gsT8HNFZTWryx2Buc4_H1loCid06tckHIA"


#Actuator Info (Unauthenticated)
curl -X GET "http://localhost:8080/actuator/info" 


#Actuator Health (Authenticated)
curl -X GET "http://localhost:8080/actuator/health" -H "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJhZG1pbiIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTM0NTM5MjQ1LCJleHAiOjE1MzU0MDMyNDV9.yk-k7drDi9ibQ6VLEkGqdVkMJxDj8DeWp60kRvBEU2kLELSVWwe0gsT8HNFZTWryx2Buc4_H1loCid06tckHIA"

