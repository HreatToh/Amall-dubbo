package org.amall.dubbo.utils;

import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateConfig;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;

import java.util.Map;

public class TextTemplateUtils {

    public static String formatStr(String text, Map<String, Object> map) {
        //自动根据用户引入的模板引擎库的jar来自动选择使用的引擎
        //TemplateConfig为模板引擎的选项，可选内容有字符编码、模板路径、模板加载方式等，默认通过模板字符串渲染
        TemplateEngine engine = TemplateUtil.createEngine(new TemplateConfig());
        Template template = engine.getTemplate(text);
        return template.render(map);
    }
}
