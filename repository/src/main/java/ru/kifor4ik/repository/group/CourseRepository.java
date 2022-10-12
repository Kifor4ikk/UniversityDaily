package ru.kifor4ik.repository.group;

import org.apache.ibatis.annotations.*;
import ru.kifor4ik.group.Course;

import java.util.List;

@Mapper
public interface CourseRepository {

    @Insert("INSERT INTO course (idfaculty, coursenumber) VALUES (#{idFaculty},#{courseNumber}) RETURNING TRUE;")
    boolean create(Course course);

    @Select("SELECT id as id, idFaculty as idFaculty, courseNumber as courseNumber FROM course WHERE ID = #{id}  AND isDeleted = false")
    Course getById(Long id);

    @Update("UPDATE course SET isDeleted = true WHERE id = #{id} RETURNING TRUE;")
    boolean softDelete(Long id);

    @Select("SELECT id FROM course WHERE idFaculty = (SELECT id FROM faculty WHERE faculty.shortName = #{facultyName})")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByFacultyName(@Param("facultyName") String facultyName);
}
