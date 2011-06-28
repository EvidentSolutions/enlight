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

package fi.evident.enlight.highlighter;

import java.util.ArrayList;
import java.util.List;

import static fi.evident.enlight.utils.CollectionUtils.join;
import static java.util.Collections.unmodifiableList;

public final class SourceLine {

    private final int lineNumber;
    private final List<Token> tokens;

    public SourceLine(int lineNumber, List<Token> tokens) {
        if (lineNumber < 0) throw new IllegalArgumentException("negative lineNumber");

        this.lineNumber = lineNumber;
        this.tokens = unmodifiableList(new ArrayList<Token>(tokens));
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return join(tokens);
    }
}
