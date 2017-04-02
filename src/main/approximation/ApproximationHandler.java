package main.approximation;

import main.Point;
import main.ResponseFunction;

public class ApproximationHandler
{
    private static final double POINTS_DELTA = 0.1;
    private Point startPoint;
    private ResponseFunction responseFunction;
    private Point currentMainPoint;

    public ApproximationHandler(Point startPoint, ResponseFunction responseFunction) {
        this.startPoint = startPoint;
        this.responseFunction = responseFunction;
    }

    public void run() {
        this.currentMainPoint = this.startPoint;

        Point[] trainingPoints = this.getTrainingPoints();


    }

    private Point[] getTrainingPoints() {
        return new Point[]{
                new Point(
                        currentMainPoint.x1 + POINTS_DELTA,
                        currentMainPoint.x2 + POINTS_DELTA,
                        responseFunction.calculate(currentMainPoint.x1 + POINTS_DELTA, currentMainPoint.x2 + POINTS_DELTA)
                ),
                new Point(
                        currentMainPoint.x1 - POINTS_DELTA,
                        currentMainPoint.x2 - POINTS_DELTA,
                        responseFunction.calculate(currentMainPoint.x1 - POINTS_DELTA, currentMainPoint.x2 - POINTS_DELTA)
                ),
                new Point(
                        currentMainPoint.x1 + POINTS_DELTA,
                        currentMainPoint.x2 - POINTS_DELTA,
                        responseFunction.calculate(currentMainPoint.x1 + POINTS_DELTA, currentMainPoint.x2 - POINTS_DELTA)
                ),
                new Point(
                        currentMainPoint.x1 - POINTS_DELTA,
                        currentMainPoint.x2 + POINTS_DELTA,
                        responseFunction.calculate(currentMainPoint.x1 - POINTS_DELTA, currentMainPoint.x2 + POINTS_DELTA)
                )
        };
    }
}
