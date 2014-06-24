
public class Test {

    public static void main(String[] args) {
        CPU cpu1 = new CPU(1, 2);
        CPU cpu2 = new CPU(2, 2);
        CPU cpu3 = new CPU(3, 2);

        MultiprocessorSystem multiprocessorSystem = new MultiprocessorSystem();
        multiprocessorSystem.addCPU(cpu1);
        multiprocessorSystem.addCPU(cpu2);
        multiprocessorSystem.addCPU(cpu3);

        multiprocessorSystem.printState();
        cpu1.addProcess(new Process(6));
        multiprocessorSystem.printState();
        cpu1.addProcess(new Process(5));
        multiprocessorSystem.printState();
        cpu1.addProcess(new Process(7));
        multiprocessorSystem.printState();
        cpu3.addProcess(new Process(7));
        multiprocessorSystem.printState();
        cpu2.addProcess(new Process(1));
        multiprocessorSystem.printState();
        cpu2.addProcess(new Process(1));
        multiprocessorSystem.printState();
        cpu2.addProcess(new Process(1));
        multiprocessorSystem.printState();
        cpu2.addProcess(new Process(1));
        multiprocessorSystem.printState();
    }
}
