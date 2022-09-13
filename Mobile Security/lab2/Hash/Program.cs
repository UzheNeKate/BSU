using System.Security.Cryptography;

public class Program
{
    static void Main(string[] args)
    {
        var filePath = args[0];
        using var md5 = MD5.Create();
        using var stream = File.OpenRead(filePath);
        Console.WriteLine($"Hash:\n{System.Text.Encoding.UTF8.GetString(md5.ComputeHash(stream))}");
        Console.WriteLine("Press any key to continue");
        Console.ReadKey();
    }
}