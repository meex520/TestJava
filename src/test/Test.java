package test;

import idCard.IDCardUtil;

/**
 * Created by zhuyangfeng on 2017/12/28.
 */
public class Test {

    public static void main(String args[]) {

//        int i = 0;
//        for (int j = 0; j < 10; j++) {
//            i = i++;
//        }
//        System.out.print(i);

        System.out.printf(String.valueOf(IDCardUtil.isValid("340122199105157673")));
    }
}
