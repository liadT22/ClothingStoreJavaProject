# ClothingStoreJavaProject

run:
//Compilation
mvn clean compile

//run server
mvn exec:java -Dexec.mainClass="server.Server"

//optional: run function to initiate files with some dummy data
mvn exec:java -Dexec.mainClass="org.example.Main"

//run client
mvn exec:java -Dexec.mainClass="Client.Client"
