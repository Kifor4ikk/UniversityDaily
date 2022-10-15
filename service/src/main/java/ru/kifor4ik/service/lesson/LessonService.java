package ru.kifor4ik.service.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.dto.lesson.LessonDTO;
import ru.kifor4ik.dto.lesson.LessonDTOMapper;
import ru.kifor4ik.exception.CreateException;
import ru.kifor4ik.exception.GetException;
import ru.kifor4ik.exception.SoftDeleteException;
import ru.kifor4ik.group.Team;
import ru.kifor4ik.lesson.Lesson;
import ru.kifor4ik.repository.lesson.LessonRepository;
import ru.kifor4ik.service.subGroup.SubGroupService;
import ru.kifor4ik.service.teacher.TeacherService;
import ru.kifor4ik.service.time.TimeService;
import ru.kifor4ik.time.TimeOfLesson;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final SubGroupService subGroupService;
    private final TeacherService teacherService;
    private final TimeService timeService;
    private final LessonDTOMapper lessonDTOMapper = new LessonDTOMapper();

    @Autowired
    public LessonService(LessonRepository lessonRepository, SubGroupService subGroupService, TeacherService teacherService, TimeService timeService) {
        this.lessonRepository = lessonRepository;
        this.subGroupService = subGroupService;
        this.teacherService = teacherService;
        this.timeService = timeService;
    }

    public boolean create(Lesson lesson) {
        try {
            lessonRepository.create(lesson);
            return true;
        } catch (Exception e) {
            throw new CreateException("Create lesson exception", e.getLocalizedMessage(), "C000001");
        }
    }

    public LessonDTO getById(Long id) {
        try {
            Lesson lessonTemp = lessonRepository.getById(id);
            return lessonDTOMapper.lessonToDTO(
                    lessonTemp,
                    teacherService.getById(lessonTemp.getIdTeacher()),
                    timeService.getById(lessonTemp.getIdTimeOfLesson()),
                    subGroupService.getById(lessonTemp.getIdSubGroup())
            );
        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "G000001");
        }
    }

    public List<LessonDTO> getByTeamShortNameAndDayOfWeek(String shortName, DayOfWeek dayOfWeek) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeamAndDayOfWeek(shortName,dayOfWeek)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "G000002");
        }
    }

    public List<LessonDTO> getByClassRoomNumber(int classRoomNumber, String optional) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByClassRoomNumber(classRoomNumber, optional)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "G000003");
        }
    }

    public List<LessonDTO> getByTeacherId(Long teacherId) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeacherId(teacherId)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "G000004");
        }
    }

    public List<LessonDTO> getByTeacherInitials(String firstName, String secondName, String thirdName) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeacherAllParams(firstName,secondName,thirdName)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "G000005");
        }
    }

    public List<LessonDTO> getByTeamShortNameAndSubGroupNumber(String shortName, int subGroupNumber) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeamNameAndSubGroup(shortName, subGroupNumber)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "G000006");
        }
    }

    public List<LessonDTO> getByTeamShortNameAndOnlyGreenAndGeneral(String shortName) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeamNameOnlyGreenAndGeneral(shortName)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "G000007");
        }
    }

    public List<LessonDTO> getByTeamShortNameAndOnlyWhiteAndGeneral(String shortName) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeamNameOnlyWhiteAndGeneral(shortName)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "G000008");
        }
    }

    public List<LessonDTO> getByTeamName(String shortName) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeamName(shortName)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "G000008");
        }
    }

    public boolean update(Lesson lesson){
        try {
            lessonRepository.update(lesson);
            return true;
        } catch (Exception e) {
            throw new GetException("Update lesson service exception", e.getLocalizedMessage(), "U000001");
        }
    }

    public boolean softDelete(Long id) {
        try {
            lessonRepository.softDelete(id);
            return true;
        } catch (Exception e) {
            throw new SoftDeleteException("Soft delete lesson service exception", e.getLocalizedMessage(), "SD000001");
        }
    }
}
