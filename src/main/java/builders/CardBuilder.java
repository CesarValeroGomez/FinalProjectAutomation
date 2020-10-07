package builders;

import entities.Card;

public class CardBuilder {

    private Card card;

    public CardBuilder() {
        card = new Card();
    }

    public CardBuilder withName(String name) {
        card.setName(name);
        return this;
    }
    public CardBuilder withDescription(String description) {
        card.setDesc(description);
        return this;
    }
    public CardBuilder withDueDate(String dueDate) {
        card.setDue(dueDate);
        return this;
    }
    public CardBuilder withEmail(String email) {
        card.setEmail(email);
        return this;
    }
    public CardBuilder withIsClosed(Boolean isClosed) {
        card.setClosed(isClosed);
        return this;
    }

    public Card build() {
        return this.card;
    }
}
