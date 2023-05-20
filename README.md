## Deploy Instructions
# Start Tomcat
Go to 

C:\sys\apache-tomcat-10.1.7\bin

setclasspath.bat
rem Make sure prerequisite environment variables are set
SET JAVA_HOME=C:/sys/Java/jdk-20

# DB Connection in Tomcat
 c:\sys\apache-tomcat-10.1.7\conf\context.xml

#contex.xml content.
<Context>
...
 <Resource auth="Container"
 driverClassName="org.mariadb.jdbc.Driver"
 maxActive="100"  maxIdle="30" maxWait="10000"
 name="jdbc/student" type="javax.sql.DataSource"
 url="jdbc:mariadb://localhost:3306/student"
 username="root"  password="MariaDB@123" />

...
</Conext>

# Link to this JNDI in web.xml

#WAR
Web Application Archive

**Generate WAR :** 
mvn clean package

Copy the **student-mgmt-crud.war** from target folder to 
c:\sys\apache-tomcat-10.1.7\webapps

The war will explode and deploy

Context of the application is the same as the war name.

**Context** : student-mgmt-crud

# Testing
**List Users**: 
http://localhost:8080/student-mgmt-crud/list

