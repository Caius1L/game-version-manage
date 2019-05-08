package com.handarui.game.controller

import com.handarui.auth.base.model.ClassicalUserVo
import com.handarui.game.biz.bean.PatentBean
import com.handarui.game.biz.bean.PatentListBean
import com.handarui.game.biz.query.AddPatentQuery
import com.handarui.game.biz.query.ModifyProductPatentQuery
import com.handarui.game.biz.query.PatentListQuery
import com.handarui.game.biz.service.PatentService
import com.zhexinit.ov.common.bean.RequestBean
import com.zhexinit.ov.common.bean.ResponseBean
import com.zhexinit.ov.common.model.ExportItemInfo
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.SortPagerQuery
import com.zhexinit.ov.common.query.TitledSortPagerQuery
import com.zhexinit.ov.common.util.ResponseUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

/**
 * 专利Controller
 * @Author: xiebaobiao
 * @Date: 2019/2/16 0016 10:27
 */
@RestController
@RequestMapping("patent")
class PatentController {

    private companion object {
        const val DEPARTMENT = "法务部门"
    }

    @Autowired
    private lateinit var patentService: PatentService

    /**
     * 添加专利
     */
    @PostMapping("addPatent")
    fun addProductPatent(@RequestBody requestBean: RequestBean<AddPatentQuery>): ResponseBean<Void> {
        patentService.addProductPatent(requestBean.param)
        return ResponseUtil.success()
    }

    /**
     * 批量删除
     */
    @PostMapping("batchDeletePatent")
    fun batchDeletePatent(@RequestBody requestBean: RequestBean<List<Long>>): ResponseBean<Void> {
        patentService.batchDeletePatent(requestBean.param)
        return ResponseUtil.success()
    }

    /**
     * 修改专利
     */
    @PostMapping("modifyPatent")
    fun modifyPatent(@RequestBody requestBean: RequestBean<ModifyProductPatentQuery>): ResponseBean<Void> {
        patentService.modifyPatent(requestBean.param)
        return ResponseUtil.success()
    }

    /**
     * 通过id获取专利信息
     */
    @PostMapping("getPatentById")
    fun getPatentById(@RequestBody requestBean: RequestBean<Long>, classicalUserVo: ClassicalUserVo): ResponseBean<PatentBean> {
        val department = if (classicalUserVo.roles.any { it.name == PatentController.DEPARTMENT }) {
            null
        } else {
            classicalUserVo.roles[0].name
        }
        return ResponseUtil.success(patentService.getPatentById(requestBean.param,department))
    }

    /**
     * 获取专利列表
     */
    @PostMapping("getPatentList")
    fun getPatentList(@RequestBody requestBean: RequestBean<SortPagerQuery<PatentListQuery>>, classicalUserVo: ClassicalUserVo): ResponseBean<ListBean<PatentListBean>> {
        val department = if (classicalUserVo.roles.any { it.name == PatentController.DEPARTMENT }) {
            null
        } else {
            classicalUserVo.roles[0].name
        }
        return ResponseUtil.success(patentService.getPatentList(requestBean.param, department))
    }

    /**
     * 导入
     */
    @PostMapping("import")
    fun importCopyright(@RequestPart("file") file: MultipartFile): ResponseBean<Void> {
        patentService.import(file)
        return ResponseUtil.success()
    }

    /**
     * 导出
     */
    @PostMapping("export")
    fun exportPatent(@RequestBody requestBean: RequestBean<TitledSortPagerQuery<PatentListQuery>>, classicalUserVo: ClassicalUserVo): ByteArray {
        val department = if (classicalUserVo.roles.any { it.name == PatentController.DEPARTMENT }) {
            null
        } else {
            classicalUserVo.roles[0].name
        }
        val patentList = patentService.getExportPatentList(requestBean.param, department)
        patentList!!.data.forEach {
            when (it.type) {
                0 -> it.typeExport = "发明"
                1 -> it.typeExport = "实用新型"
                2 -> it.typeExport = "外观"
            }
            when (it.status) {
                0 -> it.statusExport = "撰写交底书"
                1 -> it.statusExport = "提交申请"
                2 -> it.statusExport = "受理"
                3 -> it.statusExport = "实审"
                4 -> it.statusExport = "办登"
                5 -> it.statusExport = "授权"
                6 -> it.statusExport = "驳回"
                7 -> it.statusExport = "复审"
                8 -> it.statusExport = "异议"
                9 -> it.statusExport = "无效"
                10 -> it.statusExport = "初步审查"
                11 -> it.statusExport = "授权公告"
                12 -> it.statusExport = "行政复议"
            }
            it.currentYearPaymentExport = if (it.currentYearPayment!!) "已缴" else "未缴"
        }
        return ResponseUtil.exportAsBytes(patentList, getExportTitles())
    }

