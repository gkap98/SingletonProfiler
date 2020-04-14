import java.util.*;

public class Profiler {
    private static Profiler instance = null;
    private static boolean isEnabled = true;

    Map<String, Integer> counts;
    Map<String, ArrayList<TimeSpan>> profilers;

    // Constructor
    private Profiler() {
        counts = new HashMap<String, Integer>();
        profilers = new HashMap<String, ArrayList<TimeSpan>>();
    }

    // Get instance of Profiler
    public static Profiler getInstance() {
        if (instance == null) {
            instance = new Profiler();
        }
        return instance;
    }

    // Meathods
    public void start(String profilerID) throws ProfilerException {
        // Check to make sure Profilers are turned on
        if (isEnabled) {
            // Add another TimeSpan
            TimeSpan t = new TimeSpan();
            t.setBegin(System.nanoTime());

            if (profilers.containsKey(profilerID)) {
                ArrayList<TimeSpan> timers = profilers.get(profilerID);
                if (timers.get(timers.size() - 1).getEnd() == null) {
                    throw new ProfilerException();
                }
                timers.add(t);
                profilers.put(profilerID, timers);
            } else {
                ArrayList<TimeSpan> timers = new ArrayList<TimeSpan>();
                timers.add(t);
                profilers.put(profilerID, timers);
            }
        }
    }

    public void start(String profilerID, String message) throws ProfilerException {
        // Check to make sure Profilers are turned on
        if (isEnabled) {
            // Add another TimeSpan
            TimeSpan t = new TimeSpan();

            t.setBegin(System.nanoTime());
            t.setBeginMessage(message);

            if (profilers.containsKey(profilerID)) {
                ArrayList<TimeSpan> timers = profilers.get(profilerID);
                if (timers.get(timers.size() - 1).getEnd() == null) {
                    throw new ProfilerException();
                }
                timers.add(t);
                profilers.put(profilerID, timers);
            } else {
                ArrayList<TimeSpan> timers = new ArrayList<TimeSpan>();
                timers.add(t);
                profilers.put(profilerID, timers);
            }
        }
    }

    public void stop(String profilerID) throws ProfilerException {
        // Check to make sure Profilers are turned on
        if (isEnabled) {
            if (profilers.containsKey(profilerID)) {
                ArrayList<TimeSpan> timers = profilers.get(profilerID);
                TimeSpan t = timers.get(timers.size() - 1);
                if (t.getBegin() == null) {
                    throw new ProfilerException();
                }
                t.setEnd(System.nanoTime());
            } else {
                System.out.println("Key does not exist");
                throw new ProfilerException();
            }
        }
    }

    public void stop(String profilerID, String message) throws ProfilerException {
        // Check to make sure Profilers are turned on
        if (isEnabled) {
            if (profilers.containsKey(profilerID)) {
                ArrayList<TimeSpan> timers = profilers.get(profilerID);
                TimeSpan t = timers.get(timers.size() - 1);
                if (t.getBegin() == null) {
                    throw new ProfilerException();
                }
                t.setEnd(System.nanoTime());
                t.setEndMessage(message);
            } else {
                System.out.println("Key does not exist");
                throw new ProfilerException();
            }
        }
    }

    public void count(String profilerID) throws ProfilerException {
        // Check to make sure Profilers are turned on
        if (isEnabled) {
            if (counts.containsKey(profilerID)) {
                Integer val = counts.get(profilerID);
                val++;
                counts.put(profilerID, val);
            } else {
                counts.put(profilerID, 1);
            }
        }
    }

    public void setEnabled(boolean set) {
        isEnabled = set;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void generateReport() {
        ReportTimes report = new ReportTimes(profilers);
        ReportCounts count = new ReportCounts(counts);
        ReportSummary summary = new ReportSummary(profilers);
        // System.out.println("Counts: ");
        // System.out.println();
        // for (String id: counts.keySet()) {
        // System.out.println(id + " - " + counts.get(id));
        // }
    }
}