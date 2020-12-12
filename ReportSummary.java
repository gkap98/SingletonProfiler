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
        super("Profiler Summary Log");

        setUpGUI(profiles);
    }

    private void setUpGUI(Map<String, ArrayList<TimeSpan>> profiles) {
        summaryPanel = new JPanel();

        // BEGINING HTML
        String html = "";
        html += "<html><h1>Profiler Log Summary</h1><table><tr><th>Profiler ID</th><th>Average Time</th><th>Shortest Time</th><th>Longest Time</th></tr>";

        for (String id : profiles.keySet()) {
            long aveTime = 0;
            long longTime = 0;

            html += "<tr><td>" + id + "</td>";
            for (int i = 0; i < profiles.get(id).size(); i++) {
                aveTime += profiles.get(id).get(i).differenceNS(profiles.get(id).get(i).getBegin(),
                        profiles.get(id).get(i).getEnd()) / profiles.get(id).size();
                if (profiles.get(id).get(i).differenceNS(profiles.get(id).get(i).getBegin(),
                        profiles.get(id).get(i).getEnd()) > longTime) {
                    longTime = profiles.get(id).get(i).differenceNS(profiles.get(id).get(i).getBegin(),
                            profiles.get(id).get(i).getEnd());
                }
            }
            long shortTime = longTime;
            for (int i = 0; i < profiles.get(id).size(); i++) {
                if (profiles.get(id).get(i).differenceNS(profiles.get(id).get(i).getBegin(),
                        profiles.get(id).get(i).getEnd()) < shortTime) {
                    shortTime = profiles.get(id).get(i).differenceNS(profiles.get(id).get(i).getBegin(),
                            profiles.get(id).get(i).getEnd());
                }
            }
            html += "<td>" + aveTime + "</td>" + "<td>" + shortTime + "</td>" + "<td>" + longTime + "</td></tr>";
        }

        // ENDING HTML
        html += "</tr>";
        html += "</table></htlm>";

        summaryLabel = new JLabel(html);
        summaryPanel.add(summaryLabel);

        add(new JScrollPane(summaryPanel));

        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 725);
        setVisible(true);
    }
}