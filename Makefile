curl http://localhost:8081/api/v1/public

curl -u admin:123456 http://localhost:8081/api/v1/admins/profiles

curl -u wronguser:wrongpass http://localhost:8081/api/v1/users/profiles

curl -u user:123456 http://localhost:8081/api/v1/admins/profiles