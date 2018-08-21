Echo Building project

Gradle build

cd build/libs

rm -rf
Echo running hotelbackoffice jars

nohup java -jar hotelbackoffice-0.0.1-SNAPSHOT.jar &> output.log &

tail -f output.log
# ps -ef | grep java
# kill -9 {port nohup}

#Service for guest

curl -i
-X POST
-H "Content-type:application/json"
 http://localhost:8080/guest/checkIn/
-d "{"firstName":"Rishabh",
 "lastName": "Mankatala", 
 "age":"24", "contact": "8736634414", 
 "email":"rish93@msn.com"}"


#Service for admin

#Register admin
curl -i
-X POST
-H "Content-type:application/json"
 localhost:8080/login/signup
-d "{ "username":"admin", "password":"123" }"
 
 #Login admin
 curl -i
-X POST
-H "Content-type:application/json"
 localhost:8080/login/signin
-d "{ "username":"admin", "password":"123" }"
 
#Get Guest (Admin)
curl -i
-X GET
-H "Authorization:Bearer {token}"
 http://localhost:8080/admin/guest/all 
 
 
#CheckOutGuest (Admin)

curl -i
-X PUT
-H "Authorization = Bearer {Token}"
 http://localhost:8080/admin/guest/checkOut
-d "{ "firstName":"Rishabh", "lastName": "M",
 "age":"24", 
 "contact": "8756845677",
 "email":"rish93@msn.com" }"

 #Actuator Info (Unauthenticated)
 
 curl -i
-X GET
 http://localhost:8080/actuator/info 


#Actuator Health (Authenticated)
 curl -i
-X GET
-H "Authorization = Bearer {Token}"
http://localhost:8080/actuator/health