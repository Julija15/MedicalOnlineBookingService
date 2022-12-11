package com.example.medicalonlinebookingservice.Dto;

import com.example.medicalonlinebookingservice.entity.Doctor;
import org.hibernate.type.LocalDateType;

import java.time.LocalDateTime;
import java.util.List;

public class Timetable {

    private Doctor doctor;

    private List<LocalDateTime> times;

}
