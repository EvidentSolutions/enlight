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

package fi.evident.enlight.recognizer;

import fi.evident.enlight.Language;
import fi.evident.enlight.testutils.TestFiles;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static fi.evident.enlight.utils.IOUtils.extensionOf;
import static fi.evident.enlight.utils.IOUtils.readFile;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.matchers.JUnitMatchers.hasItem;

@RunWith(Parameterized.class)
public class LanguageRecognizerExampleFilesTest {

    @Parameters
    public static List<Object[]> testFiles() {
        return TestFiles.findExampleFilesAsTestData();
    }

    private final File file;
    private final LanguageRecognizer recognizer = new LanguageRecognizer();

    public LanguageRecognizerExampleFilesTest(File file) {
        this.file = file;
    }

    @Test
    public void fileMustBeParsedAndDetectedSuccessfully() throws IOException {
        String extension = extensionOf(file);
        Set<Language> languages = Language.forExtension(extension);

        String source = readFile(file);

        Language language = recognizer.recognizeLanguage(source);
        assertThat(languages, hasItem(language));
    }
}
