package ru.kifor4ik.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.kifor4ik.exception.*;
import ru.kifor4ik.group.Course;
import ru.kifor4ik.repository.group.CourseRepository;
import ru.kifor4ik.repository.group.FacultyRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final FacultyRepository facultyRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, FacultyRepository facultyRepository) {
        this.courseRepository = courseRepository;
        this.facultyRepository = facultyRepository;
    }

    public boolean create(Course course){
        try {

            if(facultyRepository.doesItExists(course.getIdFaculty()) == 0)
                throw new NotExistException("Faculty not exist", "Cant create Course cause current faculty not exist", "CC10");

            if(courseRepository.doesExistSame(course) != 0)
                throw new AlreadyExistException("AlreadyExists", "Same course already exists", "CC11");

            courseRepository.create(course, Date.valueOf(LocalDate.now()),"UNDEFINED");
            return true;
        } catch (Exception e){
            throw new CreateException("Create course exception", e.getLocalizedMessage(), "CC12");
        }
    }

    public Course getById(Long id){
        try{
            return courseRepository.getById(id);
        } catch (Exception e){
            throw new GetException("Get course exception", e.getLocalizedMessage(), "CG10");
        }
    }

    public boolean softDelete(Long id){
        try {
            courseRepository.softDelete(id,Date.valueOf(LocalDate.now()),"UNDEFINED");
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("SoftDelete course exception", e.getLocalizedMessage(), "CD10");
        }
    }

}
