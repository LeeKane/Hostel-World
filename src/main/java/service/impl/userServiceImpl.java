package service.impl;

import bean.user;

import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.userService;

import java.util.Calendar;
import java.util.List;

/**
 * Created by mac on 16/7/17.
 */
public class userServiceImpl implements userService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public List<user> getAlluser() {
        return null;
    }

    @Override
    public user getUser(int cardId) {
        user user=this.userMapper.getUser(cardId);
        if(user==null)
            return null;
        return user;
    }

    @Override
    public user getUserByName(String username) {
        user user=this.userMapper.getUserByName(username);
        if(user==null)
            return null;
        return user;
    }

    @Override
    public void inserUser(String password, String username) {
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        calendar.setTime(startDate);
        calendar.add(Calendar.YEAR, 1);
        java.sql.Date stopDate  = new java.sql.Date(calendar.getTime().getTime());
        userMapper.insertUser(password,username);
        userMapper.insertCard(stopDate);
    }

    @Override
    public void modifyName(String username, int cardId) {
        userMapper.updateUsername(username,cardId);
    }

}
