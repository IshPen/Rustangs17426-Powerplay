

// TESLAV1.2, ADDED ARM MOVEMENTS AND JUST NEED ENCODER HEIGHTS

package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@TeleOp(group = "drive")
public class TESLAv1 extends LinearOpMode{
    DcMotor armMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        //base location
        double bX = 0;
        double bY = -48;
        //shift back to align with grid
        double sbX = 0;
        double sbY = -36;
        int toPosVal = 0;
        int[] xPositions = new int[]{-36, -12, -12, 12, 36};
        int[] yPositions = new int[]{48, 24, 0, 24, 48};
        int[] dirPositions = new int[]{180, 180, -180, -180, -180}; //Facing left-wards = 180, right = -180
        int[] poleHeights = new int[]{0, 1, 0, 1, 0, 1, 2, 3, 2, 1, 0, 3, 0, 3, 0, 1, 2, 3, 2, 1, 0, 1, 0, 1, 0};
        int[] armHeights = new int[]{}; //TODO: INPUT ENCODER HEIGHTS FOR GROUND, LOW, MID, AND HIGH POLES
        int toX = 0;
        int toY = 0;
        int armPoleHeight;
        final double armSpeed = 0.5f;
        // will update every tick
        boolean prevX, prevY, prevA, prevB, prevUp, prevDown, prevRight, prevLeft, inDriveMode;
        prevX = false;
        prevY = false;
        prevA = false;
        prevB = false;
        prevUp = false;
        prevDown= false;
        prevRight = false;
        prevLeft = false;
        inDriveMode = false;
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        armMotor = hardwareMap.dcMotor.get("armMotor");
        waitForStart();

