package com.handarui.game.biz.service.impl

import com.github.pagehelper.PageHelper
import com.handarui.game.biz.bean.*
import com.handarui.game.biz.query.AddPatentQuery
import com.handarui.game.biz.query.ModifyProductPatentQuery
import com.handarui.game.biz.query.PatentListQuery
import com.handarui.game.biz.service.PatentService
import com.handarui.game.dao.domain.PatentAttachDO
import com.handarui.game.dao.domain.PatentInfoDO
import com.handarui.game.dao.mapper.PatentAttachDOMapper
import com.handarui.game.dao.mapper.PatentInfoDOMapper
import com.handarui.game.util.Constant
import com.handarui.game.util.ExcelUtil
import com.zhexinit.ov.common.exception.CommonException
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.PagerQuery
import com.zhexinit.ov.common.query.TitledSortPagerQuery
import org.apache.commons.lang3.StringUtils
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import tk.mybatis.mapper.entity.Example

/**
 * @Author: xiebaobiao
 * @Date: 2019/2/16 0016 11:47
 */
@Service
class PatentServiceImpl : PatentService {
    @Autowired
    private lateinit var PatentInfoDOMapper: PatentInfoDOMapper
    @Autowired
    private lateinit var patentAttachDOMapper: PatentAttachDOMapper

    @Transactional
    override fun addProductPatent(addPatentQuery: AddPatentQuery?) {
        val productPatentInfoDO = PatentInfoDO()
        BeanUtils.copyProperties(addPatentQuery, productPatentInfoDO)
        PatentInfoDOMapper.insertSelective(productPatentInfoDO)

        //添加附件
        val statusMaterials = addPatentQuery!!.statusMaterials//状态附件
        val currentYearPaymentMaterials = addPatentQuery.currentYearPaymentMaterials//本年费缴纳情况附件
        val noteMaterials = addPatentQuery.noteMaterials//备注附件
        addAttach(productPatentInfoDO.id, statusMaterials, Constant.PATENT_ATTACH_STATUS)
        addAttach(productPatentInfoDO.id, currentYearPaymentMaterials, Constant.PATENT_ATTACH_CURRENT_YEAR_PAYMENT)
        addAttach(productPatentInfoDO.id, noteMaterials, Constant.PATENT_ATTACH_NOTE)
    }

    override fun batchDeletePatent(ids: List<Long>?) {
        ids!!.forEach {
            val productPatentInfoDO = PatentInfoDO().apply {
                id = it
                isDeleted = 1
            }
            PatentInfoDOMapper.updateByPrimaryKeySelective(productPatentInfoDO)
        }
    }

    @Transactional
    override fun modifyPatent(modifyProductPatentQuery: ModifyProductPatentQuery?) {
        val current = System.currentTimeMillis() / 1000
        val productPatentInfoDO = PatentInfoDO()
        BeanUtils.copyProperties(modifyProductPatentQuery, productPatentInfoDO)
        PatentInfoDOMapper.updateByPrimaryKeySelective(productPatentInfoDO)

        // 附件修改
        val example = Example(PatentAttachDO::class.java)
        val patentAttachCriteria = example.createCriteria()
        patentAttachCriteria.andEqualTo("patentId", modifyProductPatentQuery!!.id)
        patentAttachCriteria.andEqualTo("isDeleted", 0)
        val patentAttachDO = PatentAttachDO().apply {
            this.isDeleted = 1
            this.updatedAt = current
        }
        patentAttachDOMapper.updateByExampleSelective(patentAttachDO, example)  //先删除之前的附件，然后添加新的附件

        //添加新的附件
        val statusMaterials = modifyProductPatentQuery.statusMaterials //状态附件
        val currentYearPaymentMaterials = modifyProductPatentQuery.currentYearPaymentMaterials //本年费缴纳情况附件
        val noteMaterials = modifyProductPatentQuery.noteMaterials //备注附件

        addAttach(modifyProductPatentQuery.id!!, statusMaterials, Constant.PATENT_ATTACH_STATUS)
        addAttach(modifyProductPatentQuery.id!!, currentYearPaymentMaterials, Constant.PATENT_ATTACH_CURRENT_YEAR_PAYMENT)
        addAttach(modifyProductPatentQuery.id!!, noteMaterials, Constant.PATENT_ATTACH_NOTE)
    }

    override fun getPatentById(id: Long?, department: String?): PatentBean? {
        val selectExample=Example(PatentInfoDO::class.java)
        val createCriteria = selectExample.createCriteria()
        createCriteria.andEqualTo("id",id)
        if (department!=null){
            createCriteria.andEqualTo("drawDepartment",department)
        }
        val patentAttachDo = PatentInfoDOMapper.selectByExample(selectExample)[0]
                ?: throw CommonException(ResultEnum.patentNotExist)
        val patentPatentBean = PatentBean()
        BeanUtils.copyProperties(patentAttachDo, patentPatentBean)

        /**
         * 获取附件
         */
        val example = Example(PatentAttachDO::class.java)
        val productPatentInfoDOCriteria = example.createCriteria()
        productPatentInfoDOCriteria.andEqualTo("patentId", id)
        val patentAttachDOList = patentAttachDOMapper.selectByExample(example)
        patentAttachDOList.forEach {
            val patentAttachBean = PatentAttachBean()
            BeanUtils.copyProperties(it, patentAttachBean)
            when (it.type) {
                Constant.PATENT_ATTACH_STATUS -> {
                    patentPatentBean.statusMaterials!!.add(patentAttachBean)
                }
                Constant.PATENT_ATTACH_CURRENT_YEAR_PAYMENT -> {
                    patentPatentBean.currentYearPaymentMaterials!!.add(patentAttachBean)
                }
                Constant.PATENT_ATTACH_NOTE -> {
                    patentPatentBean.noteMaterials!!.add(patentAttachBean)
                }
            }
        }
        return patentPatentBean
    }

