import java.util.LinkedList;
import java.util.List;

public class MultiprocessorSystemEmissor {

    private LinkedList<CPU> cpuList;
    private int amountCPU = 0;
    private int sucessMessage = 0;
    private int failMessage = 0;

    public MultiprocessorSystemEmissor() {
        cpuList = new LinkedList<CPU>();
    }

    public void addCPU(CPU cpu) {
        cpuList.add(cpu);
        amountCPU++;
    }

    public void addProcess(Process process, int idx) {
        Rnd.shuffle(amountCPU);
        for (int i = 0; i < amountCPU; i++) {
            int aux = Rnd.nextInt();
            if(aux != idx){
                CPU cpu = cpuList.get(aux);
                if(cpu.doesAcceptProcess()){
                    cpu.addProcess(process);
                    sucessMessage++;
                    return;
                }
                failMessage++;
            }
        }
        CPU cpu = cpuList.get(idx);
        cpu.addProcess(process);
    }

    public void update() {
        for (CPU cpu : cpuList) {
            cpu.update();
        }
    }

    public void printState() {
        for (CPU cpu : cpuList) {
            cpu.printState();
        }
        System.out.println();
    }

    public int getAmountCPU() {
        return amountCPU;
    }

    public int getSucessMessage() {
        return sucessMessage;
    }

    public int getFailMessage() {
        return failMessage;
    }

    public static void main(String[] args){
        MultiprocessorSystemEmissor mse = new MultiprocessorSystemEmissor();
        mse.addCPU(new CPU(0,3));
        mse.addCPU(new CPU(1,3));
        mse.addCPU(new CPU(2,3));
        int TMT = Rnd.nextGaussian();
        for (int i = 0, k = 0; i < 1000; i++, k++) {
            if(k == TMT){
                k = 0;
                TMT = Rnd.nextGaussian();
                for (int j = 0; j < mse.getAmountCPU(); j++) {
                    mse.addProcess(new Process(Rnd.nextGaussian()), Rnd.getNum(mse.getAmountCPU()));
                }
            }
            mse.update();
        }
        System.out.println(mse.getFailMessage());
        System.out.println(mse.getSucessMessage());
    }
}
