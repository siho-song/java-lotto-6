package lotto.domain;

public class WinningNumbers {

    private final Lotto winningNumber;
    private final int bonusNumber;

    public WinningNumbers(Lotto winningNumber, int bonusNumber) {
        validateDuplicated(winningNumber, bonusNumber);
        this.winningNumber = winningNumber;
        this.bonusNumber = bonusNumber;
    }

    public Rank announceRank(Lotto lotto) {
        int matchingNumberCounts = lotto.compareLottoWithWinningNumber(this.winningNumber);
        boolean hasBonusNumber = lotto.contains(this.bonusNumber);
        return Rank.of(matchingNumberCounts, hasBonusNumber);
    }

    private void validateDuplicated(Lotto winningNumber, int bonusNumber) {
        if (winningNumber.getNumbers().contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_BONUS_NUMBER.getValue());
        }
    }
}