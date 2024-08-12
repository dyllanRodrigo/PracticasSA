package com.example.appointmentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppointmentService {

    private final SimpleJdbcInsert simpleJdbcInsert;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AppointmentService(DataSource dataSource) {
        this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
            .withTableName("appointments")
            .usingGeneratedKeyColumns("id");
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertAppointment(Appointment appointment) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("patient_id", appointment.getPatientId());
        parameters.put("date", appointment.getDate());
        parameters.put("description", appointment.getDescription());

        Number newId = simpleJdbcInsert.executeAndReturnKey(parameters);
        appointment.setId(newId.longValue());
    }

    // Método para obtener todas las citas
    public List<Appointment> getAllAppointments() {
        String sql = "SELECT * FROM appointments";

        RowMapper<Appointment> rowMapper = (rs, rowNum) -> {
            Appointment appointment = new Appointment(
                rs.getString("patient_id"),
                rs.getString("date"),
                rs.getString("description")
            );
            appointment.setId(rs.getLong("id"));
            return appointment;
        };

        return jdbcTemplate.query(sql, rowMapper);
    }
}
