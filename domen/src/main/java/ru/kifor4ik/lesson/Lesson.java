package ru.kifor4ik.lesson;

import lombok.*;

import java.time.DayOfWeek;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Lesson {

    private Long id;
    private String shortName;
    private String fullName;
    //Just for class Room number
    // optional String for letter like М Н А Б В Г Д etc or Медиатека
    private int classRoomNumber;
    private String optionalClassRoomNumber;
    // Optional String for if lesson only from 1 week to 5 week or something like this
    private String optional;
    //expanded info is Optional if you want to add some expanded info
    private String expandedInfo;
    // and heeere we go!
    // general info about faculty course and group
    private Long idFaculty;
    private Long idCourse;
    private Long idTeam;
    // Teacher info
    private Long idTeacher;
    // just if lesson hase two groups
    // if only one group set SubGroup 0 (Zero)
    private Long idSubGroup;
    //Day and time
    private Long idTimeOfLesson;
    private DayOfWeek dayOfWeek;
        //isOnlyGreen or onlyWhite if white and green true
        // mean that in every week
    private boolean isOnlyGreen;
    private boolean isOnlyWhite;
    private boolean isDeleted;
}
