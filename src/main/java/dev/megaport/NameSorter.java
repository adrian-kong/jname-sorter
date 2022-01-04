package dev.megaport;

import dev.megaport.data.TwoParam;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static dev.megaport.data.TwoParam.stringTwoParamFunction;
import static dev.megaport.data.TwoParam.twoParamStringFunction;

public record NameSorter(String delimiter, Reader reader, Writer writer) {

    public void process(Comparator<TwoParam> comparator) throws IOException {
        // this is for a more performant code, no addition space required nor a method which constantly reopens stream
        try (BufferedReader reader = new BufferedReader(reader()); BufferedWriter writer = new BufferedWriter(writer())) {
            reader.lines()
                    .map(stringTwoParamFunction(delimiter))
                    .filter(Objects::nonNull)
                    .sorted(comparator)
                    .map(twoParamStringFunction(delimiter))
                    .forEach(line -> write(writer, line));
        }
    }

    private void write(Writer writer, String line) {
        try {
            writer.write(line);
            writer.write(System.lineSeparator());
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
