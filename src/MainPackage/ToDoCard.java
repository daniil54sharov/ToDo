package MainPackage;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ToDoCard extends JPanel {
    private JLabel topicLabel, descriptionLabel;
    private JTextField topicTextField;
    private JTextArea descriptionTextArea;
    private JScrollPane descriptionScrollArea;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    private JButton editBtn, deleteBtn, saveChangesBtn, cancelChangesBtn;
    private ToDoDialog toDoDialog;
    private ToDo toDo;

    ToDoCard(ToDo toDo) {
        super();
        this.toDo = toDo;
        init();

        topicTextField.setVisible(false);
        descriptionScrollArea.setVisible(false);
        saveChangesBtn.setVisible(false);
        cancelChangesBtn.setVisible(false);

        topicLabel.setText(toDo.getTopic());
        descriptionLabel.setText(newLine(toDo.getDescription(), true, "<br/>"));

        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                topicTextField.setText(toDo.getTopic());
                descriptionTextArea.setText(toDo.getDescription());
                setEditMode(true);
            }
        });
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDoDialog.setVisible(true);
            }
        });

        saveChangesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!topicTextField.getText().equals("")) {
                    toDo.setTopic(topicTextField.getText());
                    topicLabel.setText(toDo.getTopic());
                } else toDo.setTopic("ToDo task");

                if(!toDo.getDescription().equals(descriptionTextArea.getText())) {
                    toDo.setDescription(descriptionTextArea.getText());
                    descriptionLabel.setText(newLine(toDo.getDescription(), true, "<br/>"));
                }
                setEditMode(false);
            }
        });
        cancelChangesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setEditMode(false);
            }
        });

        labelPanel.add(topicTextField);
        labelPanel.add(descriptionScrollArea);
        labelPanel.add(topicLabel);
        labelPanel.add(descriptionLabel);
        buttonPanel.add(editBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(saveChangesBtn);
        buttonPanel.add(cancelChangesBtn);

        this.add(labelPanel, BorderLayout.WEST);
        this.add(buttonPanel, BorderLayout.EAST);
    }
    private void setEditMode(boolean editMode) {
        if(editMode) {
            topicLabel.setVisible(false);
            descriptionLabel.setVisible(false);
            topicTextField.setVisible(true);
            descriptionScrollArea.setVisible(true);
            editBtn.setVisible(false);
            saveChangesBtn.setVisible(true);
            cancelChangesBtn.setVisible(true);
        } else {
            topicTextField.setVisible(false);
            descriptionScrollArea.setVisible(false);
            topicLabel.setVisible(true);
            descriptionLabel.setVisible(true);
            saveChangesBtn.setVisible(false);
            cancelChangesBtn.setVisible(false);
            editBtn.setVisible(true);
        }
    }
    private void init() {

        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.Y_AXIS));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        topicLabel = new JLabel();
        descriptionLabel = new JLabel();

        topicTextField = new JTextField(toDo.getTopic(), 30);
        descriptionTextArea = new JTextArea(newLine(toDo.getDescription(), false, "\n"), 10, 30);
        descriptionTextArea.setLineWrap(true);
        descriptionScrollArea = new JScrollPane(descriptionTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        editBtn = new JButton("Edit");
        deleteBtn = new JButton("Delete");
        saveChangesBtn = new JButton("Save");
        cancelChangesBtn = new JButton("Cancel");

        toDoDialog = new ToDoDialog(toDo);
        toDoDialog.setVisible(false);
    }

    public static String newLine(String str, boolean addHtml, String separator) {
        int counter = 0;
        String res = str;
            for(int i = 0; i < res.length(); i++) {
                if(res.charAt(i) == ' ' && counter > 70) {
                    if(!res.startsWith(separator, i)) {
                        res = res.substring(0, i) + separator + res.substring(i + 1);
                        counter = 0;
                    }
                }
                counter++;
            }
            if(addHtml) {
                if(!res.startsWith("<html>") && !res.endsWith("</html>")) {
                    res = "<html>" + res + "</html>";
                }
            }
        return res;
    }
}
