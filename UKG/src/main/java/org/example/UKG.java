package org.example;

import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class UKG {

    private static Singleton instance;

    private Singleton(){
        System.out.println("Singleton initialised");
    }

    static synchronized Singleton getInstance(){
        if(instance == null){
            instance = new singleton;
        }
    }


   static void generateSubsequence(int index, StringBuilder cur , String str, HashSet<String> s){
        int n = str.length();

        if(index == n){
            s.add(cur.toString());
            return;
        }

        generateSubsequence(index + 1, cur, str, s);
        //gfg
        cur.append(str.charAt(index));
        generateSubsequence(index+1, cur, str, s);

        cur.deleteCharAt(cur.length()-1);

    }

    static int distinctSubsequence(String str){
        HashSet<String> s = new HashSet<>();
        StringBuilder cur  = new StringBuilder();
        generateSubsequence(0, cur, str, s);

        return s.size();
    }

    @Bean

    public static void main(String[] args) {

//        String str = "ggg";
//        System.out.println(distinctSubsequence(str));

//        TreeSet<String> treeSet1 = new TreeSet<>(Comparator.comparing(String::length));
//
////        String[] words = {"apple", "cat"};
//        treeSet1.add("bat");
//        treeSet1.add("apple");
//        treeSet1.add("orange");
//
//        treeSet1.forEach(System.out::println);

    }




}
