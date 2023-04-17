package chess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static java.util.function.UnaryOperator.identity;

public class Engine {

    private Process process = null;
    private BufferedReader reader = null;
    private OutputStreamWriter writer = null;

    public void start(

    ) {
        Runtime runTime = Runtime.getRuntime();
        String executablePath = "C:\\Users\\MarkMark\\IdeaProjects\\stockfish_15.1_win_x64_popcnt\\stockfish-windows-2022-x86-64-modern.exe";

        try {
            this.process = runTime.exec(executablePath);
            this.reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            this.writer = new OutputStreamWriter(process.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void close() {
        if (this.process.isAlive()) {
            this.process.destroy();
        }
        try {
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public <T> T command(String cmd, Function<List<String>, T> commandProcessor, Predicate<String> breakCondition, long timeout)
            throws InterruptedException, ExecutionException, TimeoutException {

        // This completable future will send a command to the process
        // And gather all the output of the engine in the List<String>
        // At the end, the List<String> is translated to T through the
        // commandProcessor Function
        CompletableFuture<T> command = supplyAsync(() -> {
            final List<String> output = new ArrayList<>();
            try {
                writer.flush();
                writer.write(cmd + "\n");
                writer.write("isready\n");
                writer.flush();
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains("Unknown command")) {
                        throw new RuntimeException(line);
                    }
                    if (line.contains("Unexpected token")) {
                        throw new RuntimeException("Unexpected token: " + line);
                    }
                    output.add(line);
                    if (breakCondition.test(line)) {
                        // At this point we are no longer interested to read any more
                        // output from the engine, we consider that the engine responded
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return commandProcessor.apply(output);
        });

        return command.get(timeout, TimeUnit.MILLISECONDS);
    }



    public Engine() { }

    // -----
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        Engine engine = new Engine();
        engine.start();
        var position = "8/8/4Rp2/5P2/1PP1pkP1/7P/1P1r4/7K b - - 0 40";
        engine.command("uci", identity(), (s) -> s.startsWith("uciok"), 2000L);
        engine.command("position fen " + position, identity(), s -> s.startsWith("readyok"), 2000L);

        String bestMove = engine.command(
                        "go movetime 3000",
                        lines -> lines.stream().filter(s->s.startsWith("bestmove")).findFirst().get(),
                        line -> line.startsWith("bestmove"),
                        5000L)
                .split(" ")[1];

        System.out.println(bestMove);
        engine.close();
    }

}

