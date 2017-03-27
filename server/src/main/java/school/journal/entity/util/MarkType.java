package school.journal.entity.util;

public enum MarkType {
    simple("simple"), test("test"), annual("annual");

    final String meaning;

    MarkType(String meaning) {
        this.meaning = meaning;
    }

    public String getMeaning() {
        return meaning;
    }


    @Override
    public String toString() {
        return meaning;
    }
}
