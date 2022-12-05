package ru.kifor4ik.repository.teacher;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kifor4ik.lesson.Lesson;
import ru.kifor4ik.teacher.Teacher;
import ru.kifor4ik.time.TimeOfLesson;

import java.sql.Date;
import java.util.List;

@Mapper
public interface TeacherRepository {

    @Insert("INSERT INTO teacher (firstName, secondName, thirdName, idFaculty, createDate, createdBy)" +
            " VALUES (#{teacher.firstName}, #{teacher.secondName}, #{teacher.thirdName}, #{teacher.faculty.id}, #{createDate}, #{createBy}) RETURNING TRUE;")
    public boolean create(@Param("teacher") Teacher teacher, @Param("createDate") Date createDate, @Param("createBy") String createBy);

    @Results({
            @Result(property = "faculty.id", column = "idFaculty"),
    })
    @Select("SELECT id, firstName, secondName, thirdName, idFaculty FROM teacher WHERE id = #{id} AND isDeleted = false")
    public Teacher getById(Long id);

    @Select("SELECT tc.id FROM teacher tc WHERE CONCAT(tc.firstname, ' ', tc.secondname, ' ', tc.thirdname) LIKE #{teacherData}")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> getTeacherIdByFIOOrPartOfFIO(@Param("teacherData") String name);

    @Select("SELECT tc.id FROM teacher tc WHERE idFaculty = #{idFaculty} and isDeleted = false")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> getTeacherByFacultyId(@Param("idFaculty") Long idFaculty);


    @Update("UPDATE teacher SET firstName = #{firstName}, secondName = #{secondName}, thirdName = #{thirdName}," +
            " idFaculty = #{faculty.id} WHERE id = #{id} AND isDeleted = false RETURNING TRUE;")
    public boolean update(Teacher teacher);

    @Update("UPDATE teacher SET isDeleted = true, deleteDate = #{deleteDate}, deletedBy = #{deletedBy} WHERE id = #{id} RETURNING TRUE;")
    public boolean softDelete(@Param("id") Long id,  @Param("deleteDate") Date deleteDate, @Param("deletedBy") String deletedBy);

    @Select("SELECT COUNT (*) FROM teacher WHERE firstName = #{firstName} and secondName = #{secondName}" +
            " and thirdName = #{thirdName} and isDeleted = false")
    public Long doesExistSame(Teacher teacher);

    @Select("SELECT COUNT (*) FROM teacher WHERE id = #{id} and isDeleted = false")
    public Long doesItExists(Long id);
}
