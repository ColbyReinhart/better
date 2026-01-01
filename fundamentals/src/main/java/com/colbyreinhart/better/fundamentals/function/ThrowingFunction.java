package com.colbyreinhart.better.fundamentals.function;

import java.util.function.Function;

import com.colbyreinhart.better.fundamentals.util.Result;

@FunctionalInterface
public interface ThrowingFunction<T,U,V extends Throwable>
{
	U apply(final T input) throws V;

	default Function<T,Result<U,Throwable>> protect()
	{
		return input -> {
			try
			{
				return Result.success(apply(input));
			}
			catch (final Throwable error)
			{
				return Result.error(error);
			}
		};
	}

	static <T,U,V extends Throwable> Function<T,Result<U,Throwable>> protect(final ThrowingFunction<T,U,V> throwingFunction)
	{
		return throwingFunction.protect();
	}
}
