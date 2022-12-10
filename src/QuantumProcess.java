public class QuantumProcess extends Process {
    public  int Quantum;
    public QuantumProcess(String name, int arrivalTime, int burstTime, int priority, int quantum) {
        super(name, arrivalTime, burstTime, priority);
        Quantum=quantum;
    }
}
