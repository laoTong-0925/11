package com.app.shopping.service.impl;


import com.app.shopping.mapper.CommodityMapper;
import com.app.shopping.mapper.UserImgMapper;
import com.app.shopping.model.User;
import com.app.shopping.model.entity.UserImg;
import com.app.shopping.service.CommodityService;
import com.app.shopping.service.UserImgService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * (UserImg)表服务实现类
 *
 * @author makejava
 * @since 2020-04-12 17:37:06
 */
@Service
@Log4j2
public class UserImgServiceImpl implements UserImgService {
    @Autowired
    private UserImgMapper userImgMapper;
    /**
     * 上传地址
     */
    @Value("${files.upload.path}")
    private String filePath;
    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public boolean loadImg(MultipartFile file, long cid) {
        if (file.isEmpty()) {
            return false;
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
            int i = commodityMapper.updateImg(cid, filename);
            Thread.sleep(100);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserImg queryById(Long id) {
        return this.userImgMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<UserImg> queryAllByLimit(int offset, int limit) {
        return this.userImgMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userImg 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(UserImg userImg) {
        if (null != userImg)
            return userImgMapper.insert(userImg);
        return 0;
    }

    /**
     * 构建地址
     *
     * @param fileName
     * @return
     */
    @Override
    public String buPath(String fileName) {
        if (Strings.isBlank(fileName))
            return "";
        String path = "F:\\shopping-api\\src\\main\\resources\\static\\images";
        return path + "/" + fileName;
    }


    /**
     * 修改数据
     *
     * @param userImg 实例对象
     * @return 实例对象
     */
    @Override
    public UserImg update(UserImg userImg) {
        this.userImgMapper.update(userImg);
        return this.queryById(userImg.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userImgMapper.deleteById(id) > 0;
    }
}