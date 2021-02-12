**Requirements**
1. Java version > 1.8
2. Gradle > 5.6.4
3. Docker 
4. Postman

**Steps for running the project**

1.  Enter cmd inside root directory `-$./gradlew clean build`.
2. Goto /build/libs and run `-$ java -jar backend-0.0.1-SNAPSHOT.jar`
3. Mysql used by docker. Docker command > docker-compose up -- build
4. We have swagger for API documentation. Here are the link > http://localhost:8089/swagger-ui.html
     
**Operations**

To Check Rest Api End, Use Postmen Client and hit the end point.

 1. To Create Shop
 
   just hit  =>POST http://localhost:8089/v1/api/shop 
   sample Request Body :
   
   `{
      "address": {
        "city": "xyz",
        "latitude": 26.9252337,
        "longitude": 75.8010876,
        "state": "xyz"
      },
      "category": "GENERALSTORE",
      "name": "test shop",
      "ownerName": "test"
    }`
   
   sample Response json:
   
      `{
           "id": 1,
           "shopName": "test shop",
           "ownerName": "test",
           "category": "GENERALSTORE",
           "address": {
               "id": 4,
               "city": "xyz",
               "state": "xyz",
               "latitude": 12.9252337,
               "longitude": 55.8010876,
               "dateUpdated": "2021-02-11T13:11:03.182+00:00",
               "dateCreated": "2021-02-11T13:11:03.182+00:00"
           },
           "dateUpdated": "2021-02-11T13:11:03.182+00:00",
           "dateCreated": "2021-02-11T13:11:03.182+00:00"
       }
     `
     
 2. To Get All Shop
     
       just hit  =>POST http://localhost:8089/v1/api/shop/search?pageNumber=0&pageSize=10
       
       sample Request Body :
              
              `{
                 "latitude": 0,
                 "longitude": 0,
                 "name": ""
               }`
       
       sample Response json:
       
          `{
               "content": [
                   {
                       "id": 1,
                       "shopName": "test shop",
                       "ownerName": "test",
                       "category": "GENERALSTORE",
                       "address": {
                           "id": 1,
                           "city": "xyz",
                           "state": "xyz",
                           "latitude": 12.9252337,
                           "longitude": 55.8010876,
                           "dateUpdated": "2021-02-11T13:11:03.000+00:00",
                           "dateCreated": "2021-02-11T13:11:03.000+00:00"
                       },
                       "dateUpdated": "2021-02-11T13:11:03.000+00:00",
                       "dateCreated": "2021-02-11T13:11:03.000+00:00"
                   }
               ],
               "pageable": {
                   "sort": {
                       "sorted": true,
                       "unsorted": false,
                       "empty": false
                   },
                   "pageNumber": 0,
                   "pageSize": 10,
                   "offset": 0,
                   "unpaged": false,
                   "paged": true
               },
               "totalElements": 1,
               "totalPages": 1,
               "last": true,
               "first": true,
               "sort": {
                   "sorted": true,
                   "unsorted": false,
                   "empty": false
               },
               "numberOfElements": 1,
               "size": 10,
               "number": 0,
               "empty": false
           }` 