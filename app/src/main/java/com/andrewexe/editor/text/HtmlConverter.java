package com.andrewexe.editor.text;

public abstract class HtmlConverter {
    private String originalText;

    public HtmlConverter(String original) {
        this.originalText = original;
    }

    protected String getHeader() {
        return """
                <head>
                    <meta charset="UTF-8" />
                    <title>title</title>
                </head>
                        """;
    }

    protected String wrapHeading1(String heading)
    {
        StringBuilder sb = new StringBuilder();
        return sb.append("<h1>").append(heading).append("</h1>").toString();
    }

    protected String wrapHeading2(String heading)
    {
        StringBuilder sb = new StringBuilder();
        return sb.append("<h2>").append(heading).append("</h2>").toString();
    }

    protected String wrapHeading3(String heading)
    {
        StringBuilder sb = new StringBuilder();
        return sb.append("<h3>").append(heading).append("</h3>").toString();
    }

    protected String wrapParagraph(String text)
    {
        StringBuilder sb = new StringBuilder();
        return sb.append("<p>").append(text).append("</p>").toString();
    }

    protected String wrapHtml(String parts){
        //!<DOCTYPE html>\n<html> 
        StringBuilder sb = new StringBuilder();
        return sb.append("!<DOCTYPE html>\\n" + //
                        "<html>").append(parts).append("</html>").toString();
    }

    public String getHtml()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader()); 
        builder.append(getBody());
        String finalHtml = wrapHtml(builder.toString());
        return finalHtml;
    }

    public String getBody()
    {
        return "<body><h1>Hello</h1></body>";
    }
}
