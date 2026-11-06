package exceptions;

public class StrategyNotSupported extends RuntimeException {

    public StrategyNotSupported(String strategy) {
        super(String.format("%s не поддерживается", strategy));
    }
}
