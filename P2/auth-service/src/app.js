const express = require('express');
const app = express();
const port = 3000;

app.use(express.json());

app.post('/register', (req, res) => {
    // Lógica de registro
    res.send('Usuario registrado');
});

app.listen(port, () => {
    console.log(`Auth service listening at http://localhost:${port}`);
});