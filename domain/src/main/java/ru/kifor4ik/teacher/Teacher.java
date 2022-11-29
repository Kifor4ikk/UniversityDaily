package ru.kifor4ik.teacher;

import lombok.*;
import ru.kifor4ik.group.Faculty;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Teacher {

    private Long id;
    private String firstName;
    private String secondName;
    //Name po batye
    private String thirdName;
    private Faculty faculty;
}
