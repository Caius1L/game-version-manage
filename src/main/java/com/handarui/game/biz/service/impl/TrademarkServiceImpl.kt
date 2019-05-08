package com.handarui.game.biz.service.impl

import com.github.pagehelper.PageHelper
import com.handarui.game.biz.bean.TrademarkAttachBean
import com.handarui.game.biz.bean.TrademarkBean
import com.handarui.game.biz.bean.TrademarkListBean
import com.handarui.game.biz.query.AddTrademarkQuery
import com.handarui.game.biz.query.ModifyTrademarkQuery
import com.handarui.game.biz.query.TrademarkListQuery
import com.handarui.game.biz.service.TrademarkService
import com.handarui.game.dao.domain.PatentAttachDO
import com.handarui.game.dao.domain.TrademarkAttachDO
import com.handarui.game.dao.domain.TrademarkInfoDO
import com.handarui.game.dao.mapper.TrademarkAttachDOMapper
import com.handarui.game.dao.mapper.TrademarkInfoDOMapper
import com.handarui.game.util.Constant
import com.zhexinit.ov.common.query.ListBean
import com.zhexinit.ov.common.query.PagerQuery
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import tk.mybatis.mapper.entity.Example

/**
 * @Author: xiebaobiao
 * @Date: 2019/2/18 0018 11:51
 */
@Service
class TrademarkServiceImpl : TrademarkService {
    @Autowired
    private lateinit var trademarkInfoDOMapper: TrademarkInfoDOMapper

    @Autowired
    private lateinit var trademarkAttachDOMapper: TrademarkAttachDOMapper

    @Transactional
    override fun addTrademark(addTrademarkQuery: AddTrademarkQuery?) {
        val trademarkInfoDO = TrademarkInfoDO()
        BeanUtils.copyProperties(addTrademarkQuery, trademarkInfoDO)
        trademarkInfoDOMapper.insertSelective(trademarkInfoDO)

        //添加附件
        val drawingMaterials = addTrademarkQuery!!.drawingMaterials //图样附件
        val acceptRegistrationNumberMaterial = addTrademarkQuery.acceptRegistrationNumberMaterials //受理号/注册号附件
        val statusMaterials = addTrademarkQuery.statusMaterials //状态附件

        val acceptRegistrationNumberList = ArrayList<TrademarkAttachBean>()
        if (acceptRegistrationNumberMaterial != null) {
            acceptRegistrationNumberList.add(acceptRegistrationNumberMaterial)
        }
        addAttach(trademarkInfoDO.id, drawingMaterials!!, Constant.PATENT_ATTACH_STATUS)
        addAttach(trademarkInfoDO.id, acceptRegistrationNumberList, Constant.PATENT_ATTACH_CURRENT_YEAR_PAYMENT)
        addAttach(trademarkInfoDO.id, statusMaterials!!, Constant.PATENT_ATTACH_NOTE)
    }

    override fun batchDeleteTrademark(ids: List<Long>?) {
        ids!!.forEach {
            val trademarkInfoDO = TrademarkInfoDO().apply {
                this.id = it
                this.isDeleted = 1
            }
            trademarkInfoDOMapper.updateByPrimaryKeySelective(trademarkInfoDO)
        }
    }

