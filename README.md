yaml configuration

spring:
  application:
    name: payfirstMerchant

  datasource:
    url: jdbc:postgresql://localhost:5432/payfirst_db
    username: postgres
    password: root
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect

