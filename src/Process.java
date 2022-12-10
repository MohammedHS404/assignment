public class Process {
    public String Name;
    public int ArrivalTime;
    public  int BurstTime;
    public  int Priority;

    public  Process(String name,int arrivalTime, int burstTime,int priority){
        Name=name;
        ArrivalTime=arrivalTime;
        BurstTime=burstTime;
        Priority=priority;
    }
}
