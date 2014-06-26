import java.util.LinkedList;

public class Test {

    public static void main(String[] args) {


        CPU cpu1 = new CPU(1, 2);
        CPU cpu2 = new CPU(2, 2);
        CPU cpu3 = new CPU(3, 2);

        MultiprocessorSystemReceptor multiprocessorSystem = new MultiprocessorSystemReceptor();
        multiprocessorSystem.addCPU(cpu1);
        multiprocessorSystem.addCPU(cpu2);
        multiprocessorSystem.addCPU(cpu3);

        for (int i = 0; i < 20; i++) {
            multiprocessorSystem.addProcess(new Process(3), i%3);
            multiprocessorSystem.printState();
        }
        for (int i = 0; i < 18; i++) {
            multiprocessorSystem.update();
            multiprocessorSystem.printState();
        }
        multiprocessorSystem.addProcess(new Process(3), 0);
        multiprocessorSystem.addProcess(new Process(3), 0);
        multiprocessorSystem.addProcess(new Process(3), 0);
        multiprocessorSystem.printState();
        multiprocessorSystem.lookForProcess();
        multiprocessorSystem.printState();

//        multiprocessorSystem.printState();
//        cpu1.addProcess(new Process(6));
//        multiprocessorSystem.printState();
//        cpu1.addProcess(new Process(5));
//        multiprocessorSystem.printState();
//        cpu1.addProcess(new Process(7));
//        multiprocessorSystem.printState();
//        cpu3.addProcess(new Process(7));
//        multiprocessorSystem.printState();
//        cpu2.addProcess(new Process(1));
//        multiprocessorSystem.printState();
//        cpu2.addProcess(new Process(1));
//        multiprocessorSystem.printState();
//        cpu2.addProcess(new Process(1));
//        multiprocessorSystem.printState();
//        cpu2.addProcess(new Process(1));
//        multiprocessorSystem.printState();
    }
}
