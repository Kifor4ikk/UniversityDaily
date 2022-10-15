package ru.kifor4ik.controller.time;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @ApiOperation("Создание. строгая типизация времени 'HH:MM:SS'")
    @PostMapping("/new")
    public boolean create(int lessonNumber, String timeStart, String timeEnd){
        if(Time.valueOf(timeStart).before(Time.valueOf(timeEnd)))
            throw new DateTimeException("You cant start lesson in future and end it in past! This appear that you mistake time");
        return timeService.create(new TimeOfLesson(0L, lessonNumber, Time.valueOf(timeStart), Time.valueOf(timeEnd)));
    }

    @ApiOperation("Поиск по ID")
    @GetMapping("findBy/id")
    public TimeOfLesson getById(Long id){
        return timeService.getById(id);
    }

    @ApiOperation("НАМ НУЖНО ВСЁ ВРЕМЯ! ДО ПОСЛЕДНЕЙ ПЕЩИНКИ!")
    @GetMapping("findBy/all")
    public List<TimeOfLesson> getAll(){
        return timeService.getAll();
    }

    @ApiOperation("Мягчайшее удаление")
    @DeleteMapping("/softDelete")
    public boolean softDelete(Long id){
        return timeService.softDelete(id);
    }





}
