package service;

import bean.Hostel;
import bean.user;

import java.util.List;

/**
 * Created by LeeKane on 17/3/14.
 */
public interface HostelService {

    public Hostel getHostel(int id);
    public Hostel getHostelByName(String name);
    public void inserHostel(String name ,String password,String city,String address,String info,String license);
}
