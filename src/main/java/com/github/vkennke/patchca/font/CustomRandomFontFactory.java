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
package com.github.vkennke.patchca.font;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;

import com.github.vkennke.patchca.random.RandUtils;
import com.github.vkennke.patchca.word.WordFactory;

public class CustomRandomFontFactory implements FontFactory {
    protected List<String> families;
    protected int minSize;
    protected int maxSize;
    private String word;
    private WordFactory wordFactory;

    public CustomRandomFontFactory() {
        families = new ArrayList<>();
        String[] fontNames = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();
        for (String fontName : fontNames) {
            if (canDisplay(fontName, 'a')) {
                //... Display only fonts that have the alphabetic characters.
                families.add(fontName);
            }
        }

        minSize = 45;
        maxSize = 45;
    }

    public boolean canDisplay(String fontName, char ch) {
        Font f = new Font(fontName, Font.PLAIN, 12);
        return f.canDisplay(ch);
    }


    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public Font getFont(int index) {
        boolean bold = RandUtils.randBoolean();

        int size = minSize;
        if (maxSize - minSize > 0) {
            size += RandUtils.randInt(maxSize - minSize);
        }

        if (wordFactory != null) {
            String[] supportedFamilies = wordFactory.getSupportedFontFamilies();
            if (supportedFamilies != null && supportedFamilies.length > 0) {
                String family = supportedFamilies[RandUtils.randInt(supportedFamilies.length)];
                System.out.println("family:" + family);
                return new Font(family,
                        bold ? Font.BOLD : Font.PLAIN, size);
            }
        }

        String family;
        TRY:
        while (true) {
            family = families.get(RandUtils.randInt(families.size()));
            for (char ch : word.toCharArray())
                if (!canDisplay(family, ch)) continue TRY;

            break;
        }
        System.out.println("family:" + family);
        return new Font(family, bold ? Font.BOLD : Font.PLAIN, size);
    }

    @Override
    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public void setWordFactory(WordFactory wordFactory) {
        this.wordFactory = wordFactory;
    }

}
