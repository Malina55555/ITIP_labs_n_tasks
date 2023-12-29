public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            System.out.print(s + " ");
            System.out.println(isPalindrome(s));
        }
    }
    public static String reverseString(String s) {
        String result = "";
        for (int w = s.length()-1; w>-1; w-=1) {
            result += s.charAt(w);
        }
        return  result.toLowerCase();
    }
    public static boolean isPalindrome(String s) {
        if (s.toLowerCase().equals(reverseString(s))) {
            return  true;
        }
        return false;
    }
}
//java Palindrome.java madam racecar apple kayak song noon