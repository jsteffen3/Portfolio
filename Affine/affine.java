import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class affine
{
  public static void main(String[] args)
  {
    if (args[0].equals("encrypt"))
    {
      if (args.length != 5)
      {
        System.out.println("Invald number of arguments must have 4");
        return;
      }
      encrypt(args[1], args[2], args[3], args[4]);
    }
    else if (args[0].equals("decrypt"))
    {
      if (args.length != 5)
      {
        System.out.println("Invald number of arguments must have 4");
        return;
      }
      decrypt(args[1], args[2], args[3], args[4]);
    }
    else if (args[0].equals("decipher"))
    {
      if (args.length != 4)
      {
        System.out.println("Invald number of arguments must have 3");
        return;
      }
      decipher(args[1], args[2], args[3]);
    }
    else
    {
      System.out.println("Invlaid command please use encrypt, decrypt, or decipher");
    }
  }

  // encrypt is working 9/24
  private static void encrypt(String in, String out, String alpha, String beta)
  {
    System.out.print("Running encrypt");
    int a = Integer.parseInt(alpha);
    int b = Integer.parseInt(beta);
    if (gcd(a, 128) != 1)
    {
      System.out.println(
          "The key pair ({" + a + "}, {" + b + "}) is invalid, please select another key.");
      return;
    }
    File In = new File(in);
    File Out = new File(out);
    FileInputStream toIn = null;
    FileOutputStream toOut = null;
    try
    {
      toIn = new FileInputStream(In);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    try
    {
      toOut = new FileOutputStream(Out);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    int current = 0;
    int condition = 0;
    char print = 'a';
    while (condition != -1)
    {
      try
      {
        current = toIn.read();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      condition = current;
      if (current != -1)
      {
        current = ((current * a) + b) % 128;
        print = (char) current;
        try
        {
          toOut.write(current);
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
  }

  // decrypt working 9/26
  public static void decrypt(String in, String out, String alpha, String beta)
  {
    System.out.println("Running Decrypt");
    int a = Integer.parseInt(alpha);
    int b = Integer.parseInt(beta);
    if (gcd(a, 128) != 1)
    {
      System.out.println(
          "The key pair ({" + a + "}, {" + b + "}) is invalid, please select another key.");
      return;
    }
    File In = new File(in);
    File Out = new File(out);
    FileInputStream toIn = null;
    FileOutputStream toOut = null;
    try
    {
      toIn = new FileInputStream(In);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    try
    {
      toOut = new FileOutputStream(Out);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    int current = 0;
    int condition = 0;
    char print = 'a';
    while (condition != -1)
    {
      try
      {
        current = toIn.read();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      condition = current;
      int inverse = calcInverse(a);
      if (current != -1)
      {
        // multiple by the inverse mod 128
        current = (inverse * (current - b)) % 128;
        print = (char) current;
        try
        {
          toOut.write(current);
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }
    }
  }

  private static int gcd(int a, int b)
  {
    if (b == 0)
    {
      return a;
    }
    return gcd(b, a % b);
  }

  private static int calcInverse(int a)
  {
    for (int i = 1; i < 128; i++)
      if (((a % 128) * (i % 128)) % 128 == 1)
        return i;
    return 1;
  }

  private static void decipher(String in, String out, String dictonary)
  {
    System.out.println("Running Decipher");
    int maxMatchCount = 0;
    int maxA = 0;
    int maxB = 0;
    int currentMatchCount = 0;
    File In = new File(in);
    File Out = new File(out);
    File Dict = new File(dictonary);
    FileInputStream toIn = null;
    FileOutputStream toOut = null;
    FileInputStream dictIn = null;
    String encrypted = "";
    String decrypted = "";
    String dict = "";
    String maxDecrypt = "";
    int current = 0;
    int condition = 0;
    String[] compareToDict = null;
    try
    {
      toIn = new FileInputStream(In);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    try
    {
      dictIn = new FileInputStream(Dict);
    }
    catch (FileNotFoundException e1)
    {
      e1.printStackTrace();
    }
    try
    {
      toOut = new FileOutputStream(Out);
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
    while (condition != -1)
    {
      try
      {
        current = toIn.read();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      condition = current;
      if (current != -1)
      {
        // multiple by the inverse mod 128
        encrypted += (char) current;
      }
    }
    condition = 0;
    while (condition != -1)
    {
      try
      {
        current = dictIn.read();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
      condition = current;
      if (current != -1)
      {
        // multiple by the inverse mod 128
        dict += (char) current;
      }
    }
    int inverse = 0;
    for (int a = 1; a < 128; a++)
    {
      if (gcd(a, 128) == 1)
      {
        inverse = calcInverse(a);
        for (int b = 0; b < 128; b++)
        {
          decrypted = "";
          for (int i = 0; i < encrypted.length(); i++)
          {
            current = (int) encrypted.charAt(i);
            current = (inverse * (current - b)) % 128;
            decrypted += (char) current;
          }
          compareToDict = decrypted.split(" ");
          for (int i = 0; i < compareToDict.length; i++)
          {
            if (dict.lastIndexOf(compareToDict[i]) != -1)
            {
              System.out.println("Matched " + compareToDict[i]);
              currentMatchCount += compareToDict[i].length();
            }
          }
          if (currentMatchCount > maxMatchCount)
          {
            maxMatchCount = currentMatchCount;
            maxA = a;
            maxB = b;
            maxDecrypt = decrypted;
          }
          currentMatchCount = 0;
        }
      }
    }
    System.out.println("Max A = " + maxA + " Max B = " + maxB + " Text was " + maxDecrypt
        + " Dictonary was " + dict + " With " + maxMatchCount + " matches");
  }

}
