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

    @ApiOperation("��������. ��� ����� ������������� � ����� �� '���' � '���'")
    @PostMapping("/new")
    public boolean create(String firstName, String secondName, String thirdName, Long idFaculty){
        return teacherService.create(new Teacher(0L, nameNormalizer(firstName), nameNormalizer(secondName), nameNormalizer(thirdName),
                new Faculty(idFaculty, "", "")));
    }

    @ApiOperation("����� �� ID")
    @GetMapping("findBy/id")
    public Teacher getById(Long id){
        return teacherService.getById(id);
    }

    @ApiOperation("����� �� ���������� ���������� ����� �� ������ ���")
    @GetMapping("findBy/FIO")
    public List<Teacher> getByPartOfFIO(String name){
        return teacherService.getByPartOfFIO(name);
    }

    @ApiOperation("��������� ���������� �������")
    @PutMapping("/update")
    public boolean update(Teacher teacher){
        return teacherService.update(teacher);
    }

    @ApiOperation("���������� (������� ��������)")
    @DeleteMapping("/softDelete")
    public boolean softDelete(Long id){
        return teacherService.softDelete(id);
    }


    public String nameNormalizer(String name){
        return name.toUpperCase(Locale.ROOT).charAt(0) + name.toLowerCase(Locale.ROOT).substring(1);
    }
}
