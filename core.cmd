@echo off
java -jar mybatis-generator-0.0.1-SNAPSHOT.jar --driver=org.postgresql.Driver --url=jdbc:postgresql://localhost:5432/testdb --username=postgres --password=postgres --author="Li Xiaobing" ^
  --projectName=core --baseDirPath=E:\SelfWorkSpace\IDEASpace\mybatis-generator\target\tt --targetTable=%1