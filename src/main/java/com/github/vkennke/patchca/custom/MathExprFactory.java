package com.github.vkennke.patchca.custom;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.github.vkennke.patchca.random.RandUtils;
import com.github.vkennke.patchca.word.WordBean;
import com.github.vkennke.patchca.word.WordFactory;

public class MathExprFactory implements WordFactory {
    private static String[] operations = new String[] { "加+", "减", "乘X" };
    private static char[] exprOperations = new char[] { '+', '-', '*' };

    public char getOperation(int index) {
        String operation = operations[index];
        return operation.charAt(RandUtils.randInt(operation.length()));
    }

    private static ScriptEngine engine;

    static {
        ScriptEngineManager mgr = new ScriptEngineManager();
        engine = mgr.getEngineByName("JavaScript");
    }

    private String evalExpr(String expr) {
        try {
            return String.valueOf(engine.eval(expr));
        } catch (ScriptException e) {
            return "error";
        }
    }

    @Override
    public WordBean getNextWord() {
        int a = RandUtils.randInt(9) + 1;
        int b = RandUtils.randInt(9) + 1;
        int c = RandUtils.randInt(9) + 1;

        int op1 = RandUtils.randInt(operations.length);
        int op2 = RandUtils.randInt(operations.length);

        String answer = evalExpr(String.valueOf(a) + exprOperations[op1] + b + exprOperations[op2] + c);

        String word = MathArithmeticFactory.rand(a) + getOperation(op1) + b + getOperation(op2)
                + c + "=?";
        return new WordBean(word, answer, "请输入图片中?代表的数字");
    }

    @Override
    public String[] getSupportedFontFamilies() {
        return new String[]{"宋体"};
    }

}
