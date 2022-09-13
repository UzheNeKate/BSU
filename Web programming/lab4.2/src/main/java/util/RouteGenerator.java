package util;

import Model.BusStop;
import Model.IllegalTimeListException;
import Model.Route;

import java.util.*;

public class RouteGenerator {
    private static int number = 1;
    private static final Random random = new Random();
    /**
     * List of available stops
     */
    public static List<String> stopsNames = Arrays.asList("Nieharelaje", "Minsk", "Anharskaja", "Vakzal",
            "Ploshcha Lienina", "Pushkinskaja", "Park 50-hoddzia Kastrychnika", "Piershamajskaja", "Kastrychnickaja",
            "Mahiliouskaja", "2-hi Letni zavulak", "Bajkalskaja", "Niescierava", "Zimovaja", "Asenniaja", "Ahon",
            "Harazhnaja", "Niepuciovaja", "Vialikaja Baravaja", "vulica Adama Bokuna", "Kurachkina");

    /**
     * @return random route for buses
     * @throws IllegalTimeListException
     */
    public static Route generateRoute() throws IllegalTimeListException {
        var length = random.nextInt(2 * stopsNames.size() / 3) + stopsNames.size() / 3;
        var busStops = new ArrayList<BusStop>(length);
        List<String> unique = new ArrayList<>(stopsNames);
        Collections.shuffle(unique);
        var times = new ArrayList<Integer>(stopsNames.size() - 1);
        for (int i = 0; i < length; i++) {
            busStops.add(new BusStop(unique.get(i)));
            if (i > 0) {
                times.add(random.nextInt(10) * 1000);
            }
        }
        return new Route(number++, busStops, times);

    }
}
