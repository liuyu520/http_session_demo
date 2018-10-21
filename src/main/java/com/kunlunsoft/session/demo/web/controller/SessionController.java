package com.kunlunsoft.session.demo.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO 请添加注释. <br />
 *
 * @author huangweii
 * @since 2018/10/20
 */
@RequestMapping(value = "/session", produces = "application/json;charset=UTF-8")
@RestController
public class SessionController {
    @ResponseBody
    @RequestMapping(value = "/query/json")
    public String json2(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(required = false) String demo) {
        String sessionId = request.getSession(true).getId();
        return sessionId;
    }
}
