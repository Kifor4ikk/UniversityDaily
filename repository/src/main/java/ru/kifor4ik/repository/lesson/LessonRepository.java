package ru.kifor4ik.repository.lesson;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import ru.kifor4ik.lesson.Lesson;

import java.time.DayOfWeek;
import java.util.List;

@Component
@Mapper
public interface LessonRepository {

    @Insert(
        "INSERT INTO lesson(" +
                " fullname, shortname, classroomnumber, optionalclassroomnumber," +
                " optional, expandedinfo, idfaculty, idcourse, idteam, idteacher, idsubgroup," +
                "idtimeoflesson, isonlygreen, isonlywhite, dayofweek)" +
                " VALUES (#{fullName},#{shortName},#{classRoomNumber}, #{optionalClassRoomNumber}," +
                " #{optional}, #{expandedInfo}, #{idFaculty}, #{idCourse}," +
                " #{idTeam}, #{idTeacher}, #{idSubGroup}, #{idTimeOfLesson}," +
                " #{isOnlyGreen}, #{isOnlyWhite}, #{dayOfWeek}) RETURNING TRUE"
    )
    public boolean create(Lesson lesson);

    @Select("SELECT * from lesson WHERE id = #{id}  AND isDeleted = false")
    public Lesson getById(Long id);

    @Select("SELECT lesson.id FROM lesson WHERE idTeam = (SELECT id FROM team WHERE shortName = #{shortName}) AND DAYOFWEEK = #{dayOfWeek}")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByTeamAndDayOfWeek(@Param("shortName") String teamShortName, @Param("dayOfWeek") DayOfWeek dayOfWeek);

    @Select("SELECT lesson.id FROM lesson WHERE classRoomNumber = #{classRoomNumber} AND optionalClassRoomNumber = #{optional}")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByClassRoomNumber(@Param("classRoomNumber") int classRoomNumber,@Param("optional") String optional);

    @Select("SELECT lesson.id FROM lesson WHERE idTeacher = #{idTeacher} ")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByTeacherId(@Param("idTeacher") Long teacherId);

    @Select("SELECT lesson.id FROM lesson WHERE idTeacher = (SELECT teacher.id FROM teacher WHERE firstName = #{firstName} AND" +
            " secondName = #{secondName} AND thirdName = #{thirdName}) ")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByTeacherAllParams(@Param("firstName") String firstName, @Param("secondName") String secondName, @Param("thirdName") String thirdName);

    @Select("SELECT lesson.id FROM lesson WHERE idTeam = (SELECT id FROM team WHERE shortName = #{shortName}) AND isOnlyGreen = true")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByTeamNameOnlyGreenAndGeneral(@Param("shortName") String teamShortName);

    @Select("SELECT lesson.id FROM lesson WHERE idTeam = (SELECT id FROM team WHERE shortName = #{shortName}) AND isOnlyWhite = true")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByTeamNameOnlyWhiteAndGeneral(@Param("shortName") String teamShortName);

    @Select("SELECT * FROM lesson WHERE" +
            " idTeam = (SELECT id FROM team WHERE shortName = #{shortName}) AND" +
            " idSubGroup = (SELECT id FROM subGroup WHERE numberOfSubGroup = 0) or" +
            " idSubGroup = (SELECT id FROM subGroup WHERE numberOfSubGroup = #{groupNumber})")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByTeamNameAndSubGroup(@Param("shortName") String teamShortName, @Param("groupNumber") int subGroupNumber);

    @Select("SELECT * FROM lesson WHERE idTeam = (SELECT id FROM team WHERE shortName = #{shortName})")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByTeamName(@Param("shortName") String teamShortName);

    @Update("UPDATE lesson SET fullName = #{fullName}, shortName = #{shortName}, classRoomNumber = #{classRoomNumber}," +
            " optionalClassRoomNumber = #{optionalClassRoomNumber}, expandedInfo = #{expandedInfo}," +
            " idFaculty = #{idFaculty},idCourse = #{idCourse}, idTeam = #{idTeam}, idTeacher = #{idTeacher}," +
            "idSubGroup = #{idSubGroup}, idTimeOfLesson = #{idTimeOfLesson}, isOnlyGreen = #{isOnlyGreen}, isOnlyWhite = #{isOnlyWhite}" +
            "dayOfWeek = #{dayOfWeek} RETURNING TRUE")
    public boolean update(Lesson lesson);

    @Update("UPDATE lesson SET isDeleted = true WHERE id = #{id}")
    public void softDelete(Long id);

}
