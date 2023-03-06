package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "OpModeLeft221")

public class OpModeLeft221 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        //double ix = 2.0;
        //double iy = -2.0;
        double width = 17;
        double height = 18;
        double trackWidth = 13;
        //cone Terminal location
        double cTX = -58;
        double cTY = -12;
        //primary cone junction location
        double pJX = -23.4;
        double pJY = -12;

        int scannedCone = 0;
        double finX = 0;
        double finY = 0;

        if (scannedCone == 3){
            finX = -58;
            finY = -12;
        }
        if (scannedCone == 2){
            finX = -36;
            finY = -12;
        }
        if (scannedCone == 1){
            finX = -12;
            finY = -12;
        }
        double fX = finX;
        double fY = finY;


        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        //add in async arm movement

        TrajectorySequence opmode1 = drive.trajectorySequenceBuilder(new Pose2d(-36, -63.5, Math.toRadians(-180)))
                .strafeTo(new Vector2d(-36,-12))
                .lineTo(new Vector2d(cTX,cTY)) //cone terminal location
                .waitSeconds(0.3)
                //finishes preloaded cone and going to terminal
                .lineTo(new Vector2d(-38,-12))
                .lineToLinearHeading(new Pose2d(-28,-12, Math.toRadians(90)))
                .lineTo(new Vector2d(pJX, pJY)) //primary auton junction location

                .lineTo(new Vector2d(-28, -12))
                .lineToLinearHeading(new Pose2d(-38, -12, Math.toRadians(-180)))
                .lineTo(new Vector2d(cTX, cTY)) //drop space location
                .waitSeconds(0.3)
                .lineTo(new Vector2d(-38,-12))
                .lineToLinearHeading(new Pose2d(-28,-12, Math.toRadians(90)))
                .lineTo(new Vector2d(pJX, pJY)) //primary auton junction location

                .lineTo(new Vector2d(-28, -12))
                .lineToLinearHeading(new Pose2d(-38, -12, Math.toRadians(-180)))
                .lineTo(new Vector2d(cTX, cTY)) //drop space location
                .waitSeconds(0.3)
                .lineTo(new Vector2d(-38,-12))
                .lineToLinearHeading(new Pose2d(-28,-12, Math.toRadians(90)))
                .lineTo(new Vector2d(pJX, pJY)) //primary auton junction location

                .lineTo(new Vector2d(-28, -12))
                .lineToLinearHeading(new Pose2d(-38, -12, Math.toRadians(-180)))
                .lineTo(new Vector2d(cTX, cTY)) //drop space location
                .waitSeconds(0.3)
                .lineTo(new Vector2d(-38,-12))
                .lineToLinearHeading(new Pose2d(-28,-12, Math.toRadians(90)))
                .lineTo(new Vector2d(pJX, pJY)) //primary auton junction location

                .lineTo(new Vector2d(-28, -12))
                .lineToLinearHeading(new Pose2d(-38, -12, Math.toRadians(-180)))
                .lineTo(new Vector2d(cTX, cTY)) //drop space location
                .waitSeconds(0.3)
                .lineTo(new Vector2d(-38,-12))
                .lineToLinearHeading(new Pose2d(-28,-12, Math.toRadians(90)))
                .lineTo(new Vector2d(pJX, pJY)) //primary auton junction location

                .strafeTo(new Vector2d(fX, fY))

                .build();

        waitForStart();
        TrajectorySequence opmode2 = drive.trajectorySequenceBuilder(new Pose2d(-36, -63.5, Math.toRadians(90)))
                .strafeTo(new Vector2d(-36, -12))
                .build();

        while (opModeIsActive()) {
            telemetry.addData("asdf", "asdf");
            telemetry.addData("wvelocities", drive.getWheelVelocities());
            telemetry.update();
            //drive.followTrajectorySequence(opmode2);
            drive.followTrajectorySequence(opmode1);

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
        }
    }
}