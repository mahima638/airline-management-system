package TY_PROJECT.Programs.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import TY_PROJECT.Programs.service.PdfService;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class BoardingPassController {
    
    @Autowired
    private PdfService pdfService;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @GetMapping("/download-boarding-pass")
    public void generateBoardingPass(HttpServletResponse response,
                                     @RequestParam Map<String, String> params,
                                     @RequestParam(required = false) String bookingId,
                                     @RequestParam(required = false) String tripType)throws Exception {
        Map<String, Object> data = new HashMap<>(params);

        if (tripType != null) {
            data.put("tripType", tripType);
        } else {
            data.put("tripType", "Oneway"); 
        }    
        if (bookingId != null) {
            data.put("bookingId", bookingId);
        } else {
            data.put("bookingId", "DEFAULT_ID"); 
        }

        
        System.out.println("Final Data: " + data);

        Context context = new Context();
        context.setVariables(data);

        String htmlContent = templateEngine.process("boarding-pass", context);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=boarding-pass.pdf");

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(htmlContent);
            renderer.layout();
            renderer.createPDF(outputStream);
            renderer.finishPDF();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}