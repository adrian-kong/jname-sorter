package dev.megaport;

import dev.megaport.data.TwoParam;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class NameSorterTest {

    private final static String FILE_NAME = "test";

    private final static Path IN_PATH = Paths.get(FILE_NAME + ".txt");
    private final static Path OUT_PATH = Paths.get(FILE_NAME + "-sorted.txt");

    @BeforeClass
    public static void setupInputFiles() {
        assertDoesNotThrow(() -> {
            try (BufferedWriter writer = Files.newBufferedWriter(IN_PATH)) {
                writer.write("""
                        BAKER, THEODORE
                        SMITH, ANDREW
                        KENT, MADISON
                        SMITH, FREDRICK
                        """);

                assertTrue(Files.exists(IN_PATH), "Generated file does not exist!");
            }
        });
    }

    @AfterClass
    public static void cleanupFiles() {
        assertDoesNotThrow(() -> {
            Files.delete(IN_PATH);
            Files.delete(OUT_PATH);

        }, "File cleanup failed");
    }

    @Test
    @Order(1)
    public void processFile() {
        assertDoesNotThrow(() -> {
            Reader reader = Files.newBufferedReader(IN_PATH);
            Writer writer = Files.newBufferedWriter(OUT_PATH);

            var comparator = Comparator.comparing(TwoParam::firstParam).thenComparing(TwoParam::secondParam);

            new NameSorter(", ", reader, writer).process(comparator);

            assertTrue(Files.exists(OUT_PATH), "Output file does not exist!");
        });
    }

    @Test
    @Order(2)
    public void checkOutput() {
        assertDoesNotThrow(() -> {
            String actual = Files.readString(OUT_PATH).replaceAll("(\r\n|\n)", "\n"); // funky line seperatoor inconsistency

            assertEquals("""
                    BAKER, THEODORE
                    KENT, MADISON
                    SMITH, ANDREW
                    SMITH, FREDRICK
                    """, actual, "Incorrect name sorter output");
        });
    }


}
