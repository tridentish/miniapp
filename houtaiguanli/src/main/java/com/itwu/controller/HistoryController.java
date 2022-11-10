package com.itwu.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itwu.entity.History;
import com.itwu.entity.R;
import com.itwu.service.HistoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/historys")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

//分页显示标题
    @GetMapping("{current}/{size}")
    public R getPage(@PathVariable Integer current,@PathVariable Integer size,History history){//路径"?title="
        //按条件分页查询
        IPage<History> page = historyService.getPage(current,size,history);
        return new R(true,page);
    }



//上传文章文件
    @PostMapping()
    public R uploadArticle( History history,@RequestParam(value = "file") MultipartFile file) throws IllegalStateException, IOException {

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
        history.setPath(filePath);
        historyService.save(history);
        // 创建文件
        File saveFile = new File(filePath);
        // 文件保存
        file.transferTo(saveFile);
        // 返回信息
        return new R(true,history);
    }

    //删除文章
    @DeleteMapping("{id}")
    public R deleteOne(@PathVariable Integer id){
        //通过id获取文件路径
        History history=historyService.getById(id);
        String filePath =history.getPath();
        //通过路径删除文件
        File file = new File(filePath);
        if(file.exists()) file.delete();
        else return new R(false);
        //删除数据库内记录
        return new R(historyService.removeById(id));
    }

    //获取文章
    @GetMapping("{id}")
    public File getFileById(@PathVariable Integer id){
        //通过id获取文件路径
        History history=historyService.getById(id);
        String filePath =history.getPath();
        //获取文件
        File file = new File(filePath);
        //
        return file;
    }

}
