package ru.kifor4ik.controller.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kifor4ik.group.Faculty;
import ru.kifor4ik.service.group.FacultyService;

import java.util.List;

@RestController
@RequestMapping("api/v3/faculty")
public class FacultyController {

    private final FacultyService facultyService;

    @Autowired
    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @ApiOperation("Создание")
    @PostMapping("/new")
    public boolean create(@RequestParam String facultyShortName, @RequestParam String facultyFullName){
        return facultyService.create(new Faculty(0L, facultyShortName, facultyFullName));
    }

    @ApiOperation("Поиск по ID.")
    @GetMapping("/findBy/id")
    public Faculty getById(Long id){
        return facultyService.getById(id);
    }

    @ApiOperation("Поиск по частичному/полному имени.")
    @GetMapping("/findBy/partOfName")
    public List<Faculty> getById(String name){
        return facultyService.getFacultyByNameOrPartOfName(name);
    }

    @ApiOperation("Обновление информации. Айдишка тут обязательна")
    @PutMapping("/update")
    public boolean update(Faculty faculty){
        return facultyService.update(faculty);
    }

    @ApiOperation("Мягкое удаление. Тут нужна только айдишка, остальные поля можно пустыми")
    @DeleteMapping("/softDelete")
    public boolean softDelete(Faculty faculty){
        return facultyService.update(faculty);
    }
}
