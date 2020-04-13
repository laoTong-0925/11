package com.app.shopping.controller;

import com.app.shopping.model.entity.UserImg;
import com.app.shopping.service.UserImgService;
import com.app.shopping.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * (UserImg)表控制层
 *
 * @author makejava
 * @since 2020-04-12 17:37:07
 */
@RestController
@RequestMapping("/userImg")
public class UserImgController {

    @Autowired
    UserImgService userImgService;

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
     * @param file
     * @return
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public Result fileUpload(@RequestParam("file") MultipartFile file,@RequestParam("nkName") String nkName) {
        if (file.isEmpty()) {
            return Result.failed();
        }
        String fileName = file.getOriginalFilename();
        String type = fileName.substring(fileName.indexOf("."));
        if (!(type.equals("bmp") || type.equals("jpg") || type.equals("png") || type.equals("gif")))
            return Result.failed("目前支持bmp,jpg,png,gif格式");
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "F://shopping";
        File dest = new File(path + "/" + fileName);
        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            UserImg userImg = new UserImg();
//            userImg.setUserId();
//            userImgService.insert()
            return Result.success();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Result.failed();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return Result.failed();
        }
    }


}