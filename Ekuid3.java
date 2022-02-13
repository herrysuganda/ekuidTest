/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ekuid;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author DEV_MASTER
 */
public class Ekuid3 {

    public static final List<Integer> uangKertas = new ArrayList<>();

    public static void main(String[] args) {

        uangKertas.add(1000);
        uangKertas.add(2000);
        uangKertas.add(5000);
        uangKertas.add(10000);
        uangKertas.add(20000);
        uangKertas.add(50000);
        uangKertas.add(100000);

        int jumlahUang = 39000;
        int jumlahLembar = 4;
//        int jumlahUang = 24000;
//        int jumlahLembar = 4;
//        int jumlahUang = 99000;
//        int jumlahLembar = 1;

//        if (jumlahLembar == 1) {
//            System.out.println(getNilaiPecahanTerendah(jumlahUang));
//        } else {
            List<Integer> result = new ArrayList<Integer>();
            getNilaiPecahan(0, jumlahUang, 0, jumlahLembar - 1, result);
            System.out.println(result);
//        }

    }

    public static int getNilaiPecahanTertinggi(int amount) {
        int temp = 0;
        for (int i = 0; i < uangKertas.size(); i++) {
            if (amount < uangKertas.get(i)) {
                return temp;
            }
            temp = uangKertas.get(i);
        }
        return 0;
    }

    public static int getNilaiPecahanTerendah(int amount) {
        int temp = 0;
        for (int i = 0; i < uangKertas.size(); i++) {
            if (amount <= uangKertas.get(i)) {
                return uangKertas.get(i);
            }
            temp = uangKertas.get(i);
        }
        return 0;
    }

    public static Integer getNilaiPecahan(int totalAmount, int expectedAmount, int position,
            int maxPosition, List<Integer> nilaiUang) {
        int nextPosition = 0;
        int tempAmount = 0;
        for (int i = 0; i < uangKertas.size(); i++) {
//            System.out.println("position : " + position + " - uang kertas : " + uangKertas.get(i));
            if (position < maxPosition) {
//                System.out.println("total amount : " + (totalAmount + uangKertas.get(i)));
                if (totalAmount + uangKertas.get(i) >= expectedAmount) {
                    return (totalAmount);
                } else {
                    tempAmount = totalAmount + uangKertas.get(i);
                    if (nilaiUang.size() > position) {
                        nilaiUang.set(position, uangKertas.get(i));
                    } else {
                        nilaiUang.add(position, uangKertas.get(i));
                    }
                    nextPosition = position + 1;
                    int newTotalAmount = getNilaiPecahan(tempAmount, expectedAmount, nextPosition, maxPosition, nilaiUang);
//                    System.out.println("dapet return dari rekursif : " + nilaiUang);
//                    System.out.println("total amount rekursif : " + totalAmount);
                    if (newTotalAmount == expectedAmount) {
                        return newTotalAmount;
                    }
                }
            } else {
//                System.out.println("total amount : " + (totalAmount + uangKertas.get(i)));
                    if (totalAmount + uangKertas.get(i) >= expectedAmount) {
                    totalAmount += uangKertas.get(i);
//                    System.out.println("pengecekan terakhir total amount : " + totalAmount);

                    if (nilaiUang.size() > position) {
                        nilaiUang.set(position, uangKertas.get(i));
                    } else {
                        nilaiUang.add(position, uangKertas.get(i));
                    }
                    return totalAmount;
                }
            }
        }
        return 0;
    }
}
