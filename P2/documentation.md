
### 201907774
### Dyllan García Mejía

Proyecto: Plataforma de Gestión para una Clínica Dental
# Proyecto: Plataforma de Gestión para una Clínica Dental

#### Descripción General
Una plataforma de gestión para una clínica dental que permita la administración de citas, gestión de pacientes, control de inventarios, gestión de tratamientos y envío de recordatorios a los pacientes.


● Documentar el proceso de configuración e implementación.
### Documentación Breve del Proceso de Configuración e Implementación del Proyecto

#### 1. **Requisitos Previos**
- **Docker y Docker Compose**: Asegurarse de tener Docker y Docker Compose instalados en la máquina.
- **MySQL Workbench** (opcional): Para gestionar la base de datos MySQL.
- **Node.js y npm**: Necesarios para los microservicios basados en JavaScript.
- **Java y Maven**: Requeridos para el microservicio basado en Spring Boot.
- **Python**: Necesario para el microservicio de gestión de pacientes basado en Flask.
- **Go**: Para el microservicio de gestión de tratamientos.

#### 2. **Estructura del Proyecto**
El proyecto está organizado en varios directorios, cada uno representando un microservicio:

- **auth-service**: Maneja la autenticación y registro de usuarios.
- **patient-service**: Gestiona la información de los pacientes.
- **appointment-service**: Gestiona las citas de la clínica.
- **treatment-service**: Gestiona los tratamientos de los pacientes.
- **reminder-service**: Maneja el envío de recordatorios.
- **api-gateway**: Actúa como punto de entrada, enruta las solicitudes a los microservicios correspondientes.
- **mysql**: Servicio de base de datos MySQL compartido por todos los microservicios.

#### 3. **Configuración e Implementación**

##### Paso 1: Clonar el Repositorio
Clonar el repositorio en máquina local.

##### Paso 2: Configurar las Dependencias
Cada microservicio tiene sus propias dependencias que deben ser instaladas.

- **auth-service y reminder-service (Node.js)**:
  ```bash
  cd auth-service
  npm install
  cd ../reminder-service
  npm install
  ```

- **appointment-service (Java con Spring Boot)**:
  Maven se encargará de instalar las dependencias al construir el proyecto.

- **patient-service (Python con Flask)**:
  Tener un entorno virtual de Python configurado y activar las dependencias desde `requirements.txt`.

- **treatment-service (Go)**:
  Las dependencias de Go se manejan a través de módulos Go.

##### Paso 3: Configurar Docker y Docker Compose
Asegúrate de que el archivo `docker-compose.yml` esté configurado correctamente, exponiendo los puertos necesarios y configurando las variables de entorno adecuadas para cada servicio.

##### Paso 4: Iniciar los Servicios
Usa Docker Compose para construir y levantar todos los microservicios y la base de datos.

```bash
docker-compose up --build
```

Este comando construirá las imágenes de Docker y levantará los contenedores necesarios.

##### Paso 5: Verificar los Servicios
Una vez que los contenedores estén en funcionamiento, verificar que cada microservicio esté operativo accediendo a los siguientes endpoints:


● Contratos de microservicios
https://documenter.getpostman.com/view/29725331/2sA3s4mW4Z

● Dockerfile de cada microservicio.

Api gateway:

```
FROM node:14
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 8080
CMD ["node", "src/app.js"]
```

Appointment-service
```
FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/appointment-service-1.0-SNAPSHOT.jar app.jar
EXPOSE 3002
ENTRYPOINT ["java", "-jar", "app.jar"]

```

Auth-service
```
FROM node:14
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 3000
CMD ["node", "src/app.js"]
```

Patient-service
```
FROM python:3.8-slim
WORKDIR /app
COPY requirements.txt .
RUN pip install -r requirements.txt
COPY . .
EXPOSE 3001
CMD ["python", "src/app.py"]

```

Reminder-service
```
FROM node:14
WORKDIR /usr/src/app
COPY package*.json ./
RUN npm install
COPY . .
EXPOSE 3004
CMD ["node", "src/app.js"]
```

Treatment-service
```
FROM golang:1.22.1

WORKDIR /app

# Copia go.mod y go.sum (si existe)
COPY go.mod ./

# Descargar las dependencias
RUN go mod download

# Copia el resto de los archivos
COPY . .

# Construir la aplicación
RUN go build -o main .

# Exponer el puerto y ejecutar
EXPOSE 3003
CMD ["./main"]
```


● Archivo yaml (Docker compose)
```
version: '3.8'

services:
  mysql:
    image: mysql:8.0
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: 12345678
      MYSQL_DATABASE: soa_database
      MYSQL_PASSWORD: 12345678
    ports:
      - "3307:3306"
    volumes:
      - mysql_data:/var/lib/mysql

  api-gateway:
    build: ./api-gateway
    ports:
      - "8080:8080"
    depends_on:
      - appointment-service

  auth-service:
    build: ./auth-service
    ports:
      - "3001:3000"

  patient-service:
    build: ./patient-service
    ports:
      - "3002:3001"

  appointment-service:
    build: ./appointment-service
    ports:
      - "3003:3002"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/soa_database
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: 12345678

  treatment-service:
    build: ./treatment-service
    ports:
      - "3004:3003"

  reminder-service:
    build: ./reminder-service
    ports:
      - "3005:3004"

volumes:
  mysql_data:

```

● Diagrama de Arquitectura

● Diagrama ER


