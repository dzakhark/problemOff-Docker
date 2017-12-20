#!/bin/bash

sleep 40

register=$(curl -s -o /dev/null -w "%{http_code}" --request POST \
  --url http://localhost:8080/api/register \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --header 'Postman-Token: 9928adc8-e734-0c52-da32-93ada2b626a2' \
  --data '{"firstName": "Test", "lastName": "Test", "email": "test@gmail.com", "password": "testtest", "phoneNumber": "(982) 804-8938"}')

if [ "$register" != "200" ]; then
  echo "Test failed"
  exit 1
fi

login=$(curl -s -o /dev/null -w "%{http_code}" --request POST \
  --url http://localhost:8080/api/login \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --header 'Postman-Token: b9f9cab2-911b-b85e-b169-7dc1e75cb81a' \
  --data 'email=test%40gmail.com&password=testtest')

if [ "$login" != "200" ]; then
  echo "Test failed"
  exit 1
else
  echo "Test passed"
  exit 0
fi
