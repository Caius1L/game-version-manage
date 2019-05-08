package com.handarui.game.biz.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.handarui.game.biz.config.OSSConfig;
import com.handarui.game.biz.service.ObjectStorageService;
import com.zhexinit.ov.common.util.EncodeUtl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by guofe on 2016/9/17.
 */
@Service
public class ObjectStorageServiceImpl implements ObjectStorageService {


    @Autowired
    private OSSConfig ossConfig;

    /**
     * 获取OSSClient
     *
     * @return
     */
    private OSSClient getClient() {
        return new OSSClient(ossConfig.getEndpoint(), ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
    }

    /**
     * 通过字节数组创建输入流
     *
     * @param bytes
     * @return
     */
    private InputStream createInputStreamFromBytes(byte[] bytes) {
        return new ByteArrayInputStream(bytes);
    }

    @Override
    public void putObject(String key, InputStream in) throws IOException {
        OSSClient client = getClient();
        PutObjectRequest request = new PutObjectRequest(ossConfig.getBucket(), key, in);
        client.putObject(request);
        client.shutdown();
        in.close();
    }

    @Override
    public String putObjectWithMd5(String key, InputStream in) throws IOException {
        byte[] bytes = EncodeUtl.readBytes(in);
        return putObjectWithMd5(key, bytes);
    }

    @Override
    public void putObject(String key, byte[] bytes) throws IOException {
        InputStream in = createInputStreamFromBytes(bytes);
        putObject(key, in);
    }

    @Override
    public String putObjectWithMd5(String key, byte[] bytes, boolean isPublic, String contentType) throws IOException {
        String md5 = EncodeUtl.getMd5ByFile(bytes);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.addUserMetadata("md5", md5);
        if (contentType != null) {
            metadata.setContentType(contentType);
        }

        InputStream in = createInputStreamFromBytes(bytes);
        PutObjectRequest request = new PutObjectRequest(ossConfig.getBucket(), key, in, metadata);

        OSSClient client = getClient();
        client.putObject(request);
        in.close();

        if (isPublic) {
            SetObjectAclRequest setObjectAclRequest = new SetObjectAclRequest(ossConfig.getBucket(), key, CannedAccessControlList.PublicRead);
            client.setObjectAcl(setObjectAclRequest);
        }

        client.shutdown();

        return md5;
    }

    @Override
    public String putObjectWithMd5(String key, byte[] bytes) throws IOException {
        return putObjectWithMd5(key, bytes, false, null);
    }

    @Override
    public void deleteObjects(String... keys) {
        OSSClient client = getClient();
        DeleteObjectsRequest request = new DeleteObjectsRequest(ossConfig.getBucket());
        request.withKeys(Arrays.asList(keys));
        client.deleteObjects(request);
        client.shutdown();
    }

    @Override
    public String generateUrl(String key, Date expireDate) {
        OSSClient client = getClient();
        URL url = client.generatePresignedUrl(ossConfig.getBucket(), key, expireDate);
        client.shutdown();
        return url.toString();
    }

    @Override
    public String getImageServiceUrl(String key, Date expireDate) {
        OSSClient client = getClient();
        URL url = client.generatePresignedUrl(ossConfig.getBucket(), key, expireDate);
        String objectUrl = url.toString();
        objectUrl = objectUrl.replaceFirst("http", "https");

        client.shutdown();
        return objectUrl;
    }

    @Override
    public String getImageServiceUrl(String key, int expireDays) {
        if (key != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, expireDays);
            return getImageServiceUrl(key, calendar.getTime());
        } else {
            return null;
        }
    }
}