    @Transactional
    override fun modifyTrademark(modifyTrademarkQuery: ModifyTrademarkQuery?) {
        val current = System.currentTimeMillis() / 1000
        val trademarkInfoDO = TrademarkInfoDO()
        BeanUtils.copyProperties(modifyTrademarkQuery, trademarkInfoDO)
        trademarkInfoDOMapper.updateByPrimaryKeySelective(trademarkInfoDO)

        // 附件修改
        val example = Example(PatentAttachDO::class.java)
        val patentAttachCriteria = example.createCriteria()
        patentAttachCriteria.andEqualTo("productPatentId", modifyTrademarkQuery!!.id)
        patentAttachCriteria.andEqualTo("isDeleted", 0)
        val trademarkAttachDO = TrademarkAttachDO().apply {
            this.isDeleted = 1
            this.updatedAt = current
        }
        trademarkAttachDOMapper.updateByExampleSelective(trademarkAttachDO, example)  //先删除之前的附件，然后添加新的附件

        //添加新的附件
        val drawingMaterials = modifyTrademarkQuery.drawingMaterials //图样附件
        val acceptRegistrationNumberMaterial = modifyTrademarkQuery.acceptRegistrationNumberMaterial //受理号/注册号附件
        val statusMaterials = modifyTrademarkQuery.statusMaterials //状态附件

        val acceptRegistrationNumberList = ArrayList<TrademarkAttachBean>()
        if (acceptRegistrationNumberMaterial != null) {
            acceptRegistrationNumberList.add(acceptRegistrationNumberMaterial)
        }
        addAttach(modifyTrademarkQuery.id!!, drawingMaterials!!, Constant.PATENT_ATTACH_STATUS)
        addAttach(modifyTrademarkQuery.id!!, acceptRegistrationNumberList, Constant.PATENT_ATTACH_CURRENT_YEAR_PAYMENT)
        addAttach(modifyTrademarkQuery.id!!, statusMaterials!!, Constant.PATENT_ATTACH_NOTE)
    }

    override fun getTrademarkById(id: Long): TrademarkBean? {
        val trademarkAttachDO = trademarkInfoDOMapper.selectByPrimaryKey(id)
        val trademarkBean = TrademarkBean()
        BeanUtils.copyProperties(trademarkAttachDO, trademarkBean)

        //获取附件信息
        val example = Example(TrademarkAttachDO::class.java)
        val criteria = example.createCriteria()
        criteria.andEqualTo("trademarkInfoId", id)
        criteria.andEqualTo("isDeleted", 0)
        val trademarkAttachDOList = trademarkAttachDOMapper.selectByExample(example)
        trademarkAttachDOList.forEach {
            val trademarkAttachBean = TrademarkAttachBean()
            BeanUtils.copyProperties(it, trademarkAttachBean)
            when (it.type) {
                Constant.TRADEMARK_DRAWING -> {
                    trademarkBean.drawingMaterials!!.add(trademarkAttachBean)
                }
                Constant.TRADEMARK_ACCEPT_REGISTRATION_NUMBER -> {
                    trademarkBean.acceptRegistrationNumberMaterial = trademarkAttachBean
                }
                Constant.TRADEMARK_STATUS -> {
                    trademarkBean.statusMaterials!!.add(trademarkAttachBean)
                }
            }
        }

        return trademarkBean
    }

    override fun getTrademarkList(trademarkListQuery: PagerQuery<TrademarkListQuery>): ListBean<TrademarkListBean>? {
        val page = PageHelper.startPage<TrademarkListBean>(trademarkListQuery.current, trademarkListQuery.pageSize)
        val trademarkList = trademarkInfoDOMapper.getTrademarkList(trademarkListQuery.data)
        return ListBean<TrademarkListBean>().apply {
            this.total = page.total.toInt()
            this.data = trademarkList
        }
    }

    /**
     * 添加附件
     */
    private fun addAttach(trademarkId: Long, attachBeanList: List<TrademarkAttachBean>, type: Int) {
        val current = System.currentTimeMillis() / 1000
        if (attachBeanList.isNotEmpty()) {
            val patentAttachDOList = mutableListOf<TrademarkAttachDO>()
            attachBeanList.forEach {
                val trademarkAttachDO = TrademarkAttachDO().apply {
                    this.trademarkInfoId = trademarkId
                    this.type = type
                    this.status = status
                    this.updatedAt = current
                    this.isDeleted = 0
                    if (it.status == null) it.status = 0
                }
                BeanUtils.copyProperties(it, trademarkAttachDO)
                if (trademarkAttachDO.id != null) {
                    trademarkAttachDOMapper.updateByPrimaryKeySelective(trademarkAttachDO)
                } else {
                    trademarkAttachDO.createdAt = current
                    patentAttachDOList.add(trademarkAttachDO)
                }
            }
            trademarkAttachDOMapper.insertList(patentAttachDOList)
        }
    }
}