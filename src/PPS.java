import java.util.*;

public class PPS {
    public AlgorithmsOutput Execute(Process processes[], int numberOfProcesses) {
        Arrays.sort(processes, Comparator.comparingInt(a -> a.Priority));

        ArrayList<String> processesExecutionOrder = new ArrayList<>();
        Map<String, Integer> waitingTimePerProcess = new HashMap<>();
        Map<String, Integer> turnAroundTimePerProcess = new HashMap<>();

        int remainingProcesses = numberOfProcesses;
        int currentTime = 0;

        LinkedList<ProcessInCpu> pendingProcesses = new LinkedList<>();
        Vector<String> arrived = new Vector<>();

        ProcessInCpu activeProcess = null;

        while (remainingProcesses > 0) {
            for (int i = 0; i < numberOfProcesses; i++) {
                if (!arrived.contains(processes[i].Name) && processes[i].ArrivalTime <= currentTime) {
                    if (activeProcess != null && activeProcess.Process().Priority > processes[i].Priority) {
                        pendingProcesses.addLast(activeProcess);
                        activeProcess = new ProcessInCpu(processes[i]);
                        pendingProcesses.sort(Comparator.comparingInt(a -> a.Process().Priority));
                    } else {
                        pendingProcesses.addLast(new ProcessInCpu(processes[i]));
                    }
                    arrived.add(processes[i].Name);
                }
            }



            if (activeProcess == null) {
                if (pendingProcesses.isEmpty()) {
                    currentTime++;
                    continue;
                }

                activeProcess = pendingProcesses.removeFirst();
            }

            processesExecutionOrder.add(activeProcess.Process().Name);

            activeProcess.IncrementProcessingTime();

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

                remainingProcesses--;

                activeProcess = null;
            }

            currentTime++;
        }

        String name = "Preemptive Priority Scheduling";

        AlgorithmsOutput algorithmsOutput = new AlgorithmsOutput(
                name,
                processes,
                numberOfProcesses,
                processesExecutionOrder,
                waitingTimePerProcess,
                turnAroundTimePerProcess);

        return algorithmsOutput;
    }
}
