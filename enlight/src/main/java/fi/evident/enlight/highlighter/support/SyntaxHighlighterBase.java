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

import fi.evident.enlight.highlighter.HighlightedSource;
import fi.evident.enlight.highlighter.SyntaxHighlighter;
import fi.evident.enlight.highlighter.Token;

import java.util.List;

/**
 * A simple base trait for syntax-highlighters that will make implementation
 * somewhat simpler. The subclass can return tokens that span multiple lines
 * and this trait will take care of normalizing the tokens and creating
 * a {@link HighlightedSource}.
 */
public abstract class SyntaxHighlighterBase extends SyntaxHighlighter {

    /**
     * Perform the general highlighting, converting the internal stream of indexed
     * tokens into a more convenient representation.
     */
    @Override
    public final HighlightedSource highlight(String src) {
        String source = src.replace("\r\n", "\n");

        Iterable<Token> tokens = new UnknownMergingTokenStream(tokensForSource(source));

        List<List<Token>> lines = LineSequenceBuilder.normalizeTokensToLines(tokens);

        return new HighlightedSource(lines);
    }

    /**
     * Returns the tokens for this source file. Everything, including whitespace
     * and newlines should be included.
     */
    protected abstract Iterable<Token> tokensForSource(String source);

}
