package main.java.com.fourous.gitbuild.util;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

/**
 * @author fourous
 * @date: 2019/10/30
 * @description: 系统有关服务
 */
public class SystemUtil {
    private static String WEB_CLASS_PATH;
    private static String WEB_ROOT_PATH;

    /**
     * 获取WEBROOT路径
     */
    public static String getWebRootPath() {
        if (WEB_ROOT_PATH == null || "".equals(WEB_ROOT_PATH)) {
            String classesPath = getWebClassPath();
            WEB_ROOT_PATH = classesPath.substring(0, classesPath.indexOf("/WEB-INF/classes"));
        }
        return WEB_ROOT_PATH;
    }

    /**
     * 获取classes路径
     *
     * @return
     */
    public static String getWebClassPath() {
        if (WEB_CLASS_PATH == null || "".equals(WEB_CLASS_PATH)) {
            WEB_CLASS_PATH = SystemUtil.class.getClassLoader().getResource("").getPath();
        }
        return WEB_CLASS_PATH;
    }

    /**
     * 判断当前系统是什么系统
     * 更加详细的可以引如Ohshi包
     *
     * @return
     */
    public static boolean isOsWindows() {
        String os = System.getProperty("os.name");
        System.out.println(os.toLowerCase().startsWith("win"));
        return os.toLowerCase().startsWith("win");
    }

    public static String generateLicenseKey() throws Exception {
        SystemInfo systemInfo = new SystemInfo();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        CentralProcessor centralProcessor = hardwareAbstractionLayer.getProcessor();

        String vendor = operatingSystem.getManufacturer();
        String processorSerialNumber = centralProcessor.getSystemSerialNumber();
        String processorIdentifier = centralProcessor.getIdentifier();
        int processors = centralProcessor.getLogicalProcessorCount();

        String delimiter = "#";

        return vendor +
                delimiter +
                processorSerialNumber +
                delimiter +
                processorIdentifier +
                delimiter +
                processors;
    }

}
