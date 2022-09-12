defmodule ElixirMs.Model.Behaviour.HelloRepository do

  @callback hello(term()) :: {:ok, String.t()} | {:error, term()}
  @callback hello_connection_pool(term()) :: {:ok, String.t()} | {:error, term()}
end
