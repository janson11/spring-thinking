package org.janson.think.in.spring.bean.scope.web.controller;

import org.janson.think.in.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Description: 首页 Spring Web MVC Controller
 * @Author: Janson
 * @Date: 2020/8/8 18:22
 **/
@Controller
public class IndexController {

    @Autowired
    private User user;

    // JSP EL 变量搜索路径<!--  page ->request ->session ->application(ServletContext)  -->
    // userObject ->渲染上下文
    // user 对象存在ServletContext 上下文名称 :scopedTarget.user=新生成Bean名称

    @GetMapping("/index.html")
    public String index(Model model) {
        model.addAttribute("userObject", user);
        return "index";
    }
}
