import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SJF{

    public AlgorithmsOutput Execute(Process processes[], int numberOfProcesses) {
        ArrayList<String> processesExecutionOrder= new ArrayList<String>();

        long remainingProcesses = numberOfProcesses;

        Map<String, Integer>  waitingTimePerProcess= new HashMap<> ();

        Map<String, Integer> turnAroundTimePerProcess= new HashMap<> ();

        int remainingTimeForEachProcess[] = new int[numberOfProcesses];

        for (int i = 0; i < numberOfProcesses; i++) {
            remainingTimeForEachProcess[i] = processes[i].BurstTime;
        }

        int currentTime = 0;

        int currentRemainingTime = Integer.MAX_VALUE;

        int currentProcessIndex = 0;

        int completedAt=0;

        boolean executingProcess = false;

        while (remainingProcesses > 0) {

            for (int processIndex = 0; processIndex < numberOfProcesses; processIndex++) {

                Process process = processes[processIndex];

                boolean replaceCurrentProcess = process.ArrivalTime <= currentTime &&
                        remainingTimeForEachProcess[processIndex] < currentRemainingTime &&
                        remainingTimeForEachProcess[processIndex] > 0;

                if (replaceCurrentProcess) {
                    currentRemainingTime = remainingTimeForEachProcess[processIndex];
                    currentProcessIndex = processIndex;
                    executingProcess = true;
                }
            }

            if (!executingProcess) {
                currentTime++;
                continue;
            }

            processesExecutionOrder.add(processes[currentProcessIndex].Name);

            remainingTimeForEachProcess[currentProcessIndex]--;

            currentRemainingTime = remainingTimeForEachProcess[currentProcessIndex];

            if (currentRemainingTime == 0) {
                // Resets the remaining time allowing a new process to start
                currentRemainingTime = Integer.MAX_VALUE;
            }

            if (remainingTimeForEachProcess[currentProcessIndex] == 0) {
                remainingProcesses--;
                executingProcess=false;

                completedAt=currentTime+1;

                waitingTimePerProcess.put(processes[currentProcessIndex].Name,
                        completedAt -
                        processes[currentProcessIndex].BurstTime -
                        processes[currentProcessIndex].ArrivalTime);

                turnAroundTimePerProcess.put(
                        processes[currentProcessIndex].Name,
                        completedAt - processes[currentProcessIndex].ArrivalTime);
            }

            currentTime++;
        }

        String name= "SRTF";

        AlgorithmsOutput algorithmsOutput= new AlgorithmsOutput(
                name,
                processes,
                numberOfProcesses,
                processesExecutionOrder,
                waitingTimePerProcess,
                turnAroundTimePerProcess);

        return algorithmsOutput;
    }
}
