package ru.kifor4ik.lesson;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kifor4ik.group.GroupGeneral;
import ru.kifor4ik.subgroup.SubGroup;
import ru.kifor4ik.teacher.Teacher;
import ru.kifor4ik.time.TimeOfLesson;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lesson {

    private Long id;
    private String shortName;
    private String name;
    //Just for class Room number
    // optional String for letter like М Н А Б В Г Д etc
    private int classRoomNumber;
    private String optionalClassRoomNumber;
    // Optional String for if lesson only from 1 week to 5 week or something like this
    private String optional;
    //expanded info is Optional if you want to add some expanded info
    private String expandedInfo;

    // and heeere we go!
    // general info about faculty course and group
    private GroupGeneral groupGeneral;
    // Teacher info
    private Teacher teacher;
    private SubGroup subGroup;
    private TimeOfLesson timeOfLesson;
}
