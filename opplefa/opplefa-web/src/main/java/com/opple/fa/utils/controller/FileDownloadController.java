package com.opple.fa.utils.controller;

import com.opple.util.Env;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/download")
public class FileDownloadController {

    private static final Logger LOG = LoggerFactory.getLogger(FileDownloadController.class);

    @RequestMapping("/downLoadFile")
    public void downLoadFile(
            HttpServletResponse response, HttpServletRequest request, String downloadFileName) {

        String downloadPath = Env.getProperty(Env.SERVER_FILE_PATH) + downloadFileName;
        InputStream fis = null;
        ServletContext application = request.getSession().getServletContext();
        OutputStream os = null;
        try {
            fis = new BufferedInputStream(new FileInputStream(downloadPath));
            byte[] bytes = IOUtils.toByteArray(fis);
            response.reset();
            String fileName = downloadFileName;
            String userAgent = request.getHeader("User-Agent");
            //针对IE或者以IE为内核的浏览器：
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                //非IE浏览器的处理：
                fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
            response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
            // 先去掉文件名称中的空格,然后转换编码格式为utf-8,保证不出现乱码,这个文件名称用于浏览器的下载框中自动显示的文件名
            //response.addHeader("Content-Disposition", "attachment;filename=" + new String(execute.getResult().getFileName().replaceAll(" ", "").getBytes("utf-8"), "iso8859-1"));
            //response.addHeader("Content-Length", "" + bytes.length); //此处需要验证是否正确
            //设置文件MIME类型
            response.setContentType(application.getMimeType(fileName));
            response.setCharacterEncoding("UTF-8");
            os = new BufferedOutputStream(response.getOutputStream());
            os.write(bytes);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
