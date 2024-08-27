package smartbox;

import smartbox.mvc.*;

import javax.swing.*;

public class RunCommand extends Command {
    public RunCommand(Model model) { super(model); }
    @Override
    public void execute() throws Exception {
        Container c = (Container) model;
        try {
            String component = JOptionPane.showInputDialog("Which component do you want to run?");
            c.launch(component);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        }
    }
}
