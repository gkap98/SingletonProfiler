import javax.swing.*;
import java.util.*;
import java.awt.GridLayout;
import java.awt.*;

public class ReportTimes extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private JLabel label;
    private JPanel panel;

    public ReportTimes(Map<String, ArrayList<TimeSpan>> profiles) {
        super("Profiler Time Log");

        setUpGUI(profiles);
    }

    public void setUpGUI(Map<String, ArrayList<TimeSpan>> profiles) {
        panel = new JPanel();

        String html = "";
        html += "<html><h1>Profiler Time Log</h1><table><tr><th>Profiler ID</th><th>Start Time</th><th>End Time</th><th>Start Message</th><th>End Message</th></tr>";
        for (String id : profiles.keySet()) {
            for (int i = 0; i < profiles.get(id).size(); i++) {
                html += "<tr>";
                html += "<td>" + id + "</td>";
                html += "<td>" + profiles.get(id).get(i).getBegin() + "</td>";
                html += "<td>" + profiles.get(id).get(i).getEnd() + "</td>";
                html += "<td>" + profiles.get(id).get(i).getBeginMessage() + "</td>";
                html += "<td>" + profiles.get(id).get(i).getEndMessage() + "</td>";
                html += "</tr>";
            }
        }
        html += "</table></html>";
        label = new JLabel(html);
        panel.add(label);

        add(new JScrollPane(panel));

        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 100);
        setVisible(true);
    } // SetUpGUI
}