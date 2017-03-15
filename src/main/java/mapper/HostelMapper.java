package mapper;

import bean.Hostel;
import bean.user;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by LeeKane on 17/3/14.
 */
public interface HostelMapper {
    @Select("SELECT * FROM hostel WHERE id = #{id}")
    Hostel getHostel(@Param("id")int id);
    @Select("SELECT * FROM hostel WHERE name = #{name}")
    Hostel getHostelByName(@Param("name")String name);
    @Insert("INSERT INTO hostel (name,password,city,address,info,license,application) values  (#{name},#{password},#{city},#{address},#{info},#{license},0)")
    void insertHostel(@Param("name")String name,@Param("password")String password,@Param("city")String city,@Param("address")String address,@Param("info")String info,@Param("license") String license);
    @Insert("INSERT INTO application values (#{name},#{city},#{address},#{info},#{license},0,#{date})")
    void insertApplication(@Param("name")String name,@Param("city")String city,@Param("address")String address,@Param("info")String info,@Param("license")String license,@Param("date")java.sql.Date  date);
}
