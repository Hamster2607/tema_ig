package org.firstinspires.ftc.teamcode.opmodes.tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.robot.Robot;
import org.firstinspires.ftc.teamcode.robot.opmode.RobotOpMode;

@TeleOp(name = "Test")
public class Test extends RobotOpMode {

    private DcMotor motor_left, motor_right;
    private Servo arm_pivot, claw;

        public void main(String[] args) {
        //init
        motor_left = hardwareMap.get(DcMotor.class, "motor_left");
        motor_right = hardwareMap.get(DcMotor.class, "motor_right");
        arm_pivot = hardwareMap.get(Servo.class, "arm_pivot");
        claw = hardwareMap.get(Servo.class, "claw");

        boolean clawX = false;
        boolean clawIsOpen = false;
        arm_pivot.setPosition(0.2);
        claw.setPosition(0);

        //main loop
        while (true) {
            double drive = -gamepad1.left_stick_y;
            double dir = gamepad1.right_stick_x;

            double leftPower = Range.clip(drive - dir, -1, 1);
            double rightPower = Range.clip(drive + dir, -1, 1);

            motor_left.setPower(leftPower);
            motor_right.setPower(rightPower);

            if (gamepad1.dpad_up) {
                arm_pivot.setPosition(0.85);
            } else if (gamepad1.dpad_down) {
                arm_pivot.setPosition(0.2);
            }

            if (gamepad1.x && !clawX){
                clawIsOpen = !clawIsOpen;
                claw.setPosition(clawIsOpen ? 1 : 0);
            }
            clawX = gamepad1.x;

            if (gamepad1.circle) break;

            telemetry.addData("claw", clawIsOpen);
            telemetry.update();
        }
    }

    @Override
    protected void onUpdate() {

    }
}