/*
    Program created by Kim-Frederique Viens
    Date: 2nd December 2019
    Program Description: This program goes through all the txt files that are contained within the src folder. It uses a
    hashmap containing common data structures and others that I saw while looking through the source code. When it finds
    a declaration of a data structure it increments the item in the local file hashmap and in the global hashmap.

 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Count {

    public static HashMap<String, Integer> globalDatatypes = createMap();
    public static String path = "C:\\Users\\kimvi\\IdeaProjects\\CountDataTypes\\src\\";

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Datatypes for CORE");
        System.out.println("Binary");

        String[] coreBinaryFiles = {"BinaryUtils", "BitString"};
        countFiles(coreBinaryFiles);

        System.out.println("\nCombinatorics");
        String[] coreCombinatoricsFiles = {"CombinationGenerator", "PermutationGenerator"};
        countFiles(coreCombinatoricsFiles);

        System.out.println("\nNumber");
        String[] coreNumberFiles = {"AdjustableNumberGenerator", "ConstantGenerator", "NumberGenerator", "Rational"};
        countFiles(coreNumberFiles);

        System.out.println("\nRandom");
        String[] coreRandomFiles = {"AESCounterRNG", "BinomialGenerator", "CMWC4096RNG", "CellularAutomatonRNG", "ContinuousUniformGenerator",
                                    "DefaultSeedGenerator", "DevRandomSeedGenerator", "DiehardInputGenerator", "DiscreteUniformGenerator",
                                    "ExponentialGenerator", "GaussianGenerator", "JavaRNG", "MersenneTwisterRNG", "PoissonGenerator",
                                    "Probability", "RandomDotOrgSeedGenerator", "RepeatableRNG", "SecureRandomSeedGenerator",
                                    "SeedException", "SeedGenerator", "XORShiftRNG"};
        countFiles(coreRandomFiles);

        System.out.println("\nStatistics");
        String[] coreStatisticsFiles = {"Dataset", "EmptyDataSetException"};
        countFiles(coreStatisticsFiles);

        System.out.println("\nMaths");
        System.out.println("\tMaths.java");
        countTypes(path+"Maths.txt");


        System.out.println("\nDatatypes for DEMO");
        String[] coreDEMOFiles = {"BinomialDistribution", "BinomialParametersPanel", "DistributionPanel", "ExponentialDistribution",
                                  "ExponentialDistribution","ExponentialParametersPanel", "GaussianDistribution", "GaussianParametersPanel",
                                  "GraphPanel", "ParametersPanel","PoissonDistribution", "PoissonParametersPanel", "ProbabilityDistribution",
                                  "PoissonParametersPanel", "RNGPanel","RandomDemo", "UniformDistribution", "UniformParametersPanel"};
        countFiles(coreDEMOFiles);

        System.out.println("\n");
        System.out.println("Datatypes for the whole package:");
        System.out.println(printMap(globalDatatypes));
    }

    public static void countFile(String filename) throws FileNotFoundException {
        System.out.println(filename+".java");
        countTypes(path+filename+".txt");
        System.out.println();
    }

    public static void countFiles(String[] files) throws FileNotFoundException {
        for (int i = 0; i < files.length; i++) {
            countFile(files[i]);
        }
    }

    public static String getDataFromFile(String filename){
        File file = new File(filename);
        Scanner inputFile = null;
        try {
            inputFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String text = "";

        while (inputFile.hasNextLine()) {
            text += inputFile.nextLine();
            text += " ";
        }

        inputFile.close();

        return text;
    }

    public static void countTypes(String filename) throws FileNotFoundException {
        String text = getDataFromFile(filename);
        //Count all types
        HashMap<String, Integer> map = datatypesMap(text);

        System.out.println(printMap(map));
    }

    public static HashMap<String, Integer> createMap(){
        HashMap<String, Integer> datatypes = new HashMap<String, Integer>();

        datatypes.put("boolean", 0);
        datatypes.put("int", 0);
        datatypes.put("double", 0);
        datatypes.put("long", 0);
        datatypes.put("char", 0);
        datatypes.put("byte", 0);
        datatypes.put("int[]", 0);
        datatypes.put("double[]", 0);
        datatypes.put("long[]", 0);
        datatypes.put("char[]", 0);
        datatypes.put("byte[]", 0);
        datatypes.put("T[]", 0);
        datatypes.put("List<T>", 0);
        datatypes.put("Iterator", 0);
        datatypes.put("Collection", 0);
        datatypes.put("Random", 0);
        datatypes.put("BigInteger", 0);
        datatypes.put("BitString", 0);
        datatypes.put("BigDecimal", 0);
        datatypes.put("Rational", 0);
        datatypes.put("ArrayList", 0);
        datatypes.put("File", 0);
        datatypes.put("SeedGenerator", 0);
        datatypes.put("null", 0);
        datatypes.put("float", 0);
        datatypes.put("float[]", 0);
        datatypes.put("short", 0);
        datatypes.put("short[]", 0);
        datatypes.put("T", 0);
        datatypes.put("String", 0);
        datatypes.put("String[]", 0);
        datatypes.put("HashMap", 0);
        datatypes.put("SortedMap", 0);
        datatypes.put("ConstantGenerator", 0);
        datatypes.put("NumberGenerator", 0);
        datatypes.put("Integer", 0);
        datatypes.put("Probability", 0);
        datatypes.put("TreeMap", 0);
        datatypes.put("Map", 0);
        datatypes.put("Graph", 0);

        return datatypes;
    }

    public static HashMap<String, Integer> datatypesMap(String text){
        HashMap<String, Integer> datatypes = createMap();

        text = text.replaceAll("[(]", " ");
        text = text.replaceAll("[<]", " ");
        String[] arrayText = text.split("\\s+");

        for (int i = 0; i < arrayText.length; i++) {
            if(datatypes.containsKey(arrayText[i])){
                datatypes.put(arrayText[i], datatypes.get(arrayText[i])+1);
                globalDatatypes.put(arrayText[i], globalDatatypes.get(arrayText[i])+1);
            }
        }

        return datatypes;
    }

    public static String printMap(HashMap<String, Integer> map){
        String result = "";
        boolean isEmpty = true;
        for (String datatype: map.keySet()){
             if(map.get(datatype) != 0){
                 result += datatype+": "+map.get(datatype)+" ";
                 isEmpty = false;
             }
        }

        if(isEmpty){
            result = "No notable datatypes found.";
        }

        return result;
    }
}
