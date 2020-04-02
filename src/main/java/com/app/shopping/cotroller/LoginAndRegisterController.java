package com.app.shopping.cotroller;

import com.app.shopping.cache.JedisUtil;
import com.app.shopping.mapper.UserMapper;
import com.app.shopping.model.User;
import com.app.shopping.service.UserService;
import com.app.shopping.util.Result;
import com.app.shopping.util.ResultCode;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private UserService userService;

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
     * 验证图片验证码
     *
     * @param code
     * @param sessionCode
     * @return
     */
    @RequestMapping("/verify-code-img")
    public Result<Object> imgVerifyController(String code, String sessionCode) {
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

    /**
     * 查询 用户名是否可用
     *
     * @param nkName 用户名
     * @return Result
     */
    @RequestMapping("/verify-user-isExist")
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
    public Result<Object> verifyPhoneIsExist(@RequestParam("phone") String phone) {
        log.info("verifyPhoneIsExist() 参数userName: {}", phone);
        boolean b = userService.userIsExistByPhone(phone);
        if (b) {
            return Result.response(null, ResultCode.PHONE_IS_EXIST.getMessage(), ResultCode.PHONE_IS_EXIST.getCode());
        }
        return Result.response(null, ResultCode.PHONE_AVAILABLE.getMessage(), ResultCode.PHONE_AVAILABLE.getCode());
    }



}
