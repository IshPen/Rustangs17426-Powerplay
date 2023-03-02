package org.firstinspires.ftc.teamcode.drive.opmode;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@TeleOp(group = "drive")
public class TESLAv1 extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException {
        //base location
        double bX = 0;
        double bY = -48;
        //shift back to align with grid
        double sbX = 0;
        double sbY = -36;
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
        // (-36, 48)
        TrajectorySequence p1 = drive.trajectorySequenceBuilder(new Pose2d(bX, bY, Math.toRadians(90)))
                //shift back to grid
                //.lineTo(sbX,sbY)
                //align with x
                //.lineToLinearHeading()
                .build();
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (!isStopRequested()) {
            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x,
                            -gamepad1.right_stick_x
                    )
            );

            drive.update();

            Pose2d poseEstimate = drive.getPoseEstimate();
            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.update();
        }
    }

}
