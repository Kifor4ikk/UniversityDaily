package ru.kifor4ik.controller.group;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kifor4ik.group.Course;
import ru.kifor4ik.repository.group.CourseRepository;
import ru.kifor4ik.service.group.CourseService;

@RestController
@RequestMapping("api/v3/course")
public class CourseController {


    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation("Создание. названия переменных говорят сами за себя")
    @PostMapping("/new")
    public boolean create(Long idFaculty, int courseNumber){
        return courseService.create(new Course(0L, idFaculty,courseNumber));
    }

    @ApiOperation("Поиск по ИДшке")
    @GetMapping("/findBy/id")
    public Course getById(Long id){
        return courseService.getById(id);
    }

    @ApiOperation("Мягчайшее удаление")
    @DeleteMapping("/softDelete")
    public boolean softDelete(Long id){
        return courseService.softDelete(id);
    }
}
