package com.adamly.xin6.controller.learn;

import com.adamly.xin6.dataobject.UserDO;
import com.adamly.xin6.error.BusinessException;
import com.adamly.xin6.service.UserService;
import com.adamly.xin6.service.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/3/14 14:44
 */

@Controller
@RequestMapping("/learnmvc")
public class MvcController {
    @Autowired
    private UserService userService = null;

    @RequestMapping("/userdetail")
    public ModelAndView userDetail(Integer id) {
        UserModel userModel = userService.getUserById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("learn/userdetail");
//        mv.addObject(userModel);
        mv.addObject("user",userModel);
        return mv;
    }

    @RequestMapping("/userdetail2")
    public ModelAndView userDetail2(Integer id) {
        UserModel userModel = userService.getUserById(id);
        ModelAndView mv = new ModelAndView();
        MappingJackson2JsonView jsonView= new MappingJackson2JsonView();
        mv.setView(jsonView);
        mv.addObject("user",userModel);
        return mv;
    }

    @RequestMapping("/userlist1")
    public ModelAndView userList1() throws BusinessException {
        List<UserDO> userDOList = userService.userList();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("learn/userlist");
        mv.addObject("users",userDOList);
        return mv;
    }

    @RequestMapping("/userlist2")
    @ResponseBody
    public List<UserDO> userList2(@RequestParam(value="name",required = true) String name) throws BusinessException {
        List<UserDO> userDOList = userService.getUserByName(name);
        return userDOList;
    }




}
