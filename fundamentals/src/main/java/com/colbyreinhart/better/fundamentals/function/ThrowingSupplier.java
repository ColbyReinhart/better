package com.colbyreinhart.better.fundamentals.function;

import java.util.function.Supplier;

import com.colbyreinhart.better.fundamentals.util.Result;

@FunctionalInterface
public interface ThrowingSupplier<T,U extends Throwable>
{
	T get() throws U;

	default Supplier<Result<T,Throwable>> protect()
	{
		return () -> {
			try
			{
				return Result.success(get());
			}
			catch (final Throwable error)
			{
				return Result.error(error);
			}
		};
	}

	static <T,U extends Throwable> Supplier<Result<T,Throwable>> protect(final ThrowingSupplier<T,U> throwingSupplier)
	{
		return throwingSupplier.protect();
	}
}
