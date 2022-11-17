package com.mengship.shipsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Scanner;

@SpringBootTest
class ShipSystemApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Integer a;
        int b=0;
        while(true){
            a = s.nextInt();
            for(int i=0;i<a; i++){
                b = b+s.nextInt();
            }
            System.out.println(b);
            if(a ==null){
                break;
            }
        }
    }


}
