package search;

import models.PIR;

import java.util.function.Function;

public class SearchCondition<T extends PIR> {
    private final Function<T, Boolean> condition;
    public SearchCondition(Function<T, Boolean> condition) {
        this.condition = condition;
    }

    public Boolean evaluate(T pir) {
        return condition.apply(pir);
    }

    public SearchCondition<T> and(SearchCondition<T> condition) {
        Function<T, Boolean> conjunction = t -> condition.evaluate(t) && this.evaluate(t);
        return new SearchCondition<>(conjunction);
    }

    public SearchCondition<T> or(SearchCondition<T> condition) {
        Function<T, Boolean> disjunction = t -> condition.evaluate(t) || this.evaluate(t);
        return new SearchCondition<>(disjunction);
    }

    public SearchCondition<T> not() {
        Function<T, Boolean> negation = t -> !this.evaluate(t);
        return new SearchCondition<>(negation);
    }
}
