import net.k3nder.math.parser.MathParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
class MathParserTests {
    private MathParser mathParser = MathParser.getInstance();
    @Test
    @DisplayName("Normal parse")
    void parse() {
        var context = "1+2+3+4+5";
        var val = mathParser.parse(context);
        Assertions.assertEquals(15, val);
    }
    @Test
    @DisplayName("Numbers")
    void getNumbers() {
        var context = "1+2+3+4+5";
        var val = mathParser.getNumbers(context);
        Assertions.assertArrayEquals(new Long[]{1L, 2L, 3L, 4L, 5L}, val);
    }
}
