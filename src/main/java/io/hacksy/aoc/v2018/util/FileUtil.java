package io.hacksy.aoc.v2018.util;

import com.google.common.collect.Lists;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtil {

    static public File getResourceFile(String path) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        try {
            return new File(loader.getResource(path).getFile());
        } catch (NullPointerException npe) {
            throw new FileUtileException(String.format("Unable to load resource: %s", path));
        }
    }

    static public List<Integer> fileToList(File file) {
        try (Stream<String> stream = Files.lines(Paths.get(file.toURI()))) {
            return stream.map(Integer::parseInt).collect(Collectors.toList());
        } catch (Exception e) {
            return Lists.newArrayList();
        }
    }

    static public List<String> fileToStringList(File file) {
        try (Stream<String> stream = Files.lines(Paths.get(file.toURI()))) {
            return stream.collect(Collectors.toList());
        } catch (Exception e) {
            return Lists.newArrayList();
        }
    }

    static class FileUtileException extends RuntimeException {
        FileUtileException(String msg) {
            super(msg);
        }
    }
}
