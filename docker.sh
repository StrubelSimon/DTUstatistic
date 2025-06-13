cd api && mvn clean package -DskipTests;
cp target/DTUstatistic.jar ../docker/api/DTUstatistic.jar
cd ../docker/api
docker build --no-cache -t dtu-statistic-api:latest .
docker image save dtu-statistic-api -o ../dtu-statistic-api.img
