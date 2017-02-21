package mapper;

import bean.user;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * Created by mac on 16/7/18.
 */
public interface UserMapper {
    @Select("SELECT * FROM user WHERE cardId = #{cardId}")
    user getUser(@Param("cardId")int cardId);
    @Select("SELECT * FROM user WHERE username = #{username}")
    user getUserByName(@Param("username")String username);
    @Insert("INSERT INTO user (password,username) values (#{password},#{username})")
    void insertUser(@Param("password")String password,@Param("username")String username);
    @Insert("INSERT INTO card (activatied,stopDate,accountId) values (0,#{stopDate},1)")
    void insertCard(@Param("stopDate")java.sql.Date stopDate);
    @Update("update user set username=#{username} where  cardId = #{cardId}")
    void updateUsername(@Param("username")String username,@Param("cardId")int cardId);
}
