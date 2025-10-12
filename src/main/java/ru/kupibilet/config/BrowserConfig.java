package ru.kupibilet.config;

public class BrowserConfig {

    /**
     * Returns the browser type from system property or default value.
     *
     * @return BrowserType enum
     */
    public static BrowserType getBrowserType() {
        String browser = System.getProperty("browser", "chrome");
        return BrowserType.fromString(browser);
    }

    /**
     * Indicates whether tests should run in headless mode.
     * Value is read from system property "headless", default is false.
     *
     * @return true if headless mode is enabled, false otherwise
     */
    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }

    /**
     * Indicates whether the browser should start maximized.
     * Value is read from system property "maximized", default is true.
     *
     * @return true if browser should start maximized, false otherwise
     */
    public static boolean isMaximized() {
        return Boolean.parseBoolean(System.getProperty("maximized", "true"));
    }

    /**
     * Returns the browser window width.
     * Value is read from system property "browserWidth", default is 1920.
     *
     * @return browser width in pixels
     */
    public static int getBrowserWidth() {
        return Integer.parseInt(System.getProperty("browserWidth", "1920"));
    }

    /**
     * Returns the browser window height.
     * Value is read from system property "browserHeight", default is 1080.
     *
     * @return browser height in pixels
     */
    public static int getBrowserHeight() {
        return Integer.parseInt(System.getProperty("browserHeight", "1080"));
    }

    /**
     * Returns the implicit wait timeout in seconds.
     * Value is read from system property "implicitWaitTimeout", default is 5.
     *
     * @return implicit wait timeout in seconds
     */
    public static long getImplicitWaitTimeout() {
        return Long.parseLong(System.getProperty("implicitWaitTimeout", "5"));
    }

    /**
     * Returns the page load timeout in seconds.
     * Value is read from system property "pageLoadTimeout", default is 10.
     *
     * @return page load timeout in seconds
     */
    public static long getPageLoadTimeout() {
        return Long.parseLong(System.getProperty("pageLoadTimeout", "10"));
    }
}
