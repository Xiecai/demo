package test;

import com.google.common.base.Strings;
import org.junit.Test;

public class PalindromePractice {

    /**
     * 寻找字符串中最长的回文字符串O(n^2)
     * 网上有很多种写法本例为Manacher算法
     * Manacher算法是查找一个字符串的最长回文子串的线性算法
     * 针对算法 确实不太容易理解 只有多练多想。不行的话默写几遍 实践出真知
     * */
    @Test
    public void longestPalindrome() {
        String str = "oir82930fh7u8934ru304134";
        char[] chars = new char[str.length() * 2 + 1];
        for (int i = 0; i < str.length(); i++) {
            chars[2 * i] = '#';
            chars[2 * i + 1] = str.charAt(i);
        }
        int max = 0;
        int idx = 0;
        for (int i = 0;i < chars.length;i++) {
            int k = 0;
            while (i - k >= 0 && i + k < chars.length && chars[i - k] == chars[i + k]) {
                k++;
            }
            if (k > max) {
                max = k;
                idx = i;
            }
        }
        if (idx - max == -1) {
            System.out.println("not exist!");
        } else {
            String longestPalindromeStr = String.valueOf(chars).substring(idx - max + 1,idx + max);
            System.out.println(longestPalindromeStr.replaceAll("#",""));
        }

    }

    /**
     * 寻找回文字符串 O(n^2)
     * 当我第一次听到回文字符串这个题的时候我自己写了一个如下的代码，功能也能正常跑，但是有缺陷.
     * bug：无法找到 aass中的aa与ss这种形式的。总的来说是偏离了主题，我这个只是找到奇数的回文字符串，还不是那种轴对称的
     * 没有再次将它改成需求所要。如果要是遇到面试了还是直接来上方的找寻回文方法比较好。
     * 记录留念
     * */
    @Test
    public void dealWithByArray() {
        String str = "asas";

        if (Strings.isNullOrEmpty(str)) {
            System.out.println("未找到合法数据");
            return;
        }

        int length = str.length();

        char[] strArr = new char[length];

        for (int i = 0; i < length; i++) {
            strArr[i] = str.charAt(i);
        }

        if (length == 2) { // 写这个的时候还考虑了轴对称这种形式，后边儿就忘了
            if ((strArr[0]) == (strArr[1])) {
                System.out.println("回文字符串是:" + str);
            } else {
                System.out.println("未找到回文字符串");
            }
            return;
        }

        for (int startPointIndex = 0; startPointIndex < length; startPointIndex++) {
            int endPointIndex = startPointIndex + 2;
            if (endPointIndex < length) {
                char startPoint = strArr[startPointIndex];
                char endPoint = strArr[endPointIndex];
                int expandStartPoint = 0;
                int expandEndPoint = 0;
                boolean exist = false;
                if (startPoint == endPoint) {
                    exist = true;
                    expandStartPoint = startPointIndex - 1;
                    expandEndPoint = endPointIndex + 1;
                    while (expandStartPoint > 0 && expandEndPoint < length) {
                        if (strArr[expandStartPoint] != strArr[expandEndPoint]) {
                            break;
                        }
                        expandStartPoint--;
                        expandEndPoint++;
                    }
                }
                if (exist) {
                    System.out.println(str.substring(expandStartPoint + 1, expandEndPoint));
                }
            } else {
                break;
            }
        }

    }

}
