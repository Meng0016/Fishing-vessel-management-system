
package com.mengship.utils;


import com.mengship.config.COSConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.UploadResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import com.qcloud.cos.transfer.Upload;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.sun.xml.internal.fastinfoset.vocab.Vocabulary.PREFIX;

/**
 * 腾讯云COS文件上传工具类
 */
@Slf4j
public class COSClientUtil {
 
    /**
     * 获取配置信息
     */
    private static COSConfig cosConfig = SpringBeanUtils.getBean(COSConfig.class);
 
    /**
     * 初始化用户身份信息
     */
    private static COSCredentials cred = new BasicCOSCredentials(cosConfig.getAccessKey(), cosConfig.getSecretKey());
 
    /**
     * 设置bucket的区域
     */
    private static ClientConfig clientConfig = new ClientConfig(new Region(cosConfig.getRegionName()));
 
    /**
     * 生成COS客户端
     */
    private static COSClient cosClient = new COSClient(cred, clientConfig);
 
    /**
     * 上传文件
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static String upload(MultipartFile file) throws Exception {
        String date = DateUtils.format(new Date(), "yyyy-MM-dd");
        String originalFilename = file.getOriginalFilename();
        long nextId = IdGenerator.getDefault().nextId();
        String name = nextId + originalFilename.substring(originalFilename.lastIndexOf("."));
        String folderName = cosConfig.getFolderPrefix() + "/" + date + "/";
        String key = folderName + name;
        File localFile = null;
        try {
            localFile = transferToFile(file);
            String filePath = uploadFileToCOS(localFile, key);
            log.info("upload COS successful: {}", filePath);
            return filePath;
        } catch (Exception e) {
            throw new Exception("文件上传失败");
        } finally {
            assert localFile != null;
            localFile.delete();
        }
    }
 
    /**
     * 上传文件到COS
     *
     * @param localFile
     * @param key
     * @return
     */
    private static String uploadFileToCOS(File localFile, String key) throws InterruptedException {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosConfig.getBucketName(), key, localFile);
        ExecutorService threadPool = Executors.newFixedThreadPool(8);
        // 传入一个threadPool, 若不传入线程池, 默认TransferManager中会生成一个单线程的线程池
        TransferManager transferManager = new TransferManager(cosClient, threadPool);
        // 返回一个异步结果Upload, 可同步的调用waitForUploadResult等待upload结束, 成功返回UploadResult, 失败抛出异常
        Upload upload = transferManager.upload(putObjectRequest);
        UploadResult uploadResult = upload.waitForUploadResult();
        transferManager.shutdownNow();
        cosClient.shutdown();
        String filePath = cosConfig.getBaseUrl() + uploadResult.getKey();
        return filePath;
    }
 
    /**
     * 用缓冲区来实现这个转换, 即创建临时文件
     * 使用 MultipartFile.transferTo()
     *
     * @param multipartFile
     * @return
     */
    private static File transferToFile(MultipartFile multipartFile) throws IOException, IOException {
        String originalFilename = multipartFile.getOriginalFilename();
        String prefix = originalFilename.split("\\.")[0];
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        File file = File.createTempFile(prefix, suffix);
        multipartFile.transferTo(file);
        return file;
    }
    private static final String BUCKET_NAME = "meng-1314015467";
    public static final String URL = "https://meng-1314015467.cos.ap-shanghai.myqcloud.com/";

    public static String uploadfile(MultipartFile file){
        // 创建 COS 客户端连接
        COSClient cosClient = new COSClient(cred,clientConfig);

        String date = DateUtils.format(new Date(), "yyyy-MM-dd");
        String folderName = cosConfig.getFolderPrefix() + "/" + date + "/";
        String fileName = file.getOriginalFilename();
        try {
            String substring = fileName.substring(fileName.lastIndexOf("."));
            File localFile = File.createTempFile(String.valueOf(System.currentTimeMillis()),substring);
            file.transferTo(localFile);
            Random random = new Random();
            fileName = folderName+random.nextInt(10000)+System.currentTimeMillis()+substring;
            // 将 文件上传至 COS
            PutObjectRequest objectRequest = new PutObjectRequest(BUCKET_NAME,fileName,localFile);
            cosClient.putObject(objectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cosClient.shutdown();
        }
        System.out.println(URL+fileName);
        return URL+fileName;

    }

    public static String uploadavatar(MultipartFile file){
        // 创建 COS 客户端连接
        COSClient cosClient = new COSClient(cred,clientConfig);

        String date = DateUtils.format(new Date(), "yyyy-MM-dd");
        String folderName = cosConfig.getAvatarFolder() + "/" + date + "/";
        String fileName = file.getOriginalFilename();
        try {
            String substring = fileName.substring(fileName.lastIndexOf("."));
            File localFile = File.createTempFile(String.valueOf(System.currentTimeMillis()),substring);
            file.transferTo(localFile);
            Random random = new Random();
            fileName = folderName+random.nextInt(10000)+System.currentTimeMillis()+substring;
            // 将 文件上传至 COS
            PutObjectRequest objectRequest = new PutObjectRequest(BUCKET_NAME,fileName,localFile);
            cosClient.putObject(objectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cosClient.shutdown();
        }
        System.out.println(URL+fileName);
        return URL+fileName;

    }

 
}