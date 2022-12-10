public class ProcessInCpu {
    private Process _process;

    public Process Process() {
        return _process;
    }

    private int _processingTime;

    public int ProcessingTime() {
        return _processingTime;
    }

    public void IncrementProcessingTime() {
        _processingTime++;
    }

    public ProcessInCpu(Process process) {
        _process = process;
        _processingTime=0;
    }
}
