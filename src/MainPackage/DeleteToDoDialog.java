package MainPackage;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DeleteToDoDialog extends JDialog{
    private JLabel dialogLbl;
    private JButton confirmBtn, cancelBtn;
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private ToDo toDo;
    private File file;
    private DeleteToDoDialog toDoDialog;

    public DeleteToDoDialog(ToDo toDo) {
        super();
        this.toDo = toDo;
        this.setSize(250, 150);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
        toDoDialog = this;

        init();
        confirmBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file.exists()) {
                    System.out.println("Exists");
                    System.out.println(file.delete());
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

        dialogLbl.setText("Delete " + "\"" + toDo.getTopic() + "\"" + "?");
        this.add(mainPanel);
    }
    private void init() {
        dialogLbl = new JLabel();
        confirmBtn = new JButton("Delete");
        cancelBtn = new JButton("Cancel");
        mainPanel = new JPanel();
        file = new File("Data/" + toDo.getTopic().replace(' ', '_') + ".bin");
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        mainPanel.add(dialogLbl);
        mainPanel.add(buttonPanel);
        buttonPanel.add(confirmBtn);
        buttonPanel.add(cancelBtn);
    }
}
