package com.github.vkennke.patchca.custom;

import com.github.vkennke.patchca.random.RandUtils;
import com.github.vkennke.patchca.word.WordBean;
import com.github.vkennke.patchca.word.WordFactory;

public class RandomChineseFactory implements WordFactory {
    @Override
    public WordBean getNextWord() {
        String randChinese = RandUtils.randChinese((3 + RandUtils.randInt(2)));

        return new WordBean(randChinese, randChinese, "请输入图片中的文字");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return null;
    }
}
