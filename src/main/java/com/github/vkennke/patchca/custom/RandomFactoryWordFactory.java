package com.github.vkennke.patchca.custom;

import java.util.List;

import com.github.vkennke.patchca.random.RandUtils;
import com.github.vkennke.patchca.word.WordBean;
import com.github.vkennke.patchca.word.WordFactory;

public class RandomFactoryWordFactory implements WordFactory {
    private List<WordFactory> factories;
    private static ThreadLocal<WordFactory> wordFactory = new ThreadLocal<>();

    public RandomFactoryWordFactory(List<WordFactory> factories) {
        this.factories = factories;
    }

    @Override
    public WordBean getNextWord() {
        WordFactory value = factories.get(RandUtils.randInt(factories.size()));
        wordFactory.set(value);
        return value.getNextWord();
    }

    @Override
    public String[] getSupportedFontFamilies() {
        WordFactory wf = wordFactory.get();
        return wf.getSupportedFontFamilies();
    }


}
