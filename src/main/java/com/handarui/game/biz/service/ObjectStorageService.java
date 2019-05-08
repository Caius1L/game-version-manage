package com.handarui.game.biz.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * Created by guofe on 2016/9/17.
 */
public interface ObjectStorageService {

	/**
     * 上传对象
     * @param key
     * @param in
     */
    void putObject(String key, InputStream in) throws IOException;

	/**
	 * 上传对象，并添加文件md5的metadata，返回md5
	 * @param key
	 * @param in
	 * @throws IOException
	 */
	String putObjectWithMd5(String key, InputStream in) throws IOException;

    /**
     * 上传对象
     * @param key
     * @param bytes
     */
    void putObject(String key, byte[] bytes) throws IOException;

	/**
	 * 上传对象并添加md5到metadata，返回md5
	 * @param key
	 * @param bytes
	 * @param isPublic
	 * @param contentType
	 * @return
	 * @throws IOException
	 */
	String putObjectWithMd5(String key, byte[] bytes, boolean isPublic, String contentType) throws IOException;

	String putObjectWithMd5(String key, byte[] bytes) throws IOException;

    /**
     * 删除对象
     * @param keys
     */
    void deleteObjects(String... keys);

    /**
     * 生产可访问的url
     * @param key
     * @param expireDate 过期时间
     * @return
     */
    String generateUrl(String key, Date expireDate);

    /**
     * 获取图片服务url
     * @param key 文件名和图片处理参数
     * @return
     */
    String getImageServiceUrl(String key, Date expireDate);

	/**
	 * 获取图片服务url
	 * @param key
	 * @param expireDays 过期天数
	 * @return
	 */
	String getImageServiceUrl(String key, int expireDays);
}
