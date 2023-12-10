package domain;

public enum CategoryEnum {
    ART_LITERATURE("artliterature"),
    LANGUAGE("language"),
    SCIENCE_NATURE("sciencenature"),
    GENERAL("general"),
    FOOD_DRINK("fooddrink"),
    PEOPLEP_LACES("peopleplaces"),
    GEOGRAPHY("geography"),
    HISTORY_HOLIDAYS("historyholidays"),
    ENTERTAINMENT("entertainment"),
    TOYS_GAMES("toysgames"),
    MUSIC("music"),
    MATHEMATICS("mathematics"),
    RELIGION_MYTHOLOGY("religionmythology"),
    SPORTS_LEISURE("sportsleisure");

    public final String category;

    private CategoryEnum(String category){
        this.category = category;
    }

}
