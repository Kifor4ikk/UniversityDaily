package ru.kifor4ik.repository.group;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kifor4ik.group.Faculty;

import java.sql.Date;
import java.util.List;

@Mapper
public interface FacultyRepository {

    @Insert("INSERT INTO faculty (shortName, fullName, createDate, createdBy) " +
            "VALUES (#{faculty.shortName}, #{faculty.fullName}, #{createDate}, #{createBy}) RETURNING TRUE;")
    boolean create(@Param("faculty") Faculty item, @Param("createDate") Date createDate,@Param("createBy") String createBy);

    @Select("SELECT id as id, shortName as shortName, fullName as fullName FROM faculty WHERE id = #{id} AND isDeleted = false")
    Faculty getById(Long id);

    @Update("UPDATE faculty SET shortname= #{shortName}, fullname=#{fullName} WHERE id = #{id} RETURNING TRUE;")
    boolean update(Faculty newItem);

    @Update("UPDATE faculty SET isDeleted = true, deleteDate = #{deleteDate}, deletedBy = #{deletedBy} WHERE id = #{id} RETURNING TRUE;")
    boolean softDelete(@Param("id") Long id,@Param("deleteDate") Date deleteDate, @Param("deletedBy")String deletedBy);

    @Select("SELECT * FROM faculty WHERE fullName LIKE #{name}")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByNotFullOrFullName(@Param("name") String name);

    @Select("SELECT COUNT (*) FROM Faculty WHERE shortName = #{shortName} and fullName = #{fullName} and isDeleted = false")
    public Long doesExistSame(Faculty item);

    @Select("SELECT COUNT (*) FROM faculty WHERE id = #{id} and isDeleted = false")
    public Long doesItExists(Long id);
}
