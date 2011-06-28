package fi.evident.enlight.highlighter.languages;

public class ShellSyntax extends CommonSyntax {
    @Override
    protected String singleLineCommentStart() {
        return "#";
    }
}
