package com.github.vkennke.patchca.custom;

import com.github.vkennke.patchca.random.RandUtils;
import com.github.vkennke.patchca.random.SpellUtils;
import com.github.vkennke.patchca.word.WordBean;
import com.github.vkennke.patchca.word.WordFactory;

public class RandomChineseQuanpinFactory implements WordFactory {
    @Override
    public WordBean getNextWord() {
        String randChinese = RandUtils.randChinese(2);

        return new WordBean("请输入“" + randChinese + "”的全拼",
                SpellUtils.getFull(randChinese), "请输入全拼");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return null;
    }
}
