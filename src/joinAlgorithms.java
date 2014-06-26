import java.util.LinkedList;

/**
 * Created by Bruno on 24/06/2014.
 */
public class joinAlgorithms {

    private LinkedList<CPU> cpuList;
    private int amountCPU = 0;
    private int sucessMessage = 0;
    private int failMessage = 0;

    public joinAlgorithms() {
        cpuList = new LinkedList<CPU>();
    }


    public void addCPU(CPU cpu) {
        cpuList.add(cpu);
        amountCPU++;
    }

    public void addProcess(Process process, int idx) {
        CPU cpu = cpuList.get(idx);
        if(cpu.doesAcceptProcess()) {
            cpu.addProcess(process);
            return;
        }
        Rnd.shuffle(amountCPU);
        for (int i = 0; i < amountCPU; i++) {
            int aux = Rnd.nextInt();
            if(aux != idx){
                CPU cpu2 = cpuList.get(aux);
                if(cpu2.doesAcceptProcess()){
                    cpu2.addProcess(process);
                    sucessMessage++;
                    return;
                }
                failMessage++;
            }
        }
        cpu.addProcess(process);
    }

    public void update() {
        for (CPU cpu : cpuList) {
            boolean processFinished = cpu.update();
            if(processFinished && cpu.isIdle()){
                Rnd.shuffle(amountCPU);
                for (int j = 0; j < amountCPU; j++) {
                    int aux = Rnd.nextInt();
                    CPU cpu2 = cpuList.get(aux);
                    if(cpu != cpu2){
                        if(cpu2.isOverloaded()) {
                            Process process = cpu2.getLastProcess();
                            cpu2.deleteLastProcess();
                            cpu.addProcess(process);
                            sucessMessage++;
                            break;
                        }
                        failMessage++;
                    }
                }
            }
        }
    }

    public void lookForProcess(){
        for (int i = 0; i < amountCPU; i++) {
            CPU cpu = cpuList.get(i);
            if(cpu.isIdle()){
                Rnd.shuffle(amountCPU);
                for (int j = 0; j < amountCPU; j++) {
                    int aux = Rnd.nextInt();
                    CPU cpu2 = cpuList.get(aux);
                    if(i != aux){
                        if(cpu2.hasProcessWaiting()) {
                            Process process = cpu2.getLastProcess();
                            cpu2.deleteLastProcess();
                            cpu.addProcess(process);
                            sucessMessage++;
                            break;
                        }
                        failMessage++;
                    }
                }
            }
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
        joinAlgorithms msr = new joinAlgorithms();
        msr.addCPU(new CPU(0, 3));
        msr.addCPU(new CPU(1, 3));
        msr.addCPU(new CPU(2, 3));
        int TMT = Rnd.nextGaussian();
        for (int i = 0, k = 0; i < 1000; i++, k++) {
            if(k == TMT){
                k = 0;
                TMT = Rnd.nextGaussian();
                for (int j = 0; j < msr.getAmountCPU()/3; j++) {
                    msr.addProcess(new Process(Rnd.nextGaussian()), Rnd.getNum(msr.getAmountCPU()));
                }
                msr.lookForProcess();
            }
            msr.update();
        }
        System.out.println(msr.getFailMessage());
        System.out.println(msr.getSucessMessage());
    }
}
