package service.impl;

import bean.Application;
import bean.Hostel;

import bean.Plan;
import bean.SearchHostel;
import mapper.HostelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import service.HostelService;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by LeeKane on 17/3/14.
 */
public class HostelServiceImpl implements HostelService {
    @Autowired
    private HostelMapper hostelMapper;

    @Override
    public Hostel getHostel(int id) {
        return hostelMapper.getHostel(id);
    }

    @Override
    public  Hostel getHostelByName(String name)
    {
        return  hostelMapper.getHostelByName(name);
    }

    @Override
    public List<Application> getApplications() {
        return hostelMapper.getApplications();
    }

    @Override
    public void inserHostel(String name ,String password,String city,String address,String info,String license) {

        hostelMapper.insertHostel(name,password,city,address,info,license);
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        hostelMapper.insertApplication(name,city,address,info,license,startDate);
    }

    @Override
    public void pass(String name) {
        System.out.println(name+"sss");
        hostelMapper.pass(name);
        hostelMapper.passApplication(name);
    }

    @Override
    public void addPlan(int hostelId, String startTime, String overTime, int roomNum, double price) throws ParseException {
        Date start = new Date();
        Date over = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        start = sdf.parse(startTime);
        over = sdf.parse(overTime);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        startTime = sdf2.format(start);
        overTime =sdf2.format(over);
        java.util.Date  date  =  new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
        java.sql.Date  startDate  =  new java.sql.Date(date.getTime());
        java.util.Date  date1  =  new SimpleDateFormat("yyyy-MM-dd").parse(overTime);
        java.sql.Date  overDate  =  new java.sql.Date(date1.getTime());
        hostelMapper.addPlan(hostelId,startDate,overDate,roomNum,price);
    }

    @Override
    public List<Plan> getPlans(int hostelId) {
       return hostelMapper.getPlans(hostelId);
    }

    @Override
    public List getRequiredHostels(String city, String startData) throws ParseException {
        Date start = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        start = sdf.parse(startData);
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
        startData = sdf2.format(start);
        System.out.println(startData+"ss"+city);
        List hostels=hostelMapper.getRequiredHostel(city,startData);
        System.out.println(hostels.get(0));
        return  hostels;

    }

    @Override
    public SearchHostel getRequiredHostelsById(int id) {
        return hostelMapper.getRequiredHostelById(id);
    }

}
