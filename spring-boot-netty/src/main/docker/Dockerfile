FROM 10.10.30.166/platform/java:v2
ADD spring-boot-netty-0.0.1-SNAPSHOT.jar app.jar
#ENTRYPOINT ["java","-jar","-Dserver","-Xms2048m","-Xmx2048m","/app.jar","server","8007"]
ENTRYPOINT ["java","-jar","-Dclient","-Xms2048m","-Xmx2048m","/app.jar","client","10.0.126.30:8008","1000","500"]
#ENTRYPOINT ["java","-jar","-Dclient","-Xms512m","-Xmx512m","/app.jar","client","netty-server:8007","1000","500"]