package ru.kifor4ik.repository.time;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import ru.kifor4ik.time.TimeOfLesson;

import java.util.List;

@Mapper
public interface TimeOfLessonRepository {

    @Insert("INSERT INTO timeOfLesson numberoflesson, timeStart, timeEnd VALUES (#{numberOfLesson},#{timeStart},#{timeEnd}) RETURNING TRUE;")
    public boolean create(TimeOfLesson time);

    @Select("SELECT id as id, numberOfLesson as numberOfLesson, timeStart as timeStart, timeEnd as timeEnd WHERE id = #{id} AND isDeleted = false")
    public TimeOfLesson getById(Long id);

    @Update("UPDATE timeOfLesson SET isDeleted = true WHERE id = #{id}")
    public boolean softDelete(Long id);

    @Select("SELECT id as id, numberOfLesson as numberOfLesson, timeStart as timeStart, timeEnd as timeEnd WHERE isDeleted = false")
    public List<TimeOfLesson> getAll();
}
