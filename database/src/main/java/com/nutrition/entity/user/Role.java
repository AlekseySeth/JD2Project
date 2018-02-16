package com.nutrition.entity.user;

import com.nutrition.entity.util.EnumAsString;

/**
 * @author a.shestovsky
 */
public enum Role implements EnumAsString {
    GUEST {
        @Override
        public String getAsString() {
            return "Гость";
        }
    }, ADMIN {
        @Override
        public String getAsString() {
            return "Администратор";
        }
    }, MARKETER {
        @Override
        public String getAsString() {
            return "Маркетолог";
        }
    }, CUSTOMER {
        @Override
        public String getAsString() {
            return "Покупатель";
        }
    }
}
