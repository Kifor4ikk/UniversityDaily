package ru.kifor4ik.controller.group;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kifor4ik.group.Team;
import ru.kifor4ik.service.group.TeamService;

import java.util.List;

@RestController
@RequestMapping("api/v3/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @ApiOperation("Создание")
    @PostMapping("/new")
    public boolean create(@RequestParam Long idCourse,@RequestParam String shortName,@RequestParam String fullName){
        return teamService.create(new Team(0L, idCourse, shortName, fullName));
    }

    @ApiOperation("Очевидно что получение по ID")
    @GetMapping("findBy/id")
    public Team getById(@RequestParam Long id){
        return teamService.getById(id);
    }

    @ApiOperation("Выборка по частичному КОРОТКОМУ имени ФАКУЛЬТЕТА ")
    @GetMapping("findBy/facultyShortName")
    public List<Team> getByFacultyShortName(@RequestParam String facultyShortName){
        return teamService.getTeamByFacultyName(facultyShortName);
    }

    @ApiOperation("Выборка по частичному КОРОТКОМУ имени КОМАНДЫ")
    @GetMapping("findBy/teamShortName")
    public List<Team> getByTeamShortName(@RequestParam String name){
        return teamService.getTeamByNameOrPartOfName(name);
    }

    @DeleteMapping("/softDelete")
    public boolean softDelete(@RequestParam Long id){
        return teamService.softDelete(id);
    }


}
