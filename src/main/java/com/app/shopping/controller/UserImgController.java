package com.app.shopping.controller;

import com.app.shopping.mapper.UserImgMapper;
import com.app.shopping.mapper.UserMapper;
import com.app.shopping.model.User;
import com.app.shopping.model.entity.UserImg;
import com.app.shopping.service.UserImgService;
import com.app.shopping.service.UserService;
import com.app.shopping.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;

/**
 * (UserImg)表控制层
 *
 * @author makejava
 * @since 2020-04-12 17:37:07
 */
@RestController
@RequestMapping()
public class UserImgController {

    private final ResourceLoader resourceLoader;

    @Autowired
    public UserImgController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Autowired
    private UserImgService userImgService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserImgMapper  userImgMapper;

    /**上传地址*/
    @Value("${files.upload.path}")
    private String filePath;

    @RequestMapping("/update-img")
    public void updateImg(@RequestParam("file") MultipartFile file) {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <form action="http://localhost:8080/shopping/userImg/fileUpload" method="post" enctype="multipart/form-data">
     * <p>选择文件: <input type="file" name="file"/></p>
     * <p><input type="submit" value="提交"/></p>
     * </form>
     *
     * <form action="http://localhost:8080/shopping/userImg/fileUpload" method="post"  target="form_iframe" enctype="multipart/form-data">
     * <input type="file"  name="fileName" />
     * <input type="submit" name="" value="提交" />
     * </form>
     * <iframe id="form_iframe" name="form_iframe" style="display:none;"></iframe>
     *
     *
     * <form action="../upload" method="post" enctype="multipart/form-data">
     *     <input type="file" name="file" accept="image/*">
     *     <br>
     *     <input type="submit" value="上传" class="btn btn-success">
     * </form>
     * [[${filename}]]
     * <br>
     * <img th:src="@{${filename}}" alt="图片">
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    public ModelAndView fileUpload(@RequestParam("file") MultipartFile file, @RequestParam("nkName") String nkName, ModelAndView mav) {
        if (file.isEmpty()) {
            return mav.addObject("请选择头像");
        }
        if (Strings.isBlank(nkName)){
            mav.setViewName("login");
        }
        // 获取上传文件名
        String filename = file.getOriginalFilename();
        // 定义上传文件保存路径
        String path = filePath;
        // 新建文件
        File filepath = new File(path, filename);
        // 判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            // 写入文件
            file.transferTo(new File(path + File.separator + filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 将src路径发送至html页面
        ModelAndView mv = new ModelAndView();
        User user = userService.selectByNkname(nkName);
        int i = userImgMapper.updateImgByUserId(filename, user.getId());
        //存库
//        mv.addObject("user",user);
//        mv.addObject("userImg",file.getOriginalFilename());
        mv.setViewName("redirect:userInfo?nkName="+user.getNkName());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mv;
    }

    /**
     * 显示单张图片
     * @return
     */
    @RequestMapping("/show")
    public ResponseEntity showPhotos(String fileName){

        try {
            // 由于是读取本机的文件，file是一定要加上的， path是在application配置文件中的路径
            Resource resource = resourceLoader.getResource("file:" + filePath + fileName);
            return ResponseEntity.ok(resourceLoader.getResource("file:" + filePath + fileName));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping("/get-user-img")
    public Result getUserImg(String nkName){
        if (StringUtils.isBlank(nkName))
            return Result.failed();
        User user = userService.selectByNkname(nkName);
        if (null == user)
            return Result.failed();
        UserImg userImg = userImgMapper.queryById(user.getId());
        if (null != userImg){
            return Result.success(userImg.getUserImg());
        }
        return Result.failed();
    }


}