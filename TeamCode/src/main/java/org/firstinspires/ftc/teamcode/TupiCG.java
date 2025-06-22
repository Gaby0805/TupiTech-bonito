package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="TupiCG")

public class TupiCG extends LinearOpMode {

    //Instanciado variáveis

    // Declaração de motores de movimentação
    private DcMotor LeftFront;
    private DcMotor LeftBack  ;
    private DcMotor RightFront  ;
    private DcMotor RightBack  ;

    // Motores garra
    private DcMotor CasUp;
    private DcMotor IntakeClaw;
    private DcMotor ColetaClaw;

    // Servos Garra
    private Servo Garra;
    private Servo GarraFechar;




    @Override
    public void runOpMode() {
        // Declaração dos valores dos motores

        LeftFront  = hardwareMap.get(DcMotor.class, "LF");
        LeftBack  = hardwareMap.get(DcMotor.class, "LB");
        RightFront  = hardwareMap.get(DcMotor.class, "RF");
        RightBack  = hardwareMap.get(DcMotor.class, "RB");


        double ServoGarraPosition;
        double ServoGarraFecharPosition;
        double GarraSpeed;
        double GarraFecharSpeed;
        double DefaultPosition;


        //Declaração dos valores de garra
        CasUp = hardwareMap.get(DcMotor.class,"CasUp");
        IntakeClaw = hardwareMap.get(DcMotor.class,"IntakeClaw");
        ColetaClaw = hardwareMap.get(DcMotor.class,"ColetaClaw");
        Garra = hardwareMap.get(Servo.class, "Garra");
        GarraFechar = hardwareMap.get(Servo.class, "GarraFechar");

        ServoGarraFecharPosition = 0.37;
        GarraSpeed = 0.02;
        ServoGarraPosition = 0.47;
        GarraFecharSpeed = 0.02;
        DefaultPosition = 0.47;

        LeftFront.setDirection(DcMotor.Direction.FORWARD);
        LeftBack.setDirection(DcMotor.Direction.FORWARD);
        RightFront.setDirection(DcMotor.Direction.REVERSE);
        RightBack.setDirection(DcMotor.Direction.REVERSE);



        waitForStart();
        while (opModeIsActive()) {
            double max;

            //Movimentação do robô
            double Vertical = - gamepad1.right_stick_x;
            double Horizontal = - gamepad1.right_stick_y;
            double Pivot = - gamepad1.left_stick_x;

            //Garras no gamepad
            float CascadingUp = gamepad2.right_trigger;
            float CascadingDown = gamepad2.left_trigger;
            double Intake = gamepad2.right_stick_y;
            double rot = gamepad2.left_stick_y;

            // Servo voltando pra default na cascading



            if (gamepad2.options) {
                ServoGarraPosition = DefaultPosition;
            }
            if (gamepad2.left_bumper) {
                ServoGarraPosition += GarraSpeed;
            }
            if (gamepad2.right_bumper) {
                ServoGarraPosition += -GarraSpeed;
            }
            if (gamepad2.a) {
                ServoGarraFecharPosition += GarraFecharSpeed;
            }
            if (gamepad2.b) {
                ServoGarraFecharPosition += -GarraFecharSpeed;
            }

            RightFront.setPower((-Pivot+(Horizontal-Vertical)));
            RightBack.setPower((-Pivot+(Horizontal+Vertical)));
            LeftFront.setPower((Pivot+(Horizontal+Vertical)));
            LeftBack.setPower((Pivot+(Horizontal-Vertical)));


            CasUp.setPower(CascadingUp);
            CasUp.setPower(-CascadingDown);


            IntakeClaw.setPower(Intake);
            IntakeClaw.setPower(-Intake);

            ColetaClaw.setPower(rot);
            ColetaClaw.setPower(-rot);

            ServoGarraPosition = Math.min(Math.max(ServoGarraPosition, 0.05), 0.77);

            //Telemetria do código

            ServoGarraFecharPosition = Math.min(Math.max(ServoGarraFecharPosition, 0.37), 0.47);
            Garra.setPosition(ServoGarraPosition);
            GarraFechar.setPosition(ServoGarraFecharPosition);
            telemetry.addData("Default", DefaultPosition);
            telemetry.addData("SGarra", ServoGarraPosition);
            telemetry.addData("SGarraFechar", ServoGarraFecharPosition);
            telemetry.update();

            //Lógica do código

        }
    }

}