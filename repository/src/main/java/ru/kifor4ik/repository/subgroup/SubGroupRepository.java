package ru.kifor4ik.repository.subgroup;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kifor4ik.subgroup.SubGroup;

@Mapper
public interface SubGroupRepository {

    @Insert("INSERT INTO subGroup (numberofsubgroup, description) VALUES (#{numberOfSubGroup}, #{description}) RETURNING TRUE;")
    public boolean create(SubGroup subGroup);

    @Select("SELECT id as id, numberOfSubGroup as numberOfSubGroup, description as description FROM subGroup WHERE id = #{id} AND isDeleted = false")
    public SubGroup getById(Long id);

    @Update("UPDATE subGroup SET description = #{description} WHERE id = #{id} AND isDeleted = false RETURNING TRUE;")
    public boolean update(SubGroup newSubgroup);

    @Update("UPDATE subGroup SET isDeleted = true WHERE id = #{id} RETURNING TRUE;")
    public boolean softDelete(Long id);
}
