package ru.kifor4ik.repository.teacher;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import ru.kifor4ik.teacher.Teacher;

import java.util.List;

@Mapper
@Component
public interface TeacherRepository {

    @Insert("INSERT INTO teacher (firstName, secondName, thirdName, idFaculty) VALUES (#{firstName}, #{secondName}, #{thirdName}, #{faculty.id}) RETURNING TRUE;")
    public boolean create(Teacher teacher);

    @Results({
            @Result(property = "faculty.id", column = "idFaculty"),
    })
    @Select("SELECT id, firstName, secondName, thirdName, idFaculty FROM teacher WHERE id = #{id} AND isDeleted = false")
    public Teacher getById(Long id);

    @Select("SELECT tc.id FROM teacher tc WHERE CONCAT(tc.firstname, ' ', tc.secondname, ' ', tc.thirdname) LIKE #{teacherData}")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> getTeacherIdByFIOOrPartOfFIO(@Param("teacherData") String name);

    @Update("UPDATE teacher SET firstName = #{firstName}, secondName = #{secondName}, thirdName = #{thirdName}," +
            " idFaculty = #{faculty.id} WHERE id = #{id} AND isDeleted = false RETURNING TRUE;")
    public boolean update();

    @Update("UPDATE teacher SET isDeleted = true WHERE id = #{id}")
    public boolean softDelete(Long id);
}
