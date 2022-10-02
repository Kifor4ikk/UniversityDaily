package ru.kifor4ik.time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TimeOfLesson {

    private Long id;
    private String timeStart;
    private String timeEnd;
}
