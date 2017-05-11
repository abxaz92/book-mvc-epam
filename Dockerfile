  FROM ubuntu:14.04
  MAINTAINER javacodegeeks

  RUN apt-get update && apt-get install -y python-software-properties software-properties-common
  RUN add-apt-repository ppa:webupd8team/java

  RUN echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections

  RUN apt-get update && apt-get install -y oracle-java8-installer maven
  EXPOSE 8080
  ADD . /usr/local/helloworld
  #RUN cd /usr/local/helloworld && mvn clean package
  RUN locale-gen en_US.UTF-8
  ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8'
  CMD ["java", "-jar", "/usr/local/helloworld/target/mvc.jar"]
