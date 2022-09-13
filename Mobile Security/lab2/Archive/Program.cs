using System.IO.Compression;

public class Program
{
    static void Main(string[] args)
    {
        var filePath = args[0];
        var fileInfo = new FileInfo(filePath);
        using var sourceStream = new FileStream(filePath, FileMode.OpenOrCreate);
        using var targetStream = File.Create(@$"{fileInfo.DirectoryName}\{fileInfo.Name}.zip");
        using var compressionStream = new GZipStream(targetStream, CompressionMode.Compress);
        sourceStream.CopyTo(compressionStream);
    }
}