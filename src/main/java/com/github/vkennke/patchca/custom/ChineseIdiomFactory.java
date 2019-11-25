package com.github.vkennke.patchca.custom;

import com.github.vkennke.patchca.random.RandUtils;
import com.github.vkennke.patchca.random.StrUtils;
import com.github.vkennke.patchca.word.WordBean;
import com.github.vkennke.patchca.word.WordFactory;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

public class ChineseIdiomFactory implements WordFactory {

    protected static String[] idioms;

    static {
        String str = StrUtils.loadClasspathResourceToString(
                "/com/github/vkennke/patchca/idoms2.txt");
        Iterable<String> split = Splitter.onPattern("\\s+").omitEmptyStrings().split(str);
        idioms = Iterables.toArray(split, String.class);
    }

    public WordBean getNextWord() {
        int nextInt = RandUtils.randInt(idioms.length);
        String answer = idioms[nextInt];

        return new WordBean(answer, answer, "请输入图片中的文字");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return null;
    }

}
