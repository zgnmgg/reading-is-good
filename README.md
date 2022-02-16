# Getir Reading Is Good Service

Service description: The main purpose of this service is purchase book for customers.

## Requirements

For building and running the application you need:

- [JDK 11]
- [Maven]
- Environment Variables =>
  -  SECRET_KEY (Random string generate for authentication)
  -  URL (Postman URL)
  -  token (Postman Bearer Token)

## Build
```
mvn clean 
```
### Unit Tests
```
mvn test
```

## Package

```
mvn install
```

## How to run after packaged

```
java -jar target/getir-1.0.0-SNAPSHOT.jar -p 8095
```

## Check status

```
curl -H "Content-Type: application/json" -X GET http://localhost:8085/status
```

## Docker Build

```
docker build . -t getir-case-study
```

## Postman Collection

Check Api Documentation

Getir (Workspace)

## !! Quality Gate !!

Min-Limit **%50**

Checkout sonar.internal for projects
### Service Info

## Müşteri İşlemleri [/customers]

### Bir Müşteri Üret [POST]

+ email (required, string) - Müşteri maili
+ firstName (required, string) - Müşteri adı
+ lastName (required, string) - Müşteri soyadı
+ address (required, string) - Müşteri adresi
+ phone (required, string) - Müşteri telefonu

+ Request (application/json;charset=utf-8)

  + Headers
    Authorization: Bearer token
  + Body

        {
          "email": "demo@gmail.com",
          "firstName": "Demo",
          "lastName": "Demo1",
          "address": "Demo adres",
          "phone": "1242140"
        }

+ Response 200 (application/json;charset=utf-8)

  + Body

          {
              "id": 1,
              "email": "demo@gmail.com",
              "firstName": "Demo",
              "lastName": "Demo1",
              "phone": "1242140",
              "address": "Demo adres"
          }

### Müşteriye ait siparişleri görüntüle [GET] [orders/{customerId}]
#### URL Param

+ Request (application/json;charset=utf-8)

  + Headers ->
    Authorization: Bearer token

+ Response 200 (application/json;charset=utf-8)

  + Body

        [
            {
                "id": 18,
                "customerId": 1,
                "status": "CANCELLED",
                "orderDetails": [],
                "amount": 55
            },
            {
                "id": 17,
                "customerId": 1,
                "status": "CANCELLED",
                "orderDetails": [],
                "amount": 55
            },
        ]


## Ürün İşlemleri [/product]
### Ürün ekleme [POST]
#### Bir ürün üret.

+ name (required, string) - Ürün adı
+ author (required, string) - Yazar adı
+ publishYear (required, string) - Çıkış tarihi
+ price (required, long) - Fiyatı
+ stock (required, long) - Stok değeri

+ Request (application/json;charset=utf-8)

  + Headers ->
    Authorization: Bearer token
  + Body ->

        {
          "name": "Kaşağı",
          "author": "Ömer Seyfettin",
          "publishYear": "1950",
          "price": 12,
          "stock": 25
        }

+ Response 200 (application/json;charset=utf-8)

  + Body ->

        {
          "id": 1,
          "name": "Kaşağı",
          "author": "Ömer Seyfettin",
          "publishYear": "1950",
          "price": 12,
          "stock": 25
        }


### Ürün stok güncelleme [PATCH] [/{productId}]
#### Ürün stoklarına güncelleme yapar.

+ Request (application/json;charset=utf-8)

  + Headers ->
    Authorization: Bearer token
  + Parameters ->
    productId: Ürün ID
  + Body -> { "stock": 25 }

+ Response 200 (application/json;charset=utf-8) 
+ Body ->

        {
          "id": 1,
          "name": "Kaşağı",
          "author": "Ömer Seyfettin",
          "publishYear": "1950",
          "price": 12,
          "stock": 55
        }


## Sipariş İşlemleri [/order]
### Sipariş üret [POST] 
#### Müşteriye ait sipariş tanımlar. [POST]

+ customerId (required, long) - Müşteri id
+ orderAmount (required, long) - Tutar
+ productOrderDetails (required, List) - Ürünler

+ Request (application/json;charset=utf-8)

  + Headers ->
    Authorization: Bearer token
  + Body ->

        {
          "customerId": 1,
          "orderAmount": 59,
          "productOrderDetails": []
        }

+ Response 200 (application/json;charset=utf-8)
  + Body ->

        {
          "customerId": 1,
          "date": "2022-02-16T11:51:11.156+00:00",
          "amount": 59,
          "status": "PREPARING",
          "orderDetails": []
        }

## Sipraiş getir [/{orderId}]
### Yapılan siparişleri listeler [GET]

+ Request (application/json;charset=utf-8)

  + Headers ->
    Authorization: Bearer token
  + Parameters ->
    orderId: 1 - Sipariş ID

+ Response 200 (application/json;charset=utf-8)

+ Body

          {
              "id": 1,
              "customerId": 18,
              "date": "2022-02-16T11:51:11.156+00:00",
              "amount": 59,
              "status": "PREPARING",
              "orderDetails": []
          },

## Sipariş listeleme [/all]
### Yapılan siparişleri listeler [GET]

+ Request (application/json;charset=utf-8)

  + Headers ->
    Authorization: Bearer token
  + Body ->

        {
          "startDate": "20-11-2021 11:00:00",
          "endDate": "20-11-2021 11:00:00",
        }

+ Response 200 (application/json;charset=utf-8)

+ Body

        [
            {
                "id": 18,
                "customerId": 1,
                "status": "CANCELLED",
                "orderDetails": [],
                "amount": 55
            },
            {
                "id": 17,
                "customerId": 1,
                "status": "CANCELLED",
                "orderDetails": [],
                "amount": 55
            },
        ]