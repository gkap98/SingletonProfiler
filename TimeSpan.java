
public class TimeSpan {
    // Members
    private Long begin;
    private Long end;
    private String beginMessage;
    private String endMessage;

    // Constructor
    public TimeSpan() {
        // Maybe do something. Not sure yet
    }

    // Getters
    public Long getBegin() {
        return begin;
    }

    public Long getEnd() {
        return end;
    }

    public String getBeginMessage() {
        return beginMessage;
    }

    public String getEndMessage() {
        return endMessage;
    }

    // Setters
    public void setBegin(Long b) {
        begin = b;
    }

    public void setEnd(Long e) {
        end = e;
    }

    public void setBeginMessage(String mes) {
        beginMessage = mes;
    }

    public void setEndMessage(String mes) {
        endMessage = mes;
    }

    // Methods
    public Long differenceMS(Long start, Long end) {
        return end - start;
    }

    public Long differenceNS(Long start, Long end) {
        return end - start;
    }
}