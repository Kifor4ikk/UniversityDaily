package ru.kifor4ik.teacher;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Teacher {

    private Long id;
    private String name;
    private String secondName;
    //Name po batye
    private String thirdName;
}
