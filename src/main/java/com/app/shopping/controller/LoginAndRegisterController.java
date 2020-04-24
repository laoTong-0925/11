package com.app.shopping.controller;

import com.app.shopping.service.LoginAndRegisterService;
import com.app.shopping.service.UserService;
import com.app.shopping.util.CodeUtil;
import com.app.shopping.util.Result;
import com.app.shopping.util.ResultCode;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
@Log4j2
public class LoginAndRegisterController {

    @Autowired
    private DefaultKaptcha captchaProducer;

    @Autowired
    private UserService userService;
    @Autowired
    LoginAndRegisterService loginAndRegisterService;


    @RequestMapping("/user-login")
    public ModelAndView userLogin(String account, String passWord, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView();
        if ("admin".equals(account))
        {
            mv.setViewName("redirect:commodityManage");
            return mv;
        }
        if (Strings.isBlank(account) || Strings.isBlank(passWord)) {
            //登录失败
            mv.addObject("loginF", "登录失败，请检查您的账户与密码！！！");
            mv.setViewName("login");
        } else {
            //
            boolean b = loginAndRegisterService.verifyAccount(account, CodeUtil.MD5toDo(passWord));
            if (b) {
                Cookie cookie = new Cookie("account", account);
                cookie.setMaxAge(60 * 60 * 24 * 7);//7天有效
                response.addCookie(cookie);
                mv.setViewName("index");
            } else {
                mv.addObject("loginF", "登录失败，请检查您的账户与密码！！！");
                mv.setViewName("login");
            }
        }
        return mv;
    }

    /**
     * 获取图片验证码 的 请求路径
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/defaultKaptcha")
    @ResponseBody
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = captchaProducer.createText();
            log.info("产生图片验证码:{}", createText);
            Cookie cookie = new Cookie("bs-verifyCode", createText);
            cookie.setMaxAge(60);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
//            session.setAttribute("verifyCode", createText);
//            String setex = jedisUtil.setex(session.toString(), createText, 120);
//            log.info(":{}", setex);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
     * 注册用户
     *
     * @param nkName
     * @param phone
     * @param passWord
     * @param vCode
     * @return
     */
    @RequestMapping("/register-user")
    @ResponseBody
    public Result register(String nkName, String phone, String passWord, String vCode, HttpServletResponse response) {
        log.info("register 参数nkName:{} \nphone:{} \npassword:{} \nvCode:{}", nkName, phone, passWord, vCode);
        if (Strings.isBlank(nkName) || Strings.isBlank(passWord) || Strings.isBlank(phone) || Strings.isBlank(vCode))
            return Result.validateFailed();
        Cookie cookie = new Cookie("account", nkName);
        cookie.setMaxAge(60 * 60 * 24 * 7);//7天有效
        response.addCookie(cookie);
        return userService.register(nkName, phone, passWord, vCode);
    }

    /**
     * 完善用户消息 name eMail
     *
     * @param name
     * @param eMail
     * @return
     */
    @RequestMapping("/register-perfect-info")
    @ResponseBody
    public Result registerPerfectInfo(String nkName, String name, String eMail) {
        log.info("register 参数nkName:{} name:{} eMail:{}", nkName, name, eMail);
        if (Strings.isBlank(name) || Strings.isBlank(eMail))
            return Result.validateFailed();
        return userService.perfectInfo(nkName, name, eMail);
    }


    /**
     * 验证图片验证码
     *
     * @param code
     * @param sessionCode
     * @return
     */
    @RequestMapping("/verify-code-img")
    @ResponseBody
    public Result<Object> imgVerifyController(String code, String sessionCode) {
        log.info("输入的code：{} 对应的的图片验证码：{}", code, sessionCode);
        if (Strings.isNotBlank(sessionCode) && Strings.isNotBlank(code) && code.equals(sessionCode)) {
            return Result.success(ResultCode.IMG_VERIFY_SUCCESS.getMessage());
        }
        return Result.failed(ResultCode.IMG_VERIFY_FAILED.getMessage());
    }


    /**
     * 查询 用户名是否可用
     *
     * @param nkName 用户名
     * @return Result
     */
    @RequestMapping("/verify-user-isExist")
    @ResponseBody
    public Result<Object> verifyUserIsExist(@RequestParam("nkName") String nkName) {
        log.info("verifyUserIsExist() 参数userName: {}", nkName);
        boolean b = userService.userIsExistByNkName(nkName);
        if (b) {
            return Result.response(null, ResultCode.USER_IS_EXIST.getMessage(), ResultCode.USER_IS_EXIST.getCode());
        }
        return Result.response(null, ResultCode.USER_AVAILABLE.getMessage(), ResultCode.USER_AVAILABLE.getCode());
    }

    /**
     * 查询 手机号码是否可用
     *
     * @param phone 手机号
     * @return Result
     */
    @RequestMapping("/verify-phone-isExist")
    @ResponseBody
    public Result<Object> verifyPhoneIsExist(@RequestParam("phone") String phone) {
        log.info("verifyPhoneIsExist() 参数userName: {}", phone);
        boolean b = userService.userIsExistByPhone(phone);
        if (b) {
            return Result.response(null, ResultCode.PHONE_IS_EXIST.getMessage(), ResultCode.PHONE_IS_EXIST.getCode());
        }
        return Result.response(null, ResultCode.PHONE_AVAILABLE.getMessage(), ResultCode.PHONE_AVAILABLE.getCode());
    }


}
