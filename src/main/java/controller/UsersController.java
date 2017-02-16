package controller;

/**
 * Created by mac on 16/7/16.
 */
import bean.SignUp;
import bean.user;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UsersController {
    @Autowired
    private userService userService;

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
        user user=userService.getUser(cardId);
        return new ModelAndView("home", "user", user);

    }
    @RequestMapping(value = "/login")
    public ModelAndView picture() {
        String result="";
        return new ModelAndView("login", "resut", result);
    }

    @RequestMapping(value = "/login.do")
    public ModelAndView login(String cardId,String password,HttpServletRequest request,
                             HttpServletResponse response)throws Exception {
        String result="";
        if(!(StringUtils.isNumeric(cardId)))
        {
            System.out.print("s");
            result="会员号或密码不正确";
            return new ModelAndView("login", "result", result);
        }

        user user1=userService.getUser(Integer.parseInt(cardId));
        if(user1!=null&&user1.getPassword().equals(password))
        {
            System.out.print("ss");
            HttpSession session=request.getSession();
            session.setAttribute("cardId",Integer.parseInt(cardId));
            response.sendRedirect("/HostelWorld/home");
            return null;
        }
        else {
            System.out.print("sss");
            result="会员号或密码不正确";
            return new ModelAndView("login", "result", result);
        }
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
}