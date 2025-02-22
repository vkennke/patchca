/*
 * Copyright (c) 2009 Piotr Piastucki
 * 
 * This file is part of Patchca CAPTCHA library.
 * 
 *  Patchca is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  Patchca is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Patchca. If not, see <http://www.gnu.org/licenses/>.
 */
package com.github.vkennke.patchca.service;

import java.awt.image.BufferedImage;

import com.github.vkennke.patchca.background.BackgroundFactory;
import com.github.vkennke.patchca.color.ColorFactory;
import com.github.vkennke.patchca.filter.FilterFactory;
import com.github.vkennke.patchca.font.FontFactory;
import com.github.vkennke.patchca.text.renderer.TextRenderer;
import com.github.vkennke.patchca.word.WordBean;
import com.github.vkennke.patchca.word.WordFactory;

public abstract class AbstractCaptchaService implements CaptchaService {

    protected FontFactory fontFactory;
    protected WordFactory wordFactory;
    protected ColorFactory colorFactory;
    protected BackgroundFactory backgroundFactory;
    protected TextRenderer textRenderer;
    protected FilterFactory filterFactory;
    protected int width;
    protected int height;

    public void setFontFactory(FontFactory fontFactory) {
        this.fontFactory = fontFactory;
    }

    public void setWordFactory(WordFactory wordFactory) {
        this.wordFactory = wordFactory;
    }

    public void setColorFactory(ColorFactory colorFactory) {
        this.colorFactory = colorFactory;
    }

    public void setBackgroundFactory(BackgroundFactory backgroundFactory) {
        this.backgroundFactory = backgroundFactory;
    }

    public void setTextRenderer(TextRenderer textRenderer) {
        this.textRenderer = textRenderer;
    }

    public void setFilterFactory(FilterFactory filterFactory) {
        this.filterFactory = filterFactory;
    }

    public FontFactory getFontFactory() {
        return fontFactory;
    }

    public WordFactory getWordFactory() {
        return wordFactory;
    }

    public ColorFactory getColorFactory() {
        return colorFactory;
    }

    public BackgroundFactory getBackgroundFactory() {
        return backgroundFactory;
    }

    public TextRenderer getTextRenderer() {
        return textRenderer;
    }

    public FilterFactory getFilterFactory() {
        return filterFactory;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public Captcha getCaptcha() {
        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        backgroundFactory.fillBackground(bufImage);
        WordBean wordBean = wordFactory.getNextWord();
        fontFactory.setWord(wordBean.getWord());
        textRenderer.draw(wordBean.getWord(), bufImage, fontFactory, colorFactory);
        bufImage = filterFactory.applyFilters(bufImage);
        return new Captcha(wordBean.getWord(), wordBean.getAnswer(), bufImage, wordBean.getTips());
    }

}
