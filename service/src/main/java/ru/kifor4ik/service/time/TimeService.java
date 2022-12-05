package ru.kifor4ik.service.time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kifor4ik.exception.AlreadyExistException;
import ru.kifor4ik.exception.CreateException;
import ru.kifor4ik.exception.GetException;
import ru.kifor4ik.exception.SoftDeleteException;
import ru.kifor4ik.repository.time.TimeOfLessonRepository;
import ru.kifor4ik.time.TimeOfLesson;

import java.sql.Date;
import java.sql.Time;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Timer;

@Service
public class TimeService {

    @Autowired
    private final TimeOfLessonRepository timeOfLessonRepository;

    public TimeService(TimeOfLessonRepository timeOfLessonRepository) {
        this.timeOfLessonRepository = timeOfLessonRepository;
    }

    public boolean create(TimeOfLesson time){
        try {
            if(!time.getTimeStart().before(time.getTimeEnd()))
                throw new DateTimeException("You cant start lesson in future and end it in past! This appear that you mistake time");
            if(timeOfLessonRepository.doesExistSame(time) != 0)
                throw new AlreadyExistException("AlreadyExists", "Same course already exists", "C000001");

            timeOfLessonRepository.create(time, Date.valueOf(LocalDate.now()),"UNDEFINED");
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
            timeOfLessonRepository.softDelete(id,Date.valueOf(LocalDate.now()),"UNDEFINED");
            return true;
        } catch (Exception e){
            throw new SoftDeleteException("Soft delete time of lesson exception", e.getLocalizedMessage(), "SD000001");
        }
    }

}
