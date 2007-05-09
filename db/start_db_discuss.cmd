@REM $Id: start_mycrm_db.cmd,v 1.3 2005/05/29 13:55:48 shawn Exp $
@REM hsqldb start script

@REM @start java -cp mycrm-startup-1.0.jar;hsqldb-1.7.3.3.jar -Ddata=data Main
@start java -cp hsqldb-startup-0.1-SNAPSHOT.jar;hsqldb-1.8.0.7.jar -Ddata=data -Ddb=discuss Main
