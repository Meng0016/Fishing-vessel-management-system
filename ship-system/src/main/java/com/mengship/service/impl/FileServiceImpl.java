package com.mengship.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mengship.entity.File;
import com.mengship.mapper.FileMapper;
import com.mengship.service.IFileService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author meng
 * @Data 2023/2/23 11:18
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements IFileService {
}
