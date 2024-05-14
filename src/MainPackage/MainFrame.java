package MainPackage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.util.ArrayList;

public final class MainFrame extends JFrame{
    private static final JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private static final JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    private static final ArrayList<ToDo> toDoArrayList = new ArrayList<>();
    private static final MainFrame mainFrame = new MainFrame("ToDo list");
    MainFrame(String appName) {
        super(appName);
        this.add(mainScrollPane);
    }

    public static void main(String[] args) {
        String[] fileNameList = IOToDo.getList("Data/");

//        for(int i = 0; i < 10; i++) {
//            ToDo toDo = new ToDo("To Do " + i);
//            toDo.setDescription("this is description label, here you can leave more information about task");
//            IOToDo.writeTask(toDo);
//        }


        for(int i = 0; i < 10; i++) {
            toDoArrayList.add(IOToDo.readTask(fileNameList[i]));
        }
        setTasksInMainPanel();

        mainFrame.add(mainScrollPane);
        mainFrame.setVisible(true);
        mainFrame.initFrame();
        mainFrame.initMainScrollPane();
    }

    private static void setTasksInMainPanel() {
        for(int i = 0; i < toDoArrayList.size(); i++) {
            ToDoCard toDoCard = new ToDoCard(toDoArrayList.get(i));
            toDoCard.setPreferredSize(new Dimension(600, 200));
            mainPanel.add(toDoCard);
        }
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
