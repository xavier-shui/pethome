package cn.xavier.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 对数据库列排序, 方便找差别
 * @author Zheng-Wei Shui
 * @date 12/1/2021
 */
public class DbColumnSort {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while(scanner.next() != null) { // 输入任意字符进入下一次循环
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new FileReader
                    ("H:\\Java\\ideaworkspace\\pethome\\src\\test\\resources" +
                    "\\ddl.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String[] inputArr = sb.toString().split("\n");
            String[] columnArr = Arrays.copyOfRange(inputArr, 1, inputArr.length);
            Arrays.sort(columnArr);
            System.out.println(inputArr[0]); // 第一行表名原样输出
            Arrays.stream(columnArr).forEach(System.out::println);
            br.close();
        }
    }
}
