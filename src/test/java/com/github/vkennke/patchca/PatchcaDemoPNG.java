package com.github.vkennke.patchca;

import java.awt.Color;
import java.io.FileOutputStream;

import com.github.vkennke.patchca.color.SingleColorFactory;
import com.github.vkennke.patchca.custom.ConfigurableCaptchaService;
import com.github.vkennke.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.vkennke.patchca.filter.predefined.DiffuseRippleFilterFactory;
import com.github.vkennke.patchca.filter.predefined.DoubleRippleFilterFactory;
import com.github.vkennke.patchca.filter.predefined.MarbleRippleFilterFactory;
import com.github.vkennke.patchca.filter.predefined.WobbleRippleFilterFactory;
import com.github.vkennke.patchca.utils.encoder.EncoderHelper;


public class PatchcaDemoPNG {

    public static void main(String[] args) throws Exception {
        for (int counter = 0; counter < 5; counter++) {
            ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
            cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
            switch (counter % 5) {
            case 0:
                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
                break;
            case 1:
                cs.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 2:
                cs.setFilterFactory(new DoubleRippleFilterFactory());
                break;
            case 3:
                cs.setFilterFactory(new WobbleRippleFilterFactory());
                break;
            case 4:
                cs.setFilterFactory(new DiffuseRippleFilterFactory());
                break;
            }
            FileOutputStream fos = new FileOutputStream("patcha_demo" + counter + ".png");
            EncoderHelper.getChallangeAndWriteImage(cs, "png", fos);
            fos.close();
        }
    }
}
