package edu.wpi.first.wpilibj.templates;
//<editor-fold defaultstate="collapsed" desc="Imports">
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Initializations">
public class RobotTemplate extends SimpleRobot {
    
    int BckRghtWhl = 9;
    int BckLftWhl = 2;
    int FrntRghtWhl = 1;
    int FrntLftWhl = 10;
    
    RobotDrive driveRobot = new RobotDrive (
            FrntLftWhl,
            BckLftWhl,
            FrntRghtWhl,
            BckRghtWhl);
    
    int Sol = 1;
    Solenoid FrisbeeLauncher = new Solenoid(Sol);
    
    int Sol2 = 8;
    Solenoid FrisbeeLifter = new Solenoid(Sol2);
    
    int Sol3 = 3;
    Solenoid VerticalMovement = new Solenoid(Sol3);
    int Sol4 = 4;
    Solenoid VerticalDown = new Solenoid(Sol4);
    
    int PressureSensor = 1;
    int RelayComp = 4;
    Compressor Compressor = new Compressor(PressureSensor, RelayComp);
    
    int Switch = 2;
    DigitalInput FrisbeeSwitch = new DigitalInput(Switch);
    int Switch2 = 5;
    DigitalInput LoaderSwitch = new DigitalInput(Switch2);
    
    int ConveyorMotorLow = 3;
    Victor Conveyor1 = new Victor(ConveyorMotorLow);
    
    
    int Angle = 7;
    Victor AngleMotor = new Victor(Angle);
    
    int Shoot1 = 4;
    Jaguar Shooter1 = new Jaguar(Shoot1);
    int Shoot2 = 5;
    Jaguar Shooter2 = new Jaguar(Shoot2);
    
    int JStick = 1;
    int JStick2 = 2;
    int JStick3 = 3;
    Joystick Joystick1 = new Joystick(JStick);
    Joystick Joystick2 = new Joystick(JStick2);
    Joystick Joystick3 = new Joystick(JStick3);
    Timer timer = new Timer();
    
    double Time = 0;
    double TotalTime = 1;
    double Presses = 0;
    double Speed = 0;
    
    int Spot = 7;
        AnalogChannel SPOTY = new AnalogChannel(Spot);
    
