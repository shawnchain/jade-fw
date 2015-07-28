# Run the sample application #


## 1. Maven user ##
  * Make sure you have connected to the internet
  * Run the sample:
> c:\sample-app>mvn clean jetty:run-war

  * Regenerate the eclipse project file
> c:\sample-app>mvn eclipse:clean eclipse:eclipse

  * Regenerate with source attached
> c:\sample-app>mvn eclipse:clean eclipse:eclipse -DdownloadSources=true

## 2. Eclipse user ##
  * Checkout ths source code from http://jade-fw.googlecode.com/svn/trunk/
  * Download the packaged war file from http://jade-fw.googlecode.com/files/discuss-app-0.1-SNAPSHOT.war and extract it to a folder. You need the jar files under the WEB-INF/lib dir
  * Import the project into your workspace.
  * Configure the libraries, also remember to add servlet23.jar to project's lib setting, sample app is not including this jar file.
  * If you want to run the JUnit test cases, please run the sample-app/db/start\_db\_discuss.cmd file to startup the hsqldb instance first.

## 3. Tomcat user ##
  * Download the packaged war file from http://jade-fw.googlecode.com/files/discuss-app-0.1-SNAPSHOT.war
  * Copy to webapps folder
  * Startup tomcat
  * Open Browswer and type http://localhost:8080/discuss/

That's it. If you still have problems, please post them to the discuss forum http://groups.google.com/group/jade-fw

Enjoy!

Shawn.
