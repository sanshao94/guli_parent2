package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertisUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author shkstart
 * @create 2021-05-19-11:20
 */

@Service
public class OssServiceImpl implements OssService {

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertisUtils.END_POINT;
// 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertisUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertisUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertisUtils.BUCKET_NAME;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 创建PutObjectRequest对象。
// 填写Bucket名称、Object完整路径和本地文件的完整路径。Object完整路径中不能包含Bucket名称。
// 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。


        try{
            InputStream inputStream = file.getInputStream();

            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            fileName = uuid+fileName;

            String datePath = new DateTime().toString("yyyy/MM/dd");

            fileName = datePath + "/" + fileName;

// 如果需要上传时设置存储类型和访问权限，请参考以下示例代码。
// ObjectMetadata metadata = new ObjectMetadata();
// metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
// metadata.setObjectAcl(CannedAccessControlList.Private);
// putObjectRequest.setMetadata(metadata);

// 上传文件。
            ossClient.putObject(bucketName,fileName,inputStream);

// 关闭OSSClient。
            ossClient.shutdown();

            String url = "https://"+bucketName+"."+endpoint+"/"+fileName;


            return url;
        }
        catch (Exception e){

            e.printStackTrace();
            return null;
        }

    }
}
