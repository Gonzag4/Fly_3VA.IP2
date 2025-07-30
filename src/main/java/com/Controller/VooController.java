package com.Controller;

import com.model.Voo;
import com.model.VooInternacional;
import com.model.VooNacional;
import com.repository.IVooRepository;
import com.repository.VooRepository;

public class VooController {
    // Requisito 9: Singleton
    private static VooController instance;
    private final IVooRepository repository;

    private VooController() {
        this.repository = VooRepository.getInstance();
    }

    public static synchronized VooController getInstance() {
        if (instance == null) {
            instance = new VooController();
        }
        return instance;
    }
}
