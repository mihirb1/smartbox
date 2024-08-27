package smartbox;
import smartbox.mvc.*;

import java.beans.PropertyChangeEvent;
import java.util.*;

public class ContainerView extends View {

    private java.awt.List components;

    public ContainerView(Model model) {
        super(model);
        components = new java.awt.List(10);
        this.add(components);
    }
    @Override
    public void update() {
        //super.update();
        components.removeAll();
        Container c = (Container) model;
        Collection<Component> values = c.getComponents();
        for (smartbox.Component v : values) {
            components.add("" + v);
        }
        repaint();
    }
}