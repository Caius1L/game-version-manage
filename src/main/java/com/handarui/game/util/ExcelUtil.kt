package com.handarui.game.util

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.xssf.usermodel.XSSFCell
import java.util.*

object ExcelUtil {
    fun getXValue(xssfCell: XSSFCell?): String? {
        return when {
            xssfCell == null -> null
            xssfCell.cellTypeEnum == CellType.STRING -> xssfCell.stringCellValue
            xssfCell.cellTypeEnum == CellType.NUMERIC -> xssfCell.numericCellValue.toString()
            else -> ""
        }
    }

    fun getDate(cell: XSSFCell?): Date? {
        if (cell?.cellTypeEnum == CellType.NUMERIC) {
            val numericCellValue = cell.numericCellValue
            return org.apache.poi.ss.usermodel.DateUtil.getJavaDate(numericCellValue)
        }
        return null
    }
}
