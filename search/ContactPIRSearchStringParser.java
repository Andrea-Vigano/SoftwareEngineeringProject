package search;

import models.ContactPIR;

public class ContactPIRSearchStringParser extends SearchStringParser<ContactPIR> {
    @Override
    protected SearchCondition<ContactPIR> parseAtomicCondition(String condition) {
        if (this.isContains(condition)) {
            String containsString = this.getContainsString(condition);
            return new SearchCondition<>(pir -> pir.getName().contains(containsString));
        }
        return null;
    }
}
