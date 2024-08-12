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
