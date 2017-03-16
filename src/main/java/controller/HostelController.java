package controller;

import bean.Application;
import bean.Hostel;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.HostelService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    @ResponseBody
    @RequestMapping(value = "/getApplication.do")
    public Map getApplication(HttpServletRequest request,
                              HttpServletResponse response)throws Exception {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Application> applications= hostelService.getApplications();
        result.put("applications",applications);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/pass.do")
    public Map pass(@RequestBody Map name,HttpServletRequest request,
                              HttpServletResponse response)throws Exception {
        String jname=(String) name.get("name");
        hostelService.pass(jname);
        Map<String, Object> result = new HashMap<String, Object>();
        List<Application> applications= hostelService.getApplications();
        result.put("applications",applications);
        return result;
    }
}
