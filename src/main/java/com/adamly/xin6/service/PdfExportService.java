package com.adamly.xin6.service;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author adamly
 * @version 1.0
 * @date 2020/3/18 11:09
 */
//PDF导出服务，由于每个接口所需的导出服务不同，所以没有具体实现类，只有实现对象
public interface PdfExportService {
//    PDF导出服务
    public void mack(Map<String,Object> model, Document document,
                     PdfWriter writer, HttpServletRequest request, HttpServletResponse response);
}
