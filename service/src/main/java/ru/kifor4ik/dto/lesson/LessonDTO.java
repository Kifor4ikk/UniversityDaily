package ru.kifor4ik.dto.lesson;

import lombok.*;

import java.time.DayOfWeek;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LessonDTO {
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
    // Teacher info
    private String teacherFirstName;
    private String teacherSecondName;
    private String teacherThirdName;
    private String teacherSecondNameAndInitials;
    // just if lesson hase two groups
    // if only one group set SubGroup 0 (Zero)
    private int subGroupNumber;
    //Day and time
    private int numberOfLesson;
    private String timeStart;
    private String timeEnd;
    private String dayOfWeek;
    //isOnlyGreen or onlyWhite if white and green true
    // mean that in every week
    private boolean isOnlyGreen;
    private boolean isOnlyWhite;
}
