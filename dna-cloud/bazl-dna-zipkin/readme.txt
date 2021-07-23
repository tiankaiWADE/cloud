run
java -jar bazl-dna-zipkin-1.0.0-SNAPSHOT.jar --server.port=9411

#持久化
java -jar bazl-dna-zipkin-1.0.0-SNAPSHOT.jar --server.port=9411 --STORAGE_TYPE=mysql --MYSQL_HOST=localhost --MYSQL_TCP_PORT=3306 --MYSQL_USER=root --MYSQL_PASS=1234 --MYSQL_DB=zipkin

#elasticsearch
java -jar bazl-dna-zipkin-1.0.0-SNAPSHOT.jar --server.port=9411 --STORAGE_TYPE=elasticsearch --ES_HOSTS=localhost:9200

#参考
https://zipkin.io/zipkin-api

获取接口
http://localhost:9411/api/v2/services
http://localhost:9411/api/v2/spans?serviceName=bazl-dna-system