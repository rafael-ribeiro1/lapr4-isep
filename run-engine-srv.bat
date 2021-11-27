REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET BASE_CP=base.daemon.motorfluxo\target\base.daemon.motorfluxo-4.0.0.jar;base.daemon.motorfluxo\target\dependency\*;

REM call the java VM, e.g, 
java -cp %BASE_CP% daemon.motorfluxo.MotorFluxoDaemon
