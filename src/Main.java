import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Paths.get("src/text_to_clean.txt"));

        String trimmed = Arrays.stream(content.split("\n"))
                .dropWhile(line -> !line.contains("*** START OF THIS PROJECT GUTENBERG EBOOK"))
                .skip(1)
                .takeWhile(line -> !line.contains("*** END OF THIS PROJECT GUTENBERG EBOOK"))
                .collect(Collectors.joining(" "));



        Function<String, String> cleanText = text -> text
                .replaceAll("[.,\"'!?;:()\\[\\]\\-–—]", "")
                .toLowerCase()
                .replaceAll("\\s+", " ")
                .trim();


        String cleaned = cleanText.apply(trimmed);


    }

}