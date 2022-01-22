# About
A simple app that allows you to perform basic CRUD operations on clients, stored in your DB
# Technologies used
- Java 8
- Java EE
- JAX-RS
- CDI(in progress)
- PostgreSQL
- JDBC
- Apache TomEE
# How to run the app
1. Clone the project to your IDE
2. Initialize database using src/main/resources/init_db.sql
3. Change config properties to the ones that fit your DB in src/main/java/application/util/ConnectionUtil.java file
4. Add your favorite web server to run/debug configuration
5. Run the app. To manage DB you can use endpoints listed below
# Available endpoints
### (assuming you're running the project on your local web server)
- localhost:8080/client-service-1.0