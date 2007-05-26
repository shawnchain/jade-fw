package com.nonsoft.discuss.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultContentFilter implements IContentFilter {
    // private static final String P_URL = "^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\\\?([^#]*))?(#(.*))?";
    public String apply(String input) {
        if (input == null) {
            return input;
        }

        StringBuffer out = new StringBuffer(input.length() * 2);
        parse(input, out);
        return out.toString();
    }

    //FIXME configurale rule ?
    private static final IMatchRule[] rules = new IMatchRule[] { new UrlMatchRule(), new AuthorMatchRule()};

    private static final int STATE_BODY = 0;

    private static final int STATE_TOKEN = 1;

    //private static final int STATE_ERROR = 255;

    private static void parse(String input, StringBuffer outBuffer) {
        BufferedReader r = new BufferedReader(new StringReader(input));
        try {
            do {
                String aline = r.readLine();
                if (aline == null) {
                    break;
                }
                if(aline.trim().length() == 0){
                    outBuffer.append("<p></p>");
                    continue;
                }
                StringBuffer tokenBuffer = new StringBuffer();
                int state = STATE_BODY;
                for (int i = 0; i < aline.length(); i++) {
                    char c = aline.charAt(i);
                    if ('[' == c) {
                        switch (state) {
                        case STATE_BODY:
                            state = STATE_TOKEN;
                            break;
                        case STATE_TOKEN:
                            // met nested "[", simply append or throw exception ?
                            // state = STATE_ERROR;
                            tokenBuffer.append(c);
                        }
                    } else if (']' == c) {
                        switch (state) {
                        case STATE_TOKEN:
                            // Process the token buffer
                            processAndAppendToken(tokenBuffer.toString(), outBuffer);
                            tokenBuffer.setLength(0);
                            state = STATE_BODY;
                            break;
                        case STATE_BODY:
                            outBuffer.append(c);
                        }
                    } else {
                        switch (state) {
                        case STATE_BODY:
                            outBuffer.append(c);
                            break;
                        case STATE_TOKEN:
                            tokenBuffer.append(c);
                            break;
                        }
                        // resultBuffer.append(c);
                    }
                }
                //outBuffer.append("<br/>\n");
            } while (true);
        } catch (IOException ioe) {

        }
    }

    private static void processAndAppendToken(String input, StringBuffer outBuffer) {
        for (int i = 0; i < rules.length; i++) {
            String result = rules[i].match(input);
            if (result != null) {
                outBuffer.append(result);
                return;
            }
        }
        outBuffer.append("[").append(input).append("]");
    }
    
    private static interface IMatchRule {
        public String match(String input);
    }
    
    private static class AuthorMatchRule implements IMatchRule{
        public String match(String input) {
            if(input.trim().equalsIgnoreCase("author")){
                return "Shawn Chain";
            }
            return null;
        }
        
    }

    private static class UrlMatchRule implements IMatchRule {
        private static final Pattern URL_PATTERN = Pattern.compile("(http://|https://|ftp://)+(.)*");

        public String match(String input) {
            String url = input;
            String name = input;
            int i = input.indexOf(' ');
            if (i > 0) {
                url = input.substring(0, i).trim();
                name = input.substring(i).trim();
            }
            if (name.length() == 0) {
                name = url;
            }
            Matcher m = URL_PATTERN.matcher(url);
            if (m.matches()) {
                StringBuffer sb = new StringBuffer();
                sb.append("<a target=\"_blank\" href=\"").append(url).append("\">").append(name).append("</a>");
                return sb.toString();
            } else {
                return null;
            }
        }
    }
}
