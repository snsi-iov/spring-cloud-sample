## need mvn
## need docker

#
# 請確認
# 1. maven artifactId = project folder name
# 2. maven build.finalName = maven artifactId
# 3. 要加入的jar檔產生於 target/${jarName}

# default IMAGE_NAME = project folder name
# change this to modify the image name
IMAGE_NAME=configserver

# default JAR_NAME = project folder name
# for adding target/${jarName}.jar into image
JAR_NAME=$(notdir $(shell pwd)).jar




build:
	mvn clean install -P production
	

# default build tag = latest
# set 'buildTag' example: 'make buildTag=0.123 buildImg'
buildImg:
ifdef buildTag
	docker build --build-arg JAR_NAME=${JAR_NAME} -t ${IMAGE_NAME}:latest -t ${IMAGE_NAME}:${buildTag} .
else
	docker build --build-arg JAR_NAME=${JAR_NAME} -t ${IMAGE_NAME}:latest  .
endif


