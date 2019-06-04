package genetic;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Util {
    public static Chromosome createChromosome(int expNumber) {
        //ساخت کروموزوم

        Node[] chromosome = new Node[expNumber];

        char[] operators = {'+', '-', '*'};
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50};
        List<Integer> integerList = Arrays.asList(numbers);
        Collections.shuffle(integerList);

        Random random = new Random();

        for (int i = 0; i < chromosome.length; i++) {
            int charIndex = (Math.abs(random.nextInt())) % 3;
            int numberIndex = (Math.abs(random.nextInt())) % 50;
            chromosome[i] = new Node(operators[charIndex], integerList.get(numberIndex));
        }
        return new Chromosome(chromosome,calculateChromosomeCost(chromosome));
    }

    public static String convertChromosomeToExp(Node[] nodes) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(nodes[0].getNumper());
        for (int i = 1; i < nodes.length; i++) {
            stringBuilder.append(nodes[i].getOperation());
            stringBuilder.append(nodes[i].getNumper());
        }
        return stringBuilder.toString();
    }

    public static void chromosomeAndCostPrint(Chromosome chromosome) {
        String expression = convertChromosomeToExp(chromosome.getExp());
        System.out.println(expression + " = " + chromosome.getCost());
    }

    public static long calculateChromosomeCost(Node[] nodes) {
        String expression = convertChromosomeToExp(nodes);

        ScriptEngine engine = new ScriptEngineManager().getEngineByExtension("js");
        Object result = 0;
        try {
            result = engine.eval(expression);
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return  (Integer)result;
    }

    public static Chromosome[] bubbleSort(Chromosome[] chromosomes, int targetNumber) {
        //مرتب سازی بر اساس نزدیک ترین هزینه به عدد دلخواه

        /**
         * محاسبه فاصله هزینه ها  با عدد دلخواه
         */
        double[] costs = new double[chromosomes.length];
        for (int l = 0; l < chromosomes.length; l++) {
            if (chromosomes[l].getCost() < 0) {
                costs[l] = Math.abs(chromosomes[l].getCost()) + targetNumber;
            } else {
                costs[l] = Math.abs(targetNumber - chromosomes[l].getCost());
            }
        }
        int n = chromosomes.length;
        Chromosome temp;
        Double tempCost;
        /**
         * مرتب سازی بر اساس فاصله اعداد با عدد دلخواه
         */
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if (costs[j - 1] > costs[j]) {
                    //swap elements
                    temp = chromosomes[j - 1];
                    chromosomes[j - 1] = chromosomes[j];
                    chromosomes[j] = temp;

                    tempCost = costs[j - 1];
                    costs[j - 1] = costs[j];
                    costs[j] = tempCost;
                }
            }
        }
        for (Chromosome l : chromosomes) {
            chromosomeAndCostPrint(l);
        }
        return chromosomes;
    }

    public static void printGeneration(Chromosome[] generation) {
        // چاپ نسل
        System.out.println("Generation { \n");
        for (Chromosome chromosomes : generation) {
            System.out.print("Chromosome { ");
            for (Node node : chromosomes.getExp()) {
                System.out.print(node.toString());
            }
            System.out.println("}");
        }
        System.out.println("\n}");
    }

    public static Chromosome[] calculateNextGeneration(Chromosome[] currentSortedGeneration, int targetNumber) {
        //50 درصد از نسل بعدی از بهترین نتایج نسل فعلی به دست می آید
        //50  درصد از نسل بعدی حاصل جهش 50 درصد بهترین نتایج نسل فعلی است
        if (currentSortedGeneration[0].getCost() != targetNumber) {
            System.out.println("نسل فعلی هنوز به پاسخ مورد نظر نرسیده و نسل بعدی به شکل زیر محاسبه می شود.");
            int fiftyPercent = (currentSortedGeneration.length * 5) / 10;
            Chromosome[] nextGeneration;//= new Chromosome[currentSortedGeneration.length];
          /*  for (int i = 0; i < eightyPercent; i++) {
                nextGeneration[i] = currentSortedGeneration[i];
            }*/

            //جهش و محاسبه 50 درصد دیگر

            //نقطه جهش
            nextGeneration = mutation( currentSortedGeneration, fiftyPercent, targetNumber);

            return nextGeneration;
        } else {
            System.out.println("نیازی به محاسبه نسل های بعدی نیست نسل فعلی به عدد دلخواه رسیده است ");
            return null;
        }
    }

    private static Chromosome[] mutation( Chromosome[] currentGeneration, int fiftyPercent, int targetNumber) {

        int length = currentGeneration[0].getExp().length;
        Random random=new Random();
        // در این قسمت 50 درصد انتهایی نسل بعدی توسط جهش 50 درصد نسل اول پر میشود
        for (int i = fiftyPercent; i < currentGeneration.length; i++) {
            for (int j = 0; j < length; j++) {
                currentGeneration[i].getExp()[j] = currentGeneration[i - fiftyPercent].getExp()[Math.abs(random.nextInt())%length];
            }
        }

        for (int i = fiftyPercent; i < currentGeneration.length; i++) {
            for (int j = length-1; j >0; j--) {
                currentGeneration[i].getExp()[j] = currentGeneration[i - fiftyPercent].getExp()[Math.abs(random.nextInt())%length];
            }
        }

        for (int i = 0; i < currentGeneration.length; i++) {
            currentGeneration[i] = new Chromosome(currentGeneration[i].getExp(), calculateChromosomeCost(currentGeneration[i].getExp()));
        }
        bubbleSort(currentGeneration, targetNumber);
        return currentGeneration;
    }
}
