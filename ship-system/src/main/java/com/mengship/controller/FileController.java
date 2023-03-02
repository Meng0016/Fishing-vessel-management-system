package com.mengship.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mengship.common.Result;
import com.mengship.config.COSConfig;
import com.mengship.entity.File;
import com.mengship.entity.User;
import com.mengship.mapper.FileMapper;

import com.mengship.service.IFileService;
import com.mengship.utils.COSClientUtil;

import com.mengship.utils.TokenUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;


/**
 * @Description 文件接口
 * @Author meng
 * @Data 2023/2/23 10:29
 */
@RestController
@RequestMapping("/file")
public class FileController {


    @Resource
    private  COSConfig cosConfig;

    @Resource
    private IFileService fileService;

    @Resource
    private FileMapper fileMapper;

    @GetMapping("/allFile")
    public Result allFile(){
        List<File> files = fileMapper.selectList(new QueryWrapper<>());
        return Result.success(files);
    }
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        QueryWrapper<File> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)){
            queryWrapper.like("name",name);
        }
        return Result.success(fileService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @DeleteMapping("delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(fileService.removeById(id));
    }

    @DeleteMapping("batchDelete")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(fileService.removeByIds(ids));
    }


    @PostMapping("/update")
    public Result update(@RequestBody File file) {
        return Result.success(fileService.saveOrUpdate(file));
    }


    @PostMapping("/upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        String path = COSClientUtil.uploadfile(file);
        File file1 = new File();
        file1.setName(originalFilename);
        file1.setType(type);
        file1.setSize(size/1024);
        file1.setUrl(path);
        fileMapper.insert(file1);
        return path;
    }

    @PostMapping("/uploadavatar")
    public String uploadavatar(@RequestParam(value = "file") MultipartFile file) throws Exception {
        String originalFilename = file.getOriginalFilename();
        String type = FileUtil.extName(originalFilename);
        long size = file.getSize();
        String path = COSClientUtil.uploadavatar(file);
        File file1 = new File();
        file1.setName(originalFilename);
        file1.setType(type);
        file1.setSize(size/1024);
        file1.setUrl(path);
        fileMapper.insert(file1);
        return path;
    }

//    @GetMapping("/download/{path}")
//    public String download(@PathVariable String path, HttpServletResponse response) throws IOException {
////        java.io.File file = new java.io.File(path);
////        System.out.println(path);
////        ServletOutputStream outputStream = response.getOutputStream();
////        response.addHeader("Content-Disposition","attachment;fileName=");
////        response.setContentType("application/octet-stream");
////        byte[] bytes = FileUtil.readBytes(file);
////        outputStream.write(bytes);
////        outputStream.flush();
////        outputStream.close();
//
//        return path;
//    }



    @Bean
    public COSClient cosClient(){
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(cosConfig.getAccessKey(), cosConfig.getSecretKey());
        // 2 设置 bucket 的区域, COS 地域的简称请参照
        Region region = new Region(cosConfig.getRegionName());
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }


    @GetMapping("/{path}")
    public void download(@PathVariable String path , HttpServletResponse response) throws IOException {
        java.io.File file = new java.io.File(path);
        System.out.println(path);
        ServletOutputStream outputStream = response.getOutputStream();
        response.addHeader("Content-Disposition","attachment;fileName=");
        response.setContentType("application/octet-stream");
        byte[] bytes = FileUtil.readBytes(file);
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();

    }



}
