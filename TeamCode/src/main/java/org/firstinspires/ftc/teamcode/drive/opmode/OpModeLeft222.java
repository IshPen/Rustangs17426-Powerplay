package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "OpModeLeft222")


public class OpModeLeft222 extends LinearOpMode {
    private DcMotorEx leftFront, leftRear, rightRear, rightFront;
    @Override
    public void runOpMode() throws InterruptedException {
        leftFront = hardwareMap.get(DcMotorEx.class, "leftFront");
        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);

        // END MDT COMMAND: leftFront.setPower(0);rightFront.setPower(0);leftRear.setPower(0);rightRear.setPower(0);

        float strafeSpeed = 0.5f;
        float straightSpeed = 0.5f;
        // strafeRight command
        // leftFront.setPower(strafeSpeed);
        // leftRear.setPower(-strafeSpeed);
        // rightFront.setPower(-strafeSpeed);
        // rightRear.setPower(strafeSpeed);
        // sleep((long) (x_seconds/strafeSpeed * 100));

        //OL: leftFront.setPower(strafeSpeed);leftRear.setPower(-strafeSpeed);rightFront.setPower(-strafeSpeed);rightRear.setPower(strafeSpeed);

        // strafeLeft command
        // leftFront.setPower(-strafeSpeed);
        // leftRear.setPower(strafeSpeed);
        // rightFront.setPower(strafeSpeed);
        // rightRear.setPower(-strafeSpeed);
        // sleep((long) (x_seconds/strafeSpeed * 100));

        //OL: leftFront.setPower(-strafeSpeed);leftRear.setPower(strafeSpeed);rightFront.setPower(strafeSpeed);rightRear.setPower(-strafeSpeed);

        // driveForwards command
        // leftFront.setPower(strafeSpeed);
        // leftRear.setPower(strafeSpeed);
        // rightFront.setPower(strafeSpeed);
        // rightRear.setPower(strafeSpeed);
        // sleep((long) (x_seconds/straightSpeed * 100));

        //OL: leftFront.setPower(strafeSpeed);leftRear.setPower(strafeSpeed);rightFront.setPower(strafeSpeed);rightRear.setPower(strafeSpeed);

        // driveBackwards command
        // leftFront.setPower(-strafeSpeed);
        // leftRear.setPower(-strafeSpeed);
        // rightFront.setPower(-strafeSpeed);
        // rightRear.setPower(-strafeSpeed);
        // sleep((long) (x_seconds/straightSpeed * 100));

        //OL: leftFront.setPower(-strafeSpeed);leftRear.setPower(-strafeSpeed);rightFront.setPower(-strafeSpeed);rightRear.setPower(-strafeSpeed);

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
/*




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



        //add in async arm movement

        TrajectorySequence opmode1 = drive.trajectorySequenceBuilder(new Pose2d(-36, -63.5, Math.toRadians(180)))
                .strafeTo(new Vector2d(-36,-12))
                .lineTo(new Vector2d(cTX,cTY)) //cone terminal location
                .waitSeconds(0.3)
                //finishes preloaded cone and going to terminal
                .lineTo(new Vector2d(-38,-12))
                .lineToLinearHeading(new Pose2d(-28,-12, Math.toRadians(90)))
                .lineTo(new Vector2d(pJX, pJY)) //primary auton junction location

                .lineTo(new Vector2d(-28, -12))
                .lineToLinearHeading(new Pose2d(-38, -12, Math.toRadians(180)))
                .lineTo(new Vector2d(cTX, cTY)) //drop space location
                .waitSeconds(0.3)
                .lineTo(new Vector2d(-38,-12))
                .lineToLinearHeading(new Pose2d(-28,-12, Math.toRadians(90)))
                .lineTo(new Vector2d(pJX, pJY)) //primary auton junction location

                .lineTo(new Vector2d(-28, -12))
                .lineToLinearHeading(new Pose2d(-38, -12, Math.toRadians(180)))
                .lineTo(new Vector2d(cTX, cTY)) //drop space location
                .waitSeconds(0.3)
                .lineTo(new Vector2d(-38,-12))
                .lineToLinearHeading(new Pose2d(-28,-12, Math.toRadians(90)))
                .lineTo(new Vector2d(pJX, pJY)) //primary auton junction location

                .lineTo(new Vector2d(-28, -12))
                .lineToLinearHeading(new Pose2d(-38, -12, Math.toRadians(180)))
                .lineTo(new Vector2d(cTX, cTY)) //drop space location
                .waitSeconds(0.3)
                .lineTo(new Vector2d(-38,-12))
                .lineToLinearHeading(new Pose2d(-28,-12, Math.toRadians(90)))
                .lineTo(new Vector2d(pJX, pJY)) //primary auton junction location

                .lineTo(new Vector2d(-28, -12))
                .lineToLinearHeading(new Pose2d(-38, -12, Math.toRadians(180)))
                .lineTo(new Vector2d(cTX, cTY)) //drop space location
                .waitSeconds(0.3)
                .lineTo(new Vector2d(-38,-12))
                .lineToLinearHeading(new Pose2d(-28,-12, Math.toRadians(90)))
                .lineTo(new Vector2d(pJX, pJY)) //primary auton junction location

                .strafeTo(new Vector2d(fX, fY))

                .build();
*/
        waitForStart();
        TrajectorySequence opmode2 = drive.trajectorySequenceBuilder(new Pose2d(-36, -63.5, Math.toRadians(90)))
                .strafeTo(new Vector2d(-36, -12))
                .build();

        while (opModeIsActive()) {
            telemetry.addData("asdf", "asdf");
            telemetry.addData("wvelocities", drive.getWheelVelocities());
            telemetry.update();

            //drive.followTrajectorySequence(opmode2);
            //drive.followTrajectorySequence(opmode1);


            // Start oriented with cone
            // 1. Strafe next to high pole with hardcode commands
            // 2. Lift slide with hardcode
            // 3. Strafe slightly over pole with hardcode
            // 4. Drop cone with hardcode claw
            // 5. Drop slide (to stack height?) with hardcode
            // 6. Strafe in line with stack with hardcode
            drive.turn(Math.toRadians(180)); // 7. Orient with stack
            // 8. Move forwards to stack with hardcode
            // 9. Grab cone
            //10. Lift slide with hardcode
            //11. Move backwards to orient self right next to high pole with hardcode
            //12. Lift slide to max with hardcode
            drive.turn(Math.toRadians(90));
            //14. Move forwards to align over high pole
            //15. Drop cone with hardcode
            //16. Shift to orient self right behind to high pole with hardcode
            //17. Strafe to park

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
        }
    }
}