package com.itwu.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itwu.entity.Administrator;
import com.itwu.entity.BackGround;
import com.itwu.entity.History;
import com.itwu.entity.R;
import com.itwu.service.BackGroundService;
import com.itwu.utils.IdUtils;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.tomcat.jni.FileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/backgrounds")
public class BackGroundController {
    @Autowired
    private BackGroundService backGroundService;

    //配置图片保存的路径
    @Value("/jiuqi/upload")
    private String folder;

    //配置图片反射
    @Value("/upload/")
    private String uploadPath;

    @GetMapping("{current}/{size}")
    public R getAll(@PathVariable Integer current, @PathVariable Integer size, BackGround backGround){

        IPage<BackGround> page =backGroundService.getPage(current,size,backGround);
        return new R(true,page);
    }

    //上传文章文件
//    @PostMapping()
//    public R uploadPicture(BackGround backGround, @RequestParam(value = "file") MultipartFile file) throws IllegalStateException, IOException {
//
//        //获取文件后缀
//        String imgFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//        // 创建文件名称
//        String fileName = UUID.randomUUID() + imgFormat;
//        // 获取到文件的路径信息
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
//        String filePath = servletRequestAttributes.getRequest().getServletContext().getRealPath("/") + fileName;
//        // 打印保存路径
//        System.out.println(filePath);
//        //路径保存到数据库中
//        backGround.setPath(filePath);
//        backGroundService.save(backGround);
//        // 创建文件
//        File saveFile = new File(filePath);
//        // 文件保存
//        file.transferTo(saveFile);
//        // 返回信息
//        return new R(true,backGround,"上传成功");
//    }
    @PostMapping()
    public R imageUpload(HttpServletRequest request, BackGround backGround,@RequestParam(value = "file") MultipartFile file) throws IOException {

        /**
         * filename 图片名
         * basePath  服务器IP加端口号
         * simpleUUID 图片重命名
         * photoName 图片重命名加后缀
         * fileNamePath 图片加保存的路径
         * fileUrl 图片的链接地址
         */
        String filename, basePath, simpleUUID, photoName, fileNamePath, fileUrl;

        if (file != null && !file.isEmpty()) {
            //获取文件名
            filename = file.getOriginalFilename();
            //获取协议号
            basePath = request.getScheme()
                    + "://"
                    + request.getServerName()//获取IP地址
                    + ":"
                    + request.getServerPort()//获取端口号
                    + request.getContextPath();//获取工程路径

            String[] split = filename.split("\\.");

            //只接受jpg、png、gif、jpeg、bmp、webp文件
            if ("jpg".equalsIgnoreCase(split[1]) ||
                    "png".equalsIgnoreCase(split[1]) ||
                    "gif".equalsIgnoreCase(split[1]) ||
                    "jpeg".equalsIgnoreCase(split[1]) ||
                    "bmp".equalsIgnoreCase(split[1]) ||
                    "webp".equalsIgnoreCase(split[1])) {

                simpleUUID = IdUtils.genImageName();
                photoName = simpleUUID + "." + split[1];

                fileNamePath = folder + File.separator + photoName;
                File destFile = new File(fileNamePath);
                //判断是否存在, 不存在就创建
                if (!destFile.getParentFile().exists()) {
                    destFile.getParentFile().mkdirs();
                }
                //压缩图片并保存
                Thumbnails.of(file.getInputStream()).scale(0.8).toFile(destFile);
                fileUrl = basePath + uploadPath + photoName;
                //图片信息保存在数据库
                backGround.setPath(fileUrl);
                backGround.setUrl(fileUrl);
                backGroundService.save(backGround);

                return new R(true,backGround,"上传成功");
            }
        }
        return new R(false,"上传失败");
    }

    //批量删除
    @DeleteMapping
    public R deleteMany(@RequestBody List<Integer> ids){
//        int id=0;
//        int t=ids.size();
//        for(int i=0;i<t;i++){
//        id= ids.get(i);
//            //通过id获取文件路径
//            BackGround backGround=backGroundService.getById(id);
//            String filePath =backGround.getPath();
//            //通过路径删除文件
//            File file = new File(filePath);
//            if(file.exists()) file.delete();
//            backGroundService.removeById(id);
//        }
        backGroundService.removeByIds(ids);
        return new R(true,"删除成功");
    }

}
