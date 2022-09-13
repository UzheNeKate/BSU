using lab1;
using System.Security.Cryptography.X509Certificates;
using System.Text;
using System.Text.Json;
using System.Text.RegularExpressions;
using File = lab1.File;

//Папка, в которой ищутся исполняемые файлы
var dirName = @"C:\Users\1111\Downloads";
//Путь к json-файлу, в котором будет записан результат
var filePath = @"result.json";

//Настройка фильтра (publisher-ы, которых нужно игнорировать)
//Если их несколько, то вводить через ;
//Если фильтр не нужен, просто нажать Enter
var filter = Console.ReadLine();

var ext = new HashSet<string> { ".exe", ".dll", ".com", ".so", ".dylib" };

var resultDic = new Dictionary<string, List<File>>();

var directory = new DirectoryInfo(dirName);
var files = directory.GetFiles("*.*", SearchOption.AllDirectories)
         .Where(f => ext.Contains(
             f.Extension,
             StringComparer.OrdinalIgnoreCase)).ToArray();

var ignorePublishers = GetIgnorePublishers(filter);


foreach (var file in files) {
    X509Certificate xcert;
    try
    {
        xcert = X509Certificate.CreateFromSignedFile(file.FullName);        
    }
    catch 
    {
        continue;
        //Console.WriteLine($"{file.Name}\tCannot get publisher");
    }

    Regex reg = new(@"CN=""(.*?)""", RegexOptions.Compiled);

    if (reg.IsMatch(xcert.Subject))
    {
        CheckAndAddFile(xcert.Subject, @"CN=""(.*?)""", file, ignorePublishers);
        continue;
    }
    CheckAndAddFile(xcert.Subject, @"CN=(.*?),", file, ignorePublishers);
}

//вывод результатов на консоль
//Console.WriteLine(GetFilesInfo(resultDic));

//вывод результатов в json
Serialize(resultDic);


void CheckAndAddFile(string source, string pattern, FileInfo file, List<string> ignore)
{
    Match match = Regex.Match(source, pattern);
    if (match.Success)
    {
        var pub = match.Groups[1].Value;

        if (ignore.Contains(pub))
            return;

        if (!resultDic.ContainsKey(pub))
            resultDic[pub] = new List<File>();
        resultDic[pub].Add(
                new File
                {
                    Name = file.Name,
                    Size = file.Length
                });
    }   
}

string GetFilesInfo(Dictionary<string, List<File>> filesInfo)
{
    var sb = new StringBuilder();
    var sorted = filesInfo.OrderByDescending(x => x.Value.Count);

    foreach (var files in sorted)
    {
        sb.Append($"{files.Key} -");
        foreach (var file in files.Value)
        {
            sb.Append($" {file.Name},");
        }
        sb.Remove(sb.Length - 1, 1);

        sb.Append($" ({files.Value.Sum(x => x.Size)} byte)");
        sb.Append("\n");
    }
    return sb.ToString();
}

void Serialize(Dictionary<string, List<File>> files)
{
    var res = new List<JsonItem>();
    var sorted = files.OrderByDescending(x => x.Value.Count);
    foreach(var (pub, filesInfo) in sorted)
    {
        res.Add(new JsonItem
        {
            Publisher = pub,
            Apps = filesInfo,
            TotalSize = filesInfo.Sum(x => x.Size)
        });
    }
    var json = JsonSerializer.Serialize(res, typeof(List<JsonItem>), 
        new JsonSerializerOptions 
        { 
            WriteIndented = true
        });
    System.IO.File.WriteAllText(filePath, json);
}

List<string> GetIgnorePublishers(string filter) 
    => new List<string>(filter.Split(';'));