package smartbox;

import java.util.*;
import java.io.Serializable;
import java.lang.reflect.*;



public class Component implements Serializable {

    private Set<Class<?>> requiredInterfaces;
    private Set<Class<?>> providedInterfaces;
    private transient Map<Class<?>, Field> fields; // transient because Field not serializable
    protected Container container;
    protected String name;

    public Component() {
        fields = new HashMap<Class<?>, Field>();
        providedInterfaces = new HashSet<Class<?>>();
        requiredInterfaces = new HashSet<Class<?>>();
        computeRequiredInterfaces();
        computeProvidedInterfaces();
        container = null;
        name = this.getClass().getSimpleName();
    }

    // add needed getters & setters
    public void setContainer(Container c) {
        container = c;
    }

    public Set<Class<?>> getProvidedInterfaces() {
        return providedInterfaces;
    }

    public Set<Class<?>> getRequiredInterfaces() {
        return requiredInterfaces;
    }
    public String toString() { return name; }

    // initializes fields and requiredInterfaces
    //computeRequiredInterfaces edited 5/10
    public void computeRequiredInterfaces() {
        Field[] fieldArray = this.getClass().getDeclaredFields();
        for(int i = 0; i < fieldArray.length; i++) {
            Field field = fieldArray[i];
            Class<?> fieldType = field.getType();
            if (fieldType.isInterface()) {
                // Assuming you have some lists to store fields and required interfaces
                fields.put(fieldType, field);
                requiredInterfaces.add(fieldType);
            }
        }
    }

    // initializes provided interfaces
    //computeProvidedInterfaces edited 5/10
    public void computeProvidedInterfaces() {
        Class<?>[] implementedInterfaces = this.getClass().getInterfaces();
        providedInterfaces.addAll(Arrays.asList(implementedInterfaces));
        // get interfaces implemented by the class of this component and add them to providedInterfaces
    }

    // set the field of this object to the provider
    public void setProvider(Class<?> intf, Component provider) throws Exception {
        Field field = fields.get(intf);
        field.set(this, provider); // field probably needs to be public for this.
    }

    // needed by file/open
    public void initComponent() {
        fields = new HashMap<Class<?>, Field>();
        computeProvidedInterfaces();
        computeRequiredInterfaces();
    }
}
