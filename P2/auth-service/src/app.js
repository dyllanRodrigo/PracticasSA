const express = require('express');
const mysql = require('mysql2');
const app = express();
const port = 3000;

// Configuración de la conexión a la base de datos
const pool = mysql.createPool({
    host: 'mysql', // Nombre del servicio MySQL en Docker
    user: 'root',
    password: '12345678',
    database: 'soa_database',
    waitForConnections: true,
    connectionLimit: 10,
    queueLimit: 0
});

const db = pool.promise(); // Convertir a Promesa para usar async/await

app.use(express.json());

// Endpoint de registro
app.post('/register', async (req, res) => {
    const { username, password, email, rol } = req.body;

    try {
        // Inserta el nuevo usuario en la base de datos
        const [result] = await db.query(
            'INSERT INTO users (username, password, email, rol) VALUES (?, ?, ?, ?)',
            [username, password, email, rol]
        );

        res.status(201).send({ message: 'Usuario registrado con éxito', userId: result.insertId });
    } catch (error) {
        console.error('Error al registrar el usuario:', error);
        res.status(500).send({ message: 'Error al registrar el usuario' });
    }
});

app.listen(port, () => {
    console.log(`Auth service listening at http://localhost:${port}`);
});
