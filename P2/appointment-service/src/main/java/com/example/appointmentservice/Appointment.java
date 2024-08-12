package com.example.appointmentservice;

public class Appointment {

    private Long id;
    private String patientId;
    private String date;
    private String description;

    // Constructor
    public Appointment(String patientId, String date, String description) {
        this.patientId = patientId;
        this.date = date;
        this.description = description;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
