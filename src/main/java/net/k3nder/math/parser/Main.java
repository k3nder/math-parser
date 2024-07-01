package net.k3nder.math.parser;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MathParser parser = MathParser.getInstance();
        while (true) {
            System.out.print("op: ");
            var l = sc.nextLine();
            if ("exit".equals(l)) break;
            var probs = parser.getProbs(l);
            System.out.println("PROBS");
            Arrays.stream(probs.probs()).forEach(v -> System.out.println(v.getA() + " " + v.getOperation() + " " + v.getB() + " ---> " + v.operate()));
            System.out.println("OPS");
            Arrays.stream(probs.operations()).forEach(System.out::println);

            System.out.println("-----------> " + parser.parse(l));
        }
        //var probob = new MathParser().getProbs(s);
        //Arrays.stream(probob.operations()).forEach(System.out::println);
        //Arrays.stream(probob.probs()).forEach(System.out::println);
    }
}