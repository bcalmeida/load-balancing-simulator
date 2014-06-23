import java.util.LinkedList;
import java.util.List;

public class CPU {

    private int index;
    private List<Process> processQueue;
    private int threshold;
    private OnSendMessageListener onSendMessageListener;

    public CPU(int index, int threshold) {
        this.index = index;
        this.threshold = threshold;
        processQueue = new LinkedList<Process>();
    }

    public void setOnSendMessageListener(OnSendMessageListener onSendMessageListener) {
        this.onSendMessageListener = onSendMessageListener;
    }

    // TODO: Remove more recent added
    public void addProcess(Process process) {
        processQueue.add(process);

        if (isOverloaded()) {
            if (sendMessage(processQueue.get(0))) {
                processQueue.remove(0);
            }
        }
    }

    // TODO: Implement update
    public void update() { }

    private boolean sendMessage(Process processToDelegate) {
        return onSendMessageListener.onSendMessage(index, processToDelegate);
    }

    public boolean isOverloaded() {
        return processQueue.size() > threshold;
    }

    public int getIndex() {
        return index;
    }

    public interface OnSendMessageListener {
        public boolean onSendMessage(int index, Process process);
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
