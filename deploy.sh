cd ../chat-bot-master-ui/ &&
npm install &&
rm -rf dist &&
ng build --prod &&
rm -rf ../chat-bot-master/src/main/resources/static &&
cp -r dist ../chat-bot-master/src/main/resources/static &&
cd ../chat-bot-master &&
./mvnw -f ../lexical-identifier/pom.xml clean install -Dmaven.test.skip=true &&
./mvnw -f ../natural-language-resolver/pom.xml clean install -Dmaven.test.skip=true &&
./mvnw clean package -Dmaven.test.skip=true &&
scp target/chat-bot-master.jar chatbot@192.168.1.71:~