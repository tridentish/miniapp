package com.itwu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itwu.entity.Administrator;
import com.itwu.entity.BackGround;
import com.itwu.entity.History;
import com.itwu.entity.R;
import com.itwu.service.BackGroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/backgrounds")
public class BackGroundController {
    @Autowired
    private BackGroundService backGroundService;

    @GetMapping("{current}/{size}")
    public R getAll(@PathVariable Integer current, @PathVariable Integer size, BackGround backGround){

        IPage<BackGround> page =backGroundService.getPage(current,size,backGround);
        return new R(true,page);
    }

    //上传文章文件
    @PostMapping()
    public R uploadPicture(BackGround backGround, @RequestParam(value = "file") MultipartFile file) throws IllegalStateException, IOException {

        //获取文件后缀
        String imgFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        // 创建文件名称
        String fileName = UUID.randomUUID() + imgFormat;
        // 获取到文件的路径信息
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        String filePath = servletRequestAttributes.getRequest().getServletContext().getRealPath("/") + fileName;
        // 打印保存路径
        System.out.println(filePath);
        //路径保存到数据库中
        backGround.setPath(filePath);
        backGroundService.save(backGround);
        // 创建文件
        File saveFile = new File(filePath);
        // 文件保存
        file.transferTo(saveFile);
        // 返回信息
        return new R(true,backGround,"上传成功");
    }

    //批量删除
    @DeleteMapping
    public R deleteMany(@RequestBody List<Integer> ids){
        int id=0;
        int t=ids.size();
        for(int i=0;i<t;i++){
        id= ids.get(i);
            //通过id获取文件路径
            BackGround backGround=backGroundService.getById(id);
            String filePath =backGround.getPath();
            //通过路径删除文件
            File file = new File(filePath);
            if(file.exists()) file.delete();
            backGroundService.removeById(id);
        }
        return new R(true,"删除成功");
    }

}
