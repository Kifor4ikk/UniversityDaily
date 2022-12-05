package ru.kifor4ik.service.teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.exception.*;
import ru.kifor4ik.group.Faculty;
import ru.kifor4ik.group.Team;
import ru.kifor4ik.repository.subgroup.SubGroupRepository;
import ru.kifor4ik.repository.teacher.TeacherRepository;
import ru.kifor4ik.service.group.FacultyService;
import ru.kifor4ik.subgroup.SubGroup;
import ru.kifor4ik.teacher.Teacher;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final FacultyService facultyService;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository, FacultyService facultyService) {
        this.teacherRepository = teacherRepository;
        this.facultyService = facultyService;
    }

    public boolean create(Teacher teacher){
        try {
            if(teacherRepository.doesExistSame(teacher) != 0)
                throw new AlreadyExistException("AlreadyExists", "Same teacher already exists", "TC10");
            if(facultyService.getById(teacher.getFaculty().getId()) == null)
                throw new NotExistException("Faculty not exist", "Cant create Teacher cause FACULTY not exist", "TC12");

            teacherRepository.create(teacher, Date.valueOf(LocalDate.now()),"UNDEFINED");
            return true;
        } catch (Exception e){
            throw new CreateException("Create teacher exception", e.getLocalizedMessage(), "TC11");
        }
    }

    public Teacher getById(Long id){
        try{
            Teacher teacher = teacherRepository.getById(id);
            teacher.setFaculty(facultyService.getById(teacher.getFaculty().getId()));
            return teacher;
        } catch (Exception e){
            throw new GetException("Get teacher exception", e.getLocalizedMessage(), "TG10");
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
            throw new GetException("Get teacher exception", e.getLocalizedMessage(), "TG11");
        }
    }

    public boolean update(Teacher teacher){
        try {
            teacherRepository.update(teacher);
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("Update teacher exception", e.getLocalizedMessage(), "TU10");
        }
    }

    public boolean softDelete(Long id){
        try {
            teacherRepository.softDelete(id,Date.valueOf(LocalDate.now()),"UNDEFINED");
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("SoftDelete teacher exception", e.getLocalizedMessage(), "TD10");
        }
    }

    public boolean doesItExists(Long id){
        return teacherRepository.doesItExists(id) != 0;
    }
}
