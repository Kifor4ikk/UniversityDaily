package ru.kifor4ik.repository.time;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kifor4ik.group.Team;
import ru.kifor4ik.time.TimeOfLesson;

import java.sql.Date;
import java.util.List;

@Mapper
public interface TimeOfLessonRepository {

    @Insert("INSERT INTO timeOfLesson (numberoflesson, timeStart, timeEnd,createDate, createdBy)" +
            " VALUES (#{time.numberOfLesson},#{time.timeStart},#{time.timeEnd}, #{createDate}, #{createBy}) RETURNING TRUE;")
    public boolean create(@Param("time") TimeOfLesson time,  @Param("createDate") Date createDate, @Param("createBy") String createBy);

    @Select("SELECT id as id, numberOfLesson as numberOfLesson, timeStart as timeStart, timeEnd as timeEnd FROM timeOfLesson WHERE id = #{id} AND isDeleted = false")
    public TimeOfLesson getById(Long id);

    @Update("UPDATE timeOfLesson SET isDeleted = true, deleteDate = #{deleteDate}, deletedBy = #{deletedBy} WHERE id = #{id} RETURNING TRUE;")
    public boolean softDelete(@Param("id") Long id, @Param("deleteDate") Date deleteDate, @Param("deletedBy") String deletedBy);

    @Select("SELECT id as id, numberOfLesson as numberOfLesson, timeStart as timeStart, timeEnd as timeEnd FROM timeOfLesson WHERE isDeleted = false")
    public List<TimeOfLesson> getAll();

    @Select("SELECT COUNT (*) FROM timeOfLesson WHERE numberOfLesson = #{numberOfLesson} and timeStart = #{timeStart} and timeEnd = #{timeEnd} and isDeleted = false")
    public Long doesExistSame(TimeOfLesson time);

    @Select("SELECT COUNT (*) FROM timeOfLesson WHERE id = #{id} and isDeleted = false")
    public Long doesItExists(Long id);
}
