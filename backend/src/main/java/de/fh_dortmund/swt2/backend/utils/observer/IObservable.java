package de.fh_dortmund.swt2.backend.utils.observer;

public interface IObservable {
	public void registerObserver(IObserver observer);
	public void removeObserver(IObserver observer);
}
