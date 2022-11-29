package ru.kifor4ik.dto;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.kifor4ik.dto.lesson.LessonDTO;
import ru.kifor4ik.dto.lesson.LessonDTOMapper;
import ru.kifor4ik.group.Faculty;
import ru.kifor4ik.lesson.Lesson;
import ru.kifor4ik.subgroup.SubGroup;
import ru.kifor4ik.teacher.Teacher;
import ru.kifor4ik.time.TimeOfLesson;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class LessonDTOMapperTest {

    private final Lesson lesson = new Lesson(
            1L, "Test", "TestFull", 1, "A",
            "Optional info", "Expanded", 1L, 1L, 1L, 1L,
            1L, 1L, DayOfWeek.TUESDAY, true, true, false
    );

    private final SubGroup subGroup = new SubGroup(
            1L, 0, "Full group"
    );

    private final Teacher teacher = new Teacher(
            1L, "ANANAS", "ANANASOVIch", "BanaNOvich",
            new Faculty(
                    1L, "ANA", "Ananas 10"
            )
    );

    private final TimeOfLesson timeOfLesson = new TimeOfLesson(
            1L, 1, Time.valueOf("11:11:11"), Time.valueOf("11:11:12")
    );

    private final LessonDTOMapper lessonDTOMapper = new LessonDTOMapper();

    @Test
    public void DTOTest(){
        System.out.println(lessonDTOMapper.lessonToDTO(lesson,teacher,timeOfLesson,subGroup));
        LessonDTO lessonDTO = lessonDTOMapper.lessonToDTO(lesson,teacher,timeOfLesson,subGroup);
        Assert.assertEquals(lessonDTO.getShortName(),"Test");
        Assert.assertEquals(lessonDTO.getFullName(),"TestFull");
        Assert.assertEquals(lessonDTO.getClassRoomNumber(),1);
        Assert.assertEquals(lessonDTO.getOptionalClassRoomNumber(),"A");
        Assert.assertEquals(lessonDTO.getOptional(),"Optional info");
        Assert.assertEquals(lessonDTO.getExpandedInfo(),"Expanded");
        Assert.assertEquals(lessonDTO.getTeacherFirstName(),"Ananas");
        Assert.assertEquals(lessonDTO.getTeacherSecondName(),"Ananasovich");
        Assert.assertEquals(lessonDTO.getTeacherThirdName(),"Bananovich");
        Assert.assertEquals(lessonDTO.getTeacherSecondNameAndInitials(),"Ananasovich A.B.");
        Assert.assertEquals(lessonDTO.getSubGroupNumber(),0);
        Assert.assertEquals(lessonDTO.getNumberOfLesson(),1);
        Assert.assertEquals(lessonDTO.getTimeStart(), Time.valueOf("11:11:11").toString());
        Assert.assertEquals(lessonDTO.getTimeEnd(), Time.valueOf("11:11:12").toString());
        Assert.assertTrue(lessonDTO.isOnlyGreen());
        Assert.assertTrue(lessonDTO.isOnlyWhite());

    }
}
