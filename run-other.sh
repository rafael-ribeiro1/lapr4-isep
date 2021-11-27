#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export BASE_CP=base.app.other.console/target/base.app.other.console-4.0.0.jar:base.app.other.console/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP eapli.base.app.other.console.OtherApp
