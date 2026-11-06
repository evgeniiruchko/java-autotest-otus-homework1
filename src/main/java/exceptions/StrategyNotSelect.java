package exceptions;

public class StrategyNotSelect extends RuntimeException {

    public StrategyNotSelect() {
        super("Не указана стратегия поиска");
    }
}
