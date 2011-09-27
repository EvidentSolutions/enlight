package fi.evident.enlight.utils;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import static fi.evident.enlight.utils.PathUtils.extensionFor;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PathUtilsTest {

    @Test
    public void extensionForPathWithExtensionReturnsTheExtension() {
        assertThat(extensionFor("foo.bar"), is("bar"));
        assertThat(extensionFor("foo/bar.bar"), is("bar"));
    }

    @Test
    public void extensionForPathConsidersOnlyLastPathElement() {
        assertThat(extensionFor("foo.bar/baz.quux"), is("quux"));
        assertThat(extensionFor("foo.bar/baz"), is(""));
    }

    @Test
    public void withMultiplePeriodsOnlyTheLastIsConsideredAsExtension() {
        assertThat(extensionFor("foo.bar.baz"), is("baz"));
    }

    @Test
    public void extensionForEmptyPathIsEmpty() {
        assertThat(extensionFor(""), is(""));
    }

    @Test
    public void extensionForPathWithoutExtensionIsEmpty() {
        assertThat(extensionFor("foo"), is(""));
        assertThat(extensionFor("foo/bar"), is(""));
    }
}
