package lotto.controller;

import java.util.List;
import lotto.domain.Amount;
import lotto.domain.LottoNumbers;
import lotto.domain.LottoStore;
import lotto.domain.Rank;
import lotto.domain.WinningNumbers;
import lotto.domain.WinningStatistic;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    public void run() {
        Amount amount = getAmount();
        int totalLottoNumber = amount.calculateTotalLottoNumber();

        LottoStore lottoStore = generateLottoes(totalLottoNumber);

        WinningNumbers winningNumbers = generateWinningNumbers();

        WinningStatistic winningStatistic = generateWinningStatistic(lottoStore.getRanks(winningNumbers));

        OutputView.printWinningStatistic(winningStatistic.getResult());
        OutputView.printTotalEarningsRate(amount.calculateEarningsRate(winningStatistic.calculateTotalPrize()));
    }

    private Amount getAmount() {
        try {
            return new Amount(InputView.inputAmount());
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return getAmount();
        }
    }

    private LottoNumbers getLottoNumbers() {
        try {
            return new LottoNumbers(InputView.inputWinningNumbers());

        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return getLottoNumbers();
        }
    }

    private int getBonusNumber() {
        try {
            return InputView.inputBonusNumber();

        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e);
            return getBonusNumber();
        }
    }

    private WinningNumbers generateWinningNumbers() {
        LottoNumbers lottoNumbers = getLottoNumbers();
        int bonusNumber = getBonusNumber();
        return new WinningNumbers(lottoNumbers, bonusNumber);
    }

    private WinningStatistic generateWinningStatistic(List<Rank> ranks) {
        return new WinningStatistic(ranks);
    }

    private LottoStore generateLottoes(int totalLottoNumber) {
        LottoStore lottoStore = new LottoStore(totalLottoNumber);
        OutputView.printTotalLottoNumber(totalLottoNumber);
        OutputView.printLottos(lottoStore.getLottoes());
        return lottoStore;
    }
}
