package com.example.query;

public final class CISGroupQuery {
    private final String sortBy;
    private final int limit;
    private final int offset;

    private CISGroupQuery(Builder builder){
        if(builder.sortBy != null) {
            this.sortBy = builder.sortBy.getFieldName();
        } else {
            this.sortBy = null;
        }
        this.offset = builder.offset;
        this.limit = builder.limit;
    }

    public String getSortBy() {
        return this.sortBy;
    }

    public int getLimit() {
        return this.limit;
    }

    public int getOffset() {
        return this.offset;
    }

    public static enum SortGroupName{
        GROUP_NAME("g.name");
        private String fieldName;

        private SortGroupName(String fieldName) {
            this.fieldName = fieldName;
        }

        private String getFieldName() {
            return this.fieldName;
        }
    }

    public static class Builder {
        private SortGroupName sortBy;
        private int limit;
        private int offset;

        public Builder sortBy(SortGroupName sortBy) {
            this.sortBy = sortBy;
            return this;
        }

        public Builder limit(int limit) {
            this.limit = limit;
            return this;
        }

        public Builder offset(int offset) {
            this.offset = offset;
            return this;
        }

        public CISGroupQuery build() {
            return new CISGroupQuery(this);
        }
    }

}
