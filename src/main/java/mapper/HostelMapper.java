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
    @Select("SELECT plan.id,startData,overData,roomNum,price,hostel.id as hostelId,name,city,address,info,license FROM plan,hostel where hostel.city=#{city} and hostel.id=plan.hostelId and startData <= #{startData}")
    List<SearchHostel> getRequiredHostel(@Param("city") String city, @Param("startData") String startData);
    @Select("SELECT plan.id,startData,overData,roomNum,price,hostel.id as hostelId,name,city,address,info,license FROM plan,hostel where plan.id=#{id} and hostel.id=plan.hostelId")
    SearchHostel getRequiredHostelById(@Param("id") int id);
    @Insert("INSERT INTO business (userId,userName,hostelId,hostelName,startData,overData,price,cost,book,checkin,checkout) values (#{userId},#{userName},#{hostelId},#{hostelName},#{startData},#{overData},#{price},#{cost},1,0,0)")
    void addBookBusniess(@Param("userId") int userId,@Param("userName") String userName,@Param("hostelId")  int hostelId,@Param("hostelName") String hostelName,@Param("startData") String startData,@Param("overData") String overData,@Param("price") double price,@Param("cost") double cost);
    @Select("SELECT * FROM business WHERE hostelId = #{hostelId}")
    List<business> getBusiness(@Param("hostelId")int hostelId);
    @Select("SELECT * FROM business WHERE id = #{busId}")
    List<business> getBusinessById(@Param("busId")int busId);
    @Update("update business set checkin=1,book=0 where  id = #{busId}")
    void checkin(@Param("busId")int busId);
    @Update("update business set checkin=0,checkout=1 where  id = #{busId}")
    void checkout(@Param("busId")int busId);
    @Update("update business set checkout=0 where  id = #{busId}")
    void checkover(@Param("busId")int busId);
    @Select("SELECT * FROM business ")
    List<business> getAllBusiness();
}

