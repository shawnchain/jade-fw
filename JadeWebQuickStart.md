# Quick Start - Create and run JADE-Web Apps #

## Install and configure Maven ##
  * Get Maven2 from http://maven.apache.org/
  * Extract the zip file to some folder such as c:\maven-2.0.4
  * Add c:\maven-2.0.4\bin into your PATH env
  * Check whether your JAVA\_HOME is ready
  * Check whether maven is installed ok
```
Microsoft Windows XP [Version 5.1.2600]
(C) Copyright 1985-2001 Microsoft Corp.

C:\>mvn -v
Maven version: 2.0.4

C:\>
```
  * Make sure that you have connected to the internet, maven will download some dependencies automatically while building

## Create a ready-to-run web app ##
  * Use the following maven command to create a empty
```
C:\>mvn archetype:create -DarchetypeGroupId=com.nonsoft.maven2.archetypes -DarchetypeArtifactId=jade-quickstart-web -DarchetypeVersion=1.0 -DgroupId=sample -DartifactId=sample-app -DremoteRepositories=http://maven.nonsoft.com/releases/

```

## Run & Browse ##
  * Change into the created directory and use maven jetty plugin to run it
```
C:\>cd sample-app

C:\sample-app>mvn jetty:run
```
  * Open IE and type in http://localhost:8080/ when build complete

## Notes ##
  * '''If you're first time running maven, please be patient while maven downloading dependencies from the poor network.'''
  * You can also create a war and deploy it under any Servlet containers with the following commands
```
C:\sample-app>mvn war:war
C:\sample-app>start target
```

Have Fun!