package service;

import bean.Hostel;
import bean.SearchHostel;
import bean.business;
import bean.user;

import java.text.ParseException;
import java.util.List;

/**
 * Created by LeeKane on 17/3/14.
 */
public interface HostelService {

    public Hostel getHostel(int id);
    public Hostel getHostelByName(String name);
    public List getApplications();
    public void inserHostel(String name ,String password,String city,String address,String info,String license);
    public void pass(String name);
    public void addPlan(int hostelId,String startTime,String overTime,int roomNum,double price) throws ParseException;
    public List getPlans(int hostelId);
    public List getRequiredHostels(String city,String startData) throws ParseException;
    public SearchHostel getRequiredHostelsById(int id);
    public void addBookBusniess(int userId,String userName,int hostelId,String hostelName,String startData,String overData,double price,double cost) throws ParseException;
    public List<business> getBusiness(int hosteId);
    public List<business> getBusinessById(int busId);
    public void checkin(int busId);
    public void checkout(int busId);
    public List<business> getAllBusiness();
}
