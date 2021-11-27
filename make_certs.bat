del "*.jks"
del "*.pem"
REM Dashboard cert
(echo dashboard&&echo DEI&&echo ISEP&&echo PORTO&&echo PORTO&&echo PT&&echo yes) | keytool -genkey -v -alias dashboard -keyalg RSA -keysize 2048 -validity 365 -keystore dashboard.jks -storepass forgotten
keytool -exportcert -alias dashboard -keystore dashboard.jks -storepass forgotten -rfc -file dashboard.pem
REM Motor de fluxo certs
(echo client_mf&&echo DEI&&echo ISEP&&echo PORTO&&echo PORTO&&echo PT&&echo yes) | keytool -genkey -v -alias client_mf -keyalg RSA -keysize 2048 -validity 365 -keystore client_mf.jks -storepass forgotten
keytool -exportcert -alias client_mf -keystore client_mf.jks -storepass forgotten -rfc -file client_mf.pem
(echo server_engine&&echo DEI&&echo ISEP&&echo PORTO&&echo PORTO&&echo PT&&echo yes) | keytool -genkey -v -alias server_engine -keyalg RSA -keysize 2048 -validity 365 -keystore server_engine.jks -storepass forgotten
keytool -exportcert -alias server_engine -keystore server_engine.jks -storepass forgotten -rfc -file server_engine.pem
echo yes|keytool -import -alias client_mf -keystore server_engine.jks -file client_mf.pem -storepass forgotten
echo yes|keytool -import -alias server_engine -keystore client_mf.jks -file server_engine.pem -storepass forgotten
echo yes|keytool -import -alias dashboard -keystore server_engine.jks -file dashboard.pem -storepass forgotten
echo yes|keytool -import -alias server_engine -keystore dashboard.jks -file server_engine.pem -storepass forgotten
REM Executor certs
(echo server_exec&&echo DEI&&echo ISEP&&echo PORTO&&echo PORTO&&echo PT&&echo yes) | keytool -genkey -v -alias server_exec -keyalg RSA -keysize 2048 -validity 365 -keystore server_exec.jks -storepass forgotten
keytool -exportcert -alias server_exec -keystore server_exec.jks -storepass forgotten -rfc -file server_exec.pem
echo yes|keytool -import -alias server_engine -keystore server_exec.jks -file server_engine.pem -storepass forgotten
echo yes|keytool -import -alias server_exec -keystore server_engine.jks -file server_exec.pem -storepass forgotten
