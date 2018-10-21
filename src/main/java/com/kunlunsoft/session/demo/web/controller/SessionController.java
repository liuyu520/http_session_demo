package com.kunlunsoft.session.demo.web.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * TODO 请添加注释. <br />
 *
 * @author huangweii
 * @since 2018/10/20
 */
@RequestMapping(value = "/session", produces = "application/json;charset=UTF-8")
@RestController
public class SessionController {
    public static final String CRLF_LINUX = "\n";
    public static final String CRLF_WINDOW = "\r\n";
    public static final String CHARSET_UTF = "UTF-8";
    @ResponseBody
    @RequestMapping(value = "/query/json")
    public String json2(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(required = false) String demo) {
        String sessionId = request.getSession(true).getId();
        return sessionId;
    }

    @ResponseBody
    @RequestMapping(value = "/query2/json")
    public String json3(Model model, HttpServletRequest request, HttpServletResponse response
            , @RequestParam(required = false) String demo) {
        String sessionId = request.getSession(true).getId();
        Cookie emailCook = getSelectedCookie(request.getCookies(), "JSESSIONID", null);
        emailCook.setMaxAge(10000000);//单位是秒,所以大概115 天
        emailCook.setPath("/");//设置cookie时,设置path为根路径
        response.addCookie(emailCook);
        return sessionId + CRLF_LINUX + emailCook.getValue();
    }

    public static Cookie getSelectedCookie(Cookie[] cookies, String emaiCookieName, String cookieValue) {
        System.out.println("cookie 不为空");
        Cookie selectedCookie = null;
        for (Cookie c : cookies) {
            if (emaiCookieName.equals(c.getName())) {
                selectedCookie = c;
                break;
            }
        }
        if (null == selectedCookie) {
            return null;
        }
        System.out.println("找到了 " + emaiCookieName);
        System.out.println("cookie的值为 " + selectedCookie.getValue());
        if (StringUtils.isNoneBlank(cookieValue)) {
            try {
                selectedCookie.setValue(URLEncoder.encode(cookieValue, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return selectedCookie;
    }
}
