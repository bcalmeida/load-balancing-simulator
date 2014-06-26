import java.util.Random;

public class Rnd {

    private static Random r = new Random();
    private static int variance = 2;
    private static int mean = 7;
    private static int[] shuffleList;
    private static int j = 0;


    public static int nextGaussian(){
        int aux = mean + variance * (int) Math.round(r.nextGaussian());
        return (aux > 0 ? aux: nextGaussian());
    }

    public static void shuffle(int numCpu){
        shuffleList = new int[numCpu];
        for (int i = 0; i < numCpu; i++) {
            shuffleList[i] = i;
        }
        Random r = new Random();
        for (int i = numCpu - 1; i > 0 ; i--) {
            int idx = r.nextInt(i+1);
            int aux = shuffleList[idx];
            shuffleList[idx] = shuffleList[i];
            shuffleList[i] = aux;
        }
        j = 0;
    }

    public static int nextInt(){
        return shuffleList[j++];
    }

    public static int getNum(int numCPU){
        return r.nextInt(numCPU);
    }
}
