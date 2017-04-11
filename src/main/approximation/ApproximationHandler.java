package main.approximation;

import main.Point;
import main.ResponseFunction;
import main.approximation.least_square_method.Formula;
import main.approximation.least_square_method.LeastSquareMethod;
import main.matrix.Matrix;

public class ApproximationHandler
{
    private static final double POINTS_DELTA = 0.05;
    private Point startPoint;
    private ResponseFunction responseFunction;
    private Point currentCentralPoint;

    public ApproximationHandler(Point startPoint, ResponseFunction responseFunction) {
        this.startPoint = startPoint;
        this.responseFunction = responseFunction;
    }

    public void run() {
        this.currentCentralPoint = this.startPoint;

        double[] gradient;

        do {
            Point[] trainingPoints = this.getTrainingPoints();
            LeastSquareMethod method = new LeastSquareMethod(trainingPoints);
            Matrix coefficients = method.calculate();
            double k1 = coefficients.data[0][0];
            double k2 = coefficients.data[1][0];
            double k3 = coefficients.data[2][0];
            Formula formula = new Formula(k1, k2, k3);
            gradient = this.calculateGradient(formula);
            this.moveCentralPoint(gradient);
            this.currentCentralPoint.print();

        }
        while (Math.pow(gradient[0] + gradient[1], 2) > 0.0000001);


    }

    private double[] calculateGradient(Formula formula) {
        return new double[]{formula.c2, formula.c3};
    }

    private void moveCentralPoint(double[] gradient) {
        double newX1 = this.currentCentralPoint.x1 + gradient[0];
        double newX2 = this.currentCentralPoint.x2 + gradient[1];
        double newY = this.responseFunction.calculate(newX1, newX2);
        this.currentCentralPoint = new Point(newX1, newX2, newY);
    }

    private Point[] getTrainingPoints() {
        return new Point[]{
                new Point(
                        currentCentralPoint.x1 + POINTS_DELTA,
                        currentCentralPoint.x2 + POINTS_DELTA,
                        responseFunction.calculate(currentCentralPoint.x1 + POINTS_DELTA, currentCentralPoint.x2 + POINTS_DELTA)
                ),
                new Point(
                        currentCentralPoint.x1 - POINTS_DELTA,
                        currentCentralPoint.x2 - POINTS_DELTA,
                        responseFunction.calculate(currentCentralPoint.x1 - POINTS_DELTA, currentCentralPoint.x2 - POINTS_DELTA)
                ),
                new Point(
                        currentCentralPoint.x1 + POINTS_DELTA,
                        currentCentralPoint.x2 - POINTS_DELTA,
                        responseFunction.calculate(currentCentralPoint.x1 + POINTS_DELTA, currentCentralPoint.x2 - POINTS_DELTA)
                ),
                new Point(
                        currentCentralPoint.x1 - POINTS_DELTA,
                        currentCentralPoint.x2 + POINTS_DELTA,
                        responseFunction.calculate(currentCentralPoint.x1 - POINTS_DELTA, currentCentralPoint.x2 + POINTS_DELTA)
                )
        };
    }
}
