package com.galaxymerchant.model;

public enum QueryType {
    HOW_MUCH_IS("how much is"),
    HOW_MANY_CREDITS_IS("how many Credits is"),
    HAS_MORE_CREDITS_THAN("has more Credits than"),
    HAS_LESS_CREDITS_THAN("has less Credits than"),
    LARGER_THAN("larger than"),
    SMALLER_THAN("smaller than");

    private final String query;

    QueryType(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public static QueryType fromString(String text) {
        for (QueryType queryType : QueryType.values()) {
            if (text.contains(queryType.query)) {
                return queryType;
            }
        }
        return null;
    }
}
