package com.app.shopping;

import com.app.shopping.cache.JedisUtil;
import com.app.shopping.util.Result;
import com.app.shopping.util.ResultCode;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@RestController
@Log4j2
public class LoginAndRegisterController {

    @Autowired
    private DefaultKaptcha captchaProducer;

    @Autowired
    private JedisUtil jedisUtil;

    /**
     * 获取图片验证码 的 请求路径
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        byte[] captchaChallengeAsJpeg;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = captchaProducer.createText();
            log.info("产生图片验证码:{}", createText);
            HttpSession session = httpServletRequest.getSession();
            Cookie cookie = new Cookie("bs-verifyCode", createText);
            cookie.setMaxAge(60);
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


//    /**
//     * 验证图片验证码的方法
//     *
//     * @param httpServletRequest
//     * @param httpServletResponse
//     * @return
//     */
//    @RequestMapping("/imgvrifyControllerDefaultKaptcha")
//    public ModelAndView imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
//        ModelAndView andView = new ModelAndView();
//        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
//        String parameter = httpServletRequest.getParameter("vrifyCode");
//        System.out.println("Session  vrifyCode " + captchaId + " form vrifyCode " + parameter);
//
//        if (!captchaId.equals(parameter)) {
//            andView.addObject("info", "错误的验证码");
//            andView.setViewName("loginResult.html");
//        } else {
//            andView.addObject("info", "登录成功");
//            andView.setViewName("hello.html");
//        }
//        return andView;
//    }

    /**
     * 验证图片验证码
     *
     * @param code
     * @param sessionCode
     * @return
     */
    @RequestMapping("/verify-code-img")
    public Result imgVerifyController(String code, String sessionCode) {
        log.info("输入的code：{} 对应的的图片验证码：{}", code, sessionCode);
        if (Strings.isNotBlank(sessionCode) && Strings.isNotBlank(code) && code.equals(sessionCode)) {
            return Result.success(ResultCode.IMG_VERIFY_SUCCESS.getMessage());
        }
        return Result.failed(ResultCode.IMG_VERIFY_FAILED.getMessage());
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login.html");
        return mv;
    }

}