    /**
     * 获取导出excel的titles
     */
    private fun getExportTitles(): List<ExportItemInfo> {
        val dateFormat = "yyyy-MM-dd HH:mm:ss"
        val titles = mutableListOf<ExportItemInfo>()
        val numberExport = ExportItemInfo().apply {
            this.title = "编号"
            this.index = "number"
        }
        val nameExport = ExportItemInfo().apply {
            this.title = "专利名称"
            this.index = "name"
        }
        val typeExport = ExportItemInfo().apply {
            this.title = "类型"
            this.index = "typeExport"
        }
        val applyNumberExport = ExportItemInfo().apply {
            this.title = "申请号"
            this.index = "applyNumber"
        }
        val applyTimeExport = ExportItemInfo().apply {
            this.title = "申请日"
            this.index = "applyTime"
            this.type = dateFormat
        }
        val statusExport = ExportItemInfo().apply {
            this.title = "状态"
            this.index = "statusExport"
        }
        val inventionDesignerExport = ExportItemInfo().apply {
            this.title = "发明设计人"
            this.index = "inventionDesigner"
        }
        val applicantExport = ExportItemInfo().apply {
            this.title = "申请人"
            this.index = "applicant"
        }
        val drawerExport = ExportItemInfo().apply {
            this.title = "提请人"
            this.index = "drawer"
        }
        val drawDepartmentExport = ExportItemInfo().apply {
            this.title = "提请部门"
            this.index = "drawDepartment"
        }
        val currentYearPaymentExport = ExportItemInfo().apply {
            this.title = "本年费缴纳情况"
            this.index = "currentYearPaymentExport"
        }
        val currentAnnualFeeYearExport = ExportItemInfo().apply {
            this.title = "当前年费年度"
            this.index = "currentAnnualFeeYear"
        }
        val nextYearAnnualFeePaymentTimeExport = ExportItemInfo().apply {
            this.title = "下一年度年费缴纳时间"
            this.index = "nextYearAnnualFeePaymentTime"
            this.type = dateFormat
        }
        val fundingReceiptExport = ExportItemInfo().apply {
            this.title = "资助领取情况"
            this.index = "fundingReceipt"
        }
        val noteExport = ExportItemInfo().apply {
            this.title = "备注"
            this.index = "note"
        }
        val technicalFeatureExport = ExportItemInfo().apply {
            this.title = "技术特征"
            this.index = "technicalFeature"
        }
        val announcementDayExport = ExportItemInfo().apply {
            this.title = "公告日"
            this.index = "announcementDay"
            this.type = dateFormat
        }
        val announcementNumberExport = ExportItemInfo().apply {
            this.title = "公告号"
            this.index = "announcementNumber"
        }
        val bonusPaymentExport = ExportItemInfo().apply {
            this.title = "奖金发放情况"
            this.index = "bonusPayment"
        }
        val idExport = ExportItemInfo().apply {
            this.title = "专利id"
            this.index = "id"
        }

        titles.add(numberExport)
        titles.add(nameExport)
        titles.add(typeExport)
        titles.add(applyNumberExport)
        titles.add(applyTimeExport)

        titles.add(statusExport)
        titles.add(inventionDesignerExport)
        titles.add(applicantExport)
        titles.add(drawerExport)
        titles.add(drawDepartmentExport)

        titles.add(currentYearPaymentExport)
        titles.add(currentAnnualFeeYearExport)
        titles.add(nextYearAnnualFeePaymentTimeExport)
        titles.add(fundingReceiptExport)
        titles.add(noteExport)

        titles.add(technicalFeatureExport)
        titles.add(announcementDayExport)
        titles.add(announcementNumberExport)
        titles.add(bonusPaymentExport)
        titles.add(idExport)

        return titles
    }
}