cd ~/strax/google-gcm
mvn deploy:deploy-file -Durl=file://$WORKSPACE/strax/strax/repo -Dfile=target/rest-client-1.0-SNAPSHOT.jar -DgroupId=com.google.gcm -DartifactId=rest-client -Dpackaging=jar -Dversion=1.0-SNAPSHOT
