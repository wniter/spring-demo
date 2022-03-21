#基础镜像
FROM openjdk:8-jdk-alpine

# 添加一个存储空间
VOLUME /tmp
# 作者
#MAINTAINER eangulee <eangulee@gmail.com>

# 暴露8080端口
EXPOSE 8080

# 添加变量，如果使用dockerfile-maven-plugin，则会自动替换这里的变量内容
ARG JAR_FILE=target/springboot-docker-0.0.1-SNAPSHOT.jar

# 往容器中添加jar包
ADD ${JAR_FILE} app.jar

# 启动镜像自动运行程序
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom","-jar","/app.jar"]