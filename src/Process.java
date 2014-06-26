
public class Process {

    private int cpuTime;
    private int progress = 0;

    public Process(int cpuTime) {
        this.cpuTime = cpuTime;
    }

    public void consumeOneClock(){
        if(!processFinished())
            progress++;
    }

    public boolean processFinished(){
        if(progress == cpuTime)
            return true;
        return false;
    }
}
