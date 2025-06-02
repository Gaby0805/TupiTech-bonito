package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Teste de enviar")

public class TupiBonito extends LinearOpMode {

    //Instanciado variaveis

    // Declaração de motores de movimentação
    private Servo Gozar;
    private Servo dentro;
    private DcMotor LeftFront;
    private DcMotor LeftBack  ;
    private DcMotor RightFront  ;
    private DcMotor RightBack  ;

    // Motores garra
    private DcMotor CasUp;
    private DcMotor IntakeClaw;
    private DcMotor ColetaClaw;

    // Servos Garra

    private Servo HorizontalServ;
    private Servo VerticalServ;




    @Override
    public void runOpMode() {
    // Declaração dos valores dos motores

        LeftFront  = hardwareMap.get(DcMotor.class, "LF");
        LeftBack  = hardwareMap.get(DcMotor.class, "LB");
        RightFront  = hardwareMap.get(DcMotor.class, "RF");
        RightBack  = hardwareMap.get(DcMotor.class, "RB");


        double ServoPosition;
        double queroposition;
        double querospeed;
        double ServoSpeed;
        double defaultposition;


        //Declaração dos valores de garra
        CasUp = hardwareMap.get(DcMotor.class,"CasUp");
        IntakeClaw = hardwareMap.get(DcMotor.class,"IntakeClaw");
        ColetaClaw = hardwareMap.get(DcMotor.class,"ColetaClaw");
        Gozar = hardwareMap.get(Servo.class, "Gozar");
        dentro = hardwareMap.get(Servo.class, "dentro");

        queroposition = 0.37;
        querospeed = 0.02;
        ServoPosition = 0.47;
        ServoSpeed = 0.02;
        defaultposition = 0.47;

        LeftFront.setDirection(DcMotor.Direction.REVERSE);
        LeftBack.setDirection(DcMotor.Direction.REVERSE);
        RightFront.setDirection(DcMotor.Direction.FORWARD);
        RightBack.setDirection(DcMotor.Direction.FORWARD);




        waitForStart();
        while (opModeIsActive()) {
            double max;

            //Movimentação do robô
            double Vertical = gamepad1.right_stick_y;
            double Horizontal = - gamepad1.right_stick_x;
            double Pivot = gamepad1.left_stick_x;

            //Garras no gamepad
            float CascadingUp = gamepad2.right_trigger;
            float CascadingDown = gamepad2.left_trigger;

            double IntakeUp = gamepad2.left_stick_button;
            double IntakeDown = gamepad2.right_stick_button;


            if (gamepad1.options) {
                ServoPosition = defaultposition;
            }
            if (gamepad1.left_bumper) {
                ServoPosition += -ServoSpeed;
            }
            if (gamepad1.right_bumper) {
                ServoPosition += ServoSpeed;
            }
            if (gamepad1.a) {
                queroposition += -querospeed;
            }
            if (gamepad1.b) {
                queroposition += querospeed;
            }

            RightFront.setPower((-Pivot+(Horizontal-Vertical)));
            RightBack.setPower((-Pivot+(Horizontal+Vertical)));
            LeftFront.setPower((Pivot+(Horizontal+Vertical)));
            LeftBack.setPower((Pivot+(Horizontal-Vertical)));


            CasUp.setPower(CascadingUp);
            CasUp.setPower(-CascadingDown);


            IntakeClaw.setPower(IntakeUp);
            IntakeClaw.setPower(-IntakeDown);

            ServoPosition = Math.min(Math.max(ServoPosition, 0.05), 0.77);
            queroposition = Math.min(Math.max(queroposition, 0), 0.37);
            Gozar.setPosition(ServoPosition);
            dentro.setPosition(queroposition);
            telemetry.addData("deafault", defaultposition);
            telemetry.addData("Servo", ServoPosition);
            telemetry.addData("Servo2", queroposition);
            telemetry.update();

            //Lógica do código


        }
    }

}