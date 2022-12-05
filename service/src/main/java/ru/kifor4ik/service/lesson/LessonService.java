package ru.kifor4ik.service.lesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.dto.lesson.LessonDTO;
import ru.kifor4ik.dto.lesson.LessonDTOMapper;
import ru.kifor4ik.exception.*;
import ru.kifor4ik.group.Team;
import ru.kifor4ik.lesson.Lesson;
import ru.kifor4ik.repository.group.CourseRepository;
import ru.kifor4ik.repository.group.FacultyRepository;
import ru.kifor4ik.repository.group.TeamRepository;
import ru.kifor4ik.repository.lesson.LessonRepository;
import ru.kifor4ik.service.group.TeamService;
import ru.kifor4ik.service.subGroup.SubGroupService;
import ru.kifor4ik.service.teacher.TeacherService;
import ru.kifor4ik.service.time.TimeService;
import ru.kifor4ik.time.TimeOfLesson;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;


    private final SubGroupService subGroupService;
    private final TeacherService teacherService;
    private final TimeService timeService;
    private final LessonDTOMapper lessonDTOMapper = new LessonDTOMapper();

    private final FacultyRepository facultyRepository;
    private final CourseRepository courseRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository, SubGroupService subGroupService, TeacherService teacherService,
                         TimeService timeService, FacultyRepository facultyRepository,
                         CourseRepository courseRepository, TeamRepository teamRepository) {
        this.lessonRepository = lessonRepository;
        this.subGroupService = subGroupService;
        this.teacherService = teacherService;
        this.timeService = timeService;
        this.facultyRepository = facultyRepository;
        this.courseRepository = courseRepository;
        this.teamRepository = teamRepository;
    }

    public boolean create(Lesson lesson) {
        try {

            if(facultyRepository.doesItExists(lesson.getIdFaculty()) == 0)
                throw new NotExistException("Faculty not exist", "Cant create Lesson cause current FACULTY not exist", "LC10");
            if(courseRepository.doesItExists(lesson.getIdCourse()) == 0)
                throw new NotExistException("Course not exist", "Cant create Lesson cause current COURSE not exist", "LC11");
            if(teamRepository.doesItExists(lesson.getIdTeam()) == 0)
                throw new NotExistException("Team not exist", "Cant create Lesson cause current TEAM not exist", "LC12");
            if(!teacherService.doesItExists(lesson.getIdTeacher()))
                throw new NotExistException("Faculty not exist", "Cant create Lesson cause current faculty not exist", "LC13");
            if(lessonRepository.doesExistSame(lesson) != 0)
                throw new AlreadyExistException("AlreadyExists", "Same lesson already exists", "LC14");

            lessonRepository.create(lesson,Date.valueOf(LocalDate.now()),"UNDEFINED");
            return true;
        } catch (Exception e) {
            throw new CreateException("Create lesson exception", e.getLocalizedMessage(), "LC15");
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
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "LG10");
        }
    }

    public List<LessonDTO> getByTeamShortNameAndDayOfWeek(String shortName, DayOfWeek dayOfWeek) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeamAndDayOfWeek(shortName,dayOfWeek)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "LG11");
        }
    }

    public List<LessonDTO> getByClassRoomNumber(int classRoomNumber, String optional) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByClassRoomNumber(classRoomNumber, optional)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "LG12");
        }
    }

    public List<LessonDTO> getByTeacherId(Long teacherId) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeacherId(teacherId)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "LG13");
        }
    }

    public List<LessonDTO> getByTeacherInitials(String firstName, String secondName, String thirdName) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeacherAllParams(firstName,secondName,thirdName)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "LG14");
        }
    }

    public List<LessonDTO> getByTeamShortNameAndSubGroupNumber(String shortName, int subGroupNumber) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeamNameAndSubGroup(shortName, subGroupNumber)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "LG15");
        }
    }

    public List<LessonDTO> getByTeamShortNameAndSubGroupNumberAndDay(String shortName, int subGroupNumber, String day) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeamNameAndSubGroupAndDay(shortName, subGroupNumber,day)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "LG15");
        }
    }
    public List<LessonDTO> getByTeamShortNameAndOnlyGreenAndGeneral(String shortName) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeamNameOnlyGreenAndGeneral(shortName)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "LG16");
        }
    }

    public List<LessonDTO> getByTeamShortNameAndOnlyWhiteAndGeneral(String shortName) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeamNameOnlyWhiteAndGeneral(shortName)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "LG17");
        }
    }

    public List<LessonDTO> getByTeamName(String shortName) {
        try {
            List<LessonDTO> lessonList = new ArrayList<>();
            lessonRepository.findIdByTeamName(shortName)
                    .forEach(id -> lessonList.add(getById(id)));
            return lessonList;

        } catch (Exception e) {
            throw new GetException("Get lesson service exception", e.getLocalizedMessage(), "LG18");
        }
    }

    public boolean update(Lesson lesson){
        try {
            lessonRepository.update(lesson);
            return true;
        } catch (Exception e) {
            throw new GetException("Update lesson service exception", e.getLocalizedMessage(), "LU19");
        }
    }

    public boolean softDelete(Long id) {
        try {
            lessonRepository.softDelete(id, Date.valueOf(LocalDate.now()),"UNDEFINED");
            return true;
        } catch (Exception e) {
            throw new SoftDeleteException("Soft delete lesson service exception", e.getLocalizedMessage(), "LD10");
        }
    }
}
