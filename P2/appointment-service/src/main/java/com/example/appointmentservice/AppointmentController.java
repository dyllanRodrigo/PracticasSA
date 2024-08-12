package com.example.appointmentservice;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    
    private List<Appointment> appointments = new ArrayList<>();

    @PostMapping
    public String createAppointment(@RequestBody Appointment appointment) {
        appointments.add(appointment);
        return "Cita creada";
    }

    // Añadir un método GET para listar las citas - PRUEBA
    @GetMapping
    public List<Appointment> getAppointments() {
        return appointments;
    }
}
