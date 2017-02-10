package controller;

/**
 * Created by mac on 16/7/16.
 */
import bean.user;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


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
    @RequestMapping(value = "/login")
    public ModelAndView picture() {
        String result="";
        return new ModelAndView("login", "resut", result);
    }

    @RequestMapping(value = "/home")
    public ModelAndView home(user user,HttpServletRequest request,
                             HttpServletResponse response)throws Exception {
        String result="";
        user user1=userService.getUser(user.getCardId());
        if(user1!=null&&user1.getPassword().equals(user.getPassword()))
        {
            HttpSession session=request.getSession();
            session.setAttribute("cardId",user.getCardId());
            return new ModelAndView("home","resut",result);
        }
        else {
            result="会员号或密码不正确";
            return new ModelAndView("login", "result", result);
        }
    }
}