package fi.evident.enlight.utils;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PeekableIteratorTest {

    @Test
    public void peekingDoesNotConsumeItem() {
        PeekableIterator<String> it = peekableIteratorFor("foo", "bar", "baz");

        assertThat(it.next(), is("foo"));
        assertThat(it.peek(), is("bar"));
        assertThat(it.peek(), is("bar"));
        assertThat(it.next(), is("bar"));
        assertThat(it.peek(), is("baz"));
        assertThat(it.next(), is("baz"));
    }

    private PeekableIterator<String> peekableIteratorFor(String... items) {
        return new PeekableIterator<String>(asList(items).iterator());
    }
}
