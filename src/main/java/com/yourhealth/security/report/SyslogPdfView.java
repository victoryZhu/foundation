package com.yourhealth.security.report;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.yourhealth.foundation.ui.AbstractIText5PdfView;
import com.yourhealth.foundation.ui.ReportCell;
import com.yourhealth.security.domain.SysLog;

/**
 * 系统日志pdf实现类
 * @author zzm
 *
 */
public class SyslogPdfView extends AbstractIText5PdfView {
	
	public SyslogPdfView(){
		super.setDocument(new Document(PageSize.A4,36,36,36,36));
		//文档有一个0.5英寸的左边距和1英寸的右边距，上边距为1.5英寸，下边距为2.5英寸。1 英寸 = 2.54 厘米。
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 定义Pdf文件名称
		String reportname = (String) model.get("reportname");		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(reportname + ".pdf", "UTF-8"));

		@SuppressWarnings("unchecked")
		List<ReportCell> columnlist = (List<ReportCell>) model.get("columnlist");
		int col_size = columnlist.size();		
				
		PdfPTable table = new PdfPTable(col_size);
		table.setWidthPercentage(100);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		//定义每列的宽度
		int w[] = new int[col_size];
		for (int i = 0; i < col_size; i++) {
			w[i] = columnlist.get(i).getWidth();
		}
		table.setWidths(w);
		
		// 标题，合并单元格居中显示
		PdfPCell cell = new PdfPCell(new Paragraph(reportname, getChineseFont(14, Font.BOLD)));
		cell.setColspan(col_size);
		cell.setBorderWidth(0);
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		table.addCell(cell);

		// 定义列标题
		setTitle(columnlist, table);
		
		// 定义列内容
		@SuppressWarnings({ "unchecked" })
		List<SysLog> list = (List<SysLog>) model.get("list");
		writeContext(list, columnlist, table);
		
		document.add(table);		
	}

	// 设置table的正文
	private <T> void writeContext(List<T> list, List<ReportCell> collist, PdfPTable pdfpTable) {
		Font f10 = getChineseFont(10, Font.NORMAL);
		PdfPCell cell = new PdfPCell();
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		int rows = list.size();
		int cols = collist.size();
		String colType = null;
		String columnName = null;
		Object value = null;
		try {
			for (int i = 0; i < rows; i++) {
				T t = (T) list.get(i);
				for (int j = 0; j < cols; j++) {
					ReportCell reportcell = collist.get(j);
					colType = reportcell.getColType().toLowerCase();					
					columnName = reportcell.getPropertyName();					
					value = PropertyUtils.getProperty(t, columnName);
					System.out.println("colType = " + colType + ", columnName = " + columnName + ", value = " + value);
					
					if (colType.equalsIgnoreCase("String")) {
						cell.setPhrase(new Paragraph(value + "", f10));						
					} else if (colType.equalsIgnoreCase("Calendar")) {
						cell.setPhrase(new Paragraph(reportcell.format(value),	f10));						
					}
					pdfpTable.addCell(cell);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	private PdfPTable setTitle(List<ReportCell> columnlist, PdfPTable pdfpTable) {
		PdfPCell cell = new PdfPCell();
		cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
		int col_size = columnlist.size();
		try{
			for (int i = 0; i < col_size; i++) {
				ReportCell reportcell = columnlist.get(i);
				cell.setPhrase(new Paragraph(reportcell.getCnName(),	getChineseFont(12, Font.BOLD)));
				System.out.println("reportcell.getCnName()="+reportcell.getCnName());
				pdfpTable.addCell(cell);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return pdfpTable;
	}

	private static final Font getChineseFont(float size, int style) {
		Font FontChinese = null;
		try {
			BaseFont bfChinese = BaseFont.createFont("STSong-Light",	"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			FontChinese = new Font(bfChinese, size, style);  // Font.NORMAL
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		return FontChinese;
	}
}