package com.github.vkennke.patchca;

import java.util.Random;

import com.github.vkennke.patchca.background.GradientBackgroundFactory;
import com.github.vkennke.patchca.custom.ChineseIdiomFactory;
import com.github.vkennke.patchca.custom.ConfigurableCaptchaService;
import com.github.vkennke.patchca.filter.predefined.CurvesRippleFilterFactory;
import com.github.vkennke.patchca.filter.predefined.DoubleRippleFilterFactory;
import com.github.vkennke.patchca.filter.predefined.RandomFilterFactory;
import com.github.vkennke.patchca.filter.predefined.WobbleRippleFilterFactory;
import com.github.vkennke.patchca.font.CustomRandomFontFactory;
import com.github.vkennke.patchca.service.Captcha;

public class IdiomsPatchca {
    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
    private static Random random = new Random();

    static {
        cs.setBackgroundFactory(new GradientBackgroundFactory());
        //        cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setColorFactory(Patchca::getColorFactory);

        // 随机字体生成器
        //        RandomFontFactory fontFactory = new RandomFontFactory();
        CustomRandomFontFactory fontFactory = new CustomRandomFontFactory();
        fontFactory.setMaxSize(30);
        fontFactory.setMinSize(22);
        cs.setFontFactory(fontFactory);

        cs.setWordFactory(new ChineseIdiomFactory());

        RandomFilterFactory filterFactory = new RandomFilterFactory();
        filterFactory.addFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
        //        filterFactory.addFilterFactory(new MarbleRippleFilterFactory());// 糊
        filterFactory.addFilterFactory(new DoubleRippleFilterFactory());
        filterFactory.addFilterFactory(new WobbleRippleFilterFactory());
        //        filterFactory.addFilterFactory(new DiffuseRippleFilterFactory());// 糊
        cs.setFilterFactory(filterFactory);

        // 验证码图片的大小
        cs.setWidth(90);
        cs.setHeight(40);
    }

    public static Captcha next() {
        return cs.getCaptcha();
    }
}
