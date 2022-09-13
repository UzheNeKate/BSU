using System.Text.Json.Serialization;

namespace lab1;

public struct File
{
    [JsonPropertyName("File")]
    public string Name { get; set; }
    [JsonIgnore]
    public long Size { get; set; }
    [JsonIgnore]
    public string Publisher { get; set; }

    public override string ToString()
    {
        return $"{Name} {Size} {Publisher}";
    }
}