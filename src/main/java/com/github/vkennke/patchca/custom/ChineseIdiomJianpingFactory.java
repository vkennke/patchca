package com.github.vkennke.patchca.custom;

import com.github.vkennke.patchca.random.RandUtils;
import com.github.vkennke.patchca.random.SpellUtils;
import com.github.vkennke.patchca.random.StrUtils;
import com.github.vkennke.patchca.word.WordBean;
import com.github.vkennke.patchca.word.WordFactory;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

public class ChineseIdiomJianpingFactory implements WordFactory {
    protected static String[] idioms;

    static {
        String str = StrUtils.loadClasspathResourceToString(
                "/com/github/vkennke/patchca/chineseidioms.txt");
        Iterable<String> split = Splitter.onPattern("\\s+").omitEmptyStrings().split(str);
        idioms = Iterables.toArray(split, String.class);

    }

    public WordBean getNextWord() {
        int nextInt = RandUtils.randInt(idioms.length);
        String answer = idioms[nextInt];

        return new WordBean(answer, SpellUtils.getFirst(answer), "请输入简拼");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return null;
    }

}
