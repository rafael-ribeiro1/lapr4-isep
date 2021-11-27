package eapli.base.utils;

import org.apache.logging.log4j.Logger;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;

public enum ANSI{
    RESET ( "\u001B[0m"),
    //Normal
    BLACK ( "\u001B[0;30m"),
    RED ( "\u001B[0;31m"),
    GREEN ( "\u001B[0;32m"),
    YELLOW ( "\u001B[0;33m"),
    BLUE ( "\u001B[0;34m"),
    PURPLE ( "\u001B[0;35m"),
    CYAN ( "\u001B[0;36m"),
    WHITE ( "\u001B[0;37m"),
    //Bold
    BOLD_BLACK ( "\u001B[1;30m"),
    BOLD_RED ( "\u001B[1;31m"),
    BOLD_GREEN ( "\u001B[1;32m"),
    BOLD_YELLOW ( "\u001B[1;33m"),
    BOLD_BLUE ( "\u001B[1;34m"),
    BOLD_PURPLE ( "\u001B[1;35m"),
    BOLD_CYAN ( "\u001B[1;36m"),
    BOLD_WHITE ( "\u001B[1;37m"),
    //Underline
    UNDERLINE_BLACK ( "\u001B[4;30m"),
    UNDERLINE_RED ( "\u001B[4;31m"),
    UNDERLINE_GREEN ( "\u001B[4;32m"),
    UNDERLINE_YELLOW ( "\u001B[4;33m"),
    UNDERLINE_BLUE ( "\u001B[4;34m"),
    UNDERLINE_PURPLE ( "\u001B[4;35m"),
    UNDERLINE_CYAN ( "\u001B[4;36m"),
    UNDERLINE_WHITE ( "\u001B[4;37m"),
    //Background
    BACKGROUND_BLACK ( "\u001B[40m"),
    BACKGROUND_RED ( "\u001B[41m"),
    BACKGROUND_GREEN ( "\u001B[42m"),
    BACKGROUND_YELLOW ( "\u001B[43m"),
    BACKGROUND_BLUE ( "\u001B[44m"),
    BACKGROUND_PURPLE ( "\u001B[45m"),
    BACKGROUND_CYAN ( "\u001B[46m"),
    BACKGROUND_WHITE ( "\u001B[47m"),
    //High Intensity
    HIGH_INTENSITY_BLACK ( "\u001B[0;90m"),
    HIGH_INTENSITY_RED ( "\u001B[0;91m"),
    HIGH_INTENSITY_GREEN ( "\u001B[0;92m"),
    HIGH_INTENSITY_YELLOW ( "\u001B[0;93m"),
    HIGH_INTENSITY_BLUE ( "\u001B[0;94m"),
    HIGH_INTENSITY_PURPLE ( "\u001B[0;95m"),
    HIGH_INTENSITY_CYAN ( "\u001B[0;96m"),
    HIGH_INTENSITY_WHITE ( "\u001B[0;97m"),
    //Bold High Intensity
    BOLD_HIGH_INTENSITY_BLACK ( "\u001B[1;90m"),
    BOLD_HIGH_INTENSITY_RED ( "\u001B[1;91m"),
    BOLD_HIGH_INTENSITY_GREEN ( "\u001B[1;92m"),
    BOLD_HIGH_INTENSITY_YELLOW ( "\u001B[1;93m"),
    BOLD_HIGH_INTENSITY_BLUE ( "\u001B[1;94m"),
    BOLD_HIGH_INTENSITY_PURPLE ( "\u001B[1;95m"),
    BOLD_HIGH_INTENSITY_CYAN ( "\u001B[1;96m"),
    BOLD_HIGH_INTENSITY_WHITE ( "\u001B[1;97m"),
    //Bold High Intensity Background
    BOLD_HIGH_INTENSITY_BACKGROUND_BLACK ( "\u001B[0;100m"),
    BOLD_HIGH_INTENSITY_BACKGROUND_RED ( "\u001B[0;101m"),
    BOLD_HIGH_INTENSITY_BACKGROUND_GREEN ( "\u001B[0;102m"),
    BOLD_HIGH_INTENSITY_BACKGROUND_YELLOW ( "\u001B[0;103m"),
    BOLD_HIGH_INTENSITY_BACKGROUND_BLUE ( "\u001B[0;104m"),
    BOLD_HIGH_INTENSITY_BACKGROUND_PURPLE ( "\u001B[0;105m"),
    BOLD_HIGH_INTENSITY_BACKGROUND_CYAN ( "\u001B[0;106m"),
    BOLD_HIGH_INTENSITY_BACKGROUND_WHITE ( "\u001B[0;107m");

    public final String code;

    ANSI(String code) {
        this.code = code;
    }
    public static String format(String str, ANSI ansi){
        return ansi.code+str+RESET.code;
    }

    public static void log(Consumer<String> log, String str, ANSI ansi){
        log.accept ( ANSI.format ( str,ansi ) );
    }

    public static void print(String str, ANSI ansi){
        System.out.println (ANSI.format (str,ansi));
    }
}
