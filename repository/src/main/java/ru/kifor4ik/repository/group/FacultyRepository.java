package ru.kifor4ik.repository.group;

import org.apache.ibatis.annotations.*;
import ru.kifor4ik.group.Faculty;

import java.util.List;

@Mapper
public interface FacultyRepository {

    @Insert("INSERT INTO faculty (shortName, fullName) VALUES (#{shortName},#{fullName}) RETURNING TRUE;")
    boolean create(Faculty item);

    @Select("SELECT id as id, shortName as shortName, fullName as fullName FROM faculty WHERE id = #{id} AND isDeleted = false")
    Faculty getById(Long id);

    @Update("UPDATE faculty SET, shortname= #{shortName}, fullname=#{fullName} WHERE id = #{id} RETURNING TRUE;")
    boolean update(Faculty newItem);

    @Update("UPDATE faculty SET isDeleted = true WHERE id = #{id} RETURNING TRUE;")
    boolean softDelete(Long id);

    /**
        @TODO
     Add kostil to service
     create string "%" + NAME + "%"
     */
    @Select("SELECT * FROM faculty WHERE fullName LIKE #{name}")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByNotFullOrFullName(@Param("name") String name);
}
