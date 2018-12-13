package io.hacksy.aoc.v2018.day13;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RailMapTest {
    @Test
    public void regexTest() {
        Pattern pattern = Pattern.compile("[\\^>v<\\\\/\\+]");
        String input = "v   ^   +  <   >";
        Matcher m = pattern.matcher(input);

        m.find();
        assertThat(m.group(0), is("v"));
        assertThat(m.start(), is(0));
        m.find();
        assertThat(m.group(0), is("^"));
        assertThat(m.start(), is(4));
        m.find();
        assertThat(m.group(0), is("+"));
        assertThat(m.start(), is(8));
    }
}