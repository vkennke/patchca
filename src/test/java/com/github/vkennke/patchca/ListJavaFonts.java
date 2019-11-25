package com.github.vkennke.patchca;

import java.awt.GraphicsEnvironment;

public class ListJavaFonts {
    public static void main(String[] args) {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = env.getAvailableFontFamilyNames();

        for (String font : fonts) {
            System.out.println("<" + font + ">");
        }
    }

}
