package com.handarui.game.dao.domain;

import javax.persistence.*;

@Table(name = "version_material")
public class VersionMaterialDo {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 游戏id
     */
    @Column(name = "game_id")
    private Long gameId;

    /**
     * 文件oss key
     */
    private String file;

    /**
     * 文件名
     */
    private String name;

    /**
     * 版号材料类型0:上线证明1:游戏说明文档2:境外分级情况3:境外运营情况4:无旁白说明5:出版申请表6:授权书7:营业执照8:icp证书9:法人授权书
     */
    private Integer type;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Long createdAt;

    /**
     * 修改时间
     */
    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * 是否删除0:否1:是
     */
    @Column(name = "is_deleted")
    private Integer isDeleted;

    /**
     * 获取主键id
     *
     * @return id - 主键id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键id
     *
     * @param id 主键id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取游戏id
     *
     * @return game_id - 游戏id
     */
    public Long getGameId() {
        return gameId;
    }

    /**
     * 设置游戏id
     *
     * @param gameId 游戏id
     */
    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    /**
     * 获取文件oss key
     *
     * @return file - 文件oss key
     */
    public String getFile() {
        return file;
    }

    /**
     * 设置文件oss key
     *
     * @param file 文件oss key
     */
    public void setFile(String file) {
        this.file = file == null ? null : file.trim();
    }

    /**
     * 获取文件名
     *
     * @return categoryName - 文件名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置文件名
     *
     * @param name 文件名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取版号材料类型0:上线证明1:游戏说明文档2:境外分级情况3:境外运营情况4:无旁白说明5:出版申请表6:授权书7:营业执照8:icp证书9:法人授权书
     *
     * @return type - 版号材料类型0:上线证明1:游戏说明文档2:境外分级情况3:境外运营情况4:无旁白说明5:出版申请表6:授权书7:营业执照8:icp证书9:法人授权书
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置版号材料类型0:上线证明1:游戏说明文档2:境外分级情况3:境外运营情况4:无旁白说明5:出版申请表6:授权书7:营业执照8:icp证书9:法人授权书
     *
     * @param type 版号材料类型0:上线证明1:游戏说明文档2:境外分级情况3:境外运营情况4:无旁白说明5:出版申请表6:授权书7:营业执照8:icp证书9:法人授权书
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Long getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取修改时间
     *
     * @return updated_at - 修改时间
     */
    public Long getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置修改时间
     *
     * @param updatedAt 修改时间
     */
    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 获取是否删除0:否1:是
     *
     * @return is_deleted - 是否删除0:否1:是
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除0:否1:是
     *
     * @param isDeleted 是否删除0:否1:是
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}