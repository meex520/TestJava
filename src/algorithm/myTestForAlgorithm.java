package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyangfeng on 2017/12/20.
 */
public class myTestForAlgorithm {


    public static void main(String args[]) {

        int num = 2015;
        List<Integer> list = new ArrayList();
        List<Integer> listOff = new ArrayList();
        for (int i = 1; i <= num; i++) {//构造集合
            list.add(i);
            listOff.add(0);
        }

        for (int aList : list) {
            for (int i = aList; i < list.size(); i++) {
                if (list.get(i) % aList == 0) {
                    listOff.set(i, ~listOff.get(i));
                }
            }
        }

        int count = 0;
        for (int i = 0; i < listOff.size(); i++) {
            int aListOff = listOff.get(i);
            if (aListOff == 0) {
                count++;
                System.out.print(i + 1 + " ");
            }
        }
        System.out.println();
        System.out.print(count);
    }

}
