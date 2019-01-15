//package com.xyy.springboot;
//
//import com.xyy.springboot.domain.BaseHashCode;
//import org.junit.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author xuyy
// * @date 2018/7/13 15:18
// */
//public class HashCodeTest {
//
//    @Test
//    public void test1() {
//        Map<BaseHashCode, String> map = new HashMap<>();
//        map.put(new BaseHashCode(), "1");
//        map.put(new BaseHashCode(), "2");
//        //执行到这里map的size是1，因为map的key判断是否相同的依据是hashcode是否相同，因为baseHashCode设置了hashcode的放回值都是3
//        //hashCode的写法可参照BaseUser类
//        for (BaseHashCode baseHashCode : map.keySet()) {
//            System.out.println(baseHashCode.toString());
//        }
//    }
//}
