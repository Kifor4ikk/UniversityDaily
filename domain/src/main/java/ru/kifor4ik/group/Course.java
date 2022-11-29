package ru.kifor4ik.group;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Course {

    private Long id;
    private Long idFaculty;
    // number of course
    private int courseNumber;

}
