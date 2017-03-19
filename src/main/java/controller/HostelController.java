package controller;

import bean.*;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import service.HostelService;
import service.userService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by LeeKane on 17/3/3.
 */
@Controller
public class HostelController {
    @Autowired
    private HostelService hostelService;
    @Autowired
    private service.userService userService;

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
        Collections.reverse(applications);
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
        Collections.reverse(applications);
        result.put("applications",applications);
        return result;
    }
    @RequestMapping(value = "/hostelLogin.do")
    public ModelAndView login(String id, String password, HttpServletRequest request,
                              HttpServletResponse response)throws Exception {
        String result="";
        if(!(StringUtils.isNumeric(id))||id=="")
        {

            result="账号号或密码不正确";
            return new ModelAndView("hostelLogin", "result", result);
        }

        Hostel hostel1=hostelService.getHostel(Integer.parseInt(id));
        if(hostel1!=null&&hostel1.getPassword().equals(password)&&hostel1.getApplication()==1)
        {

            HttpSession session=request.getSession();
            session.setAttribute("cardId",Integer.parseInt(id));
            response.sendRedirect("/HostelWorld/hostelHome");
            return null;
        }
        else if(hostel1!=null&&hostel1.getApplication()==0)
        {
            result="申请还未审批通过,请耐心等待";
            return new ModelAndView("hostelLogin", "result", result);
        }
        else {

            result="账号号或密码不正确";
            return new ModelAndView("hostelLogin", "result", result);
        }


    }
    @RequestMapping(value = "/hostelHome")
    public ModelAndView hostelHome(HttpServletRequest request,
                             HttpServletResponse response)throws Exception {
        int cardId=(int)request.getSession().getAttribute("cardId");
        Hostel hostel=hostelService.getHostel(cardId);
        return new ModelAndView("hostelHome", "hostel", hostel);

    }
    @ResponseBody
    @RequestMapping(value = "/addPlan.do")
    public Map addPlan(@RequestBody Plan plan, HttpServletRequest request,
                       HttpServletResponse response)throws Exception {
        hostelService.addPlan(plan.getHostelId(),plan.getStartData(),plan.getOverData(),plan.getRoomNum(),plan.getPrice());
        Map<String, Object> result = new HashMap<String, Object>();
        List<Plan> plans= hostelService.getPlans(plan.getHostelId());
        Collections.reverse(plans);
        result.put("plans",plans);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/getPlans.do")
    public Map getPlans(@RequestBody Map hostel,HttpServletRequest request,
                              HttpServletResponse response)throws Exception {
        String hostelIdStr= (String) hostel.get("hostelId");
        int hostelId=Integer.parseInt(hostelIdStr);
        Map<String, Object> result = new HashMap<String, Object>();
        List<Plan> plans= hostelService.getPlans(hostelId);
        Collections.reverse(plans);
        result.put("plans",plans);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getRequiredHostel.do")
    public Map getRequiredHostel(@RequestBody Map hostel,HttpServletRequest request,
                        HttpServletResponse response)throws Exception {
        String city= (String) hostel.get("city");
        String startData=(String) hostel.get("startData");
        System.out.println(startData+"ss"+city);
        List hostels=hostelService.getRequiredHostels(city,startData);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("hostels",hostels);
        return result;
    }
    @RequestMapping(value = "/hostelInfo")
    public ModelAndView hostelInfo(@RequestParam("id") String id, HttpServletRequest request,
                                   HttpServletResponse response)throws Exception {
        System.out.println(id);
        Map<String, Object> result = new HashMap<String, Object>();
        HttpSession session=request.getSession();
        int userId= (int) session.getAttribute("cardId");
        user user=userService.getUser(userId);
        result.put("user",user);
        SearchHostel hostel=hostelService.getRequiredHostelsById(Integer.parseInt(id));
        result.put("hostel",hostel);
        return new ModelAndView("hostelInfo", "result", result);
    }


    @ResponseBody
    @RequestMapping(value = "/addBookBusniess.do")
    public Map addBookBusniess(@RequestBody Map map,HttpServletRequest request,
                                 HttpServletResponse response)throws Exception {
        String hostelIdStr= (String) map.get("hostelId");
        int hostelId=Integer.parseInt(hostelIdStr);
        String userIdStr= (String) map.get("userId");
        int userId=Integer.parseInt(userIdStr);
        String hostelName= (String) map.get("hostelName");
        String userName=(String) map.get("userName");
        String startData=(String) map.get("startData");
        String overData=(String) map.get("overData");
        String priceStr=(String) map.get("price");
        int cost =(int) map.get("cost");
        double price = Double.parseDouble(priceStr);
        hostelService.addBookBusniess(userId,userName,hostelId,hostelName,startData,overData,price,cost);
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getHostelUserStatistic.do")
    public Map getHostelUserStatistic(@RequestBody Map<String, Object> map) {
        String userIdStr= (String) map.get("hostelId");
        int hostelId=Integer.parseInt(userIdStr);
        System.out.println(hostelId);
        List<business> business=hostelService.getBusiness(hostelId);
        List<business> books=getBook(business);
        List<business> checkins=getCheckin(business);
        List<business> checkouts=getCheckout(business);
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("business",business);
        result.put("books",books);
        result.put("checkins",checkins);
        result.put("checkouts",checkouts);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/checkin.do")
    public Map checkin(@RequestBody Map<String, Object> map) {
        String userIdStr= (String) map.get("hostelId");
        int hostelId=Integer.parseInt(userIdStr);
        String busIdStr= (String) map.get("busId");
        int busId=Integer.parseInt(busIdStr);
        System.out.println(busId);
        hostelService.checkin(busId);
        Map<String, Object> result = new HashMap<String, Object>();
        List<business> business=hostelService.getBusiness(hostelId);
        List<business> books=getBook(business);
        List<business> checkins=getCheckin(business);
        List<business> checkouts=getCheckout(business);
        result.put("business",business);
        result.put("books",books);
        result.put("checkins",checkins);
        result.put("checkouts",checkouts);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/checkout.do")
    public Map checkout(@RequestBody Map<String, Object> map) {
        String userIdStr= (String) map.get("hostelId");
        int hostelId=Integer.parseInt(userIdStr);
        String busIdStr= (String) map.get("busId");
        int busId=Integer.parseInt(busIdStr);
        System.out.println(busId);
        hostelService.checkout(busId);
        Map<String, Object> result = new HashMap<String, Object>();
        List<business> business=hostelService.getBusiness(hostelId);
        List<business> books=getBook(business);
        List<business> checkins=getCheckin(business);
        List<business> checkouts=getCheckout(business);
        result.put("business",business);
        result.put("books",books);
        result.put("checkins",checkins);
        result.put("checkouts",checkouts);
        return result;
    }
    @ResponseBody
    @RequestMapping(value = "/getAllBusiness.do")
    public Map getAllBusiness(@RequestBody Map<String, Object> map) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<business> business=hostelService.getAllBusiness();
        List<business> books=getBook(business);
        List<business> checkins=getCheckin(business);
        List<business> checkouts=getCheckout(business);
        result.put("business",business);
        result.put("books",books);
        result.put("checkins",checkins);
        result.put("checkouts",checkouts);
        return result;
    }

    public List<business> getBook(List<business> business)
    {
        List result=new ArrayList<>();
        for(int i=0;i<business.size();i++)
        {
            if(business.get(i).getBook()==1)
                result.add(business.get(i));
        }
        return result;
    }
    public List<business> getCheckin(List<business> business)
    {
        List result=new ArrayList<>();
        for(int i=0;i<business.size();i++)
        {
            if(business.get(i).getCheckin()==1)
                result.add(business.get(i));
        }
        return result;
    }
    public List<business> getCheckout(List<business> business)
    {
        List result=new ArrayList<>();
        for(int i=0;i<business.size();i++)
        {
            if(business.get(i).getCheckout()==1)
                result.add(business.get(i));
        }
        return result;
    }
}
