package com.handarui.game.biz.bean

/**
 * 版号材料
 */
class VersionMaterialBean {

    /**
     * 版号材料类型0:上线证明1:游戏说明文档2:境外分级情况3:境外运营情况4:无旁白说明5:出版申请表6:授权书7:营业执照8:icp证书9:法人授权书
     */
    var type: Int? = null

    var attaches: MutableList<AttachBean>? = null

}