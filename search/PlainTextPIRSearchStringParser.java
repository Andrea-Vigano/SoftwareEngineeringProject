package search;

import models.PlainTextPIR;

public class PlainTextPIRSearchStringParser extends SearchStringParser<PlainTextPIR> {
    @Override
    protected SearchCondition<PlainTextPIR> parseAtomicCondition(String condition) {
        if (this.isContains(condition)) {
            String containsString = this.getContainsString(condition);
            return new SearchCondition<>(pir -> pir.getText().contains(containsString));
        }
        return null;
    }
}
