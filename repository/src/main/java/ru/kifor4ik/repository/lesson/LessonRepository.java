package ru.kifor4ik.repository.lesson;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.kifor4ik.lesson.Lesson;
import ru.kifor4ik.room.Room;
import ru.kifor4ik.teacher.Teacher;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;

@Mapper
public interface LessonRepository {

    @Insert(
        "INSERT INTO lesson(" +
                " fullname, shortname, classroomnumber, optionalclassroomnumber," +
                " optional, expandedinfo, idfaculty, idcourse, idteam, idteacher, idsubgroup," +
                " idtimeoflesson, isonlygreen, isonlywhite, dayofweek, createDate, createdBy)" +
                " VALUES (#{lesson.fullName},#{lesson.shortName},#{lesson.classRoomNumber}, #{lesson.optionalClassRoomNumber}," +
                " #{lesson.optional}, #{lesson.expandedInfo}, #{lesson.idFaculty}, #{lesson.idCourse}," +
                " #{lesson.idTeam}, #{lesson.idTeacher}, #{lesson.idSubGroup}, #{lesson.idTimeOfLesson}," +
                " #{lesson.isOnlyGreen}, #{lesson.isOnlyWhite}, #{lesson.dayOfWeek}, #{createDate}, #{createBy}) RETURNING TRUE"
    )
    public boolean create(@Param("lesson") Lesson lesson, @Param("createDate") Date createDate,@Param("createBy") String createBy);

    @Select("SELECT * from lesson WHERE id = #{id} AND isDeleted = false")
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

    @Select("SELECT * FROM lesson WHERE" +
            " idTeam = (SELECT id FROM team WHERE shortName = #{shortName}) AND " +
            " dayOfWeek = #{dayOfWeek} AND (" +
            " idSubGroup = (SELECT id FROM subGroup WHERE numberOfSubGroup = 0) or" +
            " idSubGroup = (SELECT id FROM subGroup WHERE numberOfSubGroup = #{groupNumber}))")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByTeamNameAndSubGroupAndDay(@Param("shortName") String teamShortName,
                                                        @Param("groupNumber") int subGroupNumber, @Param("dayOfWeek") String dayOfWeek);

    @Select("SELECT * FROM lesson WHERE idTeam = (SELECT id FROM team WHERE shortName = #{shortName}) " +
            "AND dayOfWeek = #{dayOfWeek} " +
            "AND (idSubGroup = (SELECT id FROM subGroup WHERE numberOfSubGroup = 0) " +
            "   or idSubGroup = (SELECT id FROM subGroup WHERE numberOfSubGroup = #{groupNumber})) " +
            "AND (isOnlyGreen = 'true' AND isOnlyWhite = true or isOnlyGreen = #{isOnlyGreen})")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByTeamNameAndSubGroupAndDayAndOnlyGreenOrGeneral(@Param("shortName") String teamShortName,
                                                        @Param("groupNumber") int subGroupNumber,
                                                        @Param("dayOfWeek") String dayOfWeek,
                                                        @Param("isOnlyGreen") boolean isOnlyGreen);

    @Select("SELECT * FROM lesson WHERE classRoomNumber = #{roomNumber} AND optionalClassRoomNumber = #{roomOptional} " +
            "AND dayOfWeek = #{dayOfWeek} " +
            "AND (isOnlyGreen = 'true' AND isOnlyWhite = true or isOnlyGreen = #{isOnlyGreen})")
    public List<Long> findIdByRoomParamAndDayAndOnlyGreenOrGeneral(@Param("roomNumber") Integer roomNumber,
                                                                  @Param("roomOptional") String roomOptional,
                                                                  @Param("dayOfWeek") String dayOfWeek,
                                                                  @Param("isOnlyGreen") boolean isOnlyGreen);

    @Select("SELECT DISTINCT classRoomNumber, optionalClassRoomNumber from LESSON")
    @Results(value = {@Result(property="roomNumber",column="classRoomNumber"),
            @Result(property="roomOptional",column="optionalClassRoomNumber")})
    public List<Room> findAllRooms();

    @Select("SELECT * FROM lesson WHERE idTeam = (SELECT id FROM team WHERE shortName = #{shortName})")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByTeamName(@Param("shortName") String teamShortName);


    @Select("SELECT * FROM lesson WHERE idTeacher = #{teacherId} " +
            "AND dayOfWeek = #{dayOfWeek} " +
            "AND (isOnlyGreen = 'true' AND isOnlyWhite = true or isOnlyGreen = #{isOnlyGreen})")
    @Results(value = {@Result(property="id",column="id")})
    public List<Long> findIdByTeacherIdAndDayAndOnlyGreenOrGeneral(@Param("teacherId") Long teacherId,
                                                                             @Param("dayOfWeek") String dayOfWeek,
                                                                             @Param("isOnlyGreen") boolean isOnlyGreen);
    @Update("UPDATE lesson SET fullName = #{fullName}, shortName = #{shortName}, classRoomNumber = #{classRoomNumber}," +
            " optionalClassRoomNumber = #{optionalClassRoomNumber}, expandedInfo = #{expandedInfo}," +
            " idFaculty = #{idFaculty},idCourse = #{idCourse}, idTeam = #{idTeam}, idTeacher = #{idTeacher}," +
            "idSubGroup = #{idSubGroup}, idTimeOfLesson = #{idTimeOfLesson}, isOnlyGreen = #{isOnlyGreen}, isOnlyWhite = #{isOnlyWhite}" +
            "dayOfWeek = #{dayOfWeek} RETURNING TRUE")
    public boolean update(Lesson lesson);

    @Update("UPDATE lesson SET isDeleted = true, deleteDate = #{deleteDate}, deletedBy = #{deletedBy} WHERE id = #{id} RETURNING TRUE;")
    public boolean softDelete(@Param("id") Long id, @Param("deleteDate")Date deleteDate, @Param("deletedBy") String deletedBy);

    @Select("SELECT COUNT (*) FROM lesson WHERE " +
            "fullname = #{fullName} and " +
            "shortName = #{shortName} and " +
            "classRoomNumber = #{classRoomNumber} and " +
            "optionalClassRoomNumber = #{optionalClassRoomNumber} and " +
            "optional = #{optional} and " +
            "expandedInfo = #{expandedInfo} and " +
            "idFaculty = #{idFaculty} and " +
            "idCourse = #{idCourse} and " +
            "idTeam = #{idTeam} and " +
            "idTeacher = #{idTeacher} and " +
            "idSubGroup = #{idSubGroup} and " +
            "idTimeOfLesson = #{idTimeOfLesson} and " +
            "isOnlyGreen = #{isOnlyGreen} and " +
            "isOnlyWhite = #{isOnlyWhite} and " +
            "dayOfWeek = #{dayOfWeek} and " +
            "isDeleted = false")
    public Long doesExistSame(Lesson lesson);

    @Select("SELECT COUNT (*) FROM lesson WHERE id = #{id} and isDeleted = false")
    public Long doesItExists(Long id);
}
