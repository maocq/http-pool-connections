defmodule ElixirMs.Model.Behaviour.HelloRepository do

  @callback hello_http(term()) :: {:ok, String.t()} | {:error, term()}
  @callback hello_https(term()) :: {:ok, String.t()} | {:error, term()}
  @callback hello_connection_pool_http1(term()) :: {:ok, String.t()} | {:error, term()}
  @callback hello_connection_pool_http2(term()) :: {:ok, String.t()} | {:error, term()}
end
