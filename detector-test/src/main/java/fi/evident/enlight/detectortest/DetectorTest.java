package fi.evident.enlight.detectortest;

import fi.evident.enlight.Enlight;
import fi.evident.enlight.Language;

import java.io.File;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;

import static fi.evident.enlight.utils.IOUtils.extensionOf;
import static fi.evident.enlight.utils.IOUtils.readFile;

public final class DetectorTest {

    private int total = 0;
    private int unrecognized = 0;
    private int misrecognized = 0;
    private boolean printUnrecognized = Boolean.getBoolean("printUnrecognized");
    private final Set<Language> ignoredLanguages = EnumSet.of(Language.HTML, Language.JAVASCRIPT);

    public void process(File root) throws IOException {
        processFileOrDirectory(root);

        float misrecognizedPercentage = (misrecognized*100f) / total;
        float unrecognizedPercentage = (unrecognized*100f) / total;
        System.out.printf("processed %d files with %d (%.0f%%) misrecognized and %d (%.0f%%) unrecognized\n",
                total, misrecognized, misrecognizedPercentage, unrecognized, unrecognizedPercentage);
    }

    private void processFileOrDirectory(File file) throws IOException {
        if (file.isDirectory())
            for (File child : file.listFiles())
                processFileOrDirectory(child);
        else
            processFile(file);
    }

    private void processFile(File file) throws IOException {
        Set<Language> expectedLanguages = Language.forExtension(extensionOf(file));
        if (!expectedLanguages.isEmpty() && !isIgnored(file)) {
            total++;
            Language recognizedLanguage = Enlight.recognizeLanguage(readFile(file));

            if (recognizedLanguage == null) {
                if (printUnrecognized)
                    System.out.printf("%s: did not recognize file as any of %s\n", file, expectedLanguages);
                unrecognized++;
            } else if (!expectedLanguages.contains(recognizedLanguage)) {
                System.out.printf("%s: misrecognized file as %s\n", file, recognizedLanguage);
                misrecognized++;
            }
        }
    }

    private boolean isIgnored(File file) throws IOException {
        String extension = extensionOf(file);
        for (Language language : ignoredLanguages)
            if (language.getExtensions().contains(extension))
                return true;
        return false;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("usage: detector-test ROOT-DIR");
            System.exit(1);
        }

        try {
            new DetectorTest().process(new File(args[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
