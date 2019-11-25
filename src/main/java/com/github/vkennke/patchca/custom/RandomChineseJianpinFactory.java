package com.github.vkennke.patchca.custom;

import com.github.vkennke.patchca.random.RandUtils;
import com.github.vkennke.patchca.random.SpellUtils;
import com.github.vkennke.patchca.word.WordBean;
import com.github.vkennke.patchca.word.WordFactory;

public class RandomChineseJianpinFactory implements WordFactory {
    @Override
    public WordBean getNextWord() {
        String randChinese = RandUtils.randChinese(4);

        return new WordBean("请输入“" + randChinese + "”的简拼",
                SpellUtils.getFirst(randChinese), "请输入简拼");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return new String[]{"宋体"};
    }
}
