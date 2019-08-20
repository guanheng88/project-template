cd /d %~dp0
cd .. && java -jar -Xms512m -Xmx512m -XX:MetaspaceSize=256m -XX:MaxMetaspaceSize=512m ./apps/project-template-web.jar