import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class StripComments {

    public static final char NEW_LINE = '\n';
    public static final char WHITE_SPACE = ' ';


    @Test
    public void whenTextIncludesComments_ThenGetRidOfThoseComments() {
        String text = stripComments("a \n" +
                " b \n" +
                "c ",  new String[] {"#", "a", "b", "c"});
        System.out.printf("Text: \n%d\n", text.length());
        assertThat(text, notNullValue());
    }

    /**
        TAIL RECURSIION SOLUTION
     */
    public static String stripComments(String text, String[] commentSymbols) {
        StringBuilder result = new StringBuilder();

        if(commentSymbols.length == 0) return text;

        final char[] ts = text.toCharArray();

        String[] _commentSymbols = new String[commentSymbols.length - 1];

        for(int i = 0; i < _commentSymbols.length; i++) {
            _commentSymbols[i] = commentSymbols[i];
        }

        final int LAST_COMMENT_SYMBOL = commentSymbols.length - 1;
        char commentSym = commentSymbols[LAST_COMMENT_SYMBOL].charAt(0);
        int commentStartsAt = -1;
        for(int t = 0; t < ts.length; t++) {
            if(NEW_LINE == ts[t]) {
                if(commentStartsAt >= 0) {
                    commentStartsAt = -1;
                }
                removeWhiteSpacesAtEnd(result);
                result.append(NEW_LINE);
                continue;
            }

            if(commentStartsAt >= 0) {
                continue;
            }

            if(commentSym == ts[t]) {
                commentStartsAt = t;
            }

            else {
                result.append(ts[t]);
            }
        }

        removeWhiteSpacesAtEnd(result);
        return stripComments(result.toString(), _commentSymbols);
    }

    public static void removeWhiteSpacesAtEnd(StringBuilder strBuilder) {
        int ws = 0;
        if(strBuilder.length() > 0) {
            for(int i = strBuilder.length() - 1; (i >= 0 && strBuilder.charAt(i) == WHITE_SPACE); i--) {
                ws++;
            }
            strBuilder.setLength(strBuilder.length() - ws);
        }
    }
}
