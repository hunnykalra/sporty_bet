# Sport Bet Application

## Configuration Properties

Add the following to your `src/main/resources/application.properties`:

### Kafka
```
spring.kafka.bootstrap-servers=localhost:9092
```

### H2 Database
```
spring.datasource.url=jdbc:h2:mem:sportbetdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.hibernate.ddl-auto=update
```

### RocketMQ
```
rocketmq.name-server=127.0.0.1:9876
rocketmq.producer.group=sport-bet-producer-group
```

---

## Build Steps

1. **Install dependencies and build the project:**
   ```sh
   ./mvnw clean install
   ```
   or (if you have Maven installed globally):
   ```sh
   mvn clean install
   ```

---

## Start Dependencies (Kafka & RocketMQ)

1. **Start Kafka and RocketMQ using Docker Compose:**
   - Make sure you have a `docker-compose.yml` file in your project root that defines services for Kafka, RocketMQ NameServer, and RocketMQ Broker.
   - Start the services:
     ```sh
     docker-compose up -d
     ```

2. **(Optional) Custom RocketMQ Broker Configuration:**
   - If you have a custom `broker.conf` for RocketMQ, ensure it is referenced in your `docker-compose.yml` for the broker service:
     ```yaml
     volumes:
       - ./broker.conf:/etc/rocketmq/broker.conf
     command: sh mqbroker -c /etc/rocketmq/broker.conf
     ```
   - Adjust the path as needed for your setup.

---

## Run Steps

1. **Run the application:**
   ```sh
   ./mvnw spring-boot:run
   ```
   or
   ```sh
   mvn spring-boot:run
   ```

2. **Access H2 Console:**
   - Visit [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - JDBC URL: `jdbc:h2:mem:sportbetdb`

---

## Notes
- Make sure Kafka and RocketMQ are running (via Docker Compose or manually) before starting the application.
- Adjust configuration properties as needed for your environment.
- For testing, you may want to mock external dependencies like RocketMQ. 