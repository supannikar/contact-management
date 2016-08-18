package com.example.query;

public final class CISDetailQuery {
    private final String sortBy;
    private final String email;
    private final String phone;
    private final String name;
    private final int limit;
    private final int offset;

    private CISDetailQuery(Builder builder){
        this.email = builder.email;
        this.name = builder.name;
        this.phone = builder.phone;
        if(builder.sortBy != null) {
            this.sortBy = builder.sortBy.getFieldName();
        } else {
            this.sortBy = null;
        }
        this.offset = builder.offset;
        this.limit = builder.limit;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getSortBy() {
        return sortBy;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public static enum SortGroupName{
        GROUP_NAME("d.name");
        private String fieldName;

        private SortGroupName(String fieldName) {
            this.fieldName = fieldName;
        }

        private String getFieldName() {
            return this.fieldName;
        }
    }

    public static class Builder {
        private String email;
        private String name;
        private String phone;
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

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public CISDetailQuery build() {
            return new CISDetailQuery(this);
        }
    }

}
