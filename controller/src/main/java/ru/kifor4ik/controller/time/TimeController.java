package ru.kifor4ik.controller.time;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kifor4ik.service.time.TimeService;
import ru.kifor4ik.time.TimeOfLesson;

import java.sql.Time;
import java.time.DateTimeException;
import java.util.List;

@RestController("api/v3/time")
public class TimeController {

    private final TimeService timeService;

    @Autowired
    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @ApiOperation("��������. ������� ��������� ������� 'HH:MM:SS'")
    @PostMapping("/new")
    public boolean create(@RequestParam Integer lessonNumber,@RequestParam String timeStart,@RequestParam String timeEnd){
        return timeService.create(new TimeOfLesson(0L, lessonNumber, Time.valueOf(timeStart), Time.valueOf(timeEnd)));
    }

    @ApiOperation("����� �� ID")
    @GetMapping("findBy/id")
    public TimeOfLesson getById(@RequestParam Long id){
        return timeService.getById(id);
    }

    @ApiOperation("��� ����� �Ѩ �����! �� ��������� ��������!")
    @GetMapping("findBy/all")
    public List<TimeOfLesson> getAll(){
        return timeService.getAll();
    }

    @ApiOperation("��������� ��������")
    @DeleteMapping("/softDelete")
    public boolean softDelete(@RequestParam Long id){
        return timeService.softDelete(id);
    }





}
