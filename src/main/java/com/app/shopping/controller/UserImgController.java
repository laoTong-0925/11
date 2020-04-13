package com.app.shopping.controller;

import org.apache.http.entity.ContentType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
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

    @RequestMapping("/update-img")
    public void updateImg(@RequestParam("file") MultipartFile file){
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *         <form action="http://localhost:8080/shopping/userImg/fileUpload" method="post" enctype="multipart/form-data">
     *             <p>选择文件: <input type="file" name="file"/></p>
     *             <p><input type="submit" value="提交"/></p>
     *         </form>
     *
     *         <form action="http://localhost:8080/shopping/userImg/fileUpload" method="post"  target="form_iframe" enctype="multipart/form-data">
     *             <input type="file"  name="fileName" />
     *             <input type="submit" name="" value="提交" />
     *         </form>
     *         <iframe id="form_iframe" name="form_iframe" style="display:none;"></iframe>
     * @param file
     * @return
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return "false";
        }
        String fileName = file.getOriginalFilename();
        int size = (int) file.getSize();
        System.out.println(fileName + "-->" + size);

        String path = "F://shopping" ;
        File dest = new File(path + "/" + fileName);
        if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest); //保存文件
            return "true";
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "false";
        }
    }


}