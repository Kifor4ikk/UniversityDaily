package ru.kifor4ik.service.teacher;

import org.springframework.stereotype.Service;
import ru.kifor4ik.exception.CreateException;
import ru.kifor4ik.exception.GetException;
import ru.kifor4ik.exception.SoftDeleteException;
import ru.kifor4ik.group.Faculty;
import ru.kifor4ik.group.Team;
import ru.kifor4ik.repository.subgroup.SubGroupRepository;
import ru.kifor4ik.repository.teacher.TeacherRepository;
import ru.kifor4ik.service.group.FacultyService;
import ru.kifor4ik.subgroup.SubGroup;
import ru.kifor4ik.teacher.Teacher;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final FacultyService facultyService;

    public TeacherService(TeacherRepository teacherRepository, FacultyService facultyService) {
        this.teacherRepository = teacherRepository;
        this.facultyService = facultyService;
    }

    public boolean create(Teacher teacher){
        try {
            teacherRepository.create(teacher);
            return true;
        } catch (Exception e){
            throw new CreateException("Create teacher exception", e.getLocalizedMessage(), "C00001");
        }
    }

    public Teacher getById(Long id){
        try{
            Teacher teacher = teacherRepository.getById(id);
            teacher.setFaculty(facultyService.getById(teacher.getFaculty().getId()));
            return teacher;
        } catch (Exception e){
            throw new GetException("Get teacher exception", e.getLocalizedMessage(), "G00001");
        }
    }

    public List<Teacher> getByPartOfFIO(String name){
        try{
            List<Long> tempListOfId = teacherRepository.getTeacherIdByFIOOrPartOfFIO("%" + name + "%");
            List<Teacher> teacherList = new ArrayList<>();
            for(Long id : tempListOfId)
                teacherList.add(teacherRepository.getById(id));
            return teacherList;
        } catch (Exception e){
            throw new GetException("Get teacher exception", e.getLocalizedMessage(), "G000002");
        }
    }

    public boolean update(Teacher teacher){
        try {
            teacherRepository.update(teacher);
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("Update teacher exception", e.getLocalizedMessage(), "U00001");
        }
    }

    public boolean softDelete(Long id){
        try {
            teacherRepository.softDelete(id);
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("SoftDelete teacher exception", e.getLocalizedMessage(), "SD00001");
        }
    }
}
