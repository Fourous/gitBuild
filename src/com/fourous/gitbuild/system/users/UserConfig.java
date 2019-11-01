package com.fourous.gitbuild.system.users;

import java.util.ArrayList;
import java.util.List;
/**
* @author fourous
* @date: 2019/11/1
* @description: 用户配置
*/
public class UserConfig {
    private List<User> users = new ArrayList<>();
    public List<User> getUsers(){
        return users;
    }
}
