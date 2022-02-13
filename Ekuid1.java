package ekuid;


import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DEV_MASTER
 */
public class Ekuid1 {

    public static void main(String[] args) {
        int[] data = {3, 4, 6, 17, 25, 21, 23, 2, 4, 6, 3, 8};
        List<List<Integer>> dataList = new ArrayList<>();
        int temp = data[0];
        int startIndex = 0;
        List<Integer> tempList = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            if (data[i] < temp) {
                dataList.add(tempList);
                tempList = new ArrayList<>();
                tempList.add(data[i]);
            } else {
                tempList.add(data[i]);
            }
            temp = data[i];
        }
        dataList.add(tempList);
        System.out.println(dataList);
        
        for (List<Integer> objList : dataList) {
            System.out.println("list : " + objList);
            System.out.println("mean : " + mean(objList));
            System.out.println("median : " + median(objList));
        }
    }

    public static double mean(List<Integer> m) {
        int sum = 0;
        for (int i = 0; i < m.size(); i++) {
            sum += m.get(i);
        }
        return sum / m.size();
    }

    public static double median(List<Integer> m) {
        int middle = m.size() / 2;
        if (m.size() % 2 == 1) {
            return m.get(middle);
        } else {
            return (m.get(middle - 1) + m.get(middle)) / 2.0;
        }
    }
}
