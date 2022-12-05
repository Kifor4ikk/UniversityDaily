package ru.kifor4ik.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.exception.*;
import ru.kifor4ik.group.Faculty;
import ru.kifor4ik.repository.group.CourseRepository;
import ru.kifor4ik.repository.group.FacultyRepository;
import ru.kifor4ik.repository.teacher.TeacherRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FacultyService {


    private final FacultyRepository facultyRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.facultyRepository = facultyRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    public boolean create(Faculty faculty) {
        try {
            if(facultyRepository.doesExistSame(faculty) != 0)
                throw new AlreadyExistException("AlreadyExists", "Same faculty already exists", "FC10");

            facultyRepository.create(faculty, Date.valueOf(LocalDate.now()),"UNDEFINED");
            return true;
        } catch (Exception e){
            throw new CreateException("Create faculty exception", e.getLocalizedMessage(), "FC11");
        }
    }

    public Faculty getById(Long id){
        try{

            return facultyRepository.getById(id);
        } catch (Exception e){
            throw new UpdateException("Get faculty exception", e.getLocalizedMessage(), "FG10");
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
            throw new GetException("Get faculty exception", e.getLocalizedMessage(), "FG11");
        }
    }

    public boolean update(Faculty newFaculty){
        try{
            facultyRepository.update(newFaculty);
            return true;
        } catch (Exception e){
            throw new UpdateException("Update faculty exception", e.getLocalizedMessage(), "FU10");
        }
    }

    public boolean softDelete(Long id){
        try {
            facultyRepository.softDelete(id,Date.valueOf(LocalDate.now()),"UNDEFINED");
            //Delete all teachers who included to this faculty
            for(Long teacherId : teacherRepository.getTeacherByFacultyId(id))
                teacherRepository.softDelete(teacherId,Date.valueOf(LocalDate.now()),"UNDEFINED");
            //Delete all courses
            for(Long courseId : courseRepository.findIdByFacultyId(id))
                courseRepository.softDelete(courseId,Date.valueOf(LocalDate.now()),"UNDEFINED");

            return true;
        } catch (Exception e){
            throw new SoftDeleteException("SoftDelete faculty exception", e.getLocalizedMessage(), "FD10");
        }
    }
}
