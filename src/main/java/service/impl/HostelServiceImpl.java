package service.impl;

import bean.Hostel;

import mapper.HostelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import service.HostelService;

import java.util.Calendar;
import java.util.List;

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
    public void inserHostel(String name ,String password,String city,String address,String info,String license) {

        hostelMapper.insertHostel(name,password,city,address,info,license);
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        hostelMapper.insertApplication(name,city,address,info,license,startDate);
    }

}
