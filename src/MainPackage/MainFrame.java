package MainPackage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.awt.Desktop;
import java.net.URI;

public final class MainFrame extends JFrame{
    private static final JPanel mainPanel = new JPanel(new GridLayout(0, 1));
    private static final JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    private static final ArrayList<ToDo> toDoArrayList = new ArrayList<>();
    private static final CreateToDoDialog createToDoDialog = new CreateToDoDialog();
    private static final JMenuBar menuBar = new JMenuBar();
    private static final JMenu fileMenu = new JMenu("File");
    private static final JMenu editMenu = new JMenu("Edit");
    private static final JMenu helpMenu = new JMenu("Help");
    private static final JMenuItem fileMenuItemNew = new JMenuItem("New");
    private static final JMenuItem fileMenuItemImport = new JMenuItem("Import");
    private static final JMenuItem fileMenuItemExport = new JMenuItem("Export");
    private static final JMenuItem helpMenuItemGitHub = new JMenuItem("Visit GitHub Page");
    private final String url = "https://github.com/daniil54sharov/ToDo";



    MainFrame(String appName) {
        super(appName);
        this.add(mainScrollPane);
        this.initMenuBar();
        this.init();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame("ToDo list");
        setTasksInMainPanel();
    }

    public static void setTasksInMainPanel() {
        String[] fileNameList = IOToDo.getList("Data/");

        toDoArrayList.clear();
        for (String s : fileNameList) {
            toDoArrayList.add(IOToDo.readTask(s));
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

    private void initMenuBar() {
        fileMenuItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createToDoDialog.setVisible(true);
                System.out.println("Menu item clicked!");
            }
        });
        helpMenuItemGitHub.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    try {
                        Desktop.getDesktop().browse(new URI(url));
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    } catch (URISyntaxException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        fileMenu.add(fileMenuItemNew);
        fileMenu.add(fileMenuItemImport);
        fileMenu.add(fileMenuItemExport);
        helpMenu.add(helpMenuItemGitHub);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);
    }
    private void init() {
        this.setMinimumSize(new Dimension(600, 900));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(menuBar);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
}
