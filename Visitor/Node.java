package Visitor;

//interface node, enables, inteface can be visited by visitors that implement visitorNode interface
public interface Node {
       public void accept(VisitorNode visitor);
}
