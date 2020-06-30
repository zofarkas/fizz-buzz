package com.example.fizzbuzz.acceptancetests;

import com.example.fizzbuzz.FizzBuzzApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FizzBuzzApplication.class)
@AutoConfigureMockMvc
public class FizzBuzzControllerAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    public static Stream<Arguments> dataSource() {
        return Stream.of(
            Arguments.of("3", "last-element-3.json"),
            Arguments.of("10", "last-element-10.json"),
            Arguments.of("15", "last-element-15.json"),
            Arguments.of("1234", "last-element-1234.json"));
    }

    @ParameterizedTest
    @MethodSource("dataSource")
    public void shouldGenerateFizzBuzzTillTheLastIndex(String lastElement, String expectedResponseFileName) throws Exception {
        String expectedContent = getExpectedData(expectedResponseFileName);
        mockMvc.perform(get("/fizz-buzz").param("lastElement", lastElement))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedContent));
    }

    @Test
    public void shouldReturnBadRequestForZeroLastElement() throws Exception {
        mockMvc.perform(get("/fizz-buzz").param("lastElement", "0"))
            .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturnBadRequestForNegativeLastElement() throws Exception {
        mockMvc.perform(get("/fizz-buzz").param("lastElement", "-5"))
            .andExpect(status().isBadRequest());
    }

    private String getExpectedData(String expectedResponseFileName) throws IOException {
        File file = new File("src/test/resources/data/" + expectedResponseFileName);
        return Files.readAllLines(file.toPath()).stream().collect(Collectors.joining(System.lineSeparator()));
    }
}
