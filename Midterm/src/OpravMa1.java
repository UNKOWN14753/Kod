public class OpravMa1 {

    public static boolean search(int[] arr, int element) {
        int bot = 0;
        int top = arr.length-1;
        while (bot < top) {
            int mid = (bot+top)/2;
            if (arr[mid] == element) return true;
            if (arr[mid] < element) bot = mid+1; else top = mid-1;
        }
        return false;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 10; i++)
            System.out.println(i + ".." + search(new int[]{1,3,4,5,6,7},i));

    }
}
