package ru.kifor4ik.repository.group;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kifor4ik.group.Team;

import java.util.List;

@Mapper
public interface TeamRepository {

    @Insert("INSERT INTO team (idcourse, shortname, fullname) VALUES (#{idCourse}, #{shortName}, #{fullName}) RETURNING TRUE;")
    public boolean create(Team team);

    @Select("SELECT id as id, idCourse as idCourse, shortName as shortName, fullName as fullName FROM team WHERE id = #{id} AND isDeleted = false")
    public Team getById(Long id);

    @Update("UPDATE team SET shortName = #{shortName}, fullName = #{fullName} RETURNING TRUE;")
    public boolean update(Team newTeam);

    @Update("UPDATE team SET isDeleted = true WHERE id = #{id} RETURNING TRUE;")
    public boolean softDelete(Long id);

    /**
     @TODO
     Add kostil to service
     create string "%" + NAME + "%"
     */
    @Select("SELECT * FROM team WHERE fullName LIKE #{name}")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByFullNameOrNotFullName(@Param("name") String name);

    @Select("SELECT team.id FROM faculty INNER JOIN course ON course.idfaculty = faculty.id" +
            " INNER JOIN team ON team.idcourse = course.id WHERE faculty.shortname LIKE #{facultyName}")
    @Results(value = {@Result(property ="id", column ="id")})
    public List<Long> findIdByFacultyName(@Param("facultyName") String facultyName);

}
