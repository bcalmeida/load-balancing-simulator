import java.util.LinkedList;
import java.util.List;

public class MultiprocessorSystem {

    private List<CPU> cpuList;

    public MultiprocessorSystem() {
        cpuList = new LinkedList<CPU>();
    }

    public void addCPU(CPU cpu) {
        cpuList.add(cpu);
        cpu.setOnSendMessageListener(new OverloadedMessageSendListener());
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

    // TODO: Make it try N times
    private class OverloadedMessageSendListener implements CPU.OnSendMessageListener {

        @Override
        public boolean onSendMessage(int index, Process process) {
            CPU cpu = null;
            cpu = pickAnotherCPU(index);
            if (cpu == null) {
                System.out.println("ERROR: Message didn't reach anyone");
                return false;
            }
            if (!cpu.isOverloaded()) {
                cpu.addProcess(process);
                return true;
            }
            return false;
        }

        // TODO: Make it random
        private CPU pickAnotherCPU(int index){
            for (CPU cpu : cpuList) {
                if (cpu.getIndex() != index) {
                    return cpu;
                }
            }
            return null;
        }
    }
}
