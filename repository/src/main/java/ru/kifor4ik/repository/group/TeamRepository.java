package ru.kifor4ik.repository.group;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kifor4ik.group.Faculty;
import ru.kifor4ik.group.Team;

import java.sql.Date;
import java.util.List;

@Mapper
public interface TeamRepository {

    @Insert("INSERT INTO team (idCourse, shortname, fullname, createDate, createdBy)" +
            " VALUES (#{team.courseId}, #{team.shortName}, #{team.fullName}, #{createDate}, #{createBy}) RETURNING TRUE;")
    public boolean create(@Param("team") Team team, @Param("createDate") Date createDate,@Param("createBy") String createBy);

    @Select("SELECT id as id, idCourse as courseId, shortName as shortName, fullName as fullName FROM team WHERE id = #{id} AND isDeleted = false")
    public Team getById(Long id);

    @Update("UPDATE team SET shortName = #{shortName}, fullName = #{fullName} RETURNING TRUE;")
    public boolean update(Team newTeam);

    @Update("UPDATE team SET isDeleted = true, deleteDate = #{deleteDate}, deletedBy = #{deletedBy} WHERE id = #{id} RETURNING TRUE;")
    public boolean softDelete(@Param("id") Long id, @Param("deleteDate") Date deleteDate,@Param("deletedBy") String deletedBy);

    @Select("SELECT * FROM team WHERE fullName LIKE #{name}")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByFullNameOrNotFullName(@Param("name") String name);

    @Select("SELECT team.id FROM faculty INNER JOIN course ON course.idfaculty = faculty.id" +
            " INNER JOIN team ON team.idcourse = course.id WHERE faculty.shortname LIKE #{facultyName}")
    @Results(value = {@Result(property ="id", column ="id")})
    public List<Long> findIdByFacultyName(@Param("facultyName") String facultyName);

    @Select("SELECT COUNT (*) FROM team WHERE idCourse = #{courseId} and shortName = #{shortName} and fullName = #{fullName} and isDeleted = false")
    public Long doesExistSame(Team item);

    @Select("SELECT COUNT (*) FROM team WHERE id = #{id} and isDeleted = false")
    public Long doesItExists(Long id);
}
