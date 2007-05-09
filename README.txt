1. Maven user
  * Run the sample:
  c:\sample-app>mvn jetty:run

  * Regenerate the eclipse project file with source
  c:\sample-app>mvn eclipse:clean eclipse:eclipse

 * Regenerate with source attached
c:\sample-app>mvn eclipse:clean eclipse:eclipse -DdownloadSources=true


2. Eclipse user
  * Import the project into your workspace.
  * Add servlet23.jar to project's lib setting, sample app is not including this jar file.
  * To run the test case, please run the sample-app/db/start_db_discuss.cmd file first to launch the hsqldb instance.


3. Tomcat user
  * Copy the sample-app/src/main/webapp to $TOMCAT_HOME/webapps and start the tomcat server.
    #cp src/main/webapp $TOMCAT_HOME/webapps/discuss -r
    #$TOMCAT_HOME/bin/startup.sh
  * Open Browswer and type http://localhost:8080/discuss/

That's it. If you still have problems, please post them to the discuss forum http://groups.google.com/group/jade-fw

Enjoy!

Shawn.