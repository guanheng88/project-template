cd /d %~dp0
java -jar -Xms512m -Xmx512m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m ./project-template-web.jar