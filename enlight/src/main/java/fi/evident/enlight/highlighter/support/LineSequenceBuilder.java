/*
 * Copyright (c) 2011 Evident Solutions Oy
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package fi.evident.enlight.highlighter.support;

import fi.evident.enlight.highlighter.Token;
import fi.evident.enlight.highlighter.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds a sequence of lines from token stream containing line breaks. The tokens
 * that have lines breaks are split into multiple tokens on separate lines.
 */
final class LineSequenceBuilder {

    private final List<List<Token>> result = new ArrayList<List<Token>>();
    private List<Token> currentLine = new ArrayList<Token>();
    private TokenType currentTokenType = null;
    private final StringBuilder currentTokenText = new StringBuilder();

    private LineSequenceBuilder() {
    }

    static List<List<Token>> normalizeTokensToLines(Iterable<Token> tokens) {
        LineSequenceBuilder builder = new LineSequenceBuilder();

        for (Token token : tokens)
            builder.addToken(token);

        return builder.toResult();
    }

    private void addToken(Token token) {
        finishToken();
        currentTokenType = token.getTokenType();
        
        String text = token.getText();
        for (int i = 0; i < text.length(); i++)
            addCharacter(text.charAt(i));
    }

    private void addCharacter(char ch) {
        if (ch == '\n') {
            finishToken();
            result.add(currentLine);
            // TODO: optimize this by not allocating new array lists, but by producing sub-lists
            currentLine = new ArrayList<Token>();
        } else {
            currentTokenText.append(ch);
        }
    }

    private void finishToken() {
        if (currentTokenText.length() != 0) {
            currentLine.add(new Token(currentTokenText.toString(), currentTokenType));
            currentTokenText.setLength(0);
        }
    }

    private List<List<Token>> toResult() {
        finishToken();
        if (!currentLine.isEmpty())
            result.add(currentLine);
        return result;
    }
}
