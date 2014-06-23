import java.util.LinkedList;
import java.util.List;

public class CPU {

    private int index;
    private List<Process> processQueue;
    private int threshold;
    private OnOverloadListener onOverloadListener;

    public CPU(int index, int threshold) {
        this.index = index;
        this.threshold = threshold;
        processQueue = new LinkedList<Process>();
    }

    public void setOnOverloadListener(OnOverloadListener onOverloadListener) {
        this.onOverloadListener = onOverloadListener;
    }

    public void addProcess(Process process) {
        processQueue.add(process);

        if (isOverloaded()) {
            if (onOverloadListener.sendMessage(index, process)) {
                processQueue.remove(process);
            }
        }
    }

    // TODO: Implement update
    public void update() { }

    public boolean isOverloaded() {
        return processQueue.size() > threshold;
    }

    public int getIndex() {
        return index;
    }

    public interface OnOverloadListener {
        public boolean sendMessage(int index, Process process);
    }

    @Override
    public String toString() {
        return "CPU-" + index;
    }

    public void printState() {
        StringBuilder sb = new StringBuilder();
        for (Process process : processQueue) {
            sb.append(process).append(" ");
        }
        System.out.println(this + ": " + sb);
    }
}
