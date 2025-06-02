package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Teste Lift - Ticks por CM", group = "Testes")
public class tics extends LinearOpMode {

    private DcMotor liftMotor;

    @Override
    public void runOpMode() {

        // Mapeamento do motor
        liftMotor = hardwareMap.get(DcMotor.class, "liftMotor");

        // Zera o encoder
        liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addLine("Pronto para teste. Pressione PLAY.");
        telemetry.update();
        waitForStart();

        // Ativa o motor por 3 segundos
        liftMotor.setPower(0.4);
        sleep(2000);
        liftMotor.setPower(0);

        // Lê quantos ticks ele percorreu
        int ticks = liftMotor.getCurrentPosition();

        telemetry.addData("Ticks medidos", ticks);
        telemetry.addLine("Meça a distância subida (em cm) com régua e insira no código para calcular.");
        telemetry.update();

        // Aguarda 15 segundos para você ver os dados
        sleep(15000);

        // EXEMPLO: você mediu 8.3 cm
        double distanciaSubidaCm = 8.3;

        double ticksPorCm = ticks / distanciaSubidaCm;

        telemetry.addData("Distância medida (cm)", distanciaSubidaCm);
        telemetry.addData("Ticks por cm (calculado)", ticksPorCm);
        telemetry.addLine("Salve esse valor para usar no código de lift automático!");
        telemetry.update();

        sleep(10000); // Mantém a tela visível antes de encerrar
    }
}