    AxisCamera camera;
     int Switch3 = 9;
        DigitalInput LimitSwitch = new DigitalInput(Switch3);
     int LEDlIgHtShue = 8;
     Relay WeLookSoCool = new Relay(LEDlIgHtShue);
     int LEye = 6;
     Relay LeftEye = new Relay(LEye);
     int REye = 2;
     Relay RightEye = new Relay(REye);
    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Autonomous">
    public void autonomous() {
        Compressor.start();
        VerticalMovement.set(true);
        VerticalDown.set(false);
        Timer.delay(2);
        Shooter1.set(-1);
        Shooter2.set(-1);
        Timer.delay(3);
        FrisbeeLauncher.set(true);
        Timer.delay(1);
        FrisbeeLauncher.set(false);
        Timer.delay(1.5);
        FrisbeeLauncher.set(true);
        Timer.delay(1);
        FrisbeeLauncher.set(false);
        Timer.delay(1.5);
        FrisbeeLauncher.set(true);
        Timer.delay(1);
        FrisbeeLauncher.set(false);
        Timer.delay(1);
        Shooter1.set(0);
        Shooter2.set(0);
        Timer.delay(.5);
        VerticalMovement.set(false);
        VerticalDown.set(true);
        Compressor.stop();
        
    }
    //</editor-fold>    
//<editor-fold defaultstate="collapsed" desc="Teleoperated">
    public void operatorControl() {
        //WeLookSoCool.set(Relay.Value.kOn);
        boolean LED = true;
        boolean Up = false;
        boolean Down = false;
        boolean Righty = true;
        boolean Lefty = true;
        LeftEye.set(Relay.Value.kOn);
        RightEye.set(Relay.Value.kOn);
        String Message = "YOU GUYS GOT THIS WRECK EVERYONE AROUND YOU";
        int Angle = 0;
        while (isOperatorControl() && isEnabled()) {
            Compressor.start();
            if(Joystick2.getRawButton(1) == true) {
                if(LED == true) {
                    WeLookSoCool.set(Relay.Value.kOff);
                    LED = false;
                } else {
                    WeLookSoCool.set(Relay.Value.kOn);
                    LED = true;
                }
            }
            if(Joystick1.getRawButton(5) == true) {
                if(Righty == true) {
                    RightEye.set(Relay.Value.kOff);
                    Righty = false;
                } else {
                    RightEye.set(Relay.Value.kOn);
                    Righty = true;
                }
            }
            if(Joystick1.getRawButton(4) == true) {
                if(Lefty == true) {
                    LeftEye.set(Relay.Value.kOff);
                    Lefty = false;
                } else {
                    LeftEye.set(Relay.Value.kOn);
                    Lefty = true;
                }
            }
            System.out.println(Righty);
            System.out.println(Lefty);
            driveRobot.tankDrive(Joystick1.getY() * -1.0, Joystick2.getY() * -1.0);         
            double potato = SPOTY.getVoltage();
            //System.out.println("Spot:" + potato);
            
            //Hang
            if(Joystick3.getRawButton(7) == true) {
                if((potato < 4.83) == true) {
                    AngleMotor.set(1);
                    Message = "Angle is changing.";
                }
                else if((potato > 4.85) == true) {
                    AngleMotor.set(-1);
                    Message = "Angle is changing.";
                }
                else {
                    AngleMotor.set(0);
                    Angle = 0;
                    Message = "Can't go any lower";
                }
            }
            //15 degree
            if(Joystick3.getRawButton(8) == true) {
                if((potato < 5.62) == true) {
                    AngleMotor.set(1);
                    Message = "Angle is changing";
                }
                else if((potato > 5.64) == true) {
                    AngleMotor.set(-1);
                    Message = "Angle is changing";
                }
                else {
                    AngleMotor.set(0);
                    Message = "Angle is not changing";
                    Angle = 15;
                }
            }
            //19 degree
            if(Joystick3.getRawButton(9) == true) {
                if((potato < 5.97) == true) {
                    AngleMotor.set(1);
                    Message = "Angle is changing";
                }
                else if((potato > 5.99) == true) {
                    AngleMotor.set(-1);
                    Message = "Angle is changing";
                }
                else {
                    AngleMotor.set(0);
                    Message = "Angle is not changing";
                    Angle = 19;
                }
            }
            //21 degree
            if(Joystick3.getRawButton(10) == true) {
                if((potato < 6.04) == true) {
                    AngleMotor.set(1);
                    Message = "Angle is changing.";
                }
                else if((potato > 6.06) == true) {
                    AngleMotor.set(-1);
                    Message = "Angle is changing.";
                }
                else {
                    AngleMotor.set(0);
                    Message = "Angle is not changing.";
                    Angle = 21;
                }
            }
            //53 degree
            if(Joystick3.getRawButton(11) == true) {
                if((potato < 7.66) == true) {
                    AngleMotor.set(1);
                    Message = "Angle is changing.";
                }
                else if((potato > 7.68) == true) {
                    AngleMotor.set(-1);
                    Message = "Angle is changing.";
                }
                else {
                    AngleMotor.set(0);
                    Angle = 53;
                    Message = "Angle is not changing.";
                }
            }
                
            //Angle Movement Controls
            if(Joystick2.getRawButton(3) == true){
                if((potato < 7.68) == true) {
                    AngleMotor.set(1.0);
                    Message = "Increasing Angle";
                }
                else {
                    AngleMotor.set(0);
                    Message = "Can't go higher";
                }
            }
            if(Joystick2.getRawButton(2) == true) {
                if(potato > 4.83) {
                        AngleMotor.set(-1);
                        Message = "Decreasing Angle";
                } else {
                    AngleMotor.set(0.0);
                    Message = "Can't go any lower";
                }
            }
            if(Joystick2.getRawButton(3) == false && Joystick2.getRawButton(2) == false && Joystick3.getRawButton(10) == false && Joystick3.getRawButton(8) == false && Joystick3.getRawButton(7) == false && Joystick3.getRawButton(11) == false && Joystick3.getRawButton(9) == false){
                AngleMotor.set(0);
            }
            
            //Infinite Shooter
            while(Joystick3.getRawButton(1) == true){
                Shooter1.set(-1);
                Shooter2.set(-1);
                Timer.delay(1.7);
                FrisbeeLauncher.set(true);
                Timer.delay(1);
                FrisbeeLauncher.set(false);
                Timer.delay(1.5);
                FrisbeeLauncher.set(true);
                Timer.delay(1);
                FrisbeeLauncher.set(false);
                Timer.delay(1);
                Shooter1.set(0);
                Shooter2.set(0);
                
            }
            
            /*
             * AngleLift = Joystick3.getY();
             * if (AngleLift >= .1) {
             * AngleLift = 1.0;
             * }
             * if (AngleLift <= -.1) {
             * AngleLift = -1.0;
             * }
             * if (AngleLift < .1 && AngleLift > -.1) {
             * AngleLift = 0;
             * }
             * AngleMotor.set(AngleLift);
             */
            
            //Motor Speed Mechanism
            if(Joystick3.getRawButton(3) == true) {
                Shooter1.set(-1.0);
                Shooter2.set(-1.0);
            }
            if(Joystick3.getRawButton(4) == true) {
                Shooter1.set(0.0);
                Shooter2.set(0.0);
            }
            if(Joystick3.getRawButton(6) == true) {
                Shooter1.set(1.0);
                Shooter2.set(1.0);
            }
            if(Joystick3.getRawButton(5) == true){
                Shooter1.set(-0.5);
                Shooter2.set(-0.5);
            }
            
            //Fliper Fingers
            if(Joystick2.getRawButton(10) == true) {
                FrisbeeLifter.set(false);
            }
            if(Joystick2.getRawButton(11) == true) {
                FrisbeeLifter.set(true);
            }
            
            //Alternative Shooter
           /*if(Joystick3.getRawButton(7) == true){
                Shooter1.set(-1);
                Shooter2.set(-1);
                Timer.delay(1.7);
                FrisbeeLauncher.set(true);
                Timer.delay(1);
                FrisbeeLauncher.set(false);
                Shooter1.set(0);
                Shooter2.set(0);
            }
            */
            //Conveyor
            if(Joystick2.getRawButton(8) == true) {
                Conveyor1.set(1.0);
            }
            if(Joystick2.getRawButton(9) == true) {
                Conveyor1.set(-1.0);
            }
            if(Joystick2.getRawButton(8) == false && Joystick2.getRawButton(9) == false) {
                Conveyor1.set(0.0);
            }
            
            //Shooter Vertical Movement
            if(Joystick1.getRawButton(3) == true) {
                VerticalMovement.set(true);
                if(Up == true) {
                    VerticalMovement.set(false);
                } else {
                    VerticalMovement.set(true);
                }
            }
            if(Joystick1.getRawButton(2) == true) {
                VerticalMovement.set(false);
                if(Down == true) {
                    VerticalDown.set(false);
                } else {
                    VerticalDown.set(true);
                }
            }
            
            
            //Launcher
            if(Joystick3.getRawButton(2) == true){
                FrisbeeLauncher.set(true);
                
            } else {
                
                FrisbeeLauncher.set(false);
            }
            
            //One Button Shooter Maximum
            /*
             * if(Joystick2.getRawButton(11) == true) {
             * Shooter1.set(-1);
             * Shooter2.set(-1);
             * Timer.delay(2.5);
             * FrisbeeLifter.set(true);
             * Timer.delay(1);
             * FrisbeeLauncher.set(true);
             * Timer.delay(1);
             * FrisbeeLauncher.set(false);
             * Timer.delay(1);
             * FrisbeeLifter.set(false);
             * Shooter1.set(0);
             * Shooter2.set(0);
             * }
             *
             * //One Button Shooter Medium
             * if(Joystick2.getRawButton(10) == true) {
             * Shooter1.set(-1);
             * Shooter2.set(-1);
             * Timer.delay(1);
             * FrisbeeLifter.set(true);
             * Timer.delay(1);
             * FrisbeeLauncher.set(true);
             * Timer.delay(1);
             * FrisbeeLauncher.set(false);
             * Timer.delay(1);
             * FrisbeeLifter.set(false);
             * Shooter1.set(0);
             * Shooter2.set(0);
             * }
             *
             * //One Button Shooter Low
             * if(Joystick2.getRawButton(8) == true) {
             * Shooter1.set(-1);
             * Shooter2.set(-1);
             * Timer.delay(1);
             * FrisbeeLifter.set(true);
             * Timer.delay(1);
             * FrisbeeLauncher.set(true);
             * Timer.delay(1);
             * FrisbeeLauncher.set(false);
             * Timer.delay(1);
             * FrisbeeLifter.set(false);
             * Shooter1.set(0);
             * Shooter2.set(0);
             * }
             */
            SmartDashboard.putBoolean("Are LEDs on?", LED);
            SmartDashboard.putNumber("Angle: ", Angle);
            SmartDashboard.putString("Messages:", Message);
        }
        
        Compressor.stop();
        }
    

    //</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Test">
    public void test() {
        
    }
}
//</editor-fold>