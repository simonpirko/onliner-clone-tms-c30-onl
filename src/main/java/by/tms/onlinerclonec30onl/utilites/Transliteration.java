package by.tms.onlinerclonec30onl.utilites;

public class Transliteration {

    public static String transliterate(String input) {
        return input.toLowerCase()
                .replaceAll(" ", "_")
                .replaceAll("а", "a")
                .replaceAll("б", "b")
                .replaceAll("в", "v")
                .replaceAll("г", "g")
                .replaceAll("д", "d")
                .replaceAll("е", "e")
                .replaceAll("ё", "e")
                .replaceAll("ж", "j")
                .replaceAll("з", "z")
                .replaceAll("и", "i")
                .replaceAll("й", "i")
                .replaceAll("к", "k")
                .replaceAll("л", "l")
                .replaceAll("м", "m")
                .replaceAll("н", "n")
                .replaceAll("о", "o")
                .replaceAll("п", "p")
                .replaceAll("р", "r")
                .replaceAll("с", "s")
                .replaceAll("т", "t")
                .replaceAll("у", "u")
                .replaceAll("ф", "f")
                .replaceAll("х", "h")
                .replaceAll("ц", "c")
                .replaceAll("ч", "ch")
                .replaceAll("ш", "sh")
                .replaceAll("щ", "sc")
                .replaceAll("ъ", "")
                .replaceAll("ы", "i")
                .replaceAll("ь", "")
                .replaceAll("э", "e")
                .replaceAll("ю", "y")
                .replaceAll("я", "ya");
    }
}
