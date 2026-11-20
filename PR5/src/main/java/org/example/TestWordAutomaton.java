package org.example;

public class TestWordAutomaton {

    public enum State {
        S, ONE, TWO, THREE, F;

        @Override
        public String toString() {
            return switch (this) {
                case ONE -> "1";
                case TWO -> "2";
                case THREE -> "3";
                default -> name();
            };
        }
    }

    public State process(String input) {
        if (input == null) {
            return State.S;
        }

        State currentState = State.S;

        for (char c : input.toCharArray()) {
            currentState = nextState(currentState, c);
        }

        return currentState;
    }

    private State nextState(State state, char c) {
        return switch (state) {
            case S -> (c == 'T') ? State.ONE : State.S;
            case ONE -> (c == 'E') ? State.TWO : State.S;
            case TWO -> (c == 'S') ? State.THREE : State.S;
            case THREE -> (c == 'T') ? State.F : State.S;
            case F -> State.F; // Зі стану F будь-який символ залишає автомат у F
        };
    }
}