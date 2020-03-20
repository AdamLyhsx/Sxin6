package com.adamly.xin6.view;

import com.adamly.xin6.service.PdfExportService;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/3/18 11:15
 */
//真正实现pdf视图，原pdf视图只是一个抽象类
public class PdfView extends AbstractPdfView {
    private PdfExportService pdfExportService = null;

    public PdfView(PdfExportService pdfExportService) {
        this.pdfExportService = pdfExportService;
    }

    @Override
//    调用pdf导出服务的导出方法完成数据渲染
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        pdfExportService.mack(map,document,pdfWriter,httpServletRequest,httpServletResponse);
    }
}
