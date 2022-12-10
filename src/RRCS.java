import java.util.*;
import java.util.function.Function;

public class RRCS {
    /// We loop through processes until there are no remaining processes in the queue
    /// with each loop we increase the waiting time
    /// each process has processing time
    /// with each loop we check if the processing time exceeded the quantum
    /// if it didn't we proceed
    /// if it did we move it to the end of the queue and proceed with next one
    /// with each switch we add context switching time


    public AlgorithmsOutput Execute(Process[] processes, int numberOfProcesses, int quantumTime, int contextSwitchingTime) {
        ArrayList<String> processesExecutionOrder = new ArrayList<>();
        Map<String, Integer> waitingTimePerProcess = new HashMap<>();
        Map<String, Integer> turnAroundTimePerProcess = new HashMap<>();

        int currentContextSwitchingLatency = 0;

        int currentTime = 0;

        Deque<ProcessInCpu> pendingProcesses = new LinkedList<>();
        Vector<String> arrived = new Vector<>();

        ProcessInCpu activeProcess = null;

        int currentProcessingTime = 0;

        while (arrived.size() < numberOfProcesses || pendingProcesses.isEmpty() == false) {

            for (int i = 0; i < numberOfProcesses; i++) {
                if (!arrived.contains(processes[i].Name) && processes[i].ArrivalTime <= currentTime) {
                    pendingProcesses.addLast(new ProcessInCpu(processes[i]));
                    arrived.add(processes[i].Name);
                }
            }

            if (currentProcessingTime >= quantumTime) {
                pendingProcesses.addLast(activeProcess);
                activeProcess = null;
                currentTime+=contextSwitchingTime;
            }

            if (activeProcess == null) {
                currentProcessingTime=0;
                activeProcess = pendingProcesses.removeFirst();
            }

            if(activeProcess==null){
                continue;
            }

            processesExecutionOrder.add(activeProcess.Process().Name);

            activeProcess.IncrementProcessingTime();
            currentProcessingTime++;

            int remainingTime = activeProcess.Process().BurstTime - activeProcess.ProcessingTime();

            if (remainingTime == 0) {
                int completedAt = currentTime + 1;

                waitingTimePerProcess.put(activeProcess.Process().Name,
                        completedAt -
                                activeProcess.Process().BurstTime -
                                activeProcess.Process().ArrivalTime);

                turnAroundTimePerProcess.put(
                        activeProcess.Process().Name,
                        completedAt - activeProcess.Process().ArrivalTime);

                activeProcess = null;
                currentTime+=contextSwitchingTime;
            }

            currentTime++;
        }

        String name = "Round Robin With Context Switching";

        AlgorithmsOutput output = new AlgorithmsOutput(
                name,
                processes,
                numberOfProcesses,
                processesExecutionOrder,
                waitingTimePerProcess,
                turnAroundTimePerProcess
        );

        return output;
    }
}
