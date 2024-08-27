package smartbox;
import smartbox.mvc.*;

import javax.swing.*;

public class AddCommand extends Command  {
    public AddCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        Container c = (Container) model;
        try {
            String component = JOptionPane.showInputDialog("Which component do you want to add?");
            c.addComponent(component);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }

    }
}