package com.mengship.controller;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;
import com.mengship.common.Result;
import com.mengship.entity.User;
import com.mengship.mapper.ShipMapper;
import com.mengship.service.impl.ShipServiceImpl;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mengship.service.IShipService;
import com.mengship.entity.Ship;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author meng
 * @since 2022-12-27
 */
@RestController
@RequestMapping("/ship")
public class ShipController {

    @Resource
    private IShipService shipService;

    @PostMapping
    public Result save(@RequestBody Ship ship) {
        return Result.success(shipService.saveOrUpdate(ship));
    }
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(shipService.removeById(id));
    }
    @DeleteMapping("batchDelete")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return  Result.success(shipService.removeByIds(ids));
    }
    @GetMapping
    public Result findAll() {
        return Result.success(shipService.list());
    }
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(shipService.getById(id));
    }
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam (defaultValue = "") String shipName,
                           @RequestParam (defaultValue = "") String shipCode) {
        QueryWrapper<Ship> queryWrapper = new QueryWrapper<>();
        if (!"".equals(shipName)){
            queryWrapper.like("ship_name", shipName);
        }
        if (!"".equals(shipCode)){
            queryWrapper.like("ship_code", shipCode);
        }
        queryWrapper.orderByAsc("ship_id");
        return Result.success(shipService.page(new Page<>(pageNum, pageSize),queryWrapper));
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
        List<Ship> list = shipService.list();
        // 通过工具类创建writer 写出到磁盘路径
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名  实体类注解Alias
        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("船只信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();

    }


    @PostMapping("/print")
    public void print(@RequestBody Ship ship, HttpServletResponse response) throws Exception{

        String templatePath = "classpath*:/templates/pdfTemplate";
        Map<String,Object> datas = new HashMap<>();
        datas.put("",ship.getShipId());
        datas.put("",ship.getShipName());
        datas.put("",ship.getShipCode());
        datas.put("",ship.getShipPort());
        datas.put("",ship.getShipType());
        datas.put("",ship.getShipWidth());
        datas.put("",ship.getShipLength());
        datas.put("",ship.getTypeDeep());
        datas.put("",ship.getTotalTons());
        datas.put("",ship.getHostModel());
        datas.put("",ship.getHostNum());
        datas.put("",ship.getHostId());
        datas.put("",ship.getHostPower());
        datas.put("",ship.getShipFactory());
        datas.put("",ship.getShipRegNum());
        datas.put("",ship.getShipPhysical());
        datas.put("",ship.getShipArea());
        datas.put("",ship.getEndDate());
        datas.put("",ship.getSurEquip());
        datas.put("",ship.getFirEquip());
        datas.put("",ship.getOthEquip());
        datas.put("",ship.getInsId());
        datas.put("",ship.getNatId());
        datas.put("",ship.getOwnRgsid());
        datas.put("",ship.getOwnDate());
        datas.put("",ship.getLicenseId());
        datas.put("",ship.getFishGg());
        datas.put("",ship.getWorkType());
        datas.put("",ship.getWorkSpace());
        datas.put("",ship.getOwnName());
        datas.put("",ship.getOwnShares());
        datas.put("",ship.getOwnAddress());
        datas.put("",ship.getOwnId());
        datas.put("",ship.getOwnPhone());
        datas.put("",ship.getMutalInfor());
        datas.put("",ship.getShipState());
        datas.put("",ship.getReUserId());
        datas.put("",ship.getRegDate());
        datas.put("",ship.getAprovDate());
        datas.put("",ship.getFileFlag());
        datas.put("",ship.getCertDate());
        datas.put("",ship.getValid());
        String filename = "shipPDF";


        PdfReader reader;
        OutputStream outputStream = null;
        ByteArrayOutputStream baos = null;
        PdfStamper stamper;
        try {
            outputStream = response.getOutputStream();
            // 读入pdf表单
            reader = new PdfReader(templatePath);
            baos = new ByteArrayOutputStream();
            // 根据表单生成一个新的pdf
            stamper = new PdfStamper(reader, baos);
            // 获取pdf表单
            AcroFields formTexts = stamper.getAcroFields();

            // 设置字体(这里设置为系统字体，你也可以引入其他的字体)，不设置很可能，中文无法显示。
            BaseFont bf = BaseFont.createFont("C:/WINDOWS/Fonts/SIMSUN.TTC,1",
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            formTexts.addSubstitutionFont(bf);

            // 数据查询后，值的植入 强烈推荐键值方式，其他方式很容易混
            Set<String> keySet =  datas.keySet();
            for (String key : keySet) {
                formTexts.setField(key, datas.get(key).toString());
            }

            stamper.setFormFlattening(true); // 这个必须设
            stamper.close();

            // 创建一个pdf实例
            Document document = new Document();
            PdfCopy copy = new PdfCopy(document, outputStream);
            document.open();
            PdfImportedPage importedPage = copy.getImportedPage(new PdfReader(baos.toByteArray()), 1);
            copy.addPage(importedPage);
            document.close(); // open和close一套

            // 强制下载
            //response.reset();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;fileName="
                    + URLEncoder.encode(filename, "UTF-8"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally { // 最后一定要关闭流
            try {
                baos.close();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

