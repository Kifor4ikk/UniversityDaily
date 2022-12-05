package ru.kifor4ik.controller.lesson;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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

    @ApiOperation("��������. ���������� ���-�� ����������.")
    @PostMapping("/new")
    public boolean create(@RequestBody Lesson lesson){
        return lessonService.create(lesson);
    }

    @ApiOperation("����������� ����� �� ID")
    @GetMapping("findBy/id")
    public LessonDTO getById(@RequestParam Long id){
        return lessonService.getById(id);
    }

    @ApiOperation("����� �� ������ ���������. �������� ��� ���� ��� ������� � � � � ��� � ��� � ��� ������ �� �� �����")
    @GetMapping("findBy/classRoomNumber")
    public List<LessonDTO> getByClassRoomNumber(@RequestParam Integer roomNumber,@RequestParam String optionalRoomInfo){
        return lessonService.getByClassRoomNumber(roomNumber, optionalRoomInfo);
    }

    @ApiOperation("����� �� ID �������. �� �� �����������")
    @GetMapping("findBy/teacherId")
    public List<LessonDTO> getByTeacherId(@RequestParam Long id){
        return lessonService.getByTeacherId(id);
    }

    @ApiOperation("����� �� ���� ���������� �������. ���������. ������ ��� ������� ��������")
    @GetMapping("findBy/teacherName")
    public List<LessonDTO> getByTeacherName(@RequestParam String name, @RequestParam String secondName,@RequestParam String thirdName){
        return lessonService.getByTeacherInitials(name, secondName, thirdName);
    }

    @ApiOperation("����� �� ������� ��������� ����� ������, ��-19")
    @GetMapping("findBy/fullShortName")
    public List<LessonDTO> getBy(@RequestParam String teamName){
        return lessonService.getByTeamName(teamName);
    }

    @ApiOperation("����� �� ��������� ������� ����� � ��� ������, ��-19 'SUNDAY' ")
    @GetMapping("findBy/fullShortNameAndDayOfWeek")
    public List<LessonDTO> getByTeamShortNameAndDayOfWeek(@RequestParam String name,@RequestParam String dayOfWeek){
        return lessonService.getByTeamShortNameAndDayOfWeek(name, DayOfWeek.valueOf(dayOfWeek));
    }

    @ApiOperation("����� �� ��������� ������� ����� � ������ ���������, ��-19 0")
    @GetMapping("findBy/fullShortNameAndSubGroupNumber")
    public List<LessonDTO> getByTeamShortNameAndSubGroupNumber(@RequestParam String name, @RequestParam Integer subGroupNumber){
        return lessonService.getByTeamShortNameAndSubGroupNumber(name, subGroupNumber);
    }

    @ApiOperation("����� �� ��������� ������� ����� � ������ ���������, ��-19 0 MONDAY")
    @GetMapping("findBy/fullShortNameAndSubGroupNumberAndDayOfWeek")
    public List<LessonDTO> getByTeamShortNameAndSubGroupNumber(@RequestParam String name,
                                                               @RequestParam Integer subGroupNumber,
                                                               @RequestParam String dayOfWeek){
        return lessonService.getByTeamShortNameAndSubGroupNumberAndDay(name, subGroupNumber,dayOfWeek);
    }

    @ApiOperation("����� �� ��������� ������� �����, �� ������ ������� ������ + ����� ����")
    @GetMapping("findBy/teamNameAndOnlyGreen")
    public List<LessonDTO> getByTeamShortNameAndOnlyGreen(@RequestParam String name){
        return lessonService.getByTeamShortNameAndOnlyGreenAndGeneral(name);
    }

    @ApiOperation("����� �� ��������� ������� �����, �� ������ ����� ������ + ����� ����")
    @GetMapping("findBy/teamNameAndOnlyWhite")
    public List<LessonDTO> getByTeamShortNameAndOnlyWhite(@RequestParam String name){
        return lessonService.getByTeamShortNameAndOnlyWhiteAndGeneral(name);
    }

    @ApiOperation("���������� ����")
    @PutMapping("/update")
    public boolean update(@RequestBody Lesson lesson){
        return lessonService.update(lesson);
    }

    @ApiOperation("�������� ������. ������� ���� ����� � �������")
    @DeleteMapping("/softDelete")
    public boolean softDelete(@RequestParam Long id){
        return lessonService.softDelete(id);
    }

}
