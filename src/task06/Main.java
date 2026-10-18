package task06;

public class Main {

    static Result<Integer> parseHours(String text) {
        try {
            int hours = Integer.parseInt(text.trim());
            return hours > 0 ? Result.ok(hours) : Result.error("hours must be positive: " + hours);
        } catch (NumberFormatException e) {
            return Result.error("not a number: " + text);
        }
    }

    static Result<String> normalizeCode(String text) {
        String code = text.trim().toUpperCase();
        return code.matches("[A-Z]{2,4}-\\d{3}") ? Result.ok(code) : Result.error("bad code: " + text);
    }

    public static void main(String[] args) {
        Result<Integer> hours = parseHours("40");
        Result<Integer> badHours = parseHours("abc");
        Result<String> code = normalizeCode(" java-101 ");
        Result<String> badCode = normalizeCode("x");

        System.out.println(hours + "  " + badHours);
        System.out.println(code + "  " + badCode);

        int doubled = hours.get() * 2;
        String upper = code.get().toLowerCase();
        System.out.println(doubled + " " + upper);

        System.out.println("error text: " + badHours.error());
        try {
            badHours.get();
        } catch (IllegalStateException e) {
            System.out.println("get() on error: " + e.getMessage());
        }
        try {
            hours.error();
        } catch (IllegalStateException e) {
            System.out.println("error() on ok:  " + e.getMessage());
        }
        try {
            Result.ok(null);
        } catch (NullPointerException e) {
            System.out.println("ok(null):       " + e.getMessage());
        }
    }
}
