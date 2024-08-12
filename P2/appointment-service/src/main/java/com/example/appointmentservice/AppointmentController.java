package com.example.appointmentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public String createAppointment(@RequestBody Appointment appointment) {
        appointmentService.insertAppointment(appointment);
        return "Cita creada con ID: " + appointment.getId();
    }

    @GetMapping
    public List<Appointment> getAppointments() {
        return appointmentService.getAllAppointments();
    }
}
