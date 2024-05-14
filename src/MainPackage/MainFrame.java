package MainPackage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;

public final class MainFrame extends JFrame{
    private final JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private final JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    private final ArrayList<ToDo> toDoArrayList = new ArrayList<>();
    MainFrame(String appName) {
        super(appName);
        this.add(mainScrollPane);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame("ToDo list");

//        for(int i = 0; i < 10; i++) {
//            IOToDo.writeTask(new ToDo("ToDo" + (i + 1)));
//        }

        String[] fileNameList = IOToDo.getList("Data/");

        for(int i = 0; i < 10; i++) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(600, 200));
            panel.add(new JLabel(IOToDo.readTask(fileNameList[i]).getTopic()));
            mainFrame.addToMainFrame(panel);
        }

        mainFrame.setVisible(true);
        mainFrame.initFrame();
        mainFrame.initMainScrollPane();
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
