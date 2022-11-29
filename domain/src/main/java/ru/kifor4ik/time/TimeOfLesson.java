package ru.kifor4ik.time;

import lombok.*;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TimeOfLesson {
    private Long id;
    private int numberOfLesson;
    private Time timeStart;
    private Time timeEnd;
}
