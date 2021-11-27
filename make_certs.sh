#!/bin/bash
STOREPASS="forgotten"
for ENT in server_engine client_mf dashboard server_exec ; do
 rm -f ${ENT}.jks ${ENT}.pem
 echo -e "${ENT}\nDEI\nISEP\nPORTO\nPORTO\nPT\nyes" | keytool -genkey -v -alias ${ENT} -keyalg RSA -keysize 2048 \
	-validity 365 -keystore ${ENT}.jks -storepass ${STOREPASS}
 keytool -exportcert -alias ${ENT} -keystore ${ENT}.jks -storepass ${STOREPASS} -rfc -file ${ENT}.pem
done
####
echo "Creating trust relations"
### IMPORTING TRUSTED CERTIFICATES
### (The server trusts all clients except for client4_J)
### (Every client trusts server_engine)
for ENT in client_mf dashboard; do
 echo "yes"|keytool -import -alias ${ENT} -keystore server_engine.jks -file ${ENT}.pem -storepass ${STOREPASS}
 echo "yes"|keytool -import -alias server_engine -keystore ${ENT}.jks -file server_engine.pem -storepass ${STOREPASS}
done
for ENT in server_engine; do
 echo "yes"|keytool -import -alias ${ENT} -keystore server_exec.jks -file ${ENT}.pem -storepass ${STOREPASS}
 echo "yes"|keytool -import -alias server_exec -keystore ${ENT}.jks -file server_exec.pem -storepass ${STOREPASS}
done
echo "############################################################################"
keytool -list -keystore server_engine.jks -storepass ${STOREPASS}
echo "############################################################################"
echo "WARNING: For testing, client4_J is not added to the list of authorized clients"
echo "############################################################################"
#######