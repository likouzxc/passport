package com.likou.passport.web.controller.manage;

import com.likou.core.annotation.ParamAnnotation;
import com.likou.core.web.AbstractController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jiangli on 16/10/26.
 */
@Controller
@RequestMapping(value = "manage")
public class ManageController  extends AbstractController {

    @RequestMapping(value = "list" , method = RequestMethod.GET)
    @ParamAnnotation(values = {"model","param"})
    public String list(Model model){




        return "manage/list";
    }
}
