package com.repository;

import com.exceptions.AeronaveJaCadastradaException;
import com.exceptions.AeronaveNaoEncontradaException;
import com.model.Aeronave;
import java.util.List;

public interface IAeronaveRepository {
    void cadastrar(Aeronave aeronave) throws AeronaveJaCadastradaException;
    Aeronave buscarPorId(int id) throws AeronaveNaoEncontradaException;
    List<Aeronave> listarTodas();
    void remover(int id) throws AeronaveNaoEncontradaException;
}