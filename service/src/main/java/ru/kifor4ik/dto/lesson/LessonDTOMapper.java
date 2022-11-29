package ru.kifor4ik.dto.lesson;

import ru.kifor4ik.lesson.Lesson;
import ru.kifor4ik.subgroup.SubGroup;
import ru.kifor4ik.teacher.Teacher;
import ru.kifor4ik.time.TimeOfLesson;

import java.util.Locale;

public class LessonDTOMapper {

    public LessonDTO lessonToDTO(Lesson lesson, Teacher teacher, TimeOfLesson time, SubGroup subGroup) {

        return LessonDTO.builder()
                .shortName(lesson.getShortName())
                .fullName(lesson.getFullName())
                .classRoomNumber(lesson.getClassRoomNumber())
                .optionalClassRoomNumber(lesson.getOptionalClassRoomNumber())
                .optional(lesson.getOptional())
                .expandedInfo(lesson.getExpandedInfo())
                .teacherFirstName(teacher.getFirstName().toUpperCase(Locale.ROOT).charAt(0) + teacher.getFirstName().toLowerCase(Locale.ROOT).substring(1))
                .teacherSecondName(teacher.getSecondName().toUpperCase(Locale.ROOT).charAt(0) + teacher.getSecondName().toLowerCase(Locale.ROOT).substring(1))
                .teacherThirdName(teacher.getThirdName().toUpperCase(Locale.ROOT).charAt(0) + teacher.getThirdName().toLowerCase(Locale.ROOT).substring(1))
                .teacherSecondNameAndInitials(
                        teacher.getSecondName().toUpperCase(Locale.ROOT).charAt(0)
                                + teacher.getSecondName().toLowerCase(Locale.ROOT).substring(1)
                                + " " + teacher.getFirstName().toUpperCase(Locale.ROOT).charAt(0)
                                + "." + teacher.getThirdName().toUpperCase(Locale.ROOT).charAt(0)
                                + "."
                )
                .subGroupNumber(subGroup.getNumberOfSubGroup())
                .numberOfLesson(time.getNumberOfLesson())
                .timeStart(time.getTimeStart().toString())
                .timeEnd(time.getTimeEnd().toString())
                .dayOfWeek(lesson.getDayOfWeek().toString())
                .isOnlyGreen(lesson.isOnlyGreen())
                .isOnlyWhite(lesson.isOnlyWhite())
                .build();

    }
}
