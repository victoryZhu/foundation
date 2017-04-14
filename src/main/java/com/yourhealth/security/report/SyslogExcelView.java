package com.yourhealth.security.report;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.yourhealth.foundation.ui.ReportCell;


/**
 * 系统日志excel实现类
 * @author zzm
 *
 */
public class SyslogExcelView extends AbstractExcelView {
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String reportname = (String) model.get("reportname");
		// 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="
				+ URLEncoder.encode(reportname + ".xls", "UTF-8"));

		HSSFSheet sheet = workbook.createSheet(reportname);

		@SuppressWarnings("unchecked")
		List<ReportCell> columnlist = (List<ReportCell>) model.get("columnlist");
		int col_size = columnlist.size();
		CellRangeAddress region = new CellRangeAddress(0, 0, 0, col_size - 1); // 合并单元格，第0行第0列～第0行第col_size - 1列
		sheet.addMergedRegion(region);
		HSSFCellStyle heardercellStyle = workbook.createCellStyle();// 表格头需格式 用createBorderedStyle(workbook);
																	                     
		heardercellStyle.setAlignment(CellStyle.ALIGN_CENTER);		//水平居中
		heardercellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); //垂直居中
		HSSFFont headerfont = workbook.createFont();
		headerfont.setFontHeightInPoints((short) 14);		
		headerfont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  //粗体显示
		//font.setFontName("黑体");
		heardercellStyle.setFont(headerfont);

		HSSFRow header = sheet.createRow(0);           // 第0行，标题
		header.createCell(0).setCellValue(reportname);
		header.getCell(0).setCellStyle(heardercellStyle);

		// 产生标题列
		HSSFCellStyle titlecellStyle = createBorderedStyle(workbook);
		titlecellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  //粗体显示
		titlecellStyle.setFont(font);

		HSSFRow title = sheet.createRow(2); // 第2行，和hearder之间空一行
		for (int i = 0; i < col_size; i++) {
			ReportCell reportcell = columnlist.get(i);
			title.createCell(i).setCellValue(reportcell.getCnName());
			title.getCell(i).setCellStyle(titlecellStyle);
			sheet.setColumnWidth(i, reportcell.getWidth() * 2000);
		}
		
		// double 宽度 = (字符个数 * (字符宽度 - 1) + 5) / (字符宽度 - 1) * 256;
		// 大约500*字符数
		// cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("mm/dd/yyyy"));

		// 填充数据
		HSSFCellStyle cellStyle = createBorderedStyle(workbook);
		// cellStyle.setWrapText(true);  //设置自动换行
		HSSFFont font2 = workbook.createFont();
		// font2.setFontName("仿宋_GB2312");
		font2.setFontHeightInPoints((short) 10);
		cellStyle.setFont(font2);

		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) model.get("list");
		writeContext(sheet, list, columnlist, cellStyle);
	}

	private HSSFCellStyle createBorderedStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setTopBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		return style;
	}

	private <T> void writeContext(HSSFSheet sheet, List<T> list,
			List<ReportCell> collist, HSSFCellStyle cellStyle) {
		int rows = list.size();
		int cols = collist.size();
		String colType = null;
		String columnName = null;
		Object value = null;

		try {
			for (int i = 0; i < rows; i++) {
				HSSFRow row = sheet.createRow(i + 3); // 内容从第3行开始
				T t = (T) list.get(i);
				for (int j = 0; j < cols; j++) {
					ReportCell reportcell = collist.get(j);
					colType = reportcell.getColType().toLowerCase();
					columnName = reportcell.getPropertyName();
					value = PropertyUtils.getProperty(t, columnName);
					if (colType.equalsIgnoreCase("String")) {
						row.createCell(j).setCellValue(value + "");
					} else if (colType.equalsIgnoreCase("Calendar")) {
						row.createCell(j).setCellValue(reportcell.format(value));
					}
					// 待补充
					row.getCell(j).setCellStyle(cellStyle);				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}