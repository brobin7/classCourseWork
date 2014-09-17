This is a banking application being designed for CS 442 at UIC Spring 2014.

The application uses the Java based H2 database, maven, Eclipse and JUnit.

Download and install Eclipse on your system, and then install the maven integration (Note: Installation details may vary for maven depending on OS and version of Eclipse). Information about maven can be found here: http://eclipse.org/m2e/download/
In Eclipse, go to Help -> Install New Software, then type in the "Work with" field: http://download.eclipse.org/technology/m2e/releases 
Hit Enter and then select the latest m2e Maven Integration for Eclipse and follow the wizard to install it.

Download the H2 database latest stable release platform independent version here: http://www.h2database.com/html/download.html

To install, simply extract the zip file. This will extract an h2 directory. Navigate to the h2/bin directory and run the "h2-1.3.176.jar" file (Note: This is the latest version at the time of this writing). This should start up the H2 database and open a console in your browser. Keep this open, we will come back to it.

Download the project zip file and extract it to a directory in your home directory called "cs442project". Next, navigate to the cs442project/src/main/resources/META-INF/ directory and open the "persistence.xml" file in a text editor. The property named "hibernate.connection.url" needs to be edited to include the path to the database. In my case with the project located in my home directory, it looks like this:
value="jdbc:h2:tcp://localhost/~/Stephen/cs442project/cs442DB"

Save this file, and then go back to your browser with the H2 console. In the JDBC URL field, the path also needs to be present, which should look something like this: jdbc:h2:~/Stephen/cs442project/cs442DB

Click Connect and make sure you are able to login to the database.

Next we will import the project into Eclipse. In Eclipse, go to File -> Import Project, then select Maven -> Existing Maven Projects. In the Root Directory field, browse to your cs442project directory. Select the project from the list and click Finish.
