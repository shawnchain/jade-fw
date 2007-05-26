package com.nonsoft.discuss.filter;

import junit.framework.TestCase;

public class DefaultContentFilterTest extends TestCase {
    // private static final String REG_LINK = "\\[http:\\/\\/(\\w\\.)*[a-z,A-Z]*\\/\\]";
    private static final String REG_LINK = "^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\\\?([^#]*))?(#(.*))?]";

    private static final String a = "\\{{3}(.*){replace-with}{code}$1";

    private static final String b = "\\}{3}(.*){replace-with}{code}$1";

    private static final String H1 = "={1} (.+) ={1}";

    private static final String URL = "((http://|https://|ftp://)+(.*))";

    public void testRender() throws Exception {
//        Pattern p = Pattern.compile(URL);
//        Matcher m = p.matcher("[http://www.google.com/] [http://www.sina.com/] [http://www.abc.com/] good");
//        boolean b = m.matches();
//        System.out.println("");
//        System.out.println(b);
//
//        System.out.println(m.replaceAll("<a href=\"$0\">$0</a>"));
//
//        p = Pattern.compile(H1);
//        m = p.matcher("= foo bar =");
//        System.out.println(m.replaceAll("<h1>$1</h1>"));
        
        IContentFilter filter = new DefaultContentFilter();
        String s = "http://www.google.com/";
        System.out.println(filter.apply(s));
        
        s = "[http://www.google.com/]";
        System.out.println(filter.apply(s));
        
        s = "[http://www.google.com/ google]";
        System.out.println(filter.apply(s));
        //System.out.println(new DefaultContentFilter().render("http://www.google.com/"));
    }
    
//    public void testParse(){
//        //String s = "[foo[] bar], is foobar\n and [barFoo] is bar foo";
//        String s = "Please visit [http://www.google.com xxx] ";
//        StringBuffer result = new StringBuffer();
//        parse(s,result);
//        System.out.println(s);
//        System.out.println(result);
//        result.setLength(0);
//        parse("[http://www.abc.com]", result);
//        System.out.println(result);
//    }
}
