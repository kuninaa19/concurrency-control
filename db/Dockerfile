FROM arm64v8/mysql:8.0.33

ENV TZ Asia/Seoul

ENV MYSQL_DATABASE event_db
ENV MYSQL_ROOT_PASSWORD testsql1@
ENV MYSQL_ROOT_HOST '%'
ENV MYSQL_USER admin
ENV MYSQL_PASSWORD adminsql1@
ENV MYSQL_HOST '%'

EXPOSE 3306
COPY db.sql /docker-entrypoint-initdb.d/