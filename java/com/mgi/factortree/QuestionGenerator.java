package com.mgi.factortree;

import java.util.ArrayList;
import java.util.Random;

public class QuestionGenerator {
    private final int MAXIMUM_OPTIONS = 5;
    private final int MINIMUM_NUMBER_EASY = 10;
    private final int MINIMUM_NUMBER_NORMAL = 100;
    private final int MINIMUM_NUMBER_HARD = 500;
    private final int RANGE_EASY = 90;
    private final int RANGE_NORMAL = 500;
    private final int RANGE_HARD = 500;
    private final int RANGE_MINIMUM_OPTION = 5;


    static final int MODE_EASY = 1;
    static final int MODE_NORMAL = 2;
    static final int MODE_HARD = 3;


    public boolean isPrime(int n){
        if(n==2)
            return true;
        if(n%2==0)
            return false;

        for(int i=3; i<=(int)Math.sqrt(n);i+=2)
            if(n%i==0)
                return false;

        return true;
    }

    public int generateNumber(int MODE){
        Random r = new Random();
        int number=0;
        do{
            if(MODE == MODE_EASY)
                number = MINIMUM_NUMBER_EASY+r.nextInt(RANGE_EASY + 1);
            else if(MODE == MODE_NORMAL)
                number = MINIMUM_NUMBER_NORMAL+r.nextInt(RANGE_NORMAL + 1);
            else if(MODE == MODE_HARD)
                number = MINIMUM_NUMBER_HARD+r.nextInt(RANGE_HARD + 1);
        }while(isPrime(number));

        return number;
    }

    public int searchDivisor(int n){
        int divisor=2;
        if(n%divisor == 0)
            return divisor;
        for(divisor=3; divisor <= (int) Math.sqrt(n) && n%divisor != 0; divisor+=2);
        return divisor;
    }

    public ArrayList<Integer> generateOptions(int n, int divisor){
        ArrayList<Integer> options = new ArrayList<Integer>();
        options.add(n/divisor);

        Random r = new Random();
        int randomOption;
        int rangeRandom= Math.max(RANGE_MINIMUM_OPTION,n/divisor);
        while(options.size()<MAXIMUM_OPTIONS){
            randomOption = r.nextInt(rangeRandom+ 1);
            if(!options.contains(randomOption) && randomOption != 0)
                options.add(randomOption);
        }
        options = shuffleOption(options);
        return options;
    }

    private ArrayList<Integer> shuffleOption(ArrayList<Integer> options){
        Random r = new Random();
        int temp, rIndex;
        for(int i=0; i<options.size(); i++){
            rIndex = r.nextInt(options.size());
            temp = options.get(i);
            options.set(i, options.get(rIndex));
            options.set(rIndex, temp);
        }
        return options;
    }

    public ArrayList<ArrayList<Integer>> getSolution(int n){
        ArrayList<Integer> divisors = new ArrayList<Integer>();
        ArrayList<Integer> quotients = new ArrayList<Integer>();

        for(int divisor = searchDivisor(n); !isPrime(n); divisor= searchDivisor(n)){
            n/=divisor;
            divisors.add(divisor);
            quotients.add(n);
        }
        ArrayList<ArrayList<Integer>> solution = new ArrayList<ArrayList<Integer>>();
        solution.add(divisors);
        solution.add(quotients);
        return solution;
    }
}
