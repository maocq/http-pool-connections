defmodule ElixirMs.UseCase.DBUseCase do
  @moduledoc false

  @account_repository Application.compile_env(:elixir_ms, :account_repository)

  def query() do
    @account_repository.find_by_id(4000)
  end
end