    override fun getPatentList(patentListQuery: PagerQuery<PatentListQuery>, department: String?): ListBean<PatentListBean>? {
        val page = PageHelper.startPage<PatentListBean>(patentListQuery.current, patentListQuery.pageSize)
        val patentList = PatentInfoDOMapper.getPatentList(patentListQuery.data,department)
        return ListBean<PatentListBean>().apply {
            this.total = page.total.toInt()
            this.data = patentList
        }
    }

    override fun getExportPatentList(titledSortPagerQuery: TitledSortPagerQuery<PatentListQuery>?, department: String?): ListBean<PatentListBean>? {
        val patentList = PatentInfoDOMapper.getPatentList(titledSortPagerQuery!!.data,department)
        return ListBean<PatentListBean>().apply {
            this.total = patentList.size
            this.data = patentList
        }
    }

    override fun import(file: MultipartFile) {
        val patentList = parseCopyrightList(file)
        if (patentList != null && !patentList.isEmpty()) {
            if (patentList.isNotEmpty()) {
                PatentInfoDOMapper.insertList(patentList)
            }
        }
    }

    /**
     * 添加附件
     */
    private fun addAttach(productPatentId: Long, attachBeanList: List<PatentAttachBean>?, type: Int) {
        if (attachBeanList == null) return
        val current = System.currentTimeMillis() / 1000
        if (attachBeanList.isNotEmpty()) {
            val patentAttachDOList = mutableListOf<PatentAttachDO>()
            attachBeanList.forEach {
                val patentAttachDO = PatentAttachDO().apply {
                    this.patentId = productPatentId
                    this.type = type
                    this.updatedAt = current
                    this.isDeleted = 0
                }
                BeanUtils.copyProperties(it, patentAttachDO)
                if (patentAttachDO.id != null) {
                    patentAttachDOMapper.updateByPrimaryKeySelective(patentAttachDO)
                } else {
                    patentAttachDO.status = 0
                    patentAttachDO.createdAt = current
                    patentAttachDOList.add(patentAttachDO)
                }
            }
            if (patentAttachDOList.isNotEmpty()) {
                patentAttachDOMapper.insertList(patentAttachDOList)
            }
        }
    }

    /**
     * 解析excel
     */
    private fun parseCopyrightList(file: MultipartFile): List<PatentInfoDO>? {
        val wb = XSSFWorkbook(file.inputStream)
        val sheet = wb.getSheetAt(0)
        if (sheet != null) {
            val copyrightDOList = mutableListOf<PatentInfoDO>()
            for (rowNum in 1..sheet.physicalNumberOfRows) {
                val row = sheet.getRow(rowNum)
                if (row != null) {
                    if (row.getCell(1) == null || StringUtils.isEmpty(ExcelUtil.getXValue(row.getCell(1)))) {
                        continue
                    }
                    val patentInfoDO = PatentInfoDO().apply {
                        this.number = ExcelUtil.getXValue(row.getCell(0)) ?: ""
                        this.name = ExcelUtil.getXValue(row.getCell(1)) ?: ""
                        this.type = PatentTypeEnum.getByTypeName(ExcelUtil.getXValue(row.getCell(2))!!).code
                        this.applyNumber = ExcelUtil.getXValue(row.getCell(3)) ?: ""
                        val excelApplyTime = ExcelUtil.getDate(row.getCell(4))
                        this.applyTime = excelApplyTime?.time?.div(1000) ?: 0
                        this.status = PatentStatusEnum.getByStatusName(ExcelUtil.getXValue(row.getCell(5))!!).code
                        this.inventionDesigner = ExcelUtil.getXValue(row.getCell(6)) ?: ""
                        this.applicant = ExcelUtil.getXValue(row.getCell(7)) ?: ""
                        this.drawer = ExcelUtil.getXValue(row.getCell(8)) ?: ""
                        this.drawDepartment = ExcelUtil.getXValue(row.getCell(9)) ?: ""
                        val currentYearPaymentExport = ExcelUtil.getXValue(row.getCell(10))
                        this.currentYearPayment = currentYearPaymentExport == "是"
                        this.currentAnnualFeeYear = ExcelUtil.getXValue(row.getCell(11)) ?: ""
                        val exportNextYearAnnualFeePaymentTime = ExcelUtil.getDate(row.getCell(12))
                        this.nextYearAnnualFeePaymentTime = exportNextYearAnnualFeePaymentTime?.time?.div(1000) ?: 0
                        this.fundingReceipt = ExcelUtil.getXValue(row.getCell(13)) ?: ""
                        this.note = ExcelUtil.getXValue(row.getCell(14)) ?: ""
                        this.technicalFeature = ExcelUtil.getXValue(row.getCell(15)) ?: ""
                        val exportAnnouncementDay = ExcelUtil.getDate(row.getCell(16))
                        this.announcementDay = exportAnnouncementDay?.time?.div(1000) ?: 0
                        this.announcementNumber = ExcelUtil.getXValue(row.getCell(17)) ?: ""
                        this.bonusPayment = ExcelUtil.getXValue(row.getCell(18)) ?: ""

                        val current = System.currentTimeMillis() / 1000
                        this.createdAt = current
                        this.updatedAt = current
                        this.isDeleted = 0
                    }
                    copyrightDOList.add(patentInfoDO)
                }
            }
            return copyrightDOList
        }
        return null
    }
}