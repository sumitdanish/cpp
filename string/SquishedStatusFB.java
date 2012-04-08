/*
   Some engineers got tired of dealing with all the different ways of encoding 
   status messages, so they decided to invent their own. In their new scheme, 
   an encoded status message consists of a sequence of integers representing 
   the characters in the message, separated by spaces. Each integer is between 
   1 and M, inclusive. The integers do not have leading zeroes. Unfortunately 
   they decided to compress the encoded status messages by removing all the 
   spaces!

   Your task is to figure out how many different encoded status messages a 
   given compressed status message could have originally been. Because this 
   number can be very large, you should return the answer modulo 4207849484 (
   0xfaceb00c in hex).

   For example, if the compressed status message is "12" it might have 
   originally been "1 2", or it might have originally been "12". The compressed
   status messages are between 1 and 1000 characters long, inclusive. Due to 
   database corruption, a compressed status may contain sequences of digits 
   that could not result from removing the spaces in an encoded status message.

   Input
   The input begins with a single integer, N, the number of compressed status 
   messages you must analyze. This will be followed by N compressed status 
   messages, each consisting of an integer M, the highest character code for 
   that database, then the compressed status message, which will be a string of
   digits each in the range '0' to '9', inclusive. All tokens in the input 
   will be separated by some whitespace.

   Output
   For each of the test cases numbered in order from 1 to N, output "Case #i: "
   followed by a single integer containing the number of different encoded 
   status messages that could be represented by the corresponding compressed 
   sequence modulo 4207849484. If none are possible, output a 0.

   Constraints
   5 <= N <= 25
   2 <= M <= 255
   1 <= length of encoded status <= 1000

   Example inputExample output
   5
   12
   12
   255
   219
   30
   1234321
   2
   101
   70 
   8675309
   Case #1: 2
   Case #2: 4
   Case #3: 6
   Case #4: 0
   Case #5: 2
   */

public class SquishedStatusFB {
    /* dp[n] = 
     *   single digit  starting from current + dp[n+1]
     * + double digits starting from current + dp[n+2]
     * + triple digits starting from current + dp[n+3]
     */ 
    public int possibilities(String s, int max) {
        char[] c = s.toCharArray();
        int dp[] = new int[s.length()+1];
        dp[c.length] = 1;
        for (int i=c.length-1; i>=0; i--) {
            dp[i] = single(c[i]-'0',max)? dp[i+1] : 0;
            if (i<c.length-1 && tens(c[i]-'0', c[i+1]-'0', max)) {
                dp[i] += dp[i+2];
            }
            if (i<c.length-2 && hundreds(c[i]-'0', c[i+1]-'0', c[i+2]-'0', max)) {
                dp[i] += dp[i+3];
            }
        }
        return dp[0];
    }

    public boolean single(int x, int max) {
        return x>0 && x<=max;
    }

    public boolean tens(int x0, int x1, int max) {
        return (10*x0 + x1 <= max && x0 !=0);
    }

    public boolean hundreds(int x0, int x1, int x2, int max) {
        return (100*x0 + 10*x1 + x2 <= max && x0 != 0 && x2 != 0);
    }

    public static void main(String[] args) {
        System.out.println(new SquishedStatusFB().possibilities("12", 12));
        System.out.println(new SquishedStatusFB().possibilities("219", 255));
        System.out.println(new SquishedStatusFB().possibilities("1234321",30));
        System.out.println(new SquishedStatusFB().possibilities("101", 2));
        System.out.println(new SquishedStatusFB().possibilities("8675309",70));
    }
}
