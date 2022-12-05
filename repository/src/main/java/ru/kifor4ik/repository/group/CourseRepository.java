package ru.kifor4ik.repository.group;

import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kifor4ik.group.Course;
import ru.kifor4ik.group.Faculty;

import java.sql.Date;
import java.util.List;

@Mapper
public interface CourseRepository {

    @Insert("INSERT INTO course (idfaculty, coursenumber, createDate, createdBy)" +
            " VALUES ( (SELECT faculty.id FROM faculty WHERE faculty.id = #{course.idFaculty} and isDeleted = false)," +
            " #{course.courseNumber}, #{createDate}, #{createBy}) RETURNING TRUE;")
    boolean create(@Param("course") Course course, @Param("createDate") Date createDate, @Param("createBy") String createBy);

    @Select("SELECT id as id, idFaculty as idFaculty, courseNumber as courseNumber FROM course WHERE ID = #{id}  AND isDeleted = false")
    Course getById(Long id);

    @Update("UPDATE course SET isDeleted = true, deleteDate = #{deleteDate}, deletedBy = #{deletedBy} WHERE id = #{id} RETURNING TRUE;")
    boolean softDelete(@Param("id") Long id,@Param("deleteDate") Date deleteDate,@Param("deletedBy") String deletedBy);

    @Select("SELECT id FROM course WHERE idFaculty = (SELECT id FROM faculty WHERE faculty.shortName = #{facultyName})")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByFacultyName(@Param("facultyName") String facultyName);

    @Select("SELECT id FROM course WHERE idFaculty = facultyId and isDeleted = false")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByFacultyId(@Param("facultyId") Long facultyId);

    @Select("SELECT COUNT (*) FROM course WHERE idFaculty = #{idFaculty} and courseNumber = #{courseNumber} and isDeleted = false")
    public Long doesExistSame(Course item);


    @Select("SELECT COUNT (*) FROM course WHERE id = #{id} and isDeleted = false")
    public Long doesItExists(Long id);
}
