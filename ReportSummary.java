import javax.swing.*;
import java.util.*;

public class ReportSummary extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JLabel summaryLabel;
    private JPanel summaryPanel;

    public ReportSummary(Map<String, ArrayList<TimeSpan>> profiles) {
        super("Summary Log");

        setUpGUI(profiles);
    }

    private void setUpGUI(Map<String, ArrayList<TimeSpan>> profiles) {
        summaryPanel = new JPanel();

        String html = "";
        html += "<html><table><tr><th>Average Time</th><th>Shortest Time</th><th>Longest Time</th></tr>";

        long time = 0;
        int timers = 0;
        for (String id : profiles.keySet()) {
            for (int i = 0; i < profiles.get(id).size(); i++) {
                time += profiles.get(id).get(i).differenceNS(profiles.get(id).get(i).getBegin(),
                        profiles.get(id).get(i).getEnd());
                timers++;
            }
        }
        long ave = time / timers;
        html += "<tr>";
        html += "<td>" + Long.toString(ave) + "</td>";

        long longestTime = 0;
        for (String id : profiles.keySet()) {
            for (int i = 0; i < profiles.get(id).size(); i++) {
                if (profiles.get(id).get(i).differenceNS(profiles.get(id).get(i).getBegin(),
                        profiles.get(id).get(i).getEnd()) > longestTime) {
                    longestTime = profiles.get(id).get(i).differenceNS(profiles.get(id).get(i).getBegin(),
                            profiles.get(id).get(i).getEnd());
                }
            }
        }
        html += "<td>" + Long.toString(longestTime) + "</td>";

        long shortestTime = longestTime;
        for (String id : profiles.keySet()) {
            for (int i = 0; i < profiles.get(id).size(); i++) {
                if (profiles.get(id).get(i).differenceNS(profiles.get(id).get(i).getBegin(),
                        profiles.get(id).get(i).getEnd()) < shortestTime) {
                    shortestTime = profiles.get(id).get(i).differenceNS(profiles.get(id).get(i).getBegin(),
                            profiles.get(id).get(i).getEnd());
                }
            }
        }
        html += "<td>" + Long.toString(shortestTime) + "</td>";
        html += "</tr>";
        html += "</table></htlm>";

        summaryLabel = new JLabel(html);
        summaryPanel.add(summaryLabel);

        add(new JScrollPane(summaryPanel));

        setSize(400, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 750);
        setVisible(true);
    }
}