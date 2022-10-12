package ru.kifor4ik.service.group;

import ru.kifor4ik.exception.CreateException;
import ru.kifor4ik.exception.GetException;
import ru.kifor4ik.exception.SoftDeleteException;
import ru.kifor4ik.exception.UpdateException;
import ru.kifor4ik.group.Faculty;
import ru.kifor4ik.group.Team;
import ru.kifor4ik.repository.group.TeamRepository;

import java.util.ArrayList;
import java.util.List;

public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public boolean create(Team team) {
        try {
            teamRepository.create(team);
            return true;
        } catch (Exception e){
            throw new CreateException("Create team exception", e.getLocalizedMessage(), "C000001");
        }
    }

    public List<Team> getTeamByNameOrPartOfName(String name){

        try{
            List<Long> tempListOfId = teamRepository.findIdByFacultyName("%" + name + "%");
            List<Team> teamList = new ArrayList<>();
            for(Long id : tempListOfId)
                teamList.add(teamRepository.get(id));

            return teamList;
        } catch (Exception e){
            throw new GetException("Get team exception", e.getLocalizedMessage(), "G000001");
        }
    }

    public List<Team> getTeamByFacultyName(String name){

        try{
            List<Long> tempListOfId = teamRepository.findIdByFacultyName("%" + name + "%");
            List<Team> teamList = new ArrayList<>();
            for(Long id : tempListOfId)
                teamList.add(teamRepository.get(id));
            return teamList;
        } catch (Exception e){
            throw new GetException("Get team exception", e.getLocalizedMessage(), "G000002");
        }
    }

    public boolean update(Team team){
        try{
            teamRepository.update(team);
            return true;
        } catch (Exception e){
            throw new UpdateException("Update team exception", e.getLocalizedMessage(), "U000001");
        }
    }

    public boolean softDelete(Long id){
        try {
            teamRepository.softDelete(id);
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("SoftDelete team exception", e.getLocalizedMessage(), "SD000001");
        }
    }
}
