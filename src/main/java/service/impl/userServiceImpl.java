package service.impl;

import bean.user;

import mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import service.userService;

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


}
