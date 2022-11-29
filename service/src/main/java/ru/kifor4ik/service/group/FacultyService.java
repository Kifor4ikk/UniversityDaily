package ru.kifor4ik.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.exception.CreateException;
import ru.kifor4ik.exception.GetException;
import ru.kifor4ik.exception.SoftDeleteException;
import ru.kifor4ik.exception.UpdateException;
import ru.kifor4ik.group.Faculty;
import ru.kifor4ik.repository.group.FacultyRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyService {

    @Autowired
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public boolean create(Faculty faculty) {
        try {
            facultyRepository.create(faculty);
            return true;
        } catch (Exception e){
            throw new CreateException("Create faculty exception", e.getLocalizedMessage(), "C000001");
        }
    }

    public Faculty getById(Long id){
        try{
            return facultyRepository.getById(id);
        } catch (Exception e){
            throw new UpdateException("Get faculty exception", e.getLocalizedMessage(), "G000001");
        }
    }

    public List<Faculty> getFacultyByNameOrPartOfName(String name){

        try{
            List<Long> tempListOfId = facultyRepository.findIdByNotFullOrFullName("%" + name + "%");
            List<Faculty> facultyList = new ArrayList<>();
            for(Long id : tempListOfId)
                facultyList.add(facultyRepository.getById(id));

            return facultyList;
        } catch (Exception e){
            throw new GetException("Get faculty exception", e.getLocalizedMessage(), "G000002");
        }
    }

    public boolean update(Faculty newFaculty){
        try{
            facultyRepository.update(newFaculty);
            return true;
        } catch (Exception e){
            throw new UpdateException("Update faculty exception", e.getLocalizedMessage(), "U000001");
        }
    }

    public boolean softDelete(Long id){
        try {
            facultyRepository.softDelete(id);
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("SoftDelete faculty exception", e.getLocalizedMessage(), "SD000001");
        }
    }
}
