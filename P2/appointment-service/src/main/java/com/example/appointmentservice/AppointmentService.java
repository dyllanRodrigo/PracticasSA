package com.example.appointmentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class AppointmentService {

    private final SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public AppointmentService(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("appointments")
            .usingGeneratedKeyColumns("id");
    }

    public void insertAppointment(Appointment appointment) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("patient_id", appointment.getPatientId());
        parameters.put("date", appointment.getDate());
        parameters.put("description", appointment.getDescription());

        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        appointment.setId(newId.longValue());
    }

    // Método para obtener todas las citas (si lo necesitas)
    public List<Appointment> getAllAppointments() {
        // Implementa la lógica de obtención si lo necesitas
        return null;
    }
}
