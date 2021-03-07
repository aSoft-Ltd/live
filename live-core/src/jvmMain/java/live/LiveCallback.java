package live;

public interface LiveCallback<T> {
    void execute(T value);
}
