public class Program
{
    static void Main(string[] args)
    {
        var filePath = args[0];
        File.SetAttributes(filePath, File.GetAttributes(filePath) | FileAttributes.Hidden);
    }
}