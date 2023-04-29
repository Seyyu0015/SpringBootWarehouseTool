package com.example.controller;

import com.example.common.CommonResult;
import com.example.service.LoginService;
import com.example.service.RequirementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Seyyu
 * @version 1.0
 */

@Tag(name = "申请", description = "RequirementController")
@Controller
public class RequirementController {

    @Autowired
    private RequirementService requirementService;

    @GetMapping("/requirement")
    @ResponseBody
    public CommonResult requirement(String userid) {
        return requirementService.selectRequirement(userid);
    }

    @GetMapping("/addrequirement")
    @ResponseBody
    public CommonResult requirement(String type,
                                    String itemname,
                                    String warehouse,
                                    int number,
                                    String userid,
                                    String newwarehouse) {
        return requirementService.addRequirement(type, itemname, warehouse, number, userid, newwarehouse);
    }

    @GetMapping("/setrequirement")
    @ResponseBody
    public CommonResult requirement(int id,String state) {
        return requirementService.setRequirement(id, state);
    }

}
