package com.mengship.controller;

import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mengship.entity.User;
import com.mengship.service.IUserService;
import com.mengship.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author meng
 * @Data 2022/10/13 20:18
 * <p>
 *  前端控制器
 * </p>
 *
 * @author meng
 * @since 2022-11-18
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService userService;

    @PostMapping
    public Boolean save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @DeleteMapping("delete/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return userService.removeById(id);
    }

    @DeleteMapping("batchDelete")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return  userService.removeByIds(ids);
    }

    @GetMapping
    public List<User> findAll() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public List<User> findOne(@PathVariable Integer id) {
        return userService.list();
    }

    @GetMapping("/page")
    public Page<User> findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String realname) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(username)){
            queryWrapper.like("username",username);
        }
        if (!"".equals(realname)){
            queryWrapper.like("realname",realname);
        }
        return userService.page(new Page<>(pageNum, pageSize),queryWrapper);
    }
    /**
     * @Description 导出
     * @Param null
     * @Return {@link null}
     * @Author meng
     * @Date 2022/11/20 16:31
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        List<User> list = userService.list();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("realname", "真实姓名");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("status", "使用状态");
        writer.addHeaderAlias("access", "通过状态");
        writer.addHeaderAlias("loginCount", "登录次数");
        writer.addHeaderAlias("dateCreated", "创建日期");
        writer.addHeaderAlias("smtp", "Smtp");
        writer.addHeaderAlias("port", "港口");
        writer.addHeaderAlias("phone", "电话");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }
    /***
     * @Description 导入
     * @Param null
     * @Return {@link null}
     * @Author meng
     * @Date 2022/11/20 16:31
     */
    @PostMapping("/import")
    public void importExcel(MultipartFile file) throws IOException {
        InputStream inputStream =file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
//        List<User> userList = reader.readAll(User.class);

        List<User> userList = reader.read(0, 1, User.class);
        System.out.println(userList);
        userService.saveBatch(userList);

    }
}


