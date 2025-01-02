import java.util.*;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }
        
        String delimiter = ",|\n";
        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("\n");
            delimiter = numbers.substring(2, delimiterIndex);
            numbers = numbers.substring(delimiterIndex + 1);
            delimiter = escapeSpecialCharacters(delimiter);
        }
        
        String[] numberArray = numbers.split(delimiter);
        int sum = 0;
        List<Integer> negatives = new ArrayList<>();
        
        for (String num : numberArray) {
            if (!num.isEmpty()) {
                int number = Integer.parseInt(num);
                if (number < 0) {
                    negatives.add(number);
                }
                if (number <= 1000) {
                    sum += number;
                }
            }
        }
        
        if (!negatives.isEmpty()) {
            throw new IllegalArgumentException("Negative numbers not allowed: " + negatives);
        }
        
        return sum;
    }

    private String escapeSpecialCharacters(String delimiter) {
        return delimiter.replaceAll("([\\[\\]\\*\\+\\?\\.\\^\\$\\(\\)\\|])", "\\\\$1");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringCalculator calc = new StringCalculator();
        
        System.out.print("Enter numbers: ");
        String input = scanner.nextLine();
        
        try {
            int result = calc.add(input);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        
        scanner.close();
    }
}
