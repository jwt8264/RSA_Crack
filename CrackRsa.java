/*
    CrackRsa.java
    This class will find the primes p and q and the exponent d that were
    used to generate an RSA public key.

    @author Jason Tu
    Crypto Project 4
*/

import java.math.BigInteger;

public class CrackRsa
{
    private BigInteger e;
    private BigInteger n;

    /**
        constructor for a new CrackRsa with public key <e> <n>
    */  
    public CrackRsa(BigInteger e, BigInteger n)
    {
        this.e = e;
        this.n = n;
    }
    
    /**
        initiate the crack on the RSA public key
        global variables e and n must be set before calling this
    */
    public void crack()
    {
        // find p and q
        BigInteger q = rho(n);
        if (q.compareTo(BigInteger.ZERO) == 0)
        {
            System.out.println("Not a valid public key");
            return;
        }
        BigInteger p = n.divide(q);

        // find d
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        BigInteger d = e.modInverse(phi); 

        // print results
        if (p.compareTo(q) < 0)
        {
            System.out.println(p);
            System.out.println(q);
        }
        else
        {
            System.out.println(q);
            System.out.println(p);
        }
        System.out.println(d);
    }

    /**
        find a factor of x
        @param x - the number to factor
        @return a non-trivial factor of x
    */
    private BigInteger rho(BigInteger x)
    {
        BigInteger a = new BigInteger("2");
        BigInteger b = new BigInteger("2");
        BigInteger d = BigInteger.ONE;
        while (true)
        {
            a = a.pow(2).add(BigInteger.ONE).mod(x);
            b = b.pow(2).add(BigInteger.ONE).mod(x);
            b = b.pow(2).add(BigInteger.ONE).mod(x);
            d = a.subtract(b).gcd(x);
            if (BigInteger.ONE.compareTo(d) < 0 &&
                d.compareTo(x) < 0)
            {
                return d;
            }
            if (d.compareTo(x) == 0)
            {
                return BigInteger.ZERO;
            }
        }
    }

    /**
        main function to run the program
    */
    public static void main(String[] args)
    {
        if (args.length != 2)
        {
            usage();
        }
        try 
        {
            CrackRsa cracker = new CrackRsa(new BigInteger(args[0]), new BigInteger(args[1]));
            cracker.crack();
        } 
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            usage();
        }
    
    }

    /**
        print a usage message and then exit  
    */
    public static void usage()
    {
        System.out.println("Usage: java CrackRsa <e> <n>");
        System.exit(1);
    }
}
