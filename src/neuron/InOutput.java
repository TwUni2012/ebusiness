package neuron;

public abstract class InOutput<T> {

	private T value;

	public void setValue(T value) {
		this.value = value;
	}

	public T getInputValue() {
		return value;
	}

}
