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

import fi.evident.enlight.Enlight;
import fi.evident.enlight.Language;
import fi.evident.enlight.recognizer.LanguageRecognizer;
import fi.evident.enlight.testutils.TestFiles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static fi.evident.enlight.utils.IOUtils.extensionOf;
import static fi.evident.enlight.utils.IOUtils.readFile;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class SyntaxHighlighterExampleFilesTest {

    @Parameterized.Parameters
    public static List<Object[]> testFiles() {
        return TestFiles.findExampleFilesAsTestData();
    }

    private final File file;
    private final LanguageRecognizer recognizer = new LanguageRecognizer();

    public SyntaxHighlighterExampleFilesTest(File file) {
        this.file = file;
    }

    @Test
    public void fileMustBeHighlightedWithoutExceptions() throws IOException {
        String extension = extensionOf(file);
        Language language = Language.forExtension(extension);

        assertNotNull("Could not find language for extension '" + extension + "'", language);

        String originalSource = readFile(file);

        HighlightedSource highlightedSource = Enlight.highlight(originalSource, language);
        assertNotNull("highlightedSource was null", highlightedSource);

        assertThat("reconstructing source from highlighted source failed",
                toSource(highlightedSource).trim(), is(originalSource.trim()));
    }

    private String toSource(HighlightedSource highlightedSource) {
        StringBuilder sb = new StringBuilder();
        for (SourceLine line : highlightedSource.getLines()) {
            for (Token token : line.getTokens())
                sb.append(token.getText());
            sb.append("\n");
        }
        return sb.toString();
    }
}
