package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name="Teste de enviar")

public class TupiBonito extends LinearOpMode {

    //Instanciado variaveis

    // Declaração de motores de movimentação
    private DcMotor LeftFront;
    private DcMotor LeftBack  ;
    private DcMotor RightFront  ;
    private DcMotor RightBack  ;

    // Motores garra
   // private DcMotor CasUp;
   // private DcMotor DeslizaClaw;
   // private DcMotor ColetaClaw;

    // Servos Garra

   // private Servo HorizontalServ;
   // private Servo VerticalServ;




    @Override
    public void runOpMode() {
    // Declaração dos valores dos motores

        LeftFront  = hardwareMap.get(DcMotor.class, "LF");
        LeftBack  = hardwareMap.get(DcMotor.class, "LB");
        RightFront  = hardwareMap.get(DcMotor.class, "RF");
        RightBack  = hardwareMap.get(DcMotor.class, "RB");

       //Declaração dos valores de garra
      //  CasUp = hardwareMap.get(DcMotor.class,"CasUp");
       // DeslizaClaw = hardwareMap.get(DcMotor.class,"DeslizaClaw");
        //ColetaClaw = hardwareMap.get(DcMotor.class,"ColetaClaw");


        LeftFront.setDirection(DcMotor.Direction.REVERSE);
        LeftBack.setDirection(DcMotor.Direction.REVERSE);
        RightFront.setDirection(DcMotor.Direction.FORWARD);
        RightBack.setDirection(DcMotor.Direction.FORWARD);

        waitForStart();
        while (opModeIsActive()) {
            double max;

            //Movimentação do robô
            double Vertical = gamepad1.right_stick_x;
            double Horizontal = - gamepad1.right_stick_y;
            double Pivot = gamepad1.left_stick_x;

            //Garras no gamepad
           // float CascadingUp = gamepad2.left_stick_y;


          //  float DeslizapUp = gamepad2.right_stick_y;


            // setando as forças dos motores movimentação
            RightFront.setPower((-Pivot+(Horizontal-Vertical)));
            RightBack.setPower((-Pivot+(Horizontal+Vertical)));
            LeftFront.setPower((Pivot+(Horizontal+Vertical)));
            LeftBack.setPower((Pivot+(Horizontal-Vertical)));

            // setando as forças da garra
         //   CasUp.setPower(CascadingUp);
           //    DeslizaClaw.setPower(DeslizapUp);


            //logica do codigo


        }
    }

}