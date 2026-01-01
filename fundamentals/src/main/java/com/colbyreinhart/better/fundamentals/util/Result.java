package com.colbyreinhart.better.fundamentals.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public sealed interface Result <T,U extends Throwable>
{
	T orThrow() throws U;
	T orElse(final T other);
	T orElseGet(final Supplier<T> other);
	<V> Result<V,U> map(final Function<T,V> mapper);
	<V extends Throwable> Result<T,V> mapError(final Function<U,V> mapper);
	<V> Result<V,U> flatMap(final Function<T,Result<V,U>> mapper);
	Result<T,U> ifPresent(final Consumer<T> successFunction);
	Result<T,U> ifAbsent(final Consumer<U> errorFunction);
	Result<T,U> ifPresentOrElse(final Consumer<T> successFunction, final Consumer<U> errorFunction);
	boolean isSuccess();
	boolean isError();

	public static record Success<T,U extends Throwable>(T value) implements Result<T,U>
	{
		@Override
		public T orThrow()
		{
			return value();
		}

		@Override
		public T orElse(final T other)
		{
			return value();
		}

		@Override
		public T orElseGet(final Supplier<T> other)
		{
			return value();
		}

		@Override
		public <V> Result<V,U> map(final Function<T,V> mapper)
		{
			return success(mapper.apply(value()));
		}

		@Override
		@SuppressWarnings("unchecked")
		public <V extends Throwable> Result<T,V> mapError(final Function<U,V> mapper)
		{
			return (Result<T,V>)this;
		}

		@Override
		public <V> Result<V,U> flatMap(final Function<T,Result<V,U>> mapper)
		{
			return mapper.apply(value());
		}

		@Override
		public Result<T,U> ifPresent(final Consumer<T> successFunction)
		{
			successFunction.accept(value());
			return this;
		}

		@Override
		public Result<T,U> ifAbsent(final Consumer<U> errorFunction)
		{
			return this;
		}

		@Override
		public Result<T,U> ifPresentOrElse(final Consumer<T> successFunction, final Consumer<U> errorFunction)
		{
			return ifPresent(successFunction);
		}

		@Override
		public boolean isSuccess()
		{
			return true;
		}

		@Override
		public boolean isError()
		{
			return false;
		}
	}

	public static record Error<T,U extends Throwable>(U value) implements Result<T,U>
	{
		@Override
		public T orThrow()
		throws U
		{
			throw value();
		}

		@Override
		public T orElse(final T other)
		{
			return other;
		}

		@Override
		public T orElseGet(final Supplier<T> other)
		{
			return other.get();
		}

		@Override
		@SuppressWarnings("unchecked")
		public <V> Result<V,U> map(final Function<T,V> mapper)
		{
			return (Result<V,U>)this;
		}

		@Override
		public <V extends Throwable> Result<T,V> mapError(final Function<U,V> mapper)
		{
			return error(mapper.apply(value()));
		}

		@Override
		@SuppressWarnings("unchecked")
		public <V> Result<V,U> flatMap(final Function<T,Result<V,U>> mapper)
		{
			return (Result<V,U>)this;
		}

		@Override
		public Result<T,U> ifPresent(final Consumer<T> successFunction)
		{
			return this;
		}

		@Override
		public Result<T,U> ifAbsent(final Consumer<U> errorFunction)
		{
			errorFunction.accept(value());
			return this;
		}

		@Override
		public Result<T,U> ifPresentOrElse(final Consumer<T> successFunction, final Consumer<U> errorFunction)
		{
			return ifAbsent(errorFunction);
		}

		@Override
		public boolean isSuccess()
		{
			return false;
		}

		@Override
		public boolean isError()
		{
			return true;
		}
	}

	public static <T,U extends Throwable> Result<T,U> success(final T value)
	{
		return new Result.Success<>(value);
	}

	public static <T,U extends Throwable> Result<T,U> error(final U value)
	{
		return new Result.Error<>(value);
	}
}
