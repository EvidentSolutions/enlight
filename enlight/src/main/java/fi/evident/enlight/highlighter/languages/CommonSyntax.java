package fi.evident.enlight.highlighter.languages;

import fi.evident.enlight.highlighter.Token;
import fi.evident.enlight.highlighter.TokenType;
import fi.evident.enlight.highlighter.support.*;

import java.util.Set;
import java.util.regex.Pattern;

import static java.util.Collections.emptySet;

public abstract class CommonSyntax extends TokenMatcherBasedSyntaxHighlighter {

    protected Set<String> keywords() {
        return emptySet();
    }

    @Override
    protected final TokenMatcher createTokenMatcher() {
        return TokenMatcher.oneOf(
                singleLineComment().toTokenMatcher(TokenType.COMMENT),
                multiLineComment().toTokenMatcher(TokenType.COMMENT),
                number().toTokenMatcher(TokenType.LITERAL_NUMBER),
                symbolOrKeyword(),
                operator().toTokenMatcher(TokenType.OPERATOR),
                literalString().toTokenMatcher(TokenType.LITERAL_STRING),
                punctuation().toTokenMatcher(TokenType.PUNCTUATION),
                whitespace().toTokenMatcher(TokenType.WHITESPACE));
    }

    protected StringMatcher multiLineComment() {
        return StringMatcher.NO_MATCH;
    }

    protected String singleLineCommentStart() {
        return null;
    }

    protected final StringMatcher singleLineComment() {
        String commentStart = singleLineCommentStart();
        return commentStart != null
            ? regex(Pattern.quote(commentStart) + "[^\\n]*")
            : StringMatcher.NO_MATCH;
    }

    protected StringMatcher whitespace() {
        return regex("\\s+");
    }

    private TokenMatcher symbolOrKeyword() {
        final StringMatcher symbolMatcher = symbol();
        return new TokenMatcher() {
            @Override
            public Token match(final String input) {
                String token = symbolMatcher.match(input);
                if (token != null)
                    return new Token(token, keywords().contains(token) ? TokenType.KEYWORD : TokenType.UNKNOWN);
                else
                    return null;
            }
        };
    }

    protected StringMatcher symbol() {
        return regex("[a-zA-Z_][a-zA-Z0-9_]*");
    }

    protected StringMatcher number() {
        return regex("\\d+(\\.\\d*)?");
    }

    protected StringMatcher operator() {
        return StringMatcher.NO_MATCH;
    }

    protected StringMatcher literalString() {
        return literalStringDouble().or(literalStringSingle());
    }

    protected StringMatcher literalStringSingle() {
        return literalString('\'');
    }

    protected StringMatcher literalStringDouble() {
        return literalString('"');
    }

    private static StringMatcher literalString(final char quote) {
        return new StringMatcher() {
            @Override
            public String match(String input) {
                if (input.isEmpty() || input.charAt(0) != quote) return null;

                boolean escape = false;
                for (int i = 1; i < input.length(); i++) {
                    char ch = input.charAt(i);
                    if (escape) {
                        escape = false;
                    } else if (ch == '\\') {
                        escape = true;
                    } else if (ch == quote) {
                        return input.substring(0, i+1);
                    }
                }

                return null;
            }
        };
    }

    protected StringMatcher punctuation() {
        return regex("[\\(\\)\\.\\,]");
    }
}
