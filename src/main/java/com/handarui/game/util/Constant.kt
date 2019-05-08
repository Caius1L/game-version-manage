package com.handarui.game.util

/**
 * @Author: xiebaobiao
 * @Date: 2019/2/15 0015 13:58
 */

class Constant {

    private constructor() {
        // do nothing because of constructor
    }

    companion object {
        // 著作权类型
        const val COPYRIGHT_ATTACH_PETITION = 0  //申请材料类型
        const val COPYRIGHT_ATTACH_REGISTER_NUMBER = 1 //登记号材料类型
        const val COPYRIGHT_ATTACH_PAYMENT_INVOICE = 2 //款项及发票材料类型

        // 专利附件类型
        const val PATENT_ATTACH_STATUS = 0  //状态附件类型
        const val PATENT_ATTACH_CURRENT_YEAR_PAYMENT = 1 //本年费缴纳情况附件类型
        const val PATENT_ATTACH_NOTE = 2 //备注附件

        //商标附件类型
        const val TRADEMARK_DRAWING = 0
        const val TRADEMARK_ACCEPT_REGISTRATION_NUMBER = 1
        const val TRADEMARK_STATUS = 2
    }

}