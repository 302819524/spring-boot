package com.xyy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xyy.springboot.domain.BaseUser;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author xuyy
 * @date 2018/9/13 10:18
 */
public class Test {

    private List<String> trafficCodeList = Collections.unmodifiableList(Arrays.asList());

    @org.junit.Test
    public void test1() throws IllegalAccessException {
        BaseUser baseUser = new BaseUser();
        baseUser.setName("444");
        Field[] fieldArray = baseUser.getClass().getDeclaredFields();
        for (Field field : fieldArray) {
//            f.getName();
            try {
                //得到属性
//                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                System.out.println(field.getType());
                //获取属性值
                Object value = field.get(baseUser);
                //一个个赋值
                System.out.println(field.getName() + ":" + value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private Field getFieldByClasss(String fieldName, Object object) {
        Field field = null;
        Class<?> clazz = object.getClass();

        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
            } catch (Exception e) {
                // 这里甚么都不能抛出去。
                // 如果这里的异常打印或者往外抛，则就不会进入

            }
        }
        return field;

    }

    @org.junit.Test
    public void test2() {
        String provinceCode = "330001200000".substring(0, 2).concat("0000000000");
        System.out.println(provinceCode);
    }

    @org.junit.Test
    public void setQueryDays() {
        LocalDate queryFirstDay = LocalDate.now().minusMonths(5).withDayOfMonth(1);
        LocalDate localDateNow = LocalDate.now();
        // 存储查询的日期
        List<LocalDate> dateList = new ArrayList<>();
        if (queryFirstDay.getYear() == localDateNow.getYear() && queryFirstDay.getMonthValue() == localDateNow.getMonthValue()) {
            int length = localDateNow.getDayOfMonth();
            int lastMonthDay = localDateNow.minusMonths(1).getMonth().maxLength();
            for (int i = 0; i < length + lastMonthDay; i++) {
                dateList.add(localDateNow);
                localDateNow = localDateNow.minusDays(1);
            }

        } else {
            int length = queryFirstDay.getMonth().maxLength();
            int lastMonthDay = queryFirstDay.minusMonths(1).getMonth().maxLength();
            queryFirstDay = queryFirstDay.withDayOfMonth(length);
            for (int i = 0; i < length + lastMonthDay; i++) {
                dateList.add(queryFirstDay);
                queryFirstDay = queryFirstDay.minusDays(1);
            }
            queryFirstDay = LocalDate.now().minusMonths(5).withDayOfMonth(1);
//            int dayLength = queryFirstDay.getMonth().maxLength();
            int lastDayLength = queryFirstDay.minusMonths(1).getMonth().maxLength();
            List<LocalDate> lastDateList = dateList.subList(dateList.size() - lastDayLength, dateList.size());
            List<LocalDate> queryDateList = dateList.subList(0, dateList.size() - lastDayLength);

            System.out.println(lastDateList);
            System.out.println(queryDateList);
        }
        System.out.println(dateList);
    }

    @org.junit.Test
    public void test3() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i + "");
        }
//        long l1 = System.currentTimeMillis();
//        list.sort((o1, o2) -> o1.length()>o2.length()?1:-1 );
//        long l2 = System.currentTimeMillis();
//        System.out.println(l2-l1);
        long l3 = System.currentTimeMillis();
        List<String> collect = list.stream().sorted((o1, o2) -> o1.length() > o2.length() ? 1 : -1).collect(Collectors.toList());
        System.out.println(collect);
        long l4 = System.currentTimeMillis();
        System.out.println(l4 - l3);
    }

    @org.junit.Test
    public void test4() {
        List<Double> collect = new ArrayList<>();
        for (Double i = 0.0; i < 10; i++) {
            collect.add(i);
        }
        JSONArray jsonBack = new JSONArray();
        collect = collect.stream().sorted((x, y) -> {
            return x.compareTo(y) * -1;
        }).collect(Collectors.toList());
        jsonBack.addAll(collect);
        jsonBack.subList(0, jsonBack.size());
        System.out.println(jsonBack);
        System.out.println(collect);
    }

    @org.junit.Test
    public void test5() {
        LocalDate localDate = LocalDate.of(2017, 5, 1);
        Month month = localDate.getMonth().firstMonthOfQuarter();
        System.out.println(month.getValue());
    }

    @org.junit.Test
    public void test6() {
        String code = "123456";
        System.out.println(code.substring(0, 2));
        System.out.println(code.substring(2, 4));
        System.out.println(code.substring(4, 6));
    }

    @org.junit.Test
    public void test7() {
        List<Double> totalEntTargetLastList = new ArrayList<>();
//        totalEntTargetLastList.add(1.2);
//        totalEntTargetLastList.add(1.2);
//        totalEntTargetLastList.add(null);
        double sum = totalEntTargetLastList.stream().mapToDouble(data -> data).sum();
        System.out.println(sum);
    }

    @org.junit.Test
    public void test8() {
        List<JSONObject> perList = new ArrayList<>();
        JSONObject object1 = new JSONObject();
        object1.put("industry", "a01");
        object1.put("value", 1);
        object1.put("value1", 1);
        JSONObject object2 = new JSONObject();
        object2.put("industry", "a01");
        object2.put("value", 1);
        object2.put("value2", 2);
        JSONObject object3 = new JSONObject();
        object3.put("industry", "a01,a02");
        object3.put("value", 1);
        object3.put("value3", 3);
        perList.add(object1);
        perList.add(object2);
        perList.add(object3);
        Map<String, List<JSONObject>> collect = perList.stream().collect(Collectors.groupingBy(obj -> obj.getString("industry") + obj.getString("value")));
        String l = "l";
    }

    @org.junit.Test
    public void test9() {
        String[] split = "01.26".split("\\.");
        System.out.println(split);
    }

    @org.junit.Test
    public void test10() {
        LocalDate fourthQuarter = LocalDate.now().withMonth(7);
        LocalDate quarterDate = LocalDate.now();
        boolean after = fourthQuarter.isAfter(quarterDate) || fourthQuarter.isEqual(quarterDate);
        System.out.println(after);

    }

    @org.junit.Test
    public void test11() {
        String str = "00210000000";
//        String newStr = str.replaceAll("00*$", "");
        String newStr = str.replaceAll("(00){1+}$", "");
        System.out.println(newStr);
    }

    @org.junit.Test
    public void test12() {
//        LocalDate localDate = LocalDate.now();
//        // 存储查询的日期
//        List<LocalDate> dateList = new ArrayList<>();
//        if (2018==localDate.getYear()) {
////            LocalDate firstDayOfMonth = localDate.withDayOfMonth(1);
////            for (int i = 0; i < localDate.getMonthValue(); i++) {
////                dateList.add(firstDayOfMonth);
////                firstDayOfMonth = firstDayOfMonth.minusMonths(1);
////            }
//            LocalDate firstDayOfMonth = localDate.withDayOfMonth(1);
//            for (int i = 0; i < localDate.getMonthValue(); i++) {
//                dateList.add(firstDayOfMonth);
//                firstDayOfMonth = firstDayOfMonth.minusMonths(1);
//            }
//        } else {
//            LocalDate lastMonth = LocalDate.of(2018, 12, 1);
//            for (int i = 0; i < 12; i++) {
//                dateList.add(lastMonth);
//                lastMonth = lastMonth.minusMonths(1);
//            }
//        }
//        System.out.println(dateList);
//        for(int i=dateList.size()-1;i>-1;i--){
//            System.out.println(dateList.get(i));
//        }


        List<LocalDate> dateList = new ArrayList<>();
        LocalDate lastMonth = LocalDate.of(2018, 1, 1);
        for (int i = 0; i < 10; i++) {
            dateList.add(lastMonth);
            lastMonth = lastMonth.minusYears(1);
        }
        System.out.println(dateList);

//        LocalDate queryFirstDay = LocalDate.of(2018,10,1);
//        LocalDate localDate = LocalDate.now();
//        // 存储查询的日期
//        List<LocalDate> dateList = new ArrayList<>();
//        if (2018==(localDate.getYear())) {
//            LocalDate firstDayOfMonth = localDate.withDayOfMonth(1);
//            for (int i = 0; i < localDate.getMonthValue() + 12; i++) {
//                dateList.add(firstDayOfMonth);
//                firstDayOfMonth = firstDayOfMonth.minusMonths(1);
//            }
//        } else {
//            LocalDate lastMonth = LocalDate.of(2018, 12, 1);
//            for (int i = 0; i < 24; i++) {
//                dateList.add(lastMonth);
//                lastMonth = lastMonth.minusMonths(1);
//            }
//        }
//        System.out.println(dateList);
    }

    @org.junit.Test
    public void test13() {
        JSONArray arraySort = new JSONArray();
//        JSONObject sortObj2 = new JSONObject();
//        sortObj2.put("dataValue", 12.0);
//        JSONObject sortObj1 = new JSONObject();
//        sortObj1.put("dataValue", 11.0);
//        JSONObject sortObj3 = new JSONObject();
//        sortObj3.put("dataValue", 13.0);
        JSONObject sortObj4 = new JSONObject();
        sortObj4.put("dataValue", null);
//        arraySort.add(sortObj1);
//        arraySort.add(sortObj2);
//        arraySort.add(sortObj3);
        arraySort.add(sortObj4);
        List<Object> collect = arraySort.stream()
                .filter(obj -> null != ((JSONObject) obj).getDouble("dataValue"))
                .sorted((x, y) -> -((JSONObject) x).getDouble("dataValue").compareTo(((JSONObject) y).getDouble("dataValue")))
                .collect(Collectors.toList());
        double sum = arraySort.stream()
                .filter(obj -> null != ((JSONObject) obj).getDouble("dataValue"))
                .mapToDouble(obj -> ((JSONObject) obj).getDouble("dataValue"))
                .sum();
        System.out.println(collect);
        System.out.println(sum);

    }

    @org.junit.Test
    public void test14() {
        List<String> industryCodeLimitList = new ArrayList<>();
        List<String> industryCodeList = new ArrayList<>();
        industryCodeList.add("1");
        industryCodeList.add("2");
        industryCodeList.add("3");
        industryCodeList.add("4");
        for (int i = 0; industryCodeLimitList.size() < 3; i++) {
            String industryCode = industryCodeList.get(i);
            if (!industryCodeLimitList.contains(industryCode)) {
                industryCodeLimitList.add(industryCode);
            }
        }
        System.out.println(industryCodeLimitList);
    }

    @org.junit.Test
    public void test15() {
        BaseUser baseUser = new BaseUser();
        BaseUser baseUser2 = new BaseUser();
        baseUser.setName("xyy");
        BeanUtils.copyProperties(baseUser2, baseUser);
        System.out.println(baseUser2);
    }

    @org.junit.Test
    public void test16() {
        String energyTypeDataCode = "0021.03";
        StringBuilder energyTypeCode = new StringBuilder(energyTypeDataCode);
        energyTypeCode.insert(energyTypeDataCode.indexOf("."), ".02");
        System.out.println(energyTypeCode);
    }

    @org.junit.Test
    public void test17() {
        BaseUser baseUser = new BaseUser();
        BaseUser baseUser2 = new BaseUser();
        BaseUser baseUser3 = new BaseUser();
        baseUser.setName("xyy");
        baseUser.setAge(1);
        baseUser2.setName("xyy");
        baseUser2.setAge(1);
        baseUser3.setName("xyy");
        baseUser3.setAge(2);
        List<BaseUser> list = new ArrayList<>();
        list.add(baseUser);
        list.add(baseUser2);
        list.add(baseUser3);
        Map<String, BaseUser> collect = list.stream().collect(Collectors.toMap(data -> data.getName(), data -> data));

        System.out.println(baseUser2);
    }

    @org.junit.Test
    public void test18() {
        BaseUser baseUser = new BaseUser();
        baseUser.setName("xyy");
        List<BaseUser> userList = new ArrayList<>();
        userList.add(baseUser);
        test19(userList, baseUser.getClass());

    }

    public void test19(List<?> list, Class clazz) {
        JSONArray jsonArray = new JSONArray();
        list.stream().forEach(o -> {
            if (o.getClass().equals(clazz)) {
                JSONObject jsonObject = new JSONObject();
                try {
                    Method getName = clazz.getMethod("getName");
                    Object name = getName.invoke(o);
                    jsonObject.put("name", name);
                    jsonArray.add(jsonObject);
                } catch (Exception e) {
                    throw new ClassCastException();
                }
            }
        });
        System.out.println(jsonArray);
    }

    @org.junit.Test
    public void test20() {
        String dd = "r.123456789987654321.3.01.1004.000.02.01";
        String[] split = dd.split("\\.");
        System.out.println(split[7]);

    }

    @org.junit.Test
    public void test21() {
        System.out.println(String.format("r.%s.2.03.%s.201.02.%s", "123456", "%", "01"));

    }

    @org.junit.Test
    public void test22() {
        LocalDate queryDate = LocalDate.now().withMonth(1).withDayOfMonth(1);
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate firstYearDate = queryDate.withMonth(1);
        for (int i = 0; i < queryDate.getMonthValue(); i++) {
            dateList.add(firstYearDate);
            firstYearDate = firstYearDate.plusMonths(1);
        }
        System.out.println(dateList);
    }

    @org.junit.Test
    public void test23() {
        JSONObject firstValue = new JSONObject();
        JSONObject secondValue = new JSONObject();
        JSONObject thirdValue = new JSONObject();
        JSONObject fourthValue = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        firstValue.put("totalValue", 1.1);
        secondValue.put("totalValue", 1.1);
        thirdValue.put("totalValue", null);
        fourthValue.put("totalValue", 1.1);
        jsonObject.put("1", firstValue);
        jsonObject.put("2", secondValue);
        jsonObject.put("3", thirdValue);
        jsonObject.put("4", fourthValue);
        System.out.println(jsonObject);
        getAccumulativeData(firstValue, secondValue, thirdValue, fourthValue);
        System.out.println(jsonObject);
    }

    private void getAccumulativeData(JSONObject firstValue, JSONObject secondValue, JSONObject thirdValue, JSONObject fourthValue) {
        Double firstValueDouble = firstValue.getDouble("totalValue");
        Double secondValueDouble = secondValue.getDouble("totalValue");
        Double thirdValueDouble = thirdValue.getDouble("totalValue");
        Double fourthValueDouble = fourthValue.getDouble("totalValue");
        if (null != firstValueDouble) {
            secondValueDouble = secondValueDouble == null ? firstValueDouble : firstValueDouble + secondValueDouble;
            secondValue.put("totalValue", secondValueDouble);
        }
        if (null != secondValueDouble) {
            thirdValueDouble = thirdValueDouble == null ? secondValueDouble : secondValueDouble + thirdValueDouble;
            thirdValue.put("totalValue", thirdValueDouble);
        }
        if (null != thirdValueDouble) {
            fourthValueDouble = fourthValueDouble == null ? thirdValueDouble : thirdValueDouble + fourthValueDouble;
            fourthValue.put("totalValue", fourthValueDouble);
        }
    }

    @org.junit.Test
    public void test24() {
        String s = concatSkip2Code("04.235", "02");
        System.out.println(s);
    }

    public static String concatSkip2Code(String code, String code2) {
        StringBuilder energyTypeCode = new StringBuilder(code);
        energyTypeCode.insert(code.indexOf("\\."), "." + code2);
        return energyTypeCode.toString();
    }

    @org.junit.Test
    public void test25() {
        LocalDateTime localDateTime = LocalDateTime.now().withDayOfMonth(1).withMonth(4);
        List<LocalDate> queryDates = new ArrayList<>();
        LocalDate taskDate = localDateTime.toLocalDate().plusMonths(2);
        queryDates.add(taskDate);
        int month = taskDate.getMonthValue();
        for (int i = 0; i < month - 1; i++) {
            taskDate = taskDate.minusMonths(1);
            queryDates.add(taskDate);
        }
        System.out.println(queryDates);
    }

    @org.junit.Test
    public void test26() {
        String str = "r.548795614444444416.4.01.2001.000.02.04";
        str = str.replaceFirst("\\.4\\.", ".2.");
        System.out.println(str);
    }

    @org.junit.Test
    public void test27() {
        for (int i = 0; i < trafficCodeList.size(); i++) {
            System.out.println("112");
        }
    }

    @org.junit.Test
    public void test28() {
        getTbQueryDays(LocalDate.now());
    }

    public static List<LocalDate> getTbQueryDays(LocalDate queryFirstDay) {
        LocalDate localDateNow = LocalDate.now();
        // 存储查询的日期
        List<LocalDate> dateList = new ArrayList<>();
        if (queryFirstDay.getYear() == localDateNow.getYear() && queryFirstDay.getMonthValue() == localDateNow.getMonthValue()) {
            int length = localDateNow.getDayOfMonth();
            for (int i = 0; i < length; i++) {
                dateList.add(localDateNow);
                localDateNow = localDateNow.minusDays(1);
            }
        } else {
            LocalDate queryFirstCopyDay = queryFirstDay;
            int length = queryFirstCopyDay.getMonth().maxLength();
            queryFirstCopyDay = queryFirstCopyDay.withDayOfMonth(length);
            for (int i = 0; i < length; i++) {
                dateList.add(queryFirstCopyDay);
                queryFirstCopyDay = queryFirstCopyDay.minusDays(1);
            }
        }
        int lastLength = queryFirstDay.minusYears(1).getMonth().maxLength();
        LocalDate lastMonthDay = queryFirstDay.minusYears(1).withDayOfMonth(lastLength);
        for (int i = 0; i < lastLength; i++) {
            dateList.add(lastMonthDay);
            lastMonthDay = lastMonthDay.minusDays(1);
        }
        return dateList;
    }

    @org.junit.Test
    public void test29() {
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate taskDate = LocalDate.now();
        int month = taskDate.getMonthValue();
        for (int i = 0; i < month; i++) {
            dateList.add(taskDate);
            taskDate = taskDate.minusMonths(1);
        }
        System.out.println(dateList);
    }

    @org.junit.Test
    public void test30() {
        String str = "and stat_date in (:queryDate, :lastDate)";
        str.replace(", :lastDate", "");
    }

    @org.junit.Test
    public void test31() {
        String str = "000000".replaceAll("(00){1,}$", "");
        ;
        System.out.println(str);
        System.out.println(str.startsWith(""));
    }

    @org.junit.Test
    public void test32() {
        List<LocalDate> queryQuarter = getQueryAccumulativeQuarter(LocalDate.now().withMonth(1));
        System.out.println(queryQuarter);
    }

    public static List<LocalDate> getQueryAccumulativeQuarter(LocalDate localDate) {
        LocalDate firstQuarterDate = getFirstQuarterDate(localDate);
        List<LocalDate> dateList = new ArrayList<>();
        for (int i = 0; i < (localDate.getMonthValue() + 2) / 3; i++) {
            dateList.add(firstQuarterDate);
            firstQuarterDate = firstQuarterDate.minusMonths(3);
        }
        return dateList;
    }

    public static LocalDate getFirstQuarterDate(LocalDate localDate) {
        Month month = localDate.getMonth().firstMonthOfQuarter();
        return localDate.withMonth(month.getValue()).withDayOfMonth(1);
    }

    @org.junit.Test
    public void test33() {
        LocalDate queryDate = LocalDate.now().withMonth(1);
        List<LocalDate> dateList = new ArrayList<>();
        LocalDate firstYearDate = queryDate.withMonth(1).withDayOfMonth(1);
        for (int i = 0; i < queryDate.getMonthValue(); i++) {
            dateList.add(firstYearDate);
            firstYearDate = firstYearDate.plusMonths(1);
        }
        System.out.println(dateList);
    }

    @org.junit.Test
    public void test34() {
//        String dataCodeQueryStr = " and code_1 5= 03 and code_2 = 03";
//        boolean matches = dataCodeQueryStr.matches("code_1\\s{2,}|\t| =\\s{2,}|\t| [03]");
        Pattern p=Pattern.compile("code_1(\\s+|\t+| +|)=(\\s+|\t+| +|)('03'|05)(\\s+|\t+| +|)");
        Matcher m=p.matcher(" and code_1='03' and code_3='205' ");
        System.out.println(m.find());
        System.out.println(m.group());
//        boolean matches = Pattern.matches("code_1(\\s+|\t+| +)=(\\s+|\t+| +)(03|05)", "and    code_1      =   05 and code_2 = 03");
//        System.out.println(matches);
    }

    @org.junit.Test
    public void test35() {
        LocalDate taskDate = LocalDate.now().withMonth(5).withDayOfMonth(1);
        LocalDate localDate = getFirstQuarterDate(taskDate.minusMonths(1));
        Set<LocalDate> dateSet = new HashSet<>();
        dateSet.add(localDate);
            LocalDate quarterDate = getFirstQuarterDate(taskDate.minusMonths(3));
            dateSet.add(quarterDate);
        System.out.println(dateSet);
    }

    @org.junit.Test
    public void test36() {
        System.out.println("2018-01".substring(0, 4));
    }

    @org.junit.Test
    public void test37() {
        JSON.parse("{'key':'value'}");
    }
}
