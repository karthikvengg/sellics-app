# sellics-app | Amazon search keyword scoring
 
**Prerequisites**
* Java 11 (Since Java 11's new HTTP client is used to call Amazon REST API)

---
**Tools and frameworks used**
* Gradle, SpringBoot, Java 11 HTTP Client, IntelliJ

---
**Assumptions:**
* For any search input, Amazon will only return up to 10 keywords, that have an exact prefix-match with the input.
* Any keyword with a relevant search-volume can be returned by the API
* Whenever the API is called, it operates in 2 following steps:
* Seek: Get all known keywords that match the prefix and create a Candidate-Set
* Sort/Return: Sort the Candidate-Set by search-volume and return the top 10 results.
  
---
**Usage**

**Start Application**

**Gradle**
* Run command "gradlew.bat bootRun" if Windows OS
* Run command "./gradlew bootRun" if Linux or Mac OS

**IDE**
1. Open as Gradle Java project in IntelliJ IDE.
2. Create Run configuration for a Java Application.
3. Use com.selics.SelicsAppMain as the Main Class
4. Select JRE 11
5. Run the created Run configuration.
6. SpringBoot REST application will be started in an embedded tomcat server with port 8080
---
* The default port number is port:**8080**
---
**REST Endpoints**

* GET request to http://localhost:8080/estimate with a query parameter ?keyword=value

* Response JSON with 200 status code. 
* Example: {"keyword":"iphone charger","score":100}
---
**Tools**
* Use a REST Client like Postman
* Use Java 11's new HTTP Client
* Use any thirdparty HTTP Client
---
**Stop Application**
* Stop tomcat service to stop the application
---
