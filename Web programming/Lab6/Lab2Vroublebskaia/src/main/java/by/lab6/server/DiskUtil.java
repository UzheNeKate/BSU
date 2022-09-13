package by.lab6.server;

import by.lab6.compositions.AbstractComposition;
import by.lab6.compositions.Disk;

public class DiskUtil {

    /**
     * @param d -- the disk to calculate duration
     * @return the total duration of all by.lab6.compositions on the disk
     */
    public static int getDuration(Disk d) {
        int sum = 0;
        for (var c : d) {
            sum += c.getDuration();
        }
        return sum;
    }

    /**
     * @param d -- the disk to search in
     * @param name -- the name to search
     * @param duration -- the duration to search
     * @param composer -- the composer to search
     * @return
     */
    public static AbstractComposition search(Disk d, String name, Integer duration, String composer) {
        for (var c : d) {
            var cName = name == null ? null : c.getName();
            var cDuration = duration == null ? null : c.getDuration();
            var cComposer = composer == null ? null : c.getComposer();
            if ((cName == null || cName.equals(name)) &&
                    (cDuration == null || cDuration.equals(duration)) &&
                    (cComposer == null || cComposer.equals(composer))) {
                return c;
            }
        }
        return null;
    }
}
