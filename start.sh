#service generator
cd generator
./gradlew build

cd ./../validator
./gradlew build

cd ./../frontend
npm install && npm run build && npm run start
