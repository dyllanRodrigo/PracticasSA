POST
http://localhost:8080/auth/register

{
  "username": "admin",
  "password": "12345678",
  "email": "admin@admin.com",
  "rol": "administrator"
}

http://localhost:8080/appointments/appointments
{
  "patientId": "123",
  "date": "2024-08-10T10:00:00",
  "description": "Consulta general"
}


http://localhost:8080/reminders/reminders
{
  "message": "No olvides tu cita el 2024-08-10!"
}


GET
http://localhost:8080/patients/patients
http://localhost:8080/treatments/treatments