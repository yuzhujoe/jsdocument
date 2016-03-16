public class DivideTwoIntegers{
	    public int divide(int dividend, int divisor) {  
            // special occasions
        if(divisor == 0)  
        {  
            return Integer.MAX_VALUE;  
        }  
        boolean isNeg = (dividend^divisor)>>>31 == 1;  
        int res = 0;  
        if(dividend == Integer.MIN_VALUE)  
        {  
            dividend += Math.abs(divisor);  
            if(divisor == -1)  
            {  
                return Integer.MAX_VALUE;  
            }  
            res++;  
        }  
        if(divisor == Integer.MIN_VALUE)  
        {  
            return res;  
        }  

        // real occasions
        // 用被除数一直减去除数，直到为0
        // 这种方法的迭代次数是结果的大小，即比如结果为n，算法复杂度是O(n)
        // 任何一个整数可以表示成以2的幂为底的一组基的线性组合，即num=a_0*2^0+a_1*2^1+a_2*2^2+...+a_n*2^n。
        // 基于以上这个公式以及左移一位相当于乘以2，我们先让除数左移直到大于被除数之前得到一个最大的基。
        // 每次尝试减去这个基，如果可以则结果增加加2^k,然后基继续右移迭代，直到基为0为止。
        // 因为这个方法的迭代次数是按2的幂直到超过结果，所以时间复杂度为O(logn)。
        dividend = Math.abs(dividend);  
        divisor = Math.abs(divisor);  
        int digit = 0;  
        while(divisor <= (dividend>>1))  
        {  
            divisor <<= 1;  
            digit++;  
        }  
        while(digit>=0)  
        {  
            if(dividend>=divisor)  
            {  
                res += 1<<digit;  
                dividend -= divisor;  
            }  
            divisor >>= 1;  
            digit--;  
        }  
        return isNeg?-res:res;  
    }  
}