package com.Controller;

import com.model.Passageiro;
import com.repository.IPassageiroRepository;
import com.repository.PassageiroRepository;

public class PassageiroController {
        // Requisito 9: Singleton
        private static PassageiroController instance;
        private final IPassageiroRepository repository;

        private PassageiroController() {
            this.repository = PassageiroRepository.getInstance();
        }

        public static synchronized PassageiroController getInstance() {
            if (instance == null) {
                instance = new PassageiroController();
            }
            return instance;
        }


}
