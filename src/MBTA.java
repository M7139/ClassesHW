import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MBTA {
    private Map<String, List<String>> lines;
    private String intersection = "Park Street";


    public MBTA() {
        lines = new HashMap<>();

        lines.put("Red", Arrays.asList(
                "South Station", "Park Street", "Kendall", "Central",
                "Harvard", "Porter", "Davis", "Alewife"
        ));

        lines.put("Green", Arrays.asList(
                "Government Center", "Park Street", "Boylston", "Arlington",
                "Copley", "Hynes", "Kenmore"
        ));

        lines.put("Orange", Arrays.asList(
                "North Station", "Haymarket", "Park Street", "State",
                "Downtown Crossing", "Chinatown", "Back Bay", "Forest Hills"
        ));
    }


    public int getStationIndex(String line, String station) {
        List<String> stops = lines.get(line);
        return stops.indexOf(station);
    }

    public int stopsOnSameLine(String line, String startStation, String endStation) {
        int startIndex = getStationIndex(line, startStation);
        int endIndex = getStationIndex(line, endStation);
        return Math.abs(endIndex - startIndex);
    }

    public int stopsBetweenStations(String startLine, String startStation, String endLine, String endStation) {
        if (startLine.equals(endLine)) {
            return stopsOnSameLine(startLine, startStation, endStation);
        }

        int firstLeg = stopsOnSameLine(startLine, startStation, intersection);
        int secondLeg = stopsOnSameLine(endLine, intersection, endStation);

        return firstLeg + secondLeg;
    }


    public static void main(String[] args) {
        MBTA mbta = new MBTA();

        System.out.println(mbta.stopsBetweenStations("Red", "Alewife", "Red", "Alewife"));
        System.out.println(mbta.stopsBetweenStations("Red", "Alewife", "Red", "South Station"));
        System.out.println(mbta.stopsBetweenStations("Red", "South Station", "Green", "Kenmore"));
    }
}
