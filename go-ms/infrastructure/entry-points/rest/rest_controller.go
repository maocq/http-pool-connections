package rest

import (
	"maocq/go-ms/domain/usecase"
	"net/http"

	"github.com/gin-gonic/gin"
)

func Start(hello *usecase.HelloUseCase, dbUseCase *usecase.DBUseCase) {
	router := gin.Default()
	router.GET("/api/hello", func(c *gin.Context) { c.String(http.StatusOK, "Hello") })

	router.GET("/api/get-hello", func(c *gin.Context) {
		latency := c.DefaultQuery("latency", "0")

		if body, err := hello.Hello(latency); err != nil {
			c.String(http.StatusInternalServerError, err.Error())
		} else {
			c.String(http.StatusOK, body)
		}
	})

	router.GET("/api/get-hello-pool", func(c *gin.Context) {
		latency := c.DefaultQuery("latency", "0")

		if body, err := hello.HelloConnectionPool(latency); err != nil {
			c.String(http.StatusInternalServerError, err.Error())
		} else {
			c.String(http.StatusOK, body)
		}
	})

	router.GET("/api/db", func(c *gin.Context) {
		result := dbUseCase.Query()
		c.JSON(http.StatusOK, result)
	})

	router.Run("0.0.0.0:8080")
}
