package edu.unq.desapp.eventeando.eventos

import edu.unq.desapp.eventeando.gasto.Gasto
import edu.unq.desapp.eventeando.invitado.Invitado
import java.time.LocalDate

class Canasta : Evento{
    private var listaDeGastos: MutableList<Gasto> = mutableListOf()
    private var invitados: MutableList<Invitado> = mutableListOf()
    private var fechaConfirmacion: LocalDate = LocalDate.now()

    companion object {
        fun crear(gastos: MutableList<Gasto>, invitados: MutableList<Invitado>): Canasta {
            val canasta = Canasta()
            canasta.listaDeGastos = gastos
            canasta.invitados = invitados
            return canasta
        }
    }

    override fun gastos(): MutableList<Gasto> = this.listaDeGastos

    override fun valorTotal(): Int = this.listaDeGastos.fold(0) { total, gasto -> total + gasto.valor() }

    override fun invitados(): MutableList<Invitado> = this.invitados

    override fun invitadosConfirmados(): List<Invitado> {
        val confirmados = this.invitados.filter { invitado -> invitado.asisteA(this) }
        return confirmados
    }

    override fun getFechaLimite(): LocalDate {
        return fechaConfirmacion
    }

    override fun invitar(usuario: Invitado) {
        this.invitados.add(usuario)
    }
}
