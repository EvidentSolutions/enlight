# Enlight

Enlight is a library that provides syntax highlighting and source code detection for various languages.

# Get started

The facade class `fi.evident.enlight.Enlight` is an entry-point to all functionality.
It has just two interesting static methods:

  * `Language recognizeLanguage(String source)`

    Tries to recognize the language based on various heuristics. Returns null
    if the language could not be recognized.

  * `HighlightedSource highlight(String source, Language language)`

    Returns a highlighted representation of given source string. The highlighted
    representation is not dependent on any particular way of presenting it (such
    as HTML), but is in a form that it should be easy to create formatters from it.
    See classes in `fi.evident.enlight.highlighter` for more details.

# Using Enlight with Maven

Enlight is available on the central Maven repository, so just add the following
dependency to your pom.xml:

    <dependency>
        <groupId>fi.evident.enlight</groupId>
        <artifactId>enlight</artifactId>
        <version>0.2.2</version>
    </dependency>
