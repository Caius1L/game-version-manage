package com.handarui.game.biz.bean

import org.hibernate.validator.constraints.NotEmpty
import javax.validation.Valid
import javax.validation.constraints.NotNull

open class BaseGameBean {

    var id: Long? = null

    @NotEmpty
    var name: String? = null

    @NotNull
    var type: Int? = null

    @NotEmpty
    var copyrightUnit: String? = null

    @NotEmpty
    var operationUnit: String? = null

    @NotNull
    var process: Int? = null

    @NotNull
    var sort: Int? = null

    var submitDepartment: String? = null

    /**
     * 备案号
     */
    var recordNumber: String? = null

    /**
     * 备案号附件
     */
    var recordNumberAttach: AttachBean? = null

    var approvalNumber: String? = null

    var isbnNumber: String? = null

    @NotEmpty
    var copyrightNumber: String? = null

    var record: Boolean? = null

    /**
     * 批文附件
     */
    @Valid
    var approval: AttachBean? = null

    /**
     * ISBN附件
     */
    @Valid
    var isbn: AttachBean? = null

    /**
     * 软件著作权附件
     */
    @Valid
    var copyright: MutableList<AttachBean>? = null

    /**
     * 答复意见附件
     */
    @Valid
    var replyOpinion: MutableList<AttachBean>? = null
}