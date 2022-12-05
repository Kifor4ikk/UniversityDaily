package ru.kifor4ik.controller.teacher;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import ru.kifor4ik.group.Faculty;
import ru.kifor4ik.group.Team;
import ru.kifor4ik.service.teacher.TeacherService;
import ru.kifor4ik.teacher.Teacher;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("api/v3/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ApiOperation("Создание. Все имена нормализуются в форму из 'иМя' в 'Имя'")
    @PostMapping("/new")
    public boolean create(@RequestParam String firstName,@RequestParam String secondName,@RequestParam String thirdName,@RequestParam Long idFaculty){
        return teacherService.create(new Teacher(0L, nameNormalizer(firstName), nameNormalizer(secondName), nameNormalizer(thirdName),
                new Faculty(idFaculty, "", "")));
    }

    @ApiOperation("Поиск по ID")
    @GetMapping("findBy/id")
    public Teacher getById(@RequestParam Long id){
        return teacherService.getById(id);
    }

    @ApiOperation("Поиск по частичному совпадению любой из частей ФИО")
    @GetMapping("findBy/FIO")
    public List<Teacher> getByPartOfFIO(@RequestParam String name){
        return teacherService.getByPartOfFIO(name);
    }

    @ApiOperation("Изменение параметров учителя")
    @PutMapping("/update")
    public boolean update(@RequestBody Teacher teacher){
        return teacherService.update(teacher);
    }

    @ApiOperation("УВОЛЬНЕНИЕ (мякость удаления)")
    @DeleteMapping("/softDelete")
    public boolean softDelete(@RequestParam Long id){
        return teacherService.softDelete(id);
    }

    public String nameNormalizer(String name){
        return name.toUpperCase(Locale.ROOT).charAt(0) + name.toLowerCase(Locale.ROOT).substring(1);
    }
}
