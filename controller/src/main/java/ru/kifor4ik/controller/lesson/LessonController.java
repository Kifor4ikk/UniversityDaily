package ru.kifor4ik.controller.lesson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kifor4ik.dto.lesson.LessonDTO;
import ru.kifor4ik.lesson.Lesson;
import ru.kifor4ik.service.lesson.LessonService;

import java.time.DayOfWeek;
import java.util.List;

@RestController
@RequestMapping("api/v3/lesson")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @ApiOperation("Создание. Гигантское кол-во параметров.")
    @PostMapping("/new")
    public boolean create(Lesson lesson){
        return lessonService.create(lesson);
    }

    @ApiOperation("Примитивный поиск по ID")
    @GetMapping("findBy/id")
    public LessonDTO getById(Long id){
        return lessonService.getById(id);
    }

    @ApiOperation("Поиск по номеру аудитории. Оптионал рум инфо это буковка А Б В Г или М или Н или МЕЖДАК ну ты понял")
    @GetMapping("findBy/classRoomNumber")
    public List<LessonDTO> getByClassRoomNumber(int roomNumber, String optionalRoomInfo){
        return lessonService.getByClassRoomNumber(roomNumber, optionalRoomInfo);
    }

    @ApiOperation("Поиск по ID учителя. Ну мб понадобится")
    @GetMapping("findBy/teacherId")
    public List<LessonDTO> getByTeacherId(Long id){
        return lessonService.getByTeacherId(id);
    }

    @ApiOperation("Поиск по всем параметрам учителя. Полностью. Полное имя фамилия отчество")
    @GetMapping("findBy/teacherName")
    public List<LessonDTO> getByTeacherName(String name, String secondName, String thirdName){
        return lessonService.getByTeacherInitials(name, secondName, thirdName);
    }

    @ApiOperation("Поиск по полному КОРОТКОМУ имени группы, ВС-19")
    @GetMapping("findBy/fullShortName")
    public List<LessonDTO> getBy(String teamName){
        return lessonService.getByTeamName(teamName);
    }

    @ApiOperation("Поиск по короткому ПОЛНОМУ имени и дню недели, ВС-19 'SUNDAY' ")
    @GetMapping("findBy/fullShortNameAndDayOfWeek")
    public List<LessonDTO> getByTeamShortNameAndDayOfWeek(String name, String dayOfWeek){
        return lessonService.getByTeamShortNameAndDayOfWeek(name, DayOfWeek.valueOf(dayOfWeek));
    }

    @ApiOperation("Поиск по короткому ПОЛНОМУ имени и номеру подгруппы, ВС-19 0")
    @GetMapping("findBy/fullShortNameAndSubGroupNumber")
    public List<LessonDTO> getByTeamShortNameAndSubGroupNumber(String name, int subGroupNumber){
        return lessonService.getByTeamShortNameAndSubGroupNumber(name, subGroupNumber);
    }

    @ApiOperation("Поиск по короткомУ ПОЛНОМУ имени, но только ЗЕЛЕНАЯ неделя + общие пары")
    @GetMapping("findBy/teamNameAndOnlyGreen")
    public List<LessonDTO> getByTeamShortNameAndOnlyGreen(String name){
        return lessonService.getByTeamShortNameAndOnlyGreenAndGeneral(name);
    }

    @ApiOperation("Поиск по короткомУ ПОЛНОМУ имени, но только БЕЛАЯ неделя + общие пары")
    @GetMapping("findBy/teamNameAndOnlyWhite")
    public List<LessonDTO> getByTeamShortNameAndOnlyWhite(String name){
        return lessonService.getByTeamShortNameAndOnlyWhiteAndGeneral(name);
    }

    @ApiOperation("Обновление инфы")
    @PutMapping("/update")
    public boolean update(Lesson lesson){
        return lessonService.update(lesson);
    }


    @ApiOperation("Удаление мягкое. Сыграет злую шутку в будущем")
    @PutMapping("/softDelete")
    public boolean softDelete(Long id){
        return lessonService.softDelete(id);
    }

}
