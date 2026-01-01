package com.colbyreinhart.better.fundamentals.function;

@FunctionalInterface
public interface ThrowingConsumer<T,U extends Throwable>
{
	void accept(final T input) throws U;
}
