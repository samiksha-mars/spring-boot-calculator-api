# Spring Boot Calculator API

A tiny arithmetic calculator exposed as a REST API, built with Spring Boot. No database
is required — each request is computed on the fly.

## Features

- Add, subtract, multiply, divide
- Power and square root
- Input validation (divide-by-zero and negative square root return `400`)
- RFC 7807 `ProblemDetail` error responses

## Tech Stack

- Java 17
- Spring Boot (Web MVC)

## Running

```bash
./mvnw spring-boot:run
```

App starts on http://localhost:8080.

## API

| Method | Path               | Parameters              | Example                        |
|--------|--------------------|-------------------------|--------------------------------|
| GET    | `/api/calc/add`    | `a`, `b`                | `/api/calc/add?a=2&b=3`        |
| GET    | `/api/calc/subtract` | `a`, `b`              | `/api/calc/subtract?a=10&b=4`  |
| GET    | `/api/calc/multiply` | `a`, `b`              | `/api/calc/multiply?a=6&b=7`   |
| GET    | `/api/calc/divide` | `a`, `b`                | `/api/calc/divide?a=10&b=2`    |
| GET    | `/api/calc/power`  | `base`, `exponent`      | `/api/calc/power?base=2&exponent=8` |
| GET    | `/api/calc/sqrt`   | `value`                 | `/api/calc/sqrt?value=144`     |

### Example

```bash
curl "http://localhost:8080/api/calc/power?base=2&exponent=8"
```

```json
{
  "operation": "power",
  "result": 256.0
}
```
