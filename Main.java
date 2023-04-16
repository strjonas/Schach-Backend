// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,


import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            Runtime runTime = Runtime.getRuntime();

            String executablePath = "C:\\Users\\MarkMark\\IdeaProjects\\stockfish_15.1_win_x64_popcnt\\stockfish-windows-2022-x86-64-modern.exe";

            Process process = runTime.exec(executablePath);
            BufferedReader read = process.inputReader();
            System.out.println(read.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}