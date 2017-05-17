ActiveMQ start
docker run --name='activemq' -it --rm -p 8161:8161 -p 61616:61616 -p 61613:61613 -e  'ACTIVEMQ_MIN_MEMORY=512' -e 'ACTIVEMQ_MAX_MEMORY=2048' -P webcenter/activemq:latest