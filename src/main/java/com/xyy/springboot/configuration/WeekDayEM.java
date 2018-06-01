package com.xyy.springboot.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 带有抽象方法的枚举类
 */
public enum WeekDayEM {
    SUN {
        @Override
        public Integer getValue() {
            return 0;
        }

        @Override
        public String getKey() {
            return "星期日";
        }
    },
    MON {
        @Override
        public Integer getValue() {
            return 1;
        }

        @Override
        public String getKey() {
            return "星期一";
        }
    },
    TUE {
        @Override
        public Integer getValue() {
            return 2;
        }

        @Override
        public String getKey() {
            return "星期二";
        }
    },
    WED {
        @Override
        public Integer getValue() {
            return 3;
        }

        @Override
        public String getKey() {
            return "星期三";
        }
    },
    THU {
        @Override
        public Integer getValue() {
            return 4;
        }

        @Override
        public String getKey() {
            return "星期四";
        }
    },
    FRI {
        @Override
        public Integer getValue() {
            return 5;
        }

        @Override
        public String getKey() {
            return "星期五";
        }
    },
    SAT {
        @Override
        public Integer getValue() {
            return 6;
        }

        @Override
        public String getKey() {
            return "星期六";
        }
    };

    public abstract Integer getValue();
    public abstract String getKey();

    /**
     * 返回由key和value组成的map集合:
     * {星期二=2, 星期六=6, 星期三=3, 星期四=4, 星期五=5, 星期日=0, 星期一=1}
     * @return
     */
    public static Map<String, Object> getKeyValueMap() {
        HashMap<String, Object> map = new HashMap<>();
        WeekDayEM[] values = WeekDayEM.values();
        for (WeekDayEM value : values) {
            String k = value.getKey();
            Integer v = value.getValue();
            map.put(k, v);
        }
        return map;
    }

    /**
     * 返回由所有key组成的list集合:
     * [星期日, 星期一, 星期二, 星期三, 星期四, 星期五, 星期六]
     * @return
     */
    public static List<Object> getAllKey() {
        List<Object> keyList = new ArrayList<>();
        WeekDayEM[] values = WeekDayEM.values();
        for (WeekDayEM value : values) {
            String k = value.getKey();
            keyList.add(k);
        }
        return keyList;
    }

    /**
     * 返回由所有value组成的list集合:
     * [0, 1, 2, 3, 4, 5, 6]
     * @return
     */
    public static List<Object> getAllValue() {
        List<Object> valueList = new ArrayList<>();
        WeekDayEM[] values = WeekDayEM.values();
        for (WeekDayEM value : values) {
            Integer v = value.getValue();
            valueList.add(v);
        }
        return valueList;
    }
}