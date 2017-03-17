package mapper;

import bean.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LeeKane on 17/3/14.
 */
public interface HostelMapper {
    @Select("SELECT * FROM hostel WHERE id = #{id}")
    Hostel getHostel(@Param("id")int id);
    @Select("SELECT * FROM hostel WHERE name = #{name}")
    Hostel getHostelByName(@Param("name")String name);
    @Select("SELECT * FROM application")
    List<Application> getApplications();
    @Insert("INSERT INTO hostel (name,password,city,address,info,license,application) values  (#{name},#{password},#{city},#{address},#{info},#{license},0)")
    void insertHostel(@Param("name")String name,@Param("password")String password,@Param("city")String city,@Param("address")String address,@Param("info")String info,@Param("license") String license);
    @Insert("INSERT INTO application values (#{name},#{city},#{address},#{info},#{license},0,#{date})")
    void insertApplication(@Param("name")String name,@Param("city")String city,@Param("address")String address,@Param("info")String info,@Param("license")String license,@Param("date")java.sql.Date  date);
    @Update("update application set application.read=1 where  name = #{name}")
    void pass(@Param("name") String name);
    @Update("update hostel set hostel.application=1 where  name = #{name}")
    void passApplication(@Param("name") String name);
    @Insert("INSERT INTO plan  (hostelId,startData,overData,roomNum,price) values (#{hostelId},#{startData},#{overData},#{roomNum},#{price})")
    void addPlan(@Param("hostelId") int hostelId,@Param("startData") java.sql.Date startData,@Param("overData") java.sql.Date overData,@Param("roomNum") int roomNum,@Param("price") double price);
    @Select("SELECT * FROM plan WHERE hostelId = #{hostelId}")
    List<Plan> getPlans(@Param("hostelId") int hostelId);
    @Select("SELECT startData,overData,roomNum,price,hostel.id,name,city,address,info,license FROM plan,hostel where hostel.city=#{city} and hostel.id=plan.hostelId and startData <= #{startData}")
    List<SearchHostel> getRequiredHostel(@Param("city") String city, @Param("startData") String startData);
}

