#encoding:utf-8

#Author: Miguel Muñoz Molina
#Author: Iván Valero Rodríguez

require_relative "Casilla"
require_relative "CasillaCalle"
require_relative "CasillaImpuesto"
require_relative "CasillaJuez"
require_relative "CasillaSorpresa"

module Civitas
  class Tablero
    
    attr_reader :numCasillaCarcel
    
    def initialize(carcel)
      if (carcel >= 1)
        @numCasillaCarcel = carcel
      else
        @numCasillaCarcel = 1
      end
      @casillas = Array.new()
      @casillas << Casilla.new("Salida")
      @porSalida = 0
      @tieneJuez = false
    end
    
    def correcto
      return (@tieneJuez && @casillas.size >= @numCasillaCarcel)
    end
    
    def correctoCasilla(numCasilla)
      enTablero = (numCasilla >=0 && numCasilla < @casillas.size)
      return correcto && enTablero
    end
    
    
    def getCasilla(numCasilla)
      if(correctoCasilla(numCasilla))
        return @casillas.at(numCasilla)
      else
        return nil
      end
    end
    
    def getPorSalida
      aDevolver = @porSalida
      if(@porSalida > 0)
        @porSalida = @porSalida - 1
      end
      return aDevolver
    end
    
    def aniadeCasilla(casilla)
      if (@casillas.size == @numCasillaCarcel)
        @casillas << Casilla.new("Carcel")
      end
      @casillas << casilla
      if (@casillas.size == @numCasillaCarcel)
        @casillas << Casilla.new("Carcel")
      end
    end
    
    def aniadeJuez
      if(!@tieneJuez)
        aniadeCasilla(CasillaJuez.new(@numCasillaCarcel,"Juez"))
        @tieneJuez = true
      end
    end
    
    def nuevaPosicion(actual,tirada)
      if(!correcto)
        return -1
      else
        posicion = actual + tirada
        if (posicion >= @casillas.size)
          @porSalida = @porSalida + 1
        end
        return (posicion % @casillas.size )
      end
    end
    
    def calcularTirada (origen, destino)
      resta = destino - origen
      if (resta < 0)
        resta = resta + @casillas.size
      end
      return resta
    end
    
    private :correcto
    private :correctoCasilla
  end
end
