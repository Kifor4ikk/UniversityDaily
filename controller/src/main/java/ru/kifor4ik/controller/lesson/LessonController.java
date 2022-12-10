package ru.kifor4ik.controller.lesson;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kifor4ik.dto.lesson.LessonDTO;
import ru.kifor4ik.lesson.Lesson;
import ru.kifor4ik.room.Room;
import ru.kifor4ik.service.lesson.LessonService;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

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
    @ApiOperation("����� �� ��������� ������� ����� � ������ ���������, ��-19 0 MONDAY 1/2/3 TRUE(�������)/false(�����)")
    @GetMapping("findBy/fullShortNameAndSubGroupNumberAndDayOfWeekAndGreenOrWhiteAndGeneral")
    public List<LessonDTO> getByTeamShortNameAndSubGroupNumber(@RequestParam String name,
                                                               @RequestParam Integer subGroupNumber,
                                                               @RequestParam String dayOfWeek,
                                                               @RequestParam Boolean isGreen){
        return lessonService.getByTeamShortNameAndSubGroupNumberAndDayAndGreenOrWhiteAndGeneral(name, subGroupNumber,dayOfWeek, isGreen);
    }

    @ApiOperation("����� �� id ������� � ������ �������, 1 0 MONDAY TRUE(�������)/false(�����)")
    @GetMapping("findBy/teacherIdAndDayOfWeekAndGreenOrWhiteAndGeneral")
    public List<LessonDTO> getByTeamShortNameAndSubGroupNumber(@RequestParam Long teacherId,
                                                               @RequestParam String dayOfWeek,
                                                               @RequestParam Boolean isGreen){
        return lessonService.getByTeamShortNameAndSubGroupNumberAndDayAndGreenOrWhiteAndGeneral(teacherId,dayOfWeek, isGreen);
    }

    @ApiOperation("����� �� ��������� � � ��� ����., 206 � MONDAY TRUE(�������)/false(�����)")
    @GetMapping("findBy/roomNumberAndRoomOptionalAndDayOfWeekAndGreenOrWhiteAndGeneral")
    public List<LessonDTO> getByRoomParamAndDayAndGreenOrWhiteAndGeneral(@RequestParam Integer roomNumber,
                                                               @RequestParam String roomOptional,
                                                               @RequestParam String dayOfWeek,
                                                               @RequestParam Boolean isGreen){
        return lessonService.getByRoomParamAndDayAndGreenOrWhiteAndGeneral(roomNumber, roomOptional,dayOfWeek, isGreen);
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

    @ApiOperation("��� �������� ��� ��������� �������")
    @GetMapping("list/rooms")
    public List<Room> getAllRooms(){
        return lessonService.getAllRooms();
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
