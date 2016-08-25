
build:
	mvn clean install -P production
	
buildImgs:
	cd configServer ; make buildImg
	cd eureka ; make buildImg
	cd gateway ; make buildImg
	cd demo-microservice ; make buildImg
	