public class OpravMa4 {
    public static void main(String[] args) {
        var s1 = "java";
        var x = "va";
        var s2 = "Ja" + x;
        String s3 = null;
        System.out.println(s1 == s2);                   // false
        System.out.println(s1.equalsIgnoreCase(s2));    // true
        System.out.println(s2.equals(s1));              // false
        System.out.println(s2.equals(s3));              // false
        System.out.println(s3.equals(s2));              // Null Pointer Exception
    }
}
