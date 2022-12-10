import java.io.Console;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        new Main().PPS();
    }

    void PPS() {
        int numberOfProcesses = 5;

        Process processes[] = new Process[numberOfProcesses];

        processes[0] = new Process("P1", 0, 10, 3);
        processes[1] = new Process("P2", 0, 1, 1);
        processes[2] = new Process("P3", 0, 2, 4);
        processes[3] = new Process("P4", 0, 1, 5);
        processes[4] = new Process("P5", 0, 5, 2);

        AlgorithmsOutput algorithmsOutput = new PPS().Execute(processes, numberOfProcesses);

        algorithmsOutput.Print();
    }

    void RRCS() {
        int numberOfProcesses = 5;
        int contextSwitching = 2;
        int roundRobinTime = 3;

        Process processes[] = new Process[numberOfProcesses];

        processes[0] = new Process("P1", 0, 4, 1);
        processes[1] = new Process("P2", 1, 8, 1);
        processes[2] = new Process("P3", 3, 2, 1);
        processes[3] = new Process("P4", 10, 6, 1);
        processes[4] = new Process("P5", 12, 5, 1);

        AlgorithmsOutput algorithmsOutput = new RRCS().Execute(processes, numberOfProcesses, roundRobinTime, contextSwitching);

        algorithmsOutput.Print();
    }

    void SFJ() {
        int numberOfProcesses = 4;
        int contextSwitching = 0;
        int roundRobinTime = 0;

        Process processes[] = new Process[numberOfProcesses];

        processes[0] = new Process("P1", 0, 7, 1);
        processes[1] = new Process("P2", 2, 4, 1);
        processes[2] = new Process("P3", 4, 1, 1);
        processes[3] = new Process("P4", 5, 4, 1);

        AlgorithmsOutput algorithmsOutput = new SJF().Execute(processes, numberOfProcesses);

        algorithmsOutput.Print();
    }
}