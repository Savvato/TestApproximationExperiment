package main;

import main.approximation.ApproximationHandler;

import java.io.PrintStream;
import java.util.Scanner;

public class Application
{
    private Point startPoint;
    private ResponseFunction responseFunction = ((x1, x2) -> 10 / (6 + Math.sqrt(Math.pow(x1, 2) + Math.pow(x2, 2))));

    ApproximationHandler approximationHandler;

    public static void main(String[] args) {
        Application application = new Application();
        application.run();
    }

    public void run() {
        this.readStartPoint();
        this.approximationHandler = new ApproximationHandler(this.startPoint, this.responseFunction);
        this.approximationHandler.run();
    }

    private void readStartPoint() {
        Scanner scanner = new Scanner(System.in);
        PrintStream writer = System.out;
        writer.println("=========Enter start point=========");
        writer.print("x1 = ");
        double x1 = scanner.nextDouble();
        writer.print("x2 = ");
        double x2 = scanner.nextDouble();
        writer.print("y = ");
        double y = scanner.nextDouble();

        this.startPoint = new Point(x1, x2, y);
    }


}
