const express = require('express');
const axios = require('axios');
const app = express();
const port = 8080;

app.use(express.json());

const services = {
    'auth': 'http://auth-service:3000',
    'patients': 'http://patient-service:3001',
    'appointments': 'http://appointment-service:3002',
    'treatments': 'http://treatment-service:3003',
    'reminders': 'http://reminder-service:3004'
};


app.use('/:service/*', (req, res) => {
    const serviceUrl = services[req.params.service];
    if (serviceUrl) {
        const url = `${serviceUrl}${req.originalUrl.replace(`/${req.params.service}`, '')}`;
        console.log(`Redirigiendo a URL: ${url}`);
        axios({
            method: req.method,
            url: url,
            data: req.body
        })
        .then(response => res.send(response.data))
        .catch(error => {
            console.error(`Error al redirigir a ${url}:`, error.message);
            res.status(error.response ? error.response.status : 500).send(error.response ? error.response.data : 'Error');
        });
    } else {
        res.status(404).send('Service not found');
    }
});


app.listen(port, () => {
    console.log(`API Gateway listening at http://localhost:${port}`);
});
