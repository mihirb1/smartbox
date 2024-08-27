package smartbox.mvc;

import javax.swing.*;

public class Command extends JPanel implements Subscriber {
    protected Model model;
    public Command(Model model){
        this.model = model;
    }

    public void execute() throws Exception {
    }
    @Override
    public void update() {

    }
}
