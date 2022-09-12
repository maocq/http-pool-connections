defmodule ElixirMs.UseCase.GetHelloUseCase do
  @moduledoc false

  @hello_repository Application.compile_env(:elixir_ms, :hello_repository)

  def hello(latency) do
    @hello_repository.hello(latency)
  end

  def hello_connection_pool(latency) do
    @hello_repository.hello_connection_pool(latency)
  end
end
