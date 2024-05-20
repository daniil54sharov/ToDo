package MainPackage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

public class CreateToDoDialog extends JDialog {
    private final JPanel textPanel = new JPanel();
    private final JPanel buttonPanel = new JPanel();
    private final JTextField topicField = new JTextField(30);
    private final JTextArea descriptionArea = new JTextArea(10, 30);
    private final JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
    private final JButton createBtn = new JButton("Create");
    private final JButton cancelBtn = new JButton("Cancel");
    private final CreateToDoDialog createToDoDialog = this;
    private ToDo toDo;
    public CreateToDoDialog() {
        super();
        this.setLayout(new BorderLayout());
        this.add(textPanel);
        this.add(buttonPanel);
        init();
    }
    private void init() {
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);

        descriptionArea.setLineWrap(true);
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        descriptionScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.add(new JLabel("Topic:"));
        textPanel.add(topicField);
        textPanel.add(new JLabel("Description:"));
        textPanel.add(descriptionScrollPane);

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toDo = new ToDo(topicField.getText());
                toDo.setDescription(descriptionArea.getText());
                IOToDo.writeTask(toDo);
                MainFrame.setTasksInMainPanel();
                setVisible(false);
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        buttonPanel.add(createBtn);
        buttonPanel.add(cancelBtn);

        this.add(textPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
}
