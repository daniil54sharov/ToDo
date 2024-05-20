package MainPackage;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ToDoDialog extends JDialog{
    private JLabel dialogLbl;
    private JButton confirmBtn, cancelBtn;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private ToDo toDo;
    private File file;
    private final ToDoDialog toDoDialog = this;

    public ToDoDialog(ToDo toDo) {
        super();
        this.setLayout(new BorderLayout());
        this.toDo = toDo;
        init();
        this.add(mainPanel);
    }
    private void init() {
        this.setSize(300, 100);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
        dialogLbl = new JLabel("Delete " + "\"" + toDo.getTopic() + "\"" + "?");
        confirmBtn = new JButton("Delete");
        cancelBtn = new JButton("Cancel");

        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file.exists()) {
                    file.delete();
                    MainFrame.setTasksInMainPanel();
                    toDoDialog.setVisible(false);
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                toDoDialog.setVisible(false);
            }
        });

        mainPanel = new JPanel();
        file = new File("Data/" + toDo.getTopic().replace(' ', '_') + ".bin");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        mainPanel.add(dialogLbl, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        buttonPanel.add(confirmBtn);
        buttonPanel.add(cancelBtn);
    }
}