        while (!isStopRequested()) {
            if (!inDriveMode){
                if (prevX && !gamepad1.x){ //When the button is released
                    if (toPosVal > -1 && toPosVal < 26){
                        //Ex) 21 = x: tPV%5,y: (tPV - (tpV%5))/5 = 4
                        toX = (int)(toPosVal%5);
                        toY = (int)((toPosVal - (toPosVal%5))/5);

                        if (poleHeights[toPosVal-1] == 0){ // GROUND JUNCTION
                            TrajectorySequence activeTrajectoryGround = drive.trajectorySequenceBuilder(new Pose2d(bX, bY, Math.toRadians(-90)))
                                    //shift back to grid
                                    .lineTo(new Vector2d(sbX,sbY))
                                    //TODO: DROP CONE INTO RING
                                    .lineToLinearHeading(new Pose2d(xPositions[toX-1], bY, Math.toRadians(-90)))
                                    //Add displacement marker to start lifting cone
                                    .addDisplacementMarker(() -> {
                                        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                                        armMotor.setTargetPosition(0); //TODO: REPLACE WITH GROUND JUNCTION HEIGHT
                                        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                                        armMotor.setPower(armSpeed);
                                    })
                                    .lineToLinearHeading(new Pose2d(xPositions[toX-1], yPositions[toY-1], Math.toRadians(dirPositions[toX-1])))
                                    //Deposit/Drop Cone
                                    .addDisplacementMarker(() -> {
                                        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                                        armMotor.setTargetPosition(0); //0 == Bottom Position
                                        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                                        armMotor.setPower(armSpeed);
                                    })
                                    .lineToLinearHeading(new Pose2d(xPositions[toX-1], bY, Math.toRadians(-90)))
                                    .lineTo(new Vector2d(sbX,sbY))
                                    .lineToLinearHeading(new Pose2d(bX, bY, Math.toRadians(-90)))
                                    .build();
                            //resetToPosVal
                            toPosVal = 0;
                            toX = 0;
                            toY = 0;
                            drive.followTrajectorySequence(activeTrajectoryGround);
                        }
                        else if (poleHeights[toPosVal-1] == 1){ //LOW POLE
                            TrajectorySequence activeTrajectoryLow = drive.trajectorySequenceBuilder(new Pose2d(bX, bY, Math.toRadians(-90)))
                                    //shift back to grid
                                    .lineTo(new Vector2d(sbX,sbY))
                                    //TODO: DROP CONE INTO RING
                                    .lineToLinearHeading(new Pose2d(xPositions[toX-1], bY, Math.toRadians(-90)))
                                    //Add displacement marker to start lifting cone
                                    .addDisplacementMarker(() -> {
                                        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                                        armMotor.setTargetPosition(0); //TODO: REPLACE WITH LOW JUNCTION HEIGHT
                                        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                                        armMotor.setPower(armSpeed);
                                    })
                                    .lineToLinearHeading(new Pose2d(xPositions[toX-1], yPositions[toY-1], Math.toRadians(dirPositions[toX-1])))
                                    //Deposit/Drop Cone
                                    .addDisplacementMarker(() -> {
                                        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                                        armMotor.setTargetPosition(0); //0 == Bottom Position
                                        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                                        armMotor.setPower(armSpeed);
                                    })
                                    .lineToLinearHeading(new Pose2d(xPositions[toX-1], bY, Math.toRadians(-90)))
                                    .lineTo(new Vector2d(sbX,sbY))
                                    .lineToLinearHeading(new Pose2d(bX, bY, Math.toRadians(-90)))
                                    .build();
                            //resetToPosVal
                            toPosVal = 0;
                            toX = 0;
                            toY = 0;
                            drive.followTrajectorySequence(activeTrajectoryLow);
                        }
                        else if (poleHeights[toPosVal-1] == 2){ //MEDIUM POLE
                            TrajectorySequence activeTrajectoryMedium = drive.trajectorySequenceBuilder(new Pose2d(bX, bY, Math.toRadians(-90)))
                                    //shift back to grid
                                    .lineTo(new Vector2d(sbX,sbY))
                                    //TODO: DROP CONE INTO RING
                                    .lineToLinearHeading(new Pose2d(xPositions[toX-1], bY, Math.toRadians(-90)))
                                    //Add displacement marker to start lifting cone
                                    .addDisplacementMarker(() -> {
                                        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                                        armMotor.setTargetPosition(0); //TODO: REPLACE WITH MEDIUM JUNCTION HEIGHT
                                        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                                        armMotor.setPower(armSpeed);
                                    })
                                    .lineToLinearHeading(new Pose2d(xPositions[toX-1], yPositions[toY-1], Math.toRadians(dirPositions[toX-1])))
                                    //Deposit/Drop Cone
                                    .addDisplacementMarker(() -> {
                                        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                                        armMotor.setTargetPosition(0); //0 == Bottom Position
                                        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                                        armMotor.setPower(armSpeed);
                                    })
                                    .lineToLinearHeading(new Pose2d(xPositions[toX-1], bY, Math.toRadians(-90)))
                                    .lineTo(new Vector2d(sbX,sbY))
                                    .lineToLinearHeading(new Pose2d(bX, bY, Math.toRadians(-90)))
                                    .build();
                            //resetToPosVal
                            toPosVal = 0;
                            toX = 0;
                            toY = 0;
                            drive.followTrajectorySequence(activeTrajectoryMedium);
                        }
                        else if (poleHeights[toPosVal-1] == 3){ //HIGH POLE
                            TrajectorySequence activeTrajectoryHigh = drive.trajectorySequenceBuilder(new Pose2d(bX, bY, Math.toRadians(-90)))
                                    //shift back to grid
                                    .lineTo(new Vector2d(sbX,sbY))
                                    //TODO: DROP CONE INTO RING
                                    .lineToLinearHeading(new Pose2d(xPositions[toX-1], bY, Math.toRadians(-90)))
                                    //Add displacement marker to start lifting cone
                                    .addDisplacementMarker(() -> {
                                        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                                        armMotor.setTargetPosition(0); //TODO: REPLACE WITH HIGH JUNCTION HEIGHT
                                        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                                        armMotor.setPower(armSpeed);
                                    })
                                    .lineToLinearHeading(new Pose2d(xPositions[toX-1], yPositions[toY-1], Math.toRadians(dirPositions[toX-1])))
                                    //Deposit/Drop Cone
                                    .addDisplacementMarker(() -> {
                                        armMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                                        armMotor.setTargetPosition(0); //0 == Bottom Position
                                        armMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                                        armMotor.setPower(armSpeed);
                                    })
                                    .lineToLinearHeading(new Pose2d(xPositions[toX-1], bY, Math.toRadians(-90)))
                                    .lineTo(new Vector2d(sbX,sbY))
                                    .lineToLinearHeading(new Pose2d(bX, bY, Math.toRadians(-90)))
                                    .build();
                            //resetToPosVal
                            toPosVal = 0;
                            toX = 0;
                            toY = 0;
                            drive.followTrajectorySequence(activeTrajectoryHigh);
                        }

                    }
                    else if (toPosVal < 0 || toPosVal > 25){
                        telemetry.addData("Requested Location Out of Range", toPosVal);
                        toPosVal = 0;
                    }
                }

                if (prevB && !gamepad1.b){
                    //TODO: Put code to break out of live trajectory: https://learnroadrunner.com/advanced.html#interrupting-a-live-trajectory
                }
                if (prevY && !gamepad1.y){
                    inDriveMode = false;
                }

                if (prevUp && !gamepad1.dpad_up){
                    toPosVal = toPosVal + 5;
                }
                if (prevDown && !gamepad1.dpad_down){
                    toPosVal = toPosVal - 5;
                }
                if (prevRight && !gamepad1.dpad_right){
                    toPosVal = toPosVal + 1;
                }
                if (prevLeft && !gamepad1.dpad_left){
                    toPosVal = toPosVal - 1;
                }

            }
            else {
                drive.setWeightedDrivePower(
                        new Pose2d(
                                -gamepad1.left_stick_y,
                                -gamepad1.left_stick_x,
                                -gamepad1.right_stick_x
                        )
                );
                if (prevY && !gamepad1.y){
                    inDriveMode = true;
                }
            }

            //Update variables for next internal program tick
            prevX = gamepad1.x;
            prevA = gamepad1.a;
            prevB = gamepad1.b;
            prevY = gamepad1.y;
            prevUp = gamepad1.dpad_up;
            prevDown = gamepad1.dpad_down;
            prevLeft = gamepad1.dpad_left;
            prevRight = gamepad1.dpad_right;

            drive.update();

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("toPosVal", toPosVal);
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
        }
    }
}