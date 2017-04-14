package com.yourhealth.foundation.ui;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

public abstract class AbstractIText5PdfView extends AbstractView {
	
	Document document = null;
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}	

	public AbstractIText5PdfView() {
		setContentType("application/pdf");
	}

	@Override
	protected boolean generatesDownloadContent() {
		return true;
	}

	@Override
	protected final void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ByteArrayOutputStream baos = createTemporaryOutputStream();
		if(document == null) document = new Document(PageSize.A4);		
		PdfWriter writer = newWriter(document, baos);		
		prepareWriter(model, writer, request);
		buildPdfMetadata(model, document, request);
		writer.setInitialLeading(16);   //
		document.open();
		buildPdfDocument(model, document, writer, request, response);
		document.close();
		writeToResponse(response, baos);
	}

	protected PdfWriter newWriter(Document document, OutputStream os)
			throws DocumentException {
		return PdfWriter.getInstance(document, os);
	}

	protected void prepareWriter(Map<String, Object> model, PdfWriter writer,
			HttpServletRequest request) throws DocumentException {
		writer.setViewerPreferences(getViewerPreferences());
	}

	protected int getViewerPreferences() {
		return PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage;
	}

	protected void buildPdfMetadata(Map<String, Object> model,
			Document document, HttpServletRequest request) {
	}

	/**
	 *  抽象方法，由子类实现具体的Pdf内容
	 * @param model
	 * @param document
	 * @param writer
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	protected abstract void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception;
}
