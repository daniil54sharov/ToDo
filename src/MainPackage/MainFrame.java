package MainPackage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.util.ArrayList;

public final class MainFrame extends JFrame{
    private static final JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private static final JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    private static final ArrayList<ToDo> toDoArrayList = new ArrayList<>();
    private static final MainFrame mainFrame = new MainFrame("ToDo list");
    private static final JMenuBar menuBar = new JMenuBar();
    private static final JMenu fileMenu = new JMenu("File");
    private static final JMenu editMenu = new JMenu("Edit");
    private static final JMenu helpMenu = new JMenu("Help");
    private static final JMenuItem fileMenuItemNew = new JMenu("New");
    private static final JMenuItem fileMenuItemImport = new JMenu("Import");
    private static final JMenuItem fileMenuItemExport = new JMenu("Export");
    private static final JMenuItem helpMenuItemGitHub = new JMenu("Visit GitHub Page");


    MainFrame(String appName) {
        super(appName);
        this.add(mainScrollPane);
    }

    public static void main(String[] args) {

        for(int i = 1; i <= 10; i++) {
            IOToDo.writeTask(new ToDo("To Do" + " " + i));
        }


        setTasksInMainPanel();

        mainFrame.add(mainScrollPane);
        mainFrame.setVisible(true);
        mainFrame.initFrame();
        mainFrame.initMainScrollPane();
    }

    public static void setTasksInMainPanel() {
        String[] fileNameList = IOToDo.getList("Data/");

        toDoArrayList.clear();
        for (String s : fileNameList) {
            toDoArrayList.add(IOToDo.readTask(s));
            System.out.println(s);
        }

        mainPanel.removeAll();
        for (ToDo toDo : toDoArrayList) {
            ToDoCard toDoCard = new ToDoCard(toDo);
            toDoCard.setPreferredSize(new Dimension(600, 200));
            mainPanel.add(toDoCard);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void initFrame() {
        this.setMinimumSize(new Dimension(600, 900));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void initMainScrollPane() {
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
}
