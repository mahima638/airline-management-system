package TY_PROJECT.Programs.service;

import java.util.Map;

import org.hibernate.sql.Template;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.pdf.codec.Base64.OutputStream;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class PdfService {
	private final TemplateEngine  templateEngine;
	public  PdfService(TemplateEngine templateEngine) {
		this.templateEngine=templateEngine;
	}
	public void generateBoardingPass(HttpServletResponse response, Map<String , Object>data)throws Exception{
		Context context= new Context();
		context.setVariables(data);
	
	String htmlContent=templateEngine.process("boarding-pass",context);
	response.setContentType("application/pdf");
	response.setHeader("Content-Dispositio","attachment; filename=boarding-pass.pdf");
    ServletOutputStream outputStream= response.getOutputStream();
    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(htmlContent);
    renderer.layout();
    renderer.createPDF(outputStream,false);
   outputStream.close();
}}