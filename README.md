# About smartbox
SmartBox is a Component-Container framework. It allows users to create and run applications simply by loading pre-defined components into a running container that connects them.

## Implementation
A component has a name, reference to its container, a set of interfaces it implements or provides, a set of interfaces it needs other components to implement (i.e., the required interfaces), and a table associating each required interface with the field of that type:

Set<Class<?>> requiredInterfaces;
Set<Class<?>> providedInterfaces;
Map<Class<?>, Field> fields;
Container container;
String name;

For example, the StatsCalculator does not implement any interface, but it has a field of type ICalculator:

public class StatsCalculator extends Component implements App {
   public ICalculator arithmeticCalculator;
   // etc.
}

This means that ICalculator is a required interface for StatsCalculator. By contrast, Calculator has no required interfaces but provides ICalculator.

The container maintains three tables:

Map<Class<?>, Component> providedInterfaces;
Map<Class<?>, Component> requiredInterfaces;
Map<String, Component> components;

When a component is added to a container, its container reference is set and it is placed in the components table under its name:

component.setContainer(this);
components.put(component.getName(), component);

The interfaces it implements are added to the provided interfaces table:

for(Class<?> intf: component.getProvidedInterfaces()) {
providedInterfaces.put(intf,  component);
}

Similarly, its required interfaces are added to the provided interfaces table.

Finally, the container does a little matchmaking. If component c1 of class C1 requires interface Intf1, in other words, c1 has a field fld1 of type Intf1:

class C1 extends Component {
   public Intf1 fld1;
   // etc.
}

and if component c2 of class C2 implements Intf1:

class C2 extends Component implements Intf1 { ... }

Then the container sets c1.fld1 to c2:

c1.setProvider(Intf1,  c2);

# Testing with Stack Machine Customization
Recall that a stack machine is able to execute basic arithmetic commands such as add, mul, div, and sub. Each of these commands replaces the top two elements of a stack by their sum, produce, quotient, and differences, respectively. In addition, the stack machine can also execute basic stack commands:

push arg   // push arg onto the stack where arg is any double
pop        // remove top element from the stack
top        // return top element without removing it
isEmpty    // return true if the stack is empty
clear      // remove all elements from the stack

Here's a sample session with the stack machine:

-> push 7
result: done
-> push 3
result: done
-> push 5
result: done
-> push 1
result: done
-> top
result: 1.0
-> add
result: done
-> mul
result: done
-> top
result: 18.0
-> sub
result: done
-> top
result: 11.0
-> quit
bye
