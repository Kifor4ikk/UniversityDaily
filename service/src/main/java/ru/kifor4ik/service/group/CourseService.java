package ru.kifor4ik.service.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import ru.kifor4ik.exception.CreateException;
import ru.kifor4ik.exception.GetException;
import ru.kifor4ik.exception.SoftDeleteException;
import ru.kifor4ik.group.Course;
import ru.kifor4ik.repository.group.CourseRepository;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public boolean create(Course course){
        try {
            courseRepository.create(course);
            return true;
        } catch (Exception e){
            throw new CreateException("Create course exception", e.getLocalizedMessage(), "C00001");
        }
    }

    public Course getById(Long id){
        try{
            return courseRepository.getById(id);
        } catch (Exception e){
            throw new GetException("Get course exception", e.getLocalizedMessage(), "G00001");
        }
    }

    public boolean softDelete(Long id){
        try {
            courseRepository.softDelete(id);
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("SoftDelete course exception", e.getLocalizedMessage(), "SD00001");
        }
    }

}
