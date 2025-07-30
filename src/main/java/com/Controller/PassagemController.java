package com.Controller;

import com.model.Passageiro;
import com.model.Passagem;
import com.model.Voo;
import com.repository.IPassageiroRepository;
import com.repository.IPassagemRepository;
import com.repository.IVooRepository;
import com.repository.PassageiroRepository;
import com.repository.PassagemRepository;
import com.repository.VooRepository;

public class PassagemController {
    // Requisito 9: Singleton
    private static PassagemController instance;
    private final IPassagemRepository passagemRepository;
    private final IVooRepository vooRepository;
    private final IPassageiroRepository passageiroRepository;

    private PassagemController() {
        this.passagemRepository = PassagemRepository.getInstance();
        this.vooRepository = VooRepository.getInstance();
        this.passageiroRepository = PassageiroRepository.getInstance();
    }

    public static synchronized PassagemController getInstance() {
        if (instance == null) {
            instance = new PassagemController();
        }
        return instance;
    }

    
}
