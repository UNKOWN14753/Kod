package RiesenieJozoK;
public class DOL {
    String[][] pravidla;
    public DOL(String[][] pravidla) {
        this.pravidla = pravidla;
    }
    public boolean isDOL() {
        String[] leftSides = new String[pravidla.length];
        char[] rightSides = new char[3*pravidla.length];
        int rightIndex = 0;
        for (int i=0; i < pravidla.length; i++){
            String[] rule = pravidla[i];
            String left = rule[0];
            String right = rule[1];
            if(left.length() > 1) return false;
            for(int j =0; j < i+2;j++){
                if(leftSides[j] == null){
                    leftSides[j] = left;
                    break;
                }
                else if (leftSides[j].equals(left)){
                    return false;
                }
            }
            for(int j=0; j < right.length(); j++){
                rightSides[rightIndex] = right.charAt(j);
                rightIndex++;
            }
        }
        for(int i = 0; i < rightIndex; i++){
            boolean je = false;
            for (int j = 0; j < leftSides.length; j++){
                if (rightSides[i] == leftSides[j].charAt(0)){
                    je = true;
                    break;
                }
            }
            if (!je) return false;
        }
        return true;
    }
    public String oneStep(String initString) {
        StringBuffer newS = new StringBuffer();
        for(char z : initString.toCharArray()){
            for(int i = 0; i < pravidla.length; i++){
                if(pravidla[i][0].charAt(0) == z){
                    newS.append(pravidla[i][1]);
                    break;
                }
            }
        }
        return newS.toString();
    }
    public String manySteps(int n, String initString) {
        for(int i = 0; i < n; i++){
            initString = oneStep(initString);
        }
        return initString;
    }
}
