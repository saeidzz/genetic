package genetic;

import java.util.Scanner;

public class Main {
    public static int targetNumber = 0;

    public static void main(String[] args) {
        //first step is to generate first generation random

        System.out.println("  نسل اول = n Chromosome\n" +
                "                \n" +
                "             [][][]][][][]...[]     //یک کروموزوم \n" +
                "             [][][]][][][]...[]     //یک کروموزوم \n" +
                "             [][][]][][][]...[]     //یک کروموزوم \n" +
                "             [][][]][][][]...[]     //یک کروموزوم \n" +
                "             [][][]][][][]...[]     //یک کروموزوم \n" +
                "             [][][]][][][]...[]     //یک کروموزوم \n" +
                "             [][][]][][][]...[]     //یک کروموزوم \n" +
                "             [][][]][][][]...[]     //یک کروموزوم \n" +
                "             [][][]][][][]...[]     //یک کروموزوم \n" +
                "             [][][]][][][]...[]       //یک کروموزوم ");

        System.out.println("ساختار هر کروموزم متشکل از یک آرایه از جملات می باشد و هر جمله از ترکیب یک عملگر و یک عدد به وجود می آید");

        Scanner scanner = new Scanner(System.in);
        System.out.println("لطفا تعداد دلخواه کروموزم ها را در هر نسل وارد نمایید ");
        int numbOfChromosomes = scanner.nextInt();
        Chromosome[] generation = new Chromosome[numbOfChromosomes];
        //  int a=
        System.out.println(": تولید نسل اولیه و محاسبه هزینه ");

        /**
         * تولید نسل اولیه و محاسبه هزینه
         */

        System.out.println("لطفا تعداد جملات دلخواه هر کروموزوم را وارد نمایید ");
        int expNumber = scanner.nextInt();
        System.out.println();
        System.out.println("نسل اول به شکل زیر ایجاد شده است نتیجه تابع هزینه هر کروموزم در مقابل آن نوشته شده : ");
        for (int i = 0; i < numbOfChromosomes; i++) {
            generation[i] = Util.createChromosome(expNumber);
        }

        for (Chromosome chromosome : generation) {
            Util.chromosomeAndCostPrint(chromosome);
        }

        System.out.println("لطفا عددی که میخواهید به آن برسید را وارد نمایید :");

        targetNumber = scanner.nextInt();

        System.out.println("پس از مرتب سازی : ");
        Util.bubbleSort(generation, targetNumber);

        //تشکیل نسل های بعدی

        System.out.println("لطفا تعداد نسل هایی که میخواهید جهش صورت بگیرد را وارد نمایید :");
        int numberOfGenerations = scanner.nextInt();

        while (numberOfGenerations > 0) {
            Chromosome[] nextGeneration = Util.calculateNextGeneration(generation, targetNumber);
            if (nextGeneration != null) {
                System.out.println("پس از مرتب سازی : ");
                Util.bubbleSort(nextGeneration, targetNumber);
            }
            numberOfGenerations--;
        }
    }


}
