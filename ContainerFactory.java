package smartbox;

import smartbox.mvc.AppFactory;
import smartbox.mvc.*;

public class ContainerFactory implements AppFactory {

    @Override
    public Model makeModel() {
        return new Container();
    }

    @Override
    public View makeView(Model model) {
        return new ContainerView(model);
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Add", "Rem", "Run"};
    }


    public Command makeEditCommand(Model model, String type) {
        switch(type) {
            case "Add": return new AddCommand(model);
            case "Rem": return new RemCommand(model);
            case "Run": return new RunCommand(model);
            default: return null;
        }
    }

    @Override
    public String getTitle() {
        return "Stack Calculator";
    }

    @Override
    public String[] getHelp() {
        return new String[]{
                "Add: adds component to the stack",
                "Rem: removes component from the stack",
                "Run: allows you to perform operations on chosen stack"
        };
    }

    @Override
    public String about() {
        return "Stack Calculator, made by Mihir Borkar";
    }
}
