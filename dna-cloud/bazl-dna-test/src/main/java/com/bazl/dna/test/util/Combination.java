package com.bazl.dna.test.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 计算数组的所有组合阵列
 * @author zhaoyong
 */
public class Combination {

    private static final int START = 2;

    public static void main(String[] args) {
        String[] strs = new String[]{
                "AMELOGENIN", "TH01", "TPOX", "CSF1PO", "D18S51", "D21S11"};
        try {
            List<Object> hy5 = Combination.combine(strs, 3);
            for (Object o : hy5) {
                System.out.println(o);
            }
            System.out.println("总个数：" + hy5.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从n个字符中选择m个字符生成组合阵列字符串，每个组合值每位上的数据不重复
     * <p>
     * 例如取从10个数字中取3个数字组成三位数，每位上数字不重复，如ABC
     *
     * @param dans 字符数组
     * @param m    从字符数组中取m个数产生组合阵列
     * @return List
     * @throws Exception 异常
     */
    public static List<Object> combine(String[] dans, int m) throws Exception {
        int n = dans.length;
        if (m > n) {
            throw new Exception("错误！数组a中只有" + n + "个元素。" + m + "大于" + n + "!!!");
        }
        List<Object> result = new ArrayList<>();
        if (m < START) {
            Collections.addAll(result, dans);
            return result;
        }
        List<List<String>> rList = Lists.newArrayList();
        int[] bs = new int[n];
        // 初始化
        for (int i = 0; i < m; i++) {
            bs[i] = 1;
        }
        boolean flag = true;
        boolean tempFlag = false;
        int pos = 0;
        int sum = 0;
        // 首先找到第一个10组合，然后变成01，同时将左边所有的1移动到数组的最左边
        do {
            sum = 0;
            pos = 0;
            tempFlag = true;
            rList.add(extract(bs, dans, m));
            for (int i = 0; i < n - 1; i++) {
                if (bs[i] == 1 && bs[i + 1] == 0) {
                    bs[i] = 0;
                    bs[i + 1] = 1;
                    pos = i;
                    break;
                }
            }
            // 将左边的1全部移动到数组的最左边
            for (int i = 0; i < pos; i++) {
                if (bs[i] == 1) {
                    sum++;
                }
            }
            for (int i = 0; i < pos; i++) {
                if (i < sum) {
                    bs[i] = 1;
                } else {
                    bs[i] = 0;
                }
            }
            // 检查是否所有的1都移动到了最右边
            for (int i = n - m; i < n; i++) {
                if (bs[i] == 0) {
                    tempFlag = false;
                    break;
                }
            }
            flag = !tempFlag;
        } while (flag);
        rList.add(extract(bs, dans, m));
        result.addAll(rList);
        return result;

    }

    /**
     * 从n个字符中选择m个字符生成组合阵列字符串，每个组合值每位上的数据有tel个重复
     * <p>
     * 例如取从10个数字中取3个数字组成三位数，tel=1表示有一个重复数，如AAB
     *
     * @param dans 字符数组
     * @param m    从字符数组中取m个数产生组合阵列
     * @param tel 元素
     * @return List
     * @throws Exception 异常
     */
    public static List<Object> combine(String[] dans, int m, int tel) throws Exception {

        if (tel == 0) {
            return combine(dans, m);
        }
        int n = dans.length;
        if (m - tel > n) {
            throw new Exception("错误！数组a中只有" + n + "个元素。" + m + "大于" + n + "!!!");
        }
        List<Object> result = new ArrayList<>();
        if (m < START) {

            Collections.addAll(result, dans);
            return result;
        }
        result.add(combine(dans, m - tel));
        List<Object> sb = new ArrayList<>();

        String[] rs = {};
        result.toArray(rs);
        for (String r : rs) {
            char[] cf = r.toCharArray();
            char[] tmp = new char[cf.length + tel];
            for (int j = 0; j < cf.length; j++) {
                for (int k = 0; k < tel; k++) {
                    tmp[k] = cf[(k + j) % cf.length];
                }
                for (int k = 0; k < cf.length; k++) {
                    tmp[tel + k] = cf[k];
                }
                Arrays.sort(tmp);
                for (char c : tmp) {
                    sb.add(c);
                }
            }

        }
        sb.toArray(rs);
        Arrays.sort(rs);
        sb = new ArrayList<>();
        Collections.addAll(sb, rs);
        return sb;

    }

    /**
     * 从胆码数组中提取对应组合数组标识为1的有效数据
     *
     * @param bs   组合标识数组
     * @param dans 胆码数组
     * @param m 元素
     * @return List
     */

    private static List<String> extract(int[] bs, String[] dans, int m) {

        List<String> result = Lists.newArrayList();

        for (int i = 0; i < bs.length; i++) {

            if (bs[i] == 1) {
                result.add(dans[i]);
            }

        }

        return result;

    }

}
