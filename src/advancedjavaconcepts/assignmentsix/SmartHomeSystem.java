package advancedjavaconcepts.assignmentsix;

import advancedjavaconcepts.ColorCode;

import java.util.Scanner;

class SmartHome {
    private String masterKey = "1234";
    private Device currentDevice;
    SmartHome.Settings s = new SmartHome.Settings();

    class Device {
        String deviceName;
        boolean isOn;
        Device(String deviceName, boolean isOn) {
            this.deviceName = deviceName;
            this.isOn = isOn;
        }
        public void turnOn() {
            this.isOn = true;
            System.out.println(deviceName + " is now ON");
        }
        public void turnOff() {
            this.isOn = false;
            System.out.println(deviceName + " is now OFF");
        }

        @Override
        public String toString() {
            return deviceName + " status: " + (isOn ? "ON" : "OFF");
        }
    }

    static class Settings {
        static int maxDevicesAllowed;
        static String sysVer;
        static {
            maxDevicesAllowed = 10;
            sysVer = "1.0";
        }

        @Override
        public String toString() {
            return "Max Devices Allowed: " + maxDevicesAllowed + "\nSystem Version: " + sysVer;
        }
    }

    boolean authenticate(String enteredKey) {
        class AccessValidator {
            private boolean validate() {
                return masterKey.equals(enteredKey);
            }
            boolean authenticate() {
                return validate();
            }
        }
        return new AccessValidator().authenticate();
    }

    public void switchDevice(Device device) {
        this.currentDevice = device;
        System.out.println("Switched to device: " + device.deviceName);
    }

    public Device getCurrentDevice() {
        return currentDevice;
    }

    public void performTemporaryAction() {
        Operation opt = new Operation() {
            @Override
            public void execute() {
                System.out.println("Performing a temporary action inside SmartHome...");
            }
        };
        opt.execute();
    }
}

public class SmartHomeSystem {
    public static void main(String[] args) {
        SmartHome home = new SmartHome();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter the key: ");
        String enteredKey = input.nextLine();
        if (!home.authenticate(enteredKey)) {
            System.out.println(ColorCode.error("Access Denied!!"));
            return;
        }
        System.out.println(ColorCode.right("Authentication Successful"));
        SmartHome.Device fan = home.new Device("Fan", false);
        SmartHome.Device light = home.new Device("Light", false);

        home.switchDevice(fan);
        System.out.println(fan);
        fan.turnOn();
        fan.turnOff();

        home.switchDevice(light);
        System.out.println(light);
        light.turnOn();
        light.turnOff();
        home.performTemporaryAction();
        System.out.println(home.s);
    }
}
