package by.lab6;

import by.lab6.compositions.Compositions;
import by.lab6.compositions.Disk;
import by.lab6.compositions.cmp.Fields;
import by.lab6.server.DiskUtil;

public class Main {

    public static void main(String[] args){
	    Disk disk = new Disk();
        disk.recordComposition(Compositions.Symphony, "symphony №40", 500, "Bethoven",
                "Nadejda", "фа мажор", null, null, null);
        disk.recordComposition(Compositions.Song, "song", 300, "Davidovskaya",
                "Davidovskaya", null, null, "pop", null);
        disk.sort(Fields.Duration);
        System.out.println(DiskUtil.search(disk, "song", 500, null));
        System.out.println(DiskUtil.getDuration(disk));
    }
}
