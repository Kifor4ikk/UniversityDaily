package ru.kifor4ik.repository.subgroup;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kifor4ik.lesson.Lesson;
import ru.kifor4ik.subgroup.SubGroup;

import java.sql.Date;

@Mapper
public interface SubGroupRepository {

    @Insert("INSERT INTO subGroup (numberofsubgroup, description, createDate, createdBy) " +
            "VALUES (#{subGroup.numberOfSubGroup}, #{subGroup.description}, #{createDate}, #{createBy}) RETURNING TRUE;")
    public boolean create(@Param("subGroup") SubGroup subGroup, @Param("createDate") Date createDate, @Param("createBy") String createBy);

    @Select("SELECT id as id, numberOfSubGroup as numberOfSubGroup, description as description FROM subGroup WHERE id = #{id} AND isDeleted = false")
    public SubGroup getById(Long id);

    @Update("UPDATE subGroup SET description = #{description} WHERE id = #{id} AND isDeleted = false RETURNING TRUE;")
    public boolean update(SubGroup newSubgroup);

    @Update("UPDATE subGroup SET isDeleted = true, deleteDate = #{deleteDate}, deletedBy = #{deletedBy} WHERE id = #{id} RETURNING TRUE;")
    public boolean softDelete(@Param("id") Long id, @Param("deleteDate") Date deleteDate, @Param("deletedBy") String deletedBy);

    @Select("SELECT COUNT (*) FROM subGroup WHERE numberOfSubGroup = #{numberOfSubGroup} and description = #{description} and isDeleted = false")
    public Long doesExistSame(SubGroup subGroup);

    @Select("SELECT COUNT (*) FROM subGroup WHERE id = #{id} and isDeleted = false")
    public Long doesItExists(Long id);
}
