package MainPackage;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
public class ToDoCard extends JPanel{
    private JLabel topicLabel;
    private JLabel descriptionLabel;

    ToDoCard(ToDo toDo) {
        topicLabel = new JLabel(toDo.getTopic());
        descriptionLabel = new JLabel(toDo.getDescription());
        this.add(topicLabel);
        this.add(descriptionLabel);
    }
}
