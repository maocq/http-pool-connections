defmodule ElixirMs.EntryPoint.Rest.ElixirMsController do
  alias ElixirMs.UseCase.DBUseCase
  alias ElixirMs.UseCase.GetHelloUseCase

  use Plug.Router
  use Plug.ErrorHandler

  require Logger

  plug(CORSPlug,
    methods: ["GET", "POST"],
    origin: [~r/.*/],
    headers: ["Content-Type", "Accept", "User-Agent"]
  )

  plug(Plug.Logger, log: :debug)
  plug(Plug.Parsers, parsers: [:urlencoded, :json], json_decoder: Poison)
  plug(:match)
  plug(:dispatch)

  get "/api/hello" do
    "Hello"
    |> build_response(conn)
  end

  get "/api/http" do
    latency = conn.query_params["latency"] || 0

    case GetHelloUseCase.hello_http(latency) do
      {:ok, response} -> response |> build_response(conn)
      {:error, error} ->
        Logger.error("Error get hello #{inspect(error)}")
        build_response(%{status: 500, body: "Error"}, conn)
    end
  end

  get "/api/https" do
    latency = conn.query_params["latency"] || 0

    case GetHelloUseCase.hello_https(latency) do
      {:ok, response} -> response |> build_response(conn)
      {:error, error} ->
        Logger.error("Error get hello #{inspect(error)}")
        build_response(%{status: 500, body: "Error"}, conn)
    end
  end

  get "/api/pool-http1" do
    latency = conn.query_params["latency"] || 0

    case GetHelloUseCase.hello_connection_pool_http1(latency) do
      {:ok, response} -> response |> build_response(conn)
      {:error, error} ->
        Logger.error("Error get hello #{inspect(error)}")
        build_response(%{status: 500, body: "Error"}, conn)
    end
  end

  get "/api/pool-http2" do
    latency = conn.query_params["latency"] || 0

    case GetHelloUseCase.hello_connection_pool_http2(latency) do
      {:ok, response} -> response |> build_response(conn)
      {:error, error} ->
        Logger.error("Error get hello #{inspect(error)}")
        build_response(%{status: 500, body: "Error"}, conn)
    end
  end

  get "/api/db" do
    DBUseCase.query()
      |> build_response(conn)
  end

  match _ do
    %{request_path: path} = conn
    build_response(%{status: 404, body: %{status: 404, path: path}}, conn)
  end

  def build_response(%{status: status, body: body}, conn) do
    conn
    |> put_resp_content_type("application/json")
    |> send_resp(status, Poison.encode!(body))
  end

  def build_response(response, conn), do: build_response(%{status: 200, body: response}, conn)

  @impl Plug.ErrorHandler
  def handle_errors(conn, error) do
    Logger.error("Internal server - #{inspect(error)}")
    build_response(%{status: 500, body: %{status: 500, error: "Internal server"}}, conn)
  end

end
