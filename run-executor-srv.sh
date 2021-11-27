#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
# shellcheck disable=SC2125
export BASE_CP=base.daemon.executoratividade/target/base.daemon.executoratividade-4.0.0.jar:base.daemon.executoratividade/target/dependency/*;

#REM call the java VM, e.g,
java -cp $BASE_CP daemon.executoratividade.ExecutorAtividadeDaemon