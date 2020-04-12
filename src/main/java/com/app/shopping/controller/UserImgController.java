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


}