import javax.swing.*;
import java.util.*;

public class ReportCounts extends JFrame {

    /**
     *
     *
     */
    private static final long serialVersionUID = 1L;

    private JLabel countLabel;
    private JPanel countPanel;

    public ReportCounts(Map<String, Integer> counts) {
        super("Profiler Count Log");

        setUpGUI(counts);
    }

    private void setUpGUI(Map<String, Integer> counts) {

        countPanel = new JPanel();

        String html = "";
        html += "<html><h1>Profiler Count Log</h1><table><tr><th>Profiler ID</th><th></th><th>Count</th></tr>";
        for (String id : counts.keySet()) {
            html += "<tr>";
            html += "<td>" + id + "</td>";
            html += "<td></td>";
            html += "<td>" + counts.get(id) + "</td>";
            html += "</tr>";
        }
        html += "</table></html>";
        countLabel = new JLabel(html);
        countPanel.add(countLabel);

        add(new JScrollPane(countPanel));

        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(850, 100);
        setVisible(true);
    }
}