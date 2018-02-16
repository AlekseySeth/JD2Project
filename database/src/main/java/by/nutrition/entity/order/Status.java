package by.nutrition.entity.order;

import by.nutrition.entity.util.EnumAsString;

/**
 * @author a.shestovsky
 */
public enum Status implements EnumAsString {
    OPEN {
        @Override
        public String getAsString() {
            return "Открыт";
        }
    },
    IN_PROGRESS {
        @Override
        public String getAsString() {
            return "В обработке";
        }
    },
    COMPLETED {
        @Override
        public String getAsString() {
            return "Доставлен";
        }
    },
    CLOSED {
        @Override
        public String getAsString() {
            return "Отменен";
        }
    }
}
