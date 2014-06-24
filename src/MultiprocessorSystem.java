import java.util.LinkedList;
import java.util.List;

// TODO: Store number of total messages of the system
// TODO: Implement the other algorithm on another custom OnOverloadListener, with the necessary modifications
public class MultiprocessorSystem {

    private List<CPU> cpuList;

    public MultiprocessorSystem() {
        cpuList = new LinkedList<CPU>();
    }

    public void addCPU(CPU cpu) {
        cpuList.add(cpu);
        cpu.setOnOverloadListener(new CustomOnOverloadListener());
    }

    // TODO: Implement addProcess to add the process to a random CPU
    public void addProcess(Process process) {

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

    // TODO: Make it random order each time
    private class CustomOnOverloadListener implements CPU.OnOverloadListener {

        @Override
        public boolean sendMessage(int index, Process process) {
            CPU chosenCpu = null;
            for (CPU cpu : cpuList) {
                if (cpu.getIndex() != index && cpu.doesAcceptProcess()) {
                    chosenCpu = cpu;
                    break;
                }
            }
            if (chosenCpu == null) {
                return false;
            }
            chosenCpu.addProcess(process);
            return true;
        }
    }
}
