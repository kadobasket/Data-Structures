package designpatterns;

public interface Visitor<T> {
    void visit(T data);
}