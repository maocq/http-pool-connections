defmodule ElixirMs.UseCase.GetHelloUseCase do
  @moduledoc false

  @hello_repository Application.compile_env(:elixir_ms, :hello_repository)

  def hello_http(latency) do
    @hello_repository.hello_http(latency)
  end

  def hello_https(latency) do
    @hello_repository.hello_https(latency)
  end

  def hello_connection_pool_http1(latency) do
    @hello_repository.hello_connection_pool_http1(latency)
  end

  def hello_connection_pool_http2(latency) do
    @hello_repository.hello_connection_pool_http2(latency)
  end
end
