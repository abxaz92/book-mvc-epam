  FROM chaava01/postgresql
  MAINTAINER david_chaava

  EXPOSE 5432
  RUN chown -R postgres:postgres /var/lib/postgresql/9.5/main/
  ADD  . /var/lib/postgresql/9.5/main/
  RUN locale-gen en_US.UTF-8
  ENV LANG='en_US.UTF-8' LANGUAGE='en_US:en' LC_ALL='en_US.UTF-8'
  CMD ["/bin/bash"]
