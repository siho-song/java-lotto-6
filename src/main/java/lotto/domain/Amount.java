package lotto.domain;

public class Amount {
    private final static int PRICE = 1000;
    private final int amount;

    public Amount(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int money) {
        int remainder = getRemainder(money);

        if (remainder != 0) {
            throw new IllegalArgumentException(
                    ErrorMessage.INVALID_UNIT.getValue()
            );
        }
    }

    private int getRemainder(int amount) {
        return amount % PRICE;
    }
}
