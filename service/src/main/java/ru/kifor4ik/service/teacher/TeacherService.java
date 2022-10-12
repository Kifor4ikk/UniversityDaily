package ru.kifor4ik.service.teacher;

import ru.kifor4ik.exception.CreateException;
import ru.kifor4ik.exception.GetException;
import ru.kifor4ik.exception.SoftDeleteException;
import ru.kifor4ik.group.Team;
import ru.kifor4ik.repository.subgroup.SubGroupRepository;
import ru.kifor4ik.repository.teacher.TeacherRepository;
import ru.kifor4ik.subgroup.SubGroup;
import ru.kifor4ik.teacher.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
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
            return teacherRepository.getById(id);
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

    public boolean softDelete(Long id){
        try {
            teacherRepository.softDelete(id);
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("SoftDelete teacher exception", e.getLocalizedMessage(), "SD00001");
        }
    }
}
