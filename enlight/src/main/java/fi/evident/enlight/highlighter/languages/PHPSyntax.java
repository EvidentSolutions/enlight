package fi.evident.enlight.highlighter.languages;

import static fi.evident.enlight.utils.CollectionUtils.asSet;

import java.util.Set;

public class PHPSyntax extends CFamilySyntax {
    @Override
    protected Set<String> keywords() {
        return asSet(
            "abstract", "and", "array", "as", "break", "case", "catch", "cfunction", "class",
            "clone", "const", "continue", "declare", "default", "do", "else", "elseif", "enddeclare", "endfor", "endforeach",
            "endif", "endswitch", "endwhile", "extends", "final", "for", "foreach", "function", "global", "goto", "if",
            "implements", "interface", "instanceof", "namespace", "new", "old_function", "or", "private", "protected",
            "public", "static", "switch", "throw", "try", "use", "var", "while", "xor",
            "__CLASS__", "__DIR__", "__FILE__", "__FUNCTION__", "__METHOD__", "__NAMESPACE__");
    }
}
