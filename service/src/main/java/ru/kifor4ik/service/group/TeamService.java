package ru.kifor4ik.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.exception.*;
import ru.kifor4ik.group.Faculty;
import ru.kifor4ik.group.Team;
import ru.kifor4ik.repository.group.CourseRepository;
import ru.kifor4ik.repository.group.TeamRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final CourseRepository courseRepository;


    @Autowired
    public TeamService(TeamRepository teamRepository, CourseRepository courseRepository) {
        this.teamRepository = teamRepository;
        this.courseRepository = courseRepository;
    }

    public boolean create(Team team) {
        try {

            if(courseRepository.doesItExists(team.getCourseId()) == 0)
                throw new NotExistException("Course not exist", "Cant create Team cause current COURSE not exist", "TC10");
            if(teamRepository.doesExistSame(team) != 0)
                throw new AlreadyExistException("AlreadyExists", "Same course already exists", "TC11");

            teamRepository.create(team, Date.valueOf(LocalDate.now()),"UNDEFINED");
            return true;
        } catch (Exception e) {
            throw new CreateException("Create team exception", e.getLocalizedMessage(), "TC12");
        }
    }

    public Team getById(Long id) {
        try {
            return teamRepository.getById(id);
        } catch (Exception e) {
            throw new GetException("Get team exception", e.getLocalizedMessage(), "TG10");
        }
    }

    public List<Team> getTeamByNameOrPartOfName(String name) {

        try {
            List<Long> tempListOfId = teamRepository.findIdByFullNameOrNotFullName("%" + name + "%");
            List<Team> teamList = new ArrayList<>();
            for (Long id : tempListOfId)
                teamList.add(teamRepository.getById(id));

            return teamList;
        } catch (Exception e) {
            throw new GetException("Get team exception", e.getLocalizedMessage(), "TG11");
        }
    }

    public List<Team> getTeamByFacultyName(String name) {

        try {
            List<Long> tempListOfId = teamRepository.findIdByFacultyName("%" + name + "%");
            List<Team> teamList = new ArrayList<>();
            for (Long id : tempListOfId)
                teamList.add(teamRepository.getById(id));
            return teamList;
        } catch (Exception e) {
            throw new GetException("Get team exception", e.getLocalizedMessage(), "TG12");
        }
    }

    public boolean update(Team team) {
        try {
            teamRepository.update(team);
            return true;
        } catch (Exception e) {
            throw new UpdateException("Update team exception", e.getLocalizedMessage(), "TU10");
        }
    }

    public boolean softDelete(Long id) {
        try {
            teamRepository.softDelete(id,Date.valueOf(LocalDate.now()),"UNDEFINED");
            return true;
        } catch (Exception e) {
            throw new SoftDeleteException("SoftDelete team exception", e.getLocalizedMessage(), "TD10");
        }
    }
}
