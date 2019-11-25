package com.github.vkennke.patchca;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.github.vkennke.patchca.service.Captcha;

public class PatchcaHelper {
    public static void writeCaptcha(Captcha captcha, HttpServletResponse response) {
        setResponseHeaders(response);
        try {
            ServletOutputStream os = response.getOutputStream();
            ImageIO.write(captcha.getImage(), "png", os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }
}
