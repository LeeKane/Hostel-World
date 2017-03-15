package controller;

import bean.Hostel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.HostelService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LeeKane on 17/3/3.
 */
@Controller
public class HostelController {
    @Autowired
    private HostelService hostelService;

    @RequestMapping(value = "/hostelLogin")
    public String hostelLogin() {

        return "hostelLogin";

    }
    @ResponseBody
    @RequestMapping(value = "/hostelSignup.do")
    public Map hostelSignup(@RequestBody Hostel hostel) {
        Map<String, Object> result = new HashMap<String, Object>();
        hostelService.inserHostel(hostel.getName(),hostel.getPassword(),hostel.getCity(),hostel.getAddress(),hostel.getInfo(),hostel.getLicense());
        Hostel hostelnew=hostelService.getHostelByName(hostel.getName());
        result.put("username",hostelnew.getName());
        result.put("id",hostelnew.getId()+"");
        result.put("password",hostelnew.getPassword());
        return result;
    }
}
