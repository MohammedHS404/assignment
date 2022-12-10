import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.Map;

public class AlgorithmsOutput {
    public String AlgorithmName;
    public  Process[] Processes ;
    public  int NumberOfProcesses ;
    public ArrayList<String> ProcessesExecutionOrder ;
    public Map<String,Integer> WaitingTimePerProcess;
    public Map<String,Integer> TurnAroundTimePerProcess;
    public double AverageWaitingTime;
    public double AverageTurnAroundTime;
    public AlgorithmsOutput(
            String algorithmName,
            Process[] processes,
            int numberOrProcesses,
            ArrayList<String> processesExecutionOrder,
            Map<String, Integer> waitingTimePerProcess,
            Map<String, Integer> turnAroundTimePerProcess) {
        AlgorithmName=algorithmName;
        Processes=processes;
        NumberOfProcesses=numberOrProcesses;
        ProcessesExecutionOrder = processesExecutionOrder;
        WaitingTimePerProcess = waitingTimePerProcess;
        TurnAroundTimePerProcess = turnAroundTimePerProcess;
        AverageWaitingTime = CalculateAverageWaitingTime();
        AverageTurnAroundTime = CalculateAverageTurnAroundTime();
    }

    double CalculateAverageWaitingTime(){
        double totalWaitingTime=WaitingTimePerProcess.values().stream().mapToInt(Integer::intValue).sum();
        double itemsCount=WaitingTimePerProcess.values().stream().count();

       return totalWaitingTime/itemsCount;
    }

    double CalculateAverageTurnAroundTime(){
        double totalWaitingTime=TurnAroundTimePerProcess.values().stream().mapToInt(Integer::intValue).sum();
        double itemsCount=TurnAroundTimePerProcess.values().stream().count();

        return  totalWaitingTime/itemsCount;
    }

    void Print(){
        System.out.println(AlgorithmName);
        System.out.println();
        System.out.println("    AverageWaitingTime: "+AverageWaitingTime);
        System.out.println("    AverageTurnAroundTime: "+AverageTurnAroundTime);
        System.out.println("    Execution Order:"+ String.join(",",ProcessesExecutionOrder));
        System.out.println("    Processes: ");

        for (int i = 0; i < NumberOfProcesses; i++) {
            String name=Processes[i].Name;
            System.out.println("        "+name);
            System.out.println("            Waiting Time:"+WaitingTimePerProcess.get(name));
            System.out.println("            TurnAround Time:"+TurnAroundTimePerProcess.get(name));
        }
    }

}
