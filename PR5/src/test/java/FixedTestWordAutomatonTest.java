import org.example.FixedTestWordAutomaton;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FixedTestWordAutomatonTest {

    private final FixedTestWordAutomaton automaton = new FixedTestWordAutomaton();

    @ParameterizedTest(name = "Вхід: \"{0}\" -> Очікуваний стан: {1}")
    @CsvSource({
            // Базові кейси знаходження слова (стан F)
            "TEST, F",
            "abcTESTabc, F",
            "TESTabc, F",
            "abcTEST, F",
            "TESTTEST, F",

            // Проміжні стани в кінці рядка (S, 1, 2, 3)
            "abcdef, S",
            "abcT, 1",
            "abcTE, 2",
            "abcTES, 3",

            // Скидання станів при невідповідності символів
            "Tabc, S",
            "TEabc, S",
            "TESabc, S",

            // Чутливість до регістру
            "test, S",
            "Test, S",
            "TESt, S",

            // Кейси із зірочкою (накладання префіксів 'T' та 'TE' перед 'TEST')
            "TTEST, F",
            "TTTEST, F",
            "TETEST, F",
            "TETETEST, F",
            "abcTTESTxyz, F",
            "abcTETESTxyz, F",
            "TT, 1",
            "TET, 1",
            "TETE, 2"
    })
    @DisplayName("Перевірка переходів скінченного автомата для різних вхідних рядків")
    void shouldReturnExpectedFinalState(String input, String expectedStateStr) {
        FixedTestWordAutomaton.State actualState = automaton.process(input);
        assertEquals(expectedStateStr, actualState.toString());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("Порожній рядок або null повинні залишати автомат у початковому стані S")
    void shouldReturnInitialStateForNullOrEmpty(String input) {
        assertEquals(FixedTestWordAutomaton.State.S, automaton.process(input));
    }
}