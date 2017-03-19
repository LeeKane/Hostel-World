package controller;

/**
 * Created by mac on 16/7/16.
 */
import bean.Card;
import bean.SignUp;
import bean.business;
import bean.user;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.CardService;
import service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UsersController {
    @Autowired
    private userService userService;
    @Autowired
    private CardService cardService;

    @RequestMapping(value = "/showUser",method = RequestMethod.GET)
    public String showUser(@RequestParam("id") String id, ModelMap modelMap) {
        //1.调用BLL层的服务接口
        //user user = userService.getUser(id);
        //2.设置模型数据
        //modelMap.put("user",user);
        //3.返回逻辑视图名称
        return "showUser";
    }
    @RequestMapping(value = "/overview")
    public String overView() {

        return "index";

    }
    @RequestMapping(value = "/home")
    public ModelAndView home(HttpServletRequest request,
                             HttpServletResponse response)throws Exception {
        int cardId=(int)request.getSession().getAttribute("cardId");
        ModelMap result=new ModelMap();
        user user=userService.getUser(cardId);
        result.put("user",user);
        return new ModelAndView("home", "result", result);

    }
    @RequestMapping(value = "/login")
    public ModelAndView picture() {
        String result="";
        return new ModelAndView("login", "resut", result);
    }
    @RequestMapping(value = "/manager")
    public ModelAndView manager() {
        String result="";
        return new ModelAndView("manager", "resut", result);
    }
    @RequestMapping(value = "/activitied")
    public void activitied(@RequestBody Map<String, Object> map,HttpServletRequest request,
                                   HttpServletResponse response)throws Exception {
        double income=Double.parseDouble(map.get("income").toString());
        Card card = cardService.getCard((Integer) request.getSession().getAttribute("cardId"));
        cardService.cardActivited(card.getCardId());
        cardService.income(income,card.getCardId());
    }
    @RequestMapping(value = "/vip")
    public ModelAndView vip(HttpServletRequest request,
                            HttpServletResponse response)throws Exception {
        ModelMap result=new ModelMap();

        user user = userService.getUser((Integer) request.getSession().getAttribute("cardId"));
        Card card = cardService.getCard((Integer) request.getSession().getAttribute("cardId"));
        //List<StarRepo> starRepos = userInfoService.getStaredRepo(user.getLogin());
        result.put("type", "USER");
        result.put("user", user);
        result.put("card", card);
        return new ModelAndView("vip","result",result);
    }

    @RequestMapping(value = "/login.do")
    public ModelAndView login(String cardId,String password,HttpServletRequest request,
                             HttpServletResponse response)throws Exception {
        String result="";
        if(!(StringUtils.isNumeric(cardId))||cardId=="")
        {

            result="会员号或密码不正确";
            return new ModelAndView("login", "result", result);
        }

        user user1=userService.getUser(Integer.parseInt(cardId));
        if(user1!=null&&user1.getPassword().equals(password))
        {

            HttpSession session=request.getSession();
            session.setAttribute("cardId",Integer.parseInt(cardId));
            response.sendRedirect("/HostelWorld/home");
            return null;
        }
        else {

            result="会员号或密码不正确";
            return new ModelAndView("login", "result", result);
        }
    }
    @ResponseBody
    @RequestMapping(value = "/managerLogin.do")
    public Map managerLogin(@RequestBody user user,HttpServletRequest request,
                                     HttpServletResponse response)throws Exception {
        Map<String, Object> responseMap = new HashMap<String, Object>();
        String result="";
        if(user.getUsername()=="")
        {
            result="账号号或密码不正确";
            responseMap.put("result",result);
            return responseMap;
        }

        if(user.getUsername().equals("admin") && user.getPassword().equals("123456"))
        {

            HttpSession session=request.getSession();
            session.setAttribute("cardId",666666);
            responseMap.put("user",user);
            return responseMap;
        }
        else {
            result="账号或密码不正确";
            responseMap.put("result",result);
            return  responseMap;
        }
    }

    @RequestMapping(value = "/modifyName.do")
    public void modifyName(@RequestBody Map<String, String> map,HttpServletRequest request,
                           HttpServletResponse response)throws Exception {
        String username= map.get("username").toString();
        int cardId=(Integer) request.getSession().getAttribute("cardId");
        userService.modifyName(username,cardId);
    }

    @ResponseBody
    @RequestMapping(value = "/signup.do")
    public Map signup(@RequestBody user user) {
        Map<String, Object> result = new HashMap<String, Object>();
        userService.inserUser(user.getPassword(),user.getUsername());
        user usernew=userService.getUserByName(user.getUsername());
        result.put("username",usernew.getUsername());
        result.put("cardId",usernew.getCardId()+"");
        result.put("password",usernew.getPassword());
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/getUserStatistic.do")
    public Map getUserStatistic(@RequestBody Map<String, Object> map) {
        String userIdStr= (String) map.get("userId");
        int userId=Integer.parseInt(userIdStr);
        System.out.println(userId);
        List<business> business=userService.getBusiness(userId);
        Map<String, Object> result = new HashMap<String, Object>();
        List<business> books=getBook(business);
        List<business> checkins=getCheckin(business);
        List<business> checkouts=getCheckout(business);
        result.put("business",business);
        result.put("books",books);
        result.put("checkins",checkins);
        result.put("checkouts",checkouts);
        return result;
    }


    @RequestMapping(value = "/cancel")
    public void cancel(HttpServletRequest request,
                           HttpServletResponse response)throws Exception {

        int cardId=(Integer) request.getSession().getAttribute("cardId");
        HttpSession session=request.getSession();
        session.removeAttribute("cardId");
        cardService.cancel(cardId);
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