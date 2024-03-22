package org.fakebelieve.henplus.plugins.prompt;

import java.util.Arrays;
import java.util.List;

public class Color {


    public static List<String> colors() {
        String[] colors = {"none", "black", "red", "green", "yellow", "blue", "magenta", "cyan", "white"};
        return Arrays.asList(colors);
    }

    private static int color(String color) {
        switch (color) {
            case "black":
                return 30;
            case "red":
                return 31;
            case "green":
                return 32;
            case "yellow":
                return 33;
            case "blue":
                return 34;
            case "magenta":
                return 35;
            case "cyan":
                return 36;
            case "white":
                return 37;
            default:
                return 37;
        }
    }

    public static String colorize(String color, String text) {
        if (color.equals("none")) {
            return text;
        } else {
            return String.format("\033[%dm%s\033[0m", color(color), text);
        }
    }
}
