package MainPackage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.*;

public final class MainFrame extends JFrame{

    private final JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private final JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    MainFrame(String appName) {
        super(appName);
        this.add(mainScrollPane);
    }

    public static void main(String[] args) {
        MainFrame mf = new MainFrame("ToDo list");
        mf.setVisible(true);
        for(int i = 0; i < 10; i++) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(600, 200));
            panel.add(new JLabel("String " + i));
            mf.addToMainFrame(panel);
        }
        mf.initFrame();
        mf.initMainScrollPane();
    }

    private void initFrame() {
        this.setMinimumSize(new Dimension(600, 900));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initMainScrollPane() {
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    private <T> void addToMainFrame(T t) {
        mainPanel.add((Component) t);
    }
}
