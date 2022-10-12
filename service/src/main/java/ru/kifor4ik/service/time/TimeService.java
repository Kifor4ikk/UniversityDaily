package ru.kifor4ik.service.time;

import ru.kifor4ik.exception.CreateException;
import ru.kifor4ik.exception.GetException;
import ru.kifor4ik.exception.SoftDeleteException;
import ru.kifor4ik.repository.time.TimeOfLessonRepository;
import ru.kifor4ik.time.TimeOfLesson;

import java.util.List;
import java.util.Timer;

public class TimeService {

    private final TimeOfLessonRepository timeOfLessonRepository;

    public TimeService(TimeOfLessonRepository timeOfLessonRepository) {
        this.timeOfLessonRepository = timeOfLessonRepository;
    }

    public boolean create(TimeOfLesson time){
        try {
            timeOfLessonRepository.create(time);
            return true;
        } catch (Exception e){
            throw new CreateException("Create time of lesson exception", e.getLocalizedMessage(), "C000001");
        }
    }

    public TimeOfLesson getById(Long id){
        try{
            return timeOfLessonRepository.getById(id);
        } catch (Exception e){
            throw new GetException("Get time of lesson exception", e.getLocalizedMessage(), "G000001");
        }
    }

    public List<TimeOfLesson> getAll(){
        try{
            return timeOfLessonRepository.getAll();
        } catch (Exception e){
            throw new GetException("Get time of lesson exception", e.getLocalizedMessage(), "G000002");
        }
    }

    public boolean softDelete(Long id){
        try {
            timeOfLessonRepository.softDelete(id);
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("Soft delete time of lesson exception", e.getLocalizedMessage(), "SD000001");
        }
    }

}
