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

package fi.evident.enlight.highlighter.languages;

import java.util.Set;

import static fi.evident.enlight.utils.CollectionUtils.asSet;

public class CPlusPlusSyntax extends CFamilySyntax {
    @Override
    protected Set<String> keywords() {
        return asSet(
            "and", "noexcept", "template", "and_eq", "delete", "not", "this",
            "alignof", "not_eq", "thread_local", "asm", "dynamic_cast", "nullptr",
            "throw", "operator", "true", "bitand", "or", "try", "bitor", "explicit",
            "or_eq", "bool", "export", "private", "typeid", "protected", "typename",
            "false", "public", "catch", "using", "reinterpret_cast",
            "char16_t", "friend", "char32_t2", "wchar_t", "class", "virtual",
            "compl", "inline", "const", "constexpr", "static_assert", "xor",
            "const_cast", "mutable", "static_cast", "xor_eq", "namespace",
            "decltype", "new");
    }
}
