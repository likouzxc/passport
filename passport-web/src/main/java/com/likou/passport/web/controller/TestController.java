package com.likou.passport.web.controller;

import com.likou.core.annotation.ParamAnnotation;
import com.likou.core.web.AbstractController;
import com.likou.passport.service.self.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/")
public class TestController extends AbstractController {

    @Autowired
    TestService testService;

    @RequestMapping(value = "index" , method = RequestMethod.GET)
    @ParamAnnotation(values = {"model","param"})
    public String hello(Model model,String param) throws Exception{

        try {
            testService.saveTestData("1", "2");

        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
        model.addAttribute("param",param);

        return "hello";
    }
}
