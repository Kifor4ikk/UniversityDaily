package ru.kifor4ik.group;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {

    private Long id;
    private Long facultyId;
    // number of course
    private int course;

}
