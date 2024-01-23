FROM mysql:8.3.0
ENV MYSQL_DATABASE users
ENV MYSQL_ROOT_PASSWORD root
COPY ddl/users_users.sql /docker-entrypoint-initdb.d/
