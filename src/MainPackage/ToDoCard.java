package MainPackage;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ToDoCard extends JPanel {
    private JLabel topicLabel;
    private JLabel descriptionLabel;
    private JTextField topicTextField;
    private JTextArea descriptionTextArea;
    private JScrollPane descriptionScrollArea;
    private JPanel labelPanel;
    private JButton editBtn, saveBtn, cancelBtn;

    ToDoCard(ToDo toDo) {
        super();
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));

        initLabels();
        initTextInputs();
        initEditModeButtons();

        topicTextField.setVisible(false);
        descriptionScrollArea.setVisible(false);
        saveBtn.setVisible(false);
        cancelBtn.setVisible(false);

        topicLabel.setText(toDo.getTopic());
        descriptionLabel.setText(newLine(toDo.getDescription(), true, "<br/>"));
        editBtn = new JButton("Edit");

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topicTextField.setText(toDo.getTopic());
                descriptionTextArea.setText(newLine(toDo.getDescription(), false, "\n"));
                setEditMode(true);
            }
        });
        saveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(topicTextField.getText() != "") {
                    toDo.setTopic(topicTextField.getText());
                    topicLabel.setText(toDo.getTopic());
                } else toDo.setTopic("ToDo task");
                toDo.setDescription(descriptionTextArea.getText());
                descriptionLabel.setText(newLine(toDo.getDescription(), true, "<br/>"));
                setEditMode(false);
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEditMode(false);
            }
        });

        labelPanel.add(topicTextField);
        labelPanel.add(descriptionScrollArea);
        labelPanel.add(topicLabel);
        labelPanel.add(descriptionLabel);
        this.add(labelPanel);
        this.add(editBtn);
        this.add(saveBtn);
        this.add(cancelBtn);
    }
    private void setEditMode(boolean editMode) {
        if(editMode) {
            topicLabel.setVisible(false);
            descriptionLabel.setVisible(false);
            topicTextField.setVisible(true);
            descriptionScrollArea.setVisible(true);
            editBtn.setVisible(false);
            saveBtn.setVisible(true);
            cancelBtn.setVisible(true);
        } else {
            topicTextField.setVisible(false);
            descriptionScrollArea.setVisible(false);
            topicLabel.setVisible(true);
            descriptionLabel.setVisible(true);
            saveBtn.setVisible(false);
            cancelBtn.setVisible(false);
            editBtn.setVisible(true);
        }
    }
    private void initLabels() {
        topicLabel = new JLabel();
        descriptionLabel = new JLabel();
    }
    private void initTextInputs() {
        topicTextField = new JTextField(topicLabel.getText(), 30);
        descriptionTextArea = new JTextArea(newLine(descriptionLabel.getText(), false, "\n"), 10, 30);
        descriptionScrollArea = new JScrollPane(descriptionTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    private void initEditModeButtons() {
        saveBtn = new JButton("Save");
        cancelBtn = new JButton("Cancel");
    }
    public static String newLine(String str, boolean addHtml, String separator) {
        int counter = 0;
        String res = str;
        if(!res.startsWith("<html>") && !res.endsWith("</html>")) {
            for(char c : res.toCharArray()) {
                if(c == ' ' && counter > 25) {
                    if(res.substring(res.indexOf(c), res.indexOf(c) + separator.length()) != "separator") {
                        res = res.substring(0, res.indexOf(c)) + separator + res.substring(res.indexOf(c) + 1);
                        counter = 0;
                    }
                }
                counter++;
            }
            if(addHtml) {
                res = "<html>" + res + "</html>";
            }
        }
        return res;
    }
}
