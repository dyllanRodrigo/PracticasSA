const express = require('express');
const app = express();
const port = 3004;

app.use(express.json());

app.post('/reminders', (req, res) => {
    // Lógica de recordatorio
    res.send('Recordatorio enviado');
});

app.listen(port, () => {
    console.log(`Reminder service listening at http://localhost:${port}`);
});
