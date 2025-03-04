package com.second.second.sales;


import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.StringWriter;
import java.util.List;

public class TemplateHelper {

    public static  String getTemplate(final List<?> toTemplate){

        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("C:/Users/Owner/Downloads/second/src/main/resources/template.html");

        StringWriter writer = new StringWriter();
        mustache.execute(writer, toTemplate);
        String html = writer.toString();

        return html;

    }
}
