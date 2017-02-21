package service;

import bean.user;

import java.util.List;

/**
 * Created by mac on 16/7/17.
 */
public interface userService {
    public List<user> getAlluser();
    public user getUser(int cardId);
    public user getUserByName(String username);
    public void inserUser(String password ,String username);
    public void modifyName(String username,int cardId);
}

